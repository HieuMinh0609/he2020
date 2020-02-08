package com.hieuminh.controller.androidAPI;


import com.hieuminh.controller.androidAPI.dto.DetailBillAndroidDTO;
import com.hieuminh.dto.*;
import com.hieuminh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class loginAPI {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DetailBillService detailBillService;
    @Autowired
    private BillService billService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TransporterService transporterService;

    @RequestMapping(value="/api/android/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginDTO loginAndroid(@RequestBody LoginDTO model , HttpServletRequest request){
        LoginDTO  user = new LoginDTO();
        try{
           user= loginService.findByUserName("nameLogin",model.getNameLogin());

            String passwordEncode = user.getPassWord() ;
            String passwordInput = model.getPassWord();

            String hashed = BCrypt.hashpw(passwordInput, BCrypt.gensalt(10));





        if(user.getIdLoginUserEntity().getIdRoleEntity().getRoleName().contains("TRANSPORTER") && user.getIdLoginUserEntity().getBlock()==0){
            if(BCrypt.checkpw( passwordInput,passwordEncode)){
                user.setStatus(true);
                return user;
            }
        }
        }catch(Exception e ){
            return null;
        }
        return null;
    }

    @RequestMapping(value="/api/android/list/bill/", method = RequestMethod.POST)
    @ResponseBody
    public List<DetailBillAndroidDTO> loginAndroid(@RequestBody UserDTO model , HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        List<DetailBillDTO> list = new ArrayList<>();
        map.put("transUserEntity.idUser",model.getIdUser());
        map.put("status",0);

        List<DetailBillAndroidDTO> detailBillAndroidDTOS = new ArrayList<>();

        Object[] objects = transporterService.findByPropertyMapNotLike(map,"prioritize","1",null,null);
        if(Integer.parseInt(objects[0].toString())==0){
           LocationDTO locationDTO = locationService.findEqualUnique("userEntityLocation.idUser",model.getIdUser());
           locationDTO.setReady(1);
           locationService.updateLocation(locationDTO);
        }else{
            List<TransporterDTO> transporterDTOs = (List<TransporterDTO>) objects[1];
            for (TransporterDTO trs:transporterDTOs){
                DetailBillAndroidDTO detailBillAndroidDTO = new DetailBillAndroidDTO();
                List<DetailBillDTO> list1 = detailBillService.findListByIdBill(trs.getTransBillEntity().getIdBill());
                BillDTO billDTO = billService.findById(trs.getTransBillEntity().getIdBill());
                detailBillAndroidDTO.setId(trs.getIdtransporter());
                detailBillAndroidDTO.setDetailBillAndroidDTOS(list1);
                detailBillAndroidDTO.setBillDTO(billDTO);

                detailBillAndroidDTOS.add(detailBillAndroidDTO);
            }

        }
        return detailBillAndroidDTOS;
    }

}
