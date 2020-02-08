package com.hieuminh.dto;

import java.sql.Timestamp;

public class FeedBackDTO extends AbstractDTO<FeedBackDTO>{

    private Integer idFeedBack;
    private UserDTO idUserFeedBack;
    private String feedBackContent;
    private Timestamp dateTime;
    private Integer check;



    public Integer getIdFeedBack() {
        return idFeedBack;
    }

    public void setIdFeedBack(Integer idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    public UserDTO getIdUserFeedBack() {
        return idUserFeedBack;
    }

    public void setIdUserFeedBack(UserDTO idUserFeedBack) {
        this.idUserFeedBack = idUserFeedBack;
    }

    public String getFeedBackContent() {
        return feedBackContent;
    }

    public void setFeedBackContent(String feedBackContent) {
        this.feedBackContent = feedBackContent;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }


    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }
}
