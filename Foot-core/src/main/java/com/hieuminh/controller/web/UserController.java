package com.hieuminh.controller.web;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.ProductOpenDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.LoginService;
import com.hieuminh.utils.MessageUtils;
import com.hieuminh.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    LoginService loginService;
    @Autowired
    MessageUtils messageUtils;



    @RequestMapping(value="/home-office", method = RequestMethod.GET)
    public ModelAndView showOffice( HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/user/office");

        LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());
        UserDTO userDTO = userLogin.getIdLoginUserEntity();

        mav.addObject(SystemConstant.MODEL,userDTO);
        initMessageResponse(mav, request);
        return mav;
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
