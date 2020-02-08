package com.hieuminh.controller.web;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.controller.admin.api.TransporterAPI;
import com.hieuminh.dto.*;
import com.hieuminh.entity.ConfigurationEntity;
import com.hieuminh.service.*;
import com.hieuminh.utils.SecurityUtils;
import com.hieuminh.utils.handlingTSP.GPsMapUtils;
import org.apache.log4j.Logger;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    private final Logger log = Logger.getLogger(CartController.class);
    @Autowired
    BillService billService;
    @Autowired
    GPsMapUtils gPsMapUtils;
    @Autowired
    ConfigService configService;

    @Autowired
    TransporterService transporterService;
    @Autowired
    LocationService locationService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/home-cart", method = RequestMethod.GET)
    public ModelAndView getHome(@ModelAttribute(SystemConstant.MODEL) BillDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/web/cart/cart");
        try{
            Map<String,Object> map = new HashMap<>();
            LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());

            map.put("idUserEntity",userLogin.getIdLoginUserEntity().getIdUser());
            map.put("status",0);
            Object[] objects=  billService.findByProberty(map,"dateTime","2",null,null);
            List<BillDTO> billDTOS = (List<BillDTO>) objects[1];


            billDTOS = setDistanceAndTime(billDTOS);



            mav.addObject(SystemConstant.MODEL,billDTOS);
        }catch (Exception e){
            log.debug(e);
            return null;
        }
        return mav;
    }

    private List<BillDTO> setDistanceAndTime(List<BillDTO> billDTOS) {
        for (BillDTO billDTO : billDTOS) {

            TransporterDTO transporterDTO = transporterService.findEqualUnique("transBillEntity.idBill",billDTO.getIdBill());
            LocationDTO locationDTO =  locationService.findEqualUnique("userEntityLocation.idUser",transporterDTO.getTransUserEntity().getIdUser());
            ConfigurationDTO configurationDTO = configService.findEqualUnique("key","transporter.speed.moto");

            double km =  gPsMapUtils.distance(Double.parseDouble(billDTO.getLatitude()),Double.parseDouble(billDTO.getLongitude()),Double.parseDouble(locationDTO.getLatitude()),Double.parseDouble(locationDTO.getLongitude()),'K');
            billDTO.setTime( (km/(Double.parseDouble(configurationDTO.getValue())))*60+"");
            billDTO.setDistance(km+"");
        }

        return billDTOS;
    }


}

