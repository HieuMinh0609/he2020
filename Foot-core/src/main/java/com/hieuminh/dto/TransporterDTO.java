package com.hieuminh.dto;

import java.sql.Timestamp;

public class TransporterDTO extends AbstractDTO<TransporterDTO>{

    private Integer idtransporter;
    private UserDTO transUserEntity;
    private BillDTO transBillEntity;
    private Timestamp timeStart;
    private Timestamp timeEnd;
    private Integer status;
    private Integer prioritize;


    public Integer getIdtransporter() {
        return idtransporter;
    }

    public void setIdtransporter(Integer idtransporter) {
        this.idtransporter = idtransporter;
    }



    public BillDTO getTransBillEntity() {
        return transBillEntity;
    }

    public void setTransBillEntity(BillDTO transBillEntity) {
        this.transBillEntity = transBillEntity;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getPrioritize() {
        return prioritize;
    }

    public void setPrioritize(Integer prioritize) {
        this.prioritize = prioritize;
    }


    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public UserDTO getTransUserEntity() {
        return transUserEntity;
    }

    public void setTransUserEntity(UserDTO transUserEntity) {
        this.transUserEntity = transUserEntity;
    }
}
