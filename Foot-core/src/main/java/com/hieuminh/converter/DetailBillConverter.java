package com.hieuminh.converter;

import com.hieuminh.dto.DetailBillDTO;
import com.hieuminh.entity.DetailBillEntity;

public class DetailBillConverter {

    public static DetailBillDTO entityToDto(DetailBillEntity detailBillEntity) {
        DetailBillDTO detailBillDTO = new DetailBillDTO();
        detailBillDTO.setIdDetailBill(detailBillEntity.getIdDetailBill());
        detailBillDTO.setIdDetailBillBillEntity(BillConverter.entityToDto(detailBillEntity.getIdDetailBillBillEntity()));
        detailBillDTO.setIdDBillProductEntity(ProductConverter.entityToDto(detailBillEntity.getIdDBillProductEntity()));
        detailBillDTO.setCount(detailBillEntity.getCount());
        return detailBillDTO;
    }
    public static DetailBillEntity dtoToEntity(DetailBillDTO detailBillDTO) {
        DetailBillEntity detailBillEntity = new DetailBillEntity();
        detailBillEntity.setIdDetailBill(detailBillDTO.getIdDetailBill());
        detailBillEntity.setIdDetailBillBillEntity(BillConverter.dtoToEntity(detailBillDTO.getIdDetailBillBillEntity()));
        detailBillEntity.setIdDBillProductEntity(ProductConverter.dtoToEntity(detailBillDTO.getIdDBillProductEntity()));
        detailBillEntity.setCount(detailBillDTO.getCount());
        return detailBillEntity;
    }
}
