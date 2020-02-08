package com.hieuminh.converter;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.entity.BillEntity;

public class BillConverter {

    public static BillDTO entityToDto(BillEntity billEntity) {
        BillDTO billDTO = new BillDTO();

        billDTO.setIdBill(billEntity.getIdBill());
        billDTO.setIdUserEntity(UserConverter.entityToDto(billEntity.getIdUserEntity()));
        billDTO.setPhoneNumber(billEntity.getPhoneNumber());
        billDTO.setPlace(billEntity.getPlace());
        billDTO.setDateTime(billEntity.getDateTime());
        billDTO.setStatus(billEntity.getStatus());
        billDTO.setCost(billEntity.getCost());
        billDTO.setOnline(billEntity.getOnline());
        billDTO.setLatitude(billEntity.getLatitude());
        billDTO.setLongitude(billEntity.getLongitude());
        billDTO.setPrinted(billEntity.getPrinted());
        return billDTO;
    }

    public static BillEntity dtoToEntity(BillDTO billDTO) {
        BillEntity billEntity = new BillEntity();

        billEntity.setIdBill(billDTO.getIdBill());
        billEntity.setIdUserEntity(UserConverter.dtoToEntity(billDTO.getIdUserEntity()));
        billEntity.setPhoneNumber(billDTO.getPhoneNumber());
        billEntity.setPlace(billDTO.getPlace());
        billEntity.setDateTime(billDTO.getDateTime());
        billEntity.setStatus(billDTO.getStatus());
        billEntity.setCost(billDTO.getCost());
        billEntity.setOnline(billDTO.getOnline());
        billEntity.setLatitude(billDTO.getLatitude());
        billEntity.setLongitude(billDTO.getLongitude());
        billEntity.setPrinted(billDTO.getPrinted());
        return billEntity;
    }
}
