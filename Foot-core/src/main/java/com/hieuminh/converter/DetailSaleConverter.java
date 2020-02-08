package com.hieuminh.converter;

import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.entity.DetailSaleEntity;

public class DetailSaleConverter {

    public static DetailSaleDTO entityToDto(DetailSaleEntity detailSaleEntity) {
        DetailSaleDTO detailSaleDTO = new DetailSaleDTO();
        detailSaleDTO.setIdDetailSale(detailSaleEntity.getIdDetailSale());
        detailSaleDTO.setIdSaleEntity(SaleConverter.entityToDto(detailSaleEntity.getIdSaleEntity()));
        detailSaleDTO.setIdProductSaleEntity(ProductConverter.entityToDto(detailSaleEntity.getIdProductSaleEntity()));
        detailSaleDTO.setDownpercen(detailSaleEntity.getDownpercen());

        return detailSaleDTO;
    }

    public static DetailSaleEntity dtoToEntity(DetailSaleDTO detailSaleDTO) {
        DetailSaleEntity detailSaleEntity = new DetailSaleEntity();

        detailSaleEntity.setIdDetailSale(detailSaleDTO.getIdDetailSale());
        detailSaleEntity.setIdSaleEntity(SaleConverter.dtoToEntity(detailSaleDTO.getIdSaleEntity()));
        detailSaleEntity.setIdProductSaleEntity(ProductConverter.dtoToEntity(detailSaleDTO.getIdProductSaleEntity()));
        detailSaleEntity.setDownpercen(detailSaleDTO.getDownpercen());

        return detailSaleEntity;
    }
}
