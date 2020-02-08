package com.hieuminh.controller;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.dto.utils.UserInfoDTO;
import com.hieuminh.service.LoginService;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageUtils messageUtils;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView Home(  HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/login");
        initMessageResponse(mav,request);
        return  mav;
    }



    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "redirect:/login?accessDenied";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute(SystemConstant.MODEL) UserInfoDTO model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/login");

            UserDTO userDTO = new UserDTO();
            setModelToUser(userDTO,model);



            userService.SaveUser(userDTO);

            UserDTO user = userService.findEqualUnique("phoneNumber",userDTO.getPhoneNumber());
            LoginDTO loginDTO = new LoginDTO();
            setModeltoLogin(loginDTO,model,user);

            loginService.saveLogin(loginDTO);

        return  mav;
    }

    @RequestMapping(value="/register/message", method = RequestMethod.GET)
    public ModelAndView registerMassage( HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/login");
        initMessageResponse(mav,request);

        return  mav;
    }



    private void setModeltoLogin(LoginDTO loginDTO, UserInfoDTO model,UserDTO userDTO) {
        loginDTO.setIdLoginUserEntity(userDTO);
        loginDTO.setNameLogin(model.getNameLogin());
        loginDTO.setPassWord(model.getPassWord());
    }


    private void setModelToUser(UserDTO userDTO, UserInfoDTO model) {
        userDTO.setEmail(model.getEmail());
        userDTO.setNameFull(model.getNameFull());
        userDTO.setSex(model.getSex());
        userDTO.setPlace(model.getPlace());
        userDTO.setPhoneNumber(model.getPhoneNumber());
        userDTO.setBlock(0);
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessageResponse(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }

}
