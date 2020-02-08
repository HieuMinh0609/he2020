package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.FeedBackDTO;
import com.hieuminh.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeedBackAPI {

    @Autowired
    private FeedBackService feedBackService;


    @RequestMapping(value = "/api/admin/feedBack/showModel", method = RequestMethod.GET)
    public ModelAndView showFeedBack(@ModelAttribute(SystemConstant.MODEL) FeedBackDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/home/feedback");
        return mav;
    }

    @RequestMapping(value = "/api/admin/feedBack/showList", method = RequestMethod.POST)
    @ResponseBody
    public List<FeedBackDTO> showListFeedBack(@RequestBody FeedBackDTO model, HttpServletRequest request) {
        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());
        Map<String,Object> map = new HashMap<>();
        Object[] objects = feedBackService.findByProberty(map,"dateTime","1",setFirstItem,maxPage);
        List<FeedBackDTO> list = (List<FeedBackDTO>) objects[1];
        return list;
    }


}
