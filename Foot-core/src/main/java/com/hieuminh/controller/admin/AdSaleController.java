package com.hieuminh.controller.admin;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.dto.SaleDTO;
import com.hieuminh.service.DetailSaleService;
import com.hieuminh.service.SaleService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdSaleController {

    @Autowired
    private  SaleService saleService;
    @Autowired
    private DetailSaleService detailSaleService;
    @Autowired
    private MessageUtils messageUtils;


    @RequestMapping(value = "/admin/sale/list", method = RequestMethod.GET)
    public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) SaleDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/sale/list");
        excuteSearchBill(request,model);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/saleDetail/{id}", method = RequestMethod.GET)
    public ModelAndView showDetailSale(@ModelAttribute(SystemConstant.MODEL) DetailSaleDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/sale/detail");

        Integer sizeList = detailSaleService.findListById("idSaleEntity.idSale",id).size();
        model.setListResult( detailSaleService.findListById("idSaleEntity.idSale",id));
        model.setTotalItems( sizeList);
        model.setMaxPageItems(sizeList);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }



    private void excuteSearchBill(HttpServletRequest request, SaleDTO model) {
        Map<String, Object> mapProperty = buildMapProperties(model);
        DisplayTagUtils.initSearchBean(request, model);
        Object[] objects = saleService.findByProberty(mapProperty,model.getSortExpression(),model.getSortDirection(),model.getFirstItem(),model.getMaxPageItems());
        model.setListResult((List<SaleDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildMapProperties(SaleDTO model) {
        Map<String, Object> properties = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(model.getName()) && model.getName()!=null) {
            properties.put("name", model.getName());
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
