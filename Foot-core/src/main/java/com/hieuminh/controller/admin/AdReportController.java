package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.utils.ReportDTO;
import com.hieuminh.dto.StoreDTO;
import com.hieuminh.service.StoreService;
import com.hieuminh.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class AdReportController {
    private Logger LOGGER = Logger.getLogger(AdReportController.class);

    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private StoreService storeService;


    @RequestMapping(value = "/admin/report/list", method = RequestMethod.GET)
    public ModelAndView getProduct(@ModelAttribute(SystemConstant.MODEL) ReportDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/report/list");
        List<StoreDTO> storeDTOList = storeService.findAll();

        mav.addObject(SystemConstant.ITEMS,storeDTOList);
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
