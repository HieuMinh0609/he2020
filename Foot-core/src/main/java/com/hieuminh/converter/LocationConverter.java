package com.hieuminh.converter;

import com.hieuminh.dto.LocationDTO;
import com.hieuminh.entity.LocationEntity;

public class LocationConverter {
    public static LocationDTO entityToDto(LocationEntity locationEntity) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setIdlocation(locationEntity.getIdlocation());
        locationDTO.setUserEntityLocation(UserConverter.entityToDto(locationEntity.getUserEntityLocation()));
        locationDTO.setStoreLocationEntity(StoreConverter.entityToDto(locationEntity.getStoreLocationEntity()));
        locationDTO.setLatitude(locationEntity.getLatitude());
        locationDTO.setLongitude(locationEntity.getLongitude());
        locationDTO.setCountBill(locationEntity.getCountBill());
        locationDTO.setReady(locationEntity.getReady());
        locationDTO.setKm(locationEntity.getKm());
        return locationDTO;
    }

    public static LocationEntity dtoToEntity(LocationDTO locationDTO) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setIdlocation(locationDTO.getIdlocation());
        locationEntity.setUserEntityLocation(UserConverter.dtoToEntity(locationDTO.getUserEntityLocation()));
        locationEntity.setStoreLocationEntity(StoreConverter.dtoToEntity(locationDTO.getStoreLocationEntity()));
        locationEntity.setLatitude(locationDTO.getLatitude());
        locationEntity.setLongitude(locationDTO.getLongitude());
        locationEntity.setCountBill(locationDTO.getCountBill());
        locationEntity.setReady(locationDTO.getReady());
        locationEntity.setKm(locationDTO.getKm());
        return locationEntity;
    }
}
