package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.service.TypeService;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TypeAPI {
    private Logger LOGGER = Logger.getLogger(TypeAPI.class);
    @Autowired
    TypeService typeService;
    @Autowired
    HistoryUtil historyUtil;

    @RequestMapping(value = "/api/admin/typeproduct/", method = RequestMethod.GET)
    public ModelAndView showAddType(@ModelAttribute(SystemConstant.MODEL) TypeDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/typeproduct/edit");
        mav.addObject(SystemConstant.MODEL, model);
        return mav;

    }

    @RequestMapping(value = "/api/admin/typeproduct/{id}", method = RequestMethod.GET)
    public ModelAndView showType(@ModelAttribute(SystemConstant.MODEL) TypeDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/typeproduct/edit");

        model = typeService.findById(id);

        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/api/admin/typeproduct/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateType(@RequestBody TypeDTO model, HttpServletRequest request) {
        if (model.getTypeId()!=null && model.getTypeId()!=0) {
             typeService.updateType(model);
             historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Update: "+model.getTypeId()+" "+ CustomMessages.HISTORY_TYPE);

        } else {
            typeService.SaveType(model);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add: "+model.getTypeName()+" "+ CustomMessages.HISTORY_TYPE);

        }

        return true;
    }

    @RequestMapping(value = "/api/admin/typeproduct/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteType(@RequestBody List<Integer> idList) {
         try {
             typeService.deleteType(idList);
             historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete: "+idList.size()+" "+ CustomMessages.HISTORY_TYPE);

         }catch (Exception e){
             LOGGER.debug("Delte Fail........");
             return false;
         }
         return true;
    }



}
