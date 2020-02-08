package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.controller.admin.api.UserAPI;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.TypeService;
import com.hieuminh.utils.DisplayTagUtils;
import com.hieuminh.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdTypeController {
    private Logger LOGGER = Logger.getLogger(AdTypeController.class);

    @Autowired
    TypeService typeService;
    @Autowired
    MessageUtils messageUtils;


    @RequestMapping(value = "/admin/typeproduct/list", method = RequestMethod.GET)
    public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) TypeDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/typeproduct/list");
        excuteSearchBill(request,model);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }


    private void excuteSearchBill(HttpServletRequest request, TypeDTO model) {
        Map<String, Object> mapProperty = buildMapProperties(model);
        DisplayTagUtils.initSearchBean(request, model);
        Object[] objects = typeService.findByProberty(mapProperty,model.getSortExpression(),model.getSortDirection(),model.getFirstItem(),model.getMaxPageItems());
        model.setListResult((List<TypeDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildMapProperties(TypeDTO model) {
        Map<String, Object> properties = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(model.getTypeName()) && model.getTypeName()!=null) {
            properties.put("typeName", model.getTypeName());
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
