package com.hieuminh.controller.web.api;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.controller.admin.api.UserAPI;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.LoginService;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.MessageUtils;
import com.hieuminh.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WbLoginAPI {

    private Logger LOGGER = Logger.getLogger(WbLoginAPI.class);
    @Autowired
    LoginService loginService;
    @Autowired
    MessageUtils messageUtils;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/user/reset/password", method = RequestMethod.POST)
    @ResponseBody
    public Boolean ResetPassword(@RequestBody UserDTO model, HttpServletRequest request) {
        try{
            UserDTO userDTO = userService.findEqualUnique("email",model.getEmail());
            loginService.resetPassword(userDTO.getEmail());

            return true;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }


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

