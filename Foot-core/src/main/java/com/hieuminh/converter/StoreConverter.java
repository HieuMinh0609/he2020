package com.hieuminh.converter;

import com.hieuminh.dto.StoreDTO;
import com.hieuminh.entity.StoreEntity;

public class StoreConverter {

    public static StoreDTO entityToDto(StoreEntity storeEntity) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setIdstore(storeEntity.getIdstore());
         storeDTO.setName(storeEntity.getName());
        storeDTO.setAddress(storeEntity.getAddress());
        storeDTO.setLatitude(storeEntity.getLatitude());
        storeDTO.setLongitude(storeEntity.getLongitude());
        return storeDTO;
    }

    public static StoreEntity dtoToEntity(StoreDTO storeDTO) {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setIdstore(storeDTO.getIdstore());
         storeEntity.setName(storeDTO.getName());
        storeEntity.setAddress(storeDTO.getAddress());
        storeEntity.setLatitude(storeDTO.getLatitude());
        storeEntity.setLongitude(storeDTO.getLongitude());
        return storeEntity;
    }

}
