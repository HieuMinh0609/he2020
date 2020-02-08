package com.hieuminh.dto;

import java.sql.Timestamp;

public class BillDTO extends AbstractDTO<BillDTO>{
    private Integer idBill;
    private UserDTO idUserEntity;
    private String phoneNumber;
    private String place;
    private Timestamp dateTime;
    private Integer status;
    private Integer cost;
    private Integer online;
    private String latitude;
    private String longitude;
    private Integer printed;


    private String time;
    private String distance;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }





    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public UserDTO getIdUserEntity() {
        return idUserEntity;
    }

    public void setIdUserEntity(UserDTO idUserEntity) {
        this.idUserEntity = idUserEntity;
    }

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

    public Integer getPrinted() {
        return printed;
    }

    public void setPrinted(Integer printed) {
        this.printed = printed;
    }
}
