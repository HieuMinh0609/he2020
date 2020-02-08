package com.hieuminh.controller.admin;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.FeedBackDTO;
import com.hieuminh.dto.ImageProductDTO;
import com.hieuminh.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdFeedBackController {

    @Autowired
    private FeedBackService feedBackService;


    @RequestMapping(value = "/admin/feedbackRead", method = RequestMethod.GET)
    public String updateFeedBack(@ModelAttribute(SystemConstant.MODEL) FeedBackDTO model, HttpServletRequest request) {

        try{
            feedBackService.UpdateFeedBack();
        }catch (Exception e){
            e.getMessage();
        }


        return "redirect:home";
    }
}
