package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.RoleDTO;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.service.RoleService;
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
public class RoleAPI {
    private Logger LOGGER = Logger.getLogger(RoleAPI.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private HistoryUtil historyUtil;


    @RequestMapping(value = "/api/admin/role/", method = RequestMethod.GET)
    public ModelAndView showRoleEdit(@ModelAttribute(SystemConstant.MODEL) RoleDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/role/edit");
        mav.addObject(SystemConstant.MODEL, model);
        return mav;

    }

    @RequestMapping(value = "/api/admin/role/{id}", method = RequestMethod.GET)
    public ModelAndView showType(@ModelAttribute(SystemConstant.MODEL) RoleDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/role/edit");

        model = roleService.findById(id);

        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/api/admin/role/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateType(@RequestBody RoleDTO model, HttpServletRequest request) {
        if (model.getIdRole()!=null && model.getIdRole()!=0) {
            roleService.update(model);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Update: "+model.getIdRole()+" "+ CustomMessages.HISTORY_ROLE);

        } else {
            roleService.save(model);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add: "+model.getIdRole()+" "+ CustomMessages.HISTORY_ROLE);

        }

        return true;
    }

    @RequestMapping(value = "/api/admin/role/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteType(@RequestBody List<Integer> idList) {
        try {
            roleService.delete(idList);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete: "+idList.size()+" "+ CustomMessages.HISTORY_ROLE);

        }catch (Exception e){
            LOGGER.debug("Delte Fail........");
            return false;
        }
        return true;
    }


}
