package com.hieuminh.controller.admin.api;

import com.hieuminh.dto.HistoryDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdHomeAPI {
    private Logger LOGGER = Logger.getLogger(AdHomeAPI.class);

    @Autowired
    private HistoryService historyService;

    @Autowired
    private BillService billService;
    @Autowired
    private DetailBillService detailBillService;

    @Autowired
    private UserService userService;

    @Autowired
    private FeedBackService feedBackService;


    @RequestMapping(value = "/api/admin/adHome/showRevenueOnDay", method = RequestMethod.POST)
    @ResponseBody
    public List<Integer> showRevenueOnDay() {
        return detailBillService.findCostOnProperty();
    }


    @RequestMapping(value = "/api/admin/adHome/showMemberOnline", method = RequestMethod.POST)
    @ResponseBody
    public List<UserDTO> showMemberOnline(@RequestBody UserDTO model, HttpServletRequest request) {
       /* Map<String,Object> map = new HashMap<>();
        map.put("check",1);
        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());
        Object[] objects = userService.findByProberty(map,null,null,setFirstItem,maxPage);*/
        List<UserDTO> userDTOList = null;

        return userDTOList;

    }

    @RequestMapping(value = "/api/admin/aDhome/pageHistory", method = RequestMethod.POST)
    @ResponseBody
    public List<HistoryDTO> showHistory(@RequestBody HistoryDTO model,HttpServletRequest request) {
        try{

            Integer maxPage = model.getMaxPageItems();
            Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());
            Object[] objects = historyService.findByHistory(setFirstItem, maxPage);
            List<HistoryDTO> historyDTOS = (List<HistoryDTO>) objects[0];

            return historyDTOS;
        }catch (Exception e){
            return null;
        }

    }

    @RequestMapping(value = "/api/admin/aDhome/pageReport", method = RequestMethod.POST)
    @ResponseBody
    public List<Integer> showHomePage(@RequestParam("currentPageHome") Integer currentPageHome, HttpServletRequest request) {
        List<Integer> list =new ArrayList<>();
        if(currentPageHome==1){
            list = detailBillService.findSumRevenueOnYear();

        }if(currentPageHome==2){
            list = billService.findSumRevenueOn3Year();

        }

        return list;
    }

    @RequestMapping(value = "/api/admin/aDhome/countMessenger", method = RequestMethod.GET)
    @ResponseBody
    public  Integer showSizeMessenger(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("check",1);
        Object[] objects =feedBackService.findByProberty(map,null,null,null,null);

        return Integer.parseInt(objects[0].toString());
    }

}

