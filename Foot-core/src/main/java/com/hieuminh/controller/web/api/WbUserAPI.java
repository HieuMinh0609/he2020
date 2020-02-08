package com.hieuminh.controller.web.api;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.BillService;
import com.hieuminh.service.LoginService;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WbUserAPI {
    @Autowired
    BillService billService;
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @RequestMapping(value = "/api/user/showHistory/shopping", method = RequestMethod.POST)
    @ResponseBody
    public List<BillDTO> showMenuProduct(@RequestBody BillDTO model, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());

        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());

        map.put("idUser",userLogin.getIdLoginUserEntity().getIdUser());
        map.put("status",1);
        Object[] objects = billService.findByProberty(map,"dateTime","2",setFirstItem,maxPage);
        List<BillDTO> billDTOs = (List<BillDTO>) objects[1];

        return billDTOs;
    }

        @RequestMapping(value = "/api/user/web/user/update", method = RequestMethod.POST)
        @ResponseBody
        public Boolean updateUser(@RequestBody UserDTO model, HttpServletRequest request) {

            try{
                LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());
                UserDTO userDTO = userLogin.getIdLoginUserEntity();
                userDTO.setEmail(model.getEmail());
                userDTO.setPhoneNumber(model.getPhoneNumber());
                userDTO.setPlace(model.getPlace());

                userService.updateUser(userDTO);

            }catch (Exception e){
                return false;
            }

            return true;
        }

    @RequestMapping(value = "/api/user/web/change/password", method = RequestMethod.POST)
    @ResponseBody
    public Boolean changePassword(@RequestBody LoginDTO model, HttpServletRequest request) {

        try{
            LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());
            userLogin.setPassWord(passwordEncoder.encode(model.getPassWord()));
            loginService.updateLogin(userLogin);

        }catch (Exception e){
            return false;
        }

        return true;
    }


}
