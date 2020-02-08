package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.DisplayTagUtils;
import com.hieuminh.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageUtils messageUtils;


    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/list");
        excuteSearchBill(request,model);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }
    private void excuteSearchBill(HttpServletRequest request, UserDTO model) {
        Map<String, Object> mapProperty = buildMapProperties(model);
        DisplayTagUtils.initSearchBean(request, model);
        Object[] objects = userService.findByProberty(mapProperty,model.getSortExpression(),model.getSortDirection(),model.getFirstItem(),model.getMaxPageItems());
        model.setListResult((List<UserDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

       private Map<String, Object> buildMapProperties(UserDTO model) {
        Map<String, Object> properties = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(model.getEmail()) && model.getEmail()!=null) {
            properties.put("email", model.getEmail());
        }
        return properties;
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
