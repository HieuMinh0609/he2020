package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.service.BillService;
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
public class AdBillController {

    @Autowired
    private MessageUtils messageUtils;

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/admin/bill/list", method = RequestMethod.GET)
    public ModelAndView getBill(@ModelAttribute(SystemConstant.MODEL) BillDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/bill/list");

        excuteSearchBill(request, model);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
}
    @RequestMapping(value = "/api/admin/showBillNew", method = RequestMethod.GET)
    public ModelAndView getBillNew(@ModelAttribute(SystemConstant.MODEL) BillDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/home/billnew");

        excuteSearchBillNew(request, model);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }



    private void excuteSearchBill(HttpServletRequest request, BillDTO model) {
        Map<String, Object> mapProperty = buildMapProperties(model);
        DisplayTagUtils.initSearchBean(request, model);
        Object[] objects = billService.findByProberty(mapProperty, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<BillDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private void excuteSearchBillNew(HttpServletRequest request, BillDTO model) {
        Map<String, Object> mapProperty = new HashMap<>();
        mapProperty.put("status",0);
        mapProperty.put("online",0);
        mapProperty.put("printed",0);

        DisplayTagUtils.initSearchBean(request, model);
        Object[] objects = billService.findByPropertyMapNotLike(mapProperty, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<BillDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }


    private Map<String, Object> buildMapProperties(BillDTO model) {
        Map<String, Object> properties = new HashMap<String, Object>();

        if (model.getIdBill()!= null) {
            properties.put("idBill", model.getIdBill());
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
