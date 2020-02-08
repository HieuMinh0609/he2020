package com.hieuminh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idstore]")
    private Integer idstore;

    @OneToMany(mappedBy ="storeLocationEntity" ,fetch = FetchType.LAZY)
    private List<LocationEntity> locationEntityList ;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[address]")
    private String address;

    @Column(name = "[latitude]")
    private String latitude;

    @Column(name = "[longitude]")
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


    public List<LocationEntity> getLocationEntityList() {
        return locationEntityList;
    }

    public void setLocationEntityList(List<LocationEntity> locationEntityList) {
        this.locationEntityList = locationEntityList;
    }
}
