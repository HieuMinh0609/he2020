package com.hieuminh.dto;

public class StoreDTO {
    private Integer idstore;
    private String name;
    private String address;
    private String latitude;
    private String longitude;

    public Integer getIdstore() {
        return idstore;
    }

    public void setIdstore(Integer idstore) {
        this.idstore = idstore;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
