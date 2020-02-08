package com.hieuminh.controller.web.api;

import com.hieuminh.controller.admin.api.UserAPI;
import com.hieuminh.converter.LocationConverter;
import com.hieuminh.dto.*;
import com.hieuminh.service.*;
import com.hieuminh.utils.SecurityUtils;
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
public class CartAPI {
    private Logger LOGGER = Logger.getLogger(CartAPI.class);
    @Autowired
    BillService billService;
    @Autowired
    DetailBillService detailBillService;
    @Autowired
    LocationService locationService;
    @Autowired
    TransporterService transporterService;
    @Autowired
    LoginService loginService;


    @RequestMapping(value = "/api/user/cart/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer showMemberOnline(HttpServletRequest request) {
       try{
           Map<String,Object> map = new HashMap<>();
           LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());

           map.put("idUserEntity",userLogin.getIdLoginUserEntity().getIdUser());
           map.put("online",1);
           Object[] objects=  billService.findByProberty(map,null,null,null,null);
           List<BillDTO> billDTOS = (List<BillDTO>) objects[1];

           return billDTOS.size();
       }catch (Exception e){
           return 0;
       }

    }

    @RequestMapping(value = "/api/user/web/cart/delete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUser(@RequestBody BillDTO model, HttpServletRequest request) {

        try{
            LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());
            UserDTO userDTO = userLogin.getIdLoginUserEntity();
            List<Integer> idList = new ArrayList<>();

            idList.add(model.getIdBill());
            billService.deleteBill(idList);

        }catch (Exception e){
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/api/user/web/cart/detail", method = RequestMethod.POST)
    @ResponseBody
    public List<DetailBillDTO> detailBills(@RequestBody DetailBillDTO model, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("idDetailBillBillEntity.idBill",Integer.parseInt(""+model.getId()));
        List<DetailBillDTO> list = detailBillService.findByPropertyMapNotLike(map);
        return list;
    }

    @RequestMapping(value = "/api/user/web/cart/map", method = RequestMethod.POST)
    @ResponseBody
    public LocationDTO mapBills(@RequestBody LocationDTO model, HttpServletRequest request) {
       TransporterDTO transporterDTO = transporterService.findEqualUnique("transBillEntity.idBill",Integer.parseInt(model.getId().toString()));
       LocationDTO locationDTO = locationService.findEqualUnique("userEntityLocation.idUser",transporterDTO.getTransUserEntity().getIdUser());

       return locationDTO;
    }
}
