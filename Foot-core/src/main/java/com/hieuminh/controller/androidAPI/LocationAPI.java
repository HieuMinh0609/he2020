package com.hieuminh.controller.androidAPI;


import com.hieuminh.dto.LocationDTO;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.LocationService;
import com.hieuminh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LocationAPI {

    @Autowired
    LoginService loginService;

    @Autowired
    LocationService locationService;

    @RequestMapping(value="/api/android/update/location/", method = RequestMethod.POST)
    @ResponseBody
    public void loginAndroid(@RequestBody LocationDTO model , HttpServletRequest request){


        LoginDTO loginDTO = loginService.findByUserName("nameLogin",model.getNameLogin());
        if(loginDTO!=null){
            LocationDTO locationDTO = locationService.findEqualUnique("userEntityLocation.idUser",loginDTO.getIdLoginUserEntity().getIdUser());

            locationDTO.setLatitude(model.getLatitude());
            locationDTO.setLongitude(model.getLongitude());

            locationService.updateLocation(locationDTO);
        }

    }
}
