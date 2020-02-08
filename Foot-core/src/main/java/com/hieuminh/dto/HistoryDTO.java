package com.hieuminh.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class HistoryDTO extends AbstractDTO<HistoryDTO> {


    private Integer idHistory;
    private UserDTO idHistoryUserEntity;
    private Timestamp dateTime;
    private String content;



    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public UserDTO getIdHistoryUserEntity() {
        return idHistoryUserEntity;
    }

    public void setIdHistoryUserEntity(UserDTO idHistoryUserEntity) {
        this.idHistoryUserEntity = idHistoryUserEntity;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
