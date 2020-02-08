package com.hieuminh.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "myhistory")
public class MyHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idhistory]")
    private Integer idHistory;

    @ManyToOne
    @JoinColumn(name = "[iduser]")
    private UserEntity idHistoryUserEntity;

    @Column(name = "[dateTime]")
    private Timestamp dateTime;

    @Column(name = "[content]")
    private String content;



    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public UserEntity getIdHistoryUserEntity() {
        return idHistoryUserEntity;
    }

    public void setIdHistoryUserEntity(UserEntity idHistoryUserEntity) {
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


