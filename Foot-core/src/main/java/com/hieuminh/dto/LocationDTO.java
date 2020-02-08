package com.hieuminh.dto;

public class LocationDTO extends AbstractDTO<LocationDTO> {


    private Integer idlocation;
    private UserDTO userEntityLocation;
    private StoreDTO storeLocationEntity;
    private String latitude;
    private String longitude;
    private String nameLogin;
    private Integer countBill;
    private  Integer ready;
    private double km;

    public Integer getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
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

    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    public UserDTO getUserEntityLocation() {
        return userEntityLocation;
    }

    public void setUserEntityLocation(UserDTO userEntityLocation) {
        this.userEntityLocation = userEntityLocation;
    }


    public Integer getCountBill() {
        return countBill;
    }

    public void setCountBill(Integer countBill) {
        this.countBill = countBill;
    }

    public Integer getReady() {
        return ready;
    }

    public void setReady(Integer ready) {
        this.ready = ready;
    }

    public StoreDTO getStoreLocationEntity() {
        return storeLocationEntity;
    }

    public void setStoreLocationEntity(StoreDTO storeLocationEntity) {
        this.storeLocationEntity = storeLocationEntity;
    }


    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
