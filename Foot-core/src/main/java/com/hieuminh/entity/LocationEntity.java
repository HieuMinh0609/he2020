package com.hieuminh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idlocation]")
    private Integer idlocation;

    @OneToOne
    @JoinColumn(name = "[iduser]")
    private UserEntity userEntityLocation;


    @ManyToOne
    @JoinColumn(name = "[idstore]")
    private StoreEntity storeLocationEntity;

    @Column(name = "[latitude]")
    private String latitude;

    @Column(name = "[longitude]")
    private String longitude;

    @Column(name = "[countbill]")
    private Integer countBill;

    @Column(name = "[ready]")
    private Integer ready;

    @Column(name = "[km]")
    private double km;

    public Integer getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public StoreEntity getStoreLocationEntity() {
        return storeLocationEntity;
    }

    public void setStoreLocationEntity(StoreEntity storeLocationEntity) {
        this.storeLocationEntity = storeLocationEntity;
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


    public UserEntity getUserEntityLocation() {
        return userEntityLocation;
    }

    public void setUserEntityLocation(UserEntity userEntityLocation) {
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


    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
