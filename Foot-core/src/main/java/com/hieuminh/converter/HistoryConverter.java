package com.hieuminh.converter;

import com.hieuminh.dto.HistoryDTO;
import com.hieuminh.dto.UserDTO;
import com.hieuminh.entity.MyHistoryEntity;

import java.util.Date;

public class HistoryConverter {

    public static HistoryDTO entityToDto(MyHistoryEntity myHistoryEntity) {
        HistoryDTO historyDTO= new HistoryDTO();
        historyDTO.setIdHistory(myHistoryEntity.getIdHistory());
        historyDTO.setIdHistoryUserEntity(UserConverter.entityToDto(myHistoryEntity.getIdHistoryUserEntity()));
        historyDTO.setDateTime(myHistoryEntity.getDateTime());
        historyDTO.setContent(myHistoryEntity.getContent());

        return historyDTO;

    }

    public static MyHistoryEntity dtoToEntity(HistoryDTO historyDTO) {
        MyHistoryEntity myHistoryEntity = new MyHistoryEntity();

        myHistoryEntity.setIdHistory(historyDTO.getIdHistory());
        myHistoryEntity.setIdHistoryUserEntity(UserConverter.dtoToEntity(historyDTO.getIdHistoryUserEntity()));
        myHistoryEntity.setDateTime(historyDTO.getDateTime());
        myHistoryEntity.setContent(historyDTO.getContent());

        return myHistoryEntity;
    }


}
