package com.hieuminh.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idbill]")
    private Integer idBill;


    @OneToMany(mappedBy ="transBillEntity" ,fetch = FetchType.LAZY)
    private List<TransporterEntity> transporterEntities;

    @OneToMany(mappedBy ="idDetailBillBillEntity" ,fetch = FetchType.LAZY)
    private List<DetailBillEntity> detailBillEntityList;


    @ManyToOne
    @JoinColumn(name = "[iduser]")
    private UserEntity idUserEntity;

    @Column(name = "[phonenumber]")
    private String phoneNumber;

    @Column(name = "[place]")
    private String place;

    @Column(name = "[datetime]")
    private Timestamp dateTime;

    @Column(name = "[status]")
    private Integer status;

    @Column(name = "[cost]")
    private Integer cost;


    @Column(name = "[online]")
    private Integer online;

    @Column(name = "[latitude]")
    private String latitude;

    @Column(name = "[longitude]")
    private String longitude;

    @Column(name = "[printed]")
    private Integer printed;

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public UserEntity getIdUserEntity() {
        return idUserEntity;
    }

    public void setIdUserEntity(UserEntity idUserEntity) {
        this.idUserEntity = idUserEntity;
    }

   /* public ProductEntity getIdProductEntity() {
        return idProductEntity;
    }

    public void setIdProductEntity(ProductEntity idProductEntity) {
        this.idProductEntity = idProductEntity;
    }
*/
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<TransporterEntity> getTransporterEntities() {
        return transporterEntities;
    }

    public void setTransporterEntities(List<TransporterEntity> transporterEntities) {
        this.transporterEntities = transporterEntities;
    }

    public Integer getPrinted() {
        return printed;
    }

    public void setPrinted(Integer printed) {
        this.printed = printed;
    }

    public List<DetailBillEntity> getDetailBillEntityList() {
        return detailBillEntityList;
    }

    public void setDetailBillEntityList(List<DetailBillEntity> detailBillEntityList) {
        this.detailBillEntityList = detailBillEntityList;
    }
}
