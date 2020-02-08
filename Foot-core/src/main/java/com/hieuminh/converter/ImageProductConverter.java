package com.hieuminh.converter;

import com.hieuminh.dto.ImageProductDTO;
import com.hieuminh.entity.ImageProductEntity;

public class ImageProductConverter {

    public static ImageProductDTO entityToDto(ImageProductEntity imageProductEntity) {
        ImageProductDTO imageProductDTO = new ImageProductDTO();
        imageProductDTO.setIdImageProduct(imageProductEntity.getIdImageProduct());
        imageProductDTO.setIdImageProductEntity(ProductConverter.entityToDto(imageProductEntity.getIdImageProductEntity()));
        imageProductDTO.setImages(imageProductEntity.getImages());
        return imageProductDTO;
    }

    public static ImageProductEntity dtoToEntity(ImageProductDTO imageProductDTO) {
        ImageProductEntity imageProductEntity = new ImageProductEntity();
        imageProductEntity.setIdImageProduct(imageProductDTO.getIdImageProduct());
        imageProductEntity.setIdImageProductEntity(ProductConverter.dtoToEntity(imageProductDTO.getIdImageProductEntity()));
        imageProductEntity.setImages(imageProductDTO.getImages());
        return imageProductEntity;
    }
}
