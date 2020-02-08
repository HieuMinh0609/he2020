package com.hieuminh.converter;

import com.hieuminh.dto.SaleDTO;
import com.hieuminh.entity.SaleEntity;

public class SaleConverter {

    public static SaleDTO entityToDto(SaleEntity saleEntity) {
        SaleDTO saleDTO = new SaleDTO();

        saleDTO.setIdSale(saleEntity.getIdSale());
        saleDTO.setName(saleEntity.getName());
        saleDTO.setTimeStart(saleEntity.getTimeStart());
        saleDTO.setTimeEnd(saleEntity.getTimeEnd());
        saleDTO.setDetail(saleEntity.getDetail());
        saleDTO.setImage(saleEntity.getImage());
        return saleDTO;
    }

    public static SaleEntity dtoToEntity(SaleDTO saleDTO) {
        SaleEntity saleEntity = new SaleEntity();

        saleEntity.setIdSale(saleDTO.getIdSale());
        saleEntity.setName(saleDTO.getName());
        saleEntity.setTimeStart(saleDTO.getTimeStart());
        saleEntity.setTimeEnd(saleDTO.getTimeEnd());
        saleEntity.setDetail(saleDTO.getDetail());
        saleEntity.setImage(saleDTO.getImage());

        return saleEntity;
    }
}
