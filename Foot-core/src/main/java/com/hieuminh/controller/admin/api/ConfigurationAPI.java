package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.ConfigurationDTO;
import com.hieuminh.service.ConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConfigurationAPI {
    private Logger LOGGER = Logger.getLogger(ConfigurationAPI.class);
    @Autowired
    ConfigService configService;



    @RequestMapping(value = "/api/admin/configuration/{key}/", method = RequestMethod.GET)
    public ModelAndView showUpdateUser(@ModelAttribute(SystemConstant.MODEL) ConfigurationDTO model, @PathVariable("key") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/config/edit");
        model = configService.findEqualUnique("key", id);

        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/api/admin/configuration/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUser(@RequestBody ConfigurationDTO model, HttpServletRequest request) {
        try{
            ConfigurationDTO configurationDTO = configService.findEqualUnique("key",model.getKey());
            configurationDTO.setValue(model.getValue());

            configService.update(configurationDTO);
        }catch (Exception e){
             LOGGER.error(e);
             return false;
        }

        return true;
    }


}

