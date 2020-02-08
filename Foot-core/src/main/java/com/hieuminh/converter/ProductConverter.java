package com.hieuminh.converter;

import com.hieuminh.dto.ProductDTO;
import com.hieuminh.entity.ProductEntity;

public class ProductConverter {

    public static ProductDTO entityToDto(ProductEntity productEntity) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setIdProduct(productEntity.getIdProduct());
            productDTO.setNameProduct(productEntity.getNameProduct());
            productDTO.setCost(productEntity.getCost());
            productDTO.setImage(productEntity.getImage());
            productDTO.setInformation(productEntity.getInformation());
            productDTO.setView(productEntity.getView());
            productDTO.setStatus(productEntity.getStatus());
            productDTO.setTypeIdEntity(TypeConverter.entityToDto(productEntity.getTypeIdEntity()));

            return productDTO;
    }

    public static ProductEntity dtoToEntity(ProductDTO productDTO) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setIdProduct(  productDTO.getIdProduct());
            productEntity.setNameProduct(  productDTO.getNameProduct());
            productEntity.setCost(  productDTO.getCost());
            productEntity.setImage(  productDTO.getImage());
            productEntity.setInformation(  productDTO.getInformation());
            productEntity.setView(productDTO.getView());
            productEntity.setStatus(productDTO.getStatus());

            productEntity.setTypeIdEntity(TypeConverter.dtoToEntity(productDTO.getTypeIdEntity()));

            return productEntity;
    }
}
