package com.hieuminh.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "transporter")
public class TransporterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idtransporter]")
    private Integer idtransporter;

    @ManyToOne
    @JoinColumn(name = "[iduser]")
    private UserEntity transUserEntity;

    @ManyToOne
    @JoinColumn(name = "[idbill]")
    private BillEntity transBillEntity;


    @Column(name = "[timestart]")
    private Timestamp timeStart;


    @Column(name = "[timeend]")
    private Timestamp timeEnd;

    @Column(name = "[status]")
    private Integer status;

    @Column(name = "[prioritize]")
    private Integer prioritize;

    public Integer getIdtransporter() {
        return idtransporter;
    }

    public void setIdtransporter(Integer idtransporter) {
        this.idtransporter = idtransporter;
    }


    public BillEntity getTransBillEntity() {
        return transBillEntity;
    }

    public void setTransBillEntity(BillEntity transBillEntity) {
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

    public UserEntity getTransUserEntity() {
        return transUserEntity;
    }

    public void setTransUserEntity(UserEntity transUserEntity) {
        this.transUserEntity = transUserEntity;
    }
}
