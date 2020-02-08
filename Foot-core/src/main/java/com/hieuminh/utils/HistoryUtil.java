package com.hieuminh.utils;

import com.hieuminh.dto.HistoryDTO;
import com.hieuminh.dto.LoginDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.service.HistoryService;
import com.hieuminh.service.LoginService;
import com.hieuminh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class HistoryUtil {
    private Logger LOGGER = Logger.getLogger(HistoryUtil.class);
    @Autowired
    HistoryService historyService;

    @Autowired
    LoginService loginService;

    public  void updateHistoryConverterBill(String userName ,String action) {


        try{
            HistoryDTO historyDTO = new HistoryDTO();
            Date date= new Date();

            LoginDTO loginDTO = loginService.findByUserName("nameLogin",userName);
            UserDTO userDTO = loginDTO.getIdLoginUserEntity();

            historyDTO.setIdHistoryUserEntity(userDTO);
            historyDTO.setDateTime(new Timestamp(date.getTime()));
            historyDTO.setContent(action);
            historyDTO.setIdHistory(null);

            historyService.SaveHistory(historyDTO);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);

        }
    }
}
