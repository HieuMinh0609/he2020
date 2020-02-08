package com.hieuminh.converter;

import com.hieuminh.dto.TypeDTO;
import com.hieuminh.entity.TypeEntity;

public class TypeConverter {
    public static TypeDTO entityToDto(TypeEntity typeEntity) {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setTypeId(typeEntity.getTypeId());
        typeDTO.setTypeName(typeEntity.getTypeName());

        return typeDTO;
    }

    public static TypeEntity dtoToEntity(TypeDTO typeDTO) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setTypeId(typeDTO.getTypeId());
        typeEntity.setTypeName(typeDTO.getTypeName());
        return typeEntity;
    }
}
