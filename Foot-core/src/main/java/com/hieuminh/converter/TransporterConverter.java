package com.hieuminh.converter;

import com.hieuminh.dto.SaleDTO;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.entity.SaleEntity;
import com.hieuminh.entity.TransporterEntity;

public class TransporterConverter {

    public static TransporterDTO entityToDto(TransporterEntity transporterEntity) {
        TransporterDTO transporterDTO = new TransporterDTO();
            transporterDTO.setIdtransporter(transporterEntity.getIdtransporter());
            transporterDTO.setTransUserEntity(UserConverter.entityToDto(transporterEntity.getTransUserEntity()));
            transporterDTO.setTransBillEntity(BillConverter.entityToDto(transporterEntity.getTransBillEntity()));
            transporterDTO.setTimeStart(transporterEntity.getTimeStart());
            transporterDTO.setTimeEnd(transporterEntity.getTimeEnd());
            transporterDTO.setStatus(transporterEntity.getStatus());
            transporterDTO.setPrioritize(transporterEntity.getPrioritize());
        return transporterDTO;
    }

    public static TransporterEntity dtoToEntity(TransporterDTO transporterDTO) {
        TransporterEntity transporterEntity = new TransporterEntity();
        transporterEntity.setIdtransporter(transporterDTO.getIdtransporter());
        transporterEntity.setTransUserEntity(UserConverter.dtoToEntity(transporterDTO.getTransUserEntity()));
        transporterEntity.setTransBillEntity(BillConverter.dtoToEntity(transporterDTO.getTransBillEntity()));
        transporterEntity.setTimeStart(transporterDTO.getTimeStart());
        transporterEntity.setTimeEnd(transporterDTO.getTimeEnd());
        transporterEntity.setStatus(transporterDTO.getStatus());
        transporterEntity.setPrioritize(transporterDTO.getPrioritize());
        return transporterEntity;
    }
}
