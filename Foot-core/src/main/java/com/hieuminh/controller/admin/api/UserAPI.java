package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.*;
import com.hieuminh.dto.utils.UserInfoDTO;
import com.hieuminh.service.LoginService;
import com.hieuminh.service.RoleService;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserAPI {
    private Logger LOGGER = Logger.getLogger(UserAPI.class);
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private HistoryUtil historyUtil;

    @RequestMapping(value = "/api/admin/user/", method = RequestMethod.GET)
    public ModelAndView showAddUser(@ModelAttribute(SystemConstant.MODEL) LoginDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/user/edit");
        List<RoleDTO> roleDTOS = roleService.findAll();
        mav.addObject(SystemConstant.ITEMS,roleDTOS);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;

    }

    @RequestMapping(value = "/api/admin/user/{id}", method = RequestMethod.GET)
    public ModelAndView showUpdateUser(@ModelAttribute(SystemConstant.MODEL) LoginDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/user/edit");
        model = loginService.findByIdUser("idLoginUserEntity.idUser", id);
        List<RoleDTO> roleDTOS = roleService.findAll();
        mav.addObject(SystemConstant.ITEMS,roleDTOS);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/api/admin/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteUser(@RequestBody List<Integer> idList) {
        try{


        List<LoginDTO> loginDTOS = new ArrayList<>();
        List<Integer> idLogins = new ArrayList<Integer>();

        setIdLogins(loginDTOS, idLogins, idList);
        Integer resultLogin = loginService.deleteLogin(idLogins);
        Integer resultUser = userService.deleteUser(idList);
        historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete: "+idList.size()+" "+ CustomMessages.HISTORY_SALE);

        if (resultLogin != resultUser) {
            LOGGER.debug("Delte Fail........");
            return false;

        }
        }catch (Exception e){
            LOGGER.debug("Delte Fail........");
            return false;
        }
        return true;

    }

    private void setIdLogins(List<LoginDTO> loginDTOS, List<Integer> idLogins, List<Integer> idList) {
        for (Integer item : idList) {
            LoginDTO loginDTO = loginService.findByIdUser("idLoginUserEntity.idUser", item);
            loginDTOS.add(loginDTO);
        }
        for (LoginDTO item : loginDTOS) {
            idLogins.add(item.getIdLogin());
        }
    }





    @RequestMapping(value = "/api/admin/user/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUser(@RequestBody UserInfoDTO model, HttpServletRequest request) {

        UserDTO userDTO = new UserDTO();
        setModelToUser(userDTO, model);

        if (model.getIdUser() != null && model.getIdUser() != 0) {
            try{
                setRoleEntity(userDTO, model);
                userService.updateUser(userDTO);
                UserDTO user = userService.findEqualUnique("phoneNumber", userDTO.getPhoneNumber());
                LoginDTO loginDTO = new LoginDTO();
                loginDTO = loginService.findByIdUser("idLoginUserEntity.idUser", model.getIdUser());
                model.setIdLogin(loginDTO.getIdLogin());
                setModeltoLogin(loginDTO, model, user);

                loginService.updateLogin(loginDTO);

                historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Edit ID: "+model.getIdUser()+" "+ CustomMessages.HISTORY_USER);

            }catch (Exception e){
                LOGGER.error(e.getMessage());
                return false;
            }

            return true;

        } else {
            CheckLogin checkLogin = userService.checkLogin(model.getNameLogin());
            if (!checkLogin.isMemberExist()) {
                userService.SaveUser(userDTO);
                UserDTO user = userService.findEqualUnique("phoneNumber", userDTO.getPhoneNumber());
                LoginDTO loginDTO = new LoginDTO();
                setModeltoLogin(loginDTO, model, user);
                loginService.saveLogin(loginDTO);

                historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add : "+model.getNameFull()+" "+ CustomMessages.HISTORY_USER);

            } else {
                return false;
            }

            return true;

        }


    }



    private void setRoleEntity(UserDTO userDTO, UserInfoDTO model) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(model.getIdRole());
        userDTO.setIdRoleEntity(roleDTO);
    }

    private void setModeltoLogin(LoginDTO loginDTO, UserInfoDTO model,UserDTO userDTO) {
        loginDTO.setIdLogin(model.getIdLogin());
        loginDTO.setIdLoginUserEntity(userDTO);
        loginDTO.setNameLogin(model.getNameLogin());
        loginDTO.setPassWord(model.getPassWord());
    }


    private void setModelToUser(UserDTO userDTO, UserInfoDTO model) {
        userDTO.setIdUser(model.getIdUser());
        userDTO.setEmail(model.getEmail());
        userDTO.setNameFull(model.getNameFull());
        userDTO.setSex(model.getSex());
        userDTO.setPlace(model.getPlace());
        userDTO.setPhoneNumber(model.getPhoneNumber());
        userDTO.setBlock(model.getBlock());
    }

}
