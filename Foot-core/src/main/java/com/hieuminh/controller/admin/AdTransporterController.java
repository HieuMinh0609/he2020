package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.BillService;
import com.hieuminh.service.TransporterService;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdTransporterController {
    @Autowired
    private  MessageUtils messageUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/admin/transporter/list", method = RequestMethod.GET)
    public ModelAndView getBill(@ModelAttribute(SystemConstant.MODEL) TransporterDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/transporter/list");

        Map<String,Object> property = new HashMap<String,Object>();
        property.put("idRoleEntity.idRole",3);
        property.put("block",0);


        Map<String,Object> propertyBill = new HashMap<String,Object>();
        propertyBill.put("status",0);
        propertyBill.put("online",0);
        propertyBill.put("printed",1);



        Object[] objects = userService.findByPropertyMapNotLike(property,null,null,null,null);
        List<UserDTO> user = (List<UserDTO>) objects[1];

        Object[] objectsBill = billService.findByPropertyMapNotLike(propertyBill,null,null,null,null);
        Integer count = Integer.parseInt(objectsBill[0].toString());

        mav.addObject(SystemConstant.MODEL, count);
        mav.addObject(SystemConstant.ITEMS,user);
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
