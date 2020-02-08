package com.hieuminh.controller.admin;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.HistoryDTO;
import com.hieuminh.dto.HomeAdminDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.repository.HistoryDao;
import com.hieuminh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "homeOfAdmin")
public class AdHomeController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private  TypeService typeService;
    @Autowired
    private UserService userService;
    @Autowired
    private DetailBillService detailBillService;
    @Autowired
    private BillService billService;




    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView getHome(@ModelAttribute(SystemConstant.MODEL) HomeAdminDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/home");

        Integer sum = getRevenueOnDay();

        model.setRevenueOnDay(sum);
        model.setTypeDTOS(typeService.findAll());
        model.setUserDTOList(null);
        model.setBillOnNew(billService.findListBillOnNew());
        model.setOnBillTransport(billService.findOnBillTransport());
        model.setFindRevenue7Day(billService.findRevenue7Day());
        model.setFindRevenueType(detailBillService.findSumRevenueOnType());
        mav.addObject(model);
        return mav;
    }



    private Integer getRevenueOnDay() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String day_bill = "DAY('"+date.format(formatter)+"')";
        String month_bill = "MONTH('"+date.format(formatter)+"')";
        String year_bill = "YEAR('"+date.format(formatter)+"')";
        Integer sum = billService.findSumRevenue("DAY",day_bill,month_bill,year_bill);

        return sum;
    }



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/home";
    }





}
