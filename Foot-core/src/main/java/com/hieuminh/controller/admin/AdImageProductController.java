package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.ImageProductDTO;
import com.hieuminh.service.ImageProductService;
import com.hieuminh.utils.DisplayTagUtils;
import com.hieuminh.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class AdImageProductController {

    @Autowired
    private  MessageUtils messageUtils;

    @Autowired
    private ImageProductService imageProductService;

    @RequestMapping(value = "/admin/product/image/{id}", method = RequestMethod.GET)
    public ModelAndView getImageProduct(@ModelAttribute(SystemConstant.MODEL) ImageProductDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/product/image");
        Integer sizeList = imageProductService.findListById("idImageProductEntity.idProduct",id).size();
        model.setListResult( imageProductService.findListById("idImageProductEntity.idProduct",id));
        model.setTotalItems( sizeList);
        model.setMaxPageItems(sizeList);
        mav.addObject(SystemConstant.MODEL, model);
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
