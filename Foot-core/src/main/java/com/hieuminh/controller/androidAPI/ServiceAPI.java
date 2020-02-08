package com.hieuminh.controller.androidAPI;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.controller.androidAPI.dto.CountBillDTO;
import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.BillService;
import com.hieuminh.service.TransporterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ServiceAPI {
    private Logger LOGGER = Logger.getLogger(ServiceAPI.class);
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private BillService billService;


    @RequestMapping(value="/api/android/list/bill/count/", method = RequestMethod.POST)
    @ResponseBody
    public CountBillDTO loginAndroid(@RequestBody UserDTO model , HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("idLoginUserEntity.idUser",model.getIdUser());
        map.put("status",0);

        Object[] objects = transporterService.findByPropertyMapNotLike(map,"prioritize","1",null,null);

        List<TransporterDTO> transporterDTOs = (List<TransporterDTO>) objects[1];

        CountBillDTO countBillDTO = new CountBillDTO(transporterDTOs.size());


        return countBillDTO;
    }

    @RequestMapping(value="/api/android/trans/update/", method = RequestMethod.POST)
    @ResponseBody
    public String UpdateTransporter(@RequestBody Map<String,Object> infor, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("transUserEntity.idUser",Integer.parseInt(infor.get("idUser").toString()));
        map.put("transBillEntity.idBill",Integer.parseInt(infor.get("idBill").toString()));



        try{

        Object[] objects = transporterService.findByPropertyMapNotLike(map,null,null,null,null);

        List<TransporterDTO> transporterDTOs = (List<TransporterDTO>) objects[1];

        TransporterDTO transporterDTO = transporterDTOs.get(0);
        transporterDTO.setStatus(1);
        transporterService.updateTransporter(transporterDTO);

        BillDTO billDTO = billService.findById(Integer.parseInt(infor.get("idBill").toString()));
        billDTO.setOnline(0);
        billDTO.setStatus(1);

        billService.updateBill(billDTO);

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            return SystemConstant.ERROR_SYSTEM;

        }



        return SystemConstant.UPDATE_SUCCESS;
     }

}
