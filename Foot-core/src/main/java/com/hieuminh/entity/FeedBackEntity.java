package com.hieuminh.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "feedback")
public class FeedBackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idfeedback]")
    private Integer idFeedBack;

    @ManyToOne
    @JoinColumn(name = "[iduser]")
    private UserEntity idUserFeedBack;

    @Column(name = "[feedbackcontent]")
    private String feedBackContent;

    @Column(name = "[check]")
    private Integer check;

    @Column(name = "[datetime]")
    private Timestamp dateTime;


    public Integer getIdFeedBack() {
        return idFeedBack;
    }

    public void setIdFeedBack(Integer idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    public UserEntity getIdUserFeedBack() {
        return idUserFeedBack;
    }

    public void setIdUserFeedBack(UserEntity idUserFeedBack) {
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
