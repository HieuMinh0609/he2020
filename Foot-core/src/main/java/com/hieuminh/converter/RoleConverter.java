package com.hieuminh.converter;

import com.hieuminh.dto.RoleDTO;
import com.hieuminh.entity.RoleEntity;

public class RoleConverter {

    public static RoleDTO entityToDto(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(roleEntity.getIdRole());
        roleDTO.setRoleName(roleEntity.getRoleName());
        return roleDTO;
    }

    public static RoleEntity dtoToEntity(RoleDTO roleDto) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setIdRole(roleDto.getIdRole());
        roleEntity.setRoleName(roleDto.getRoleName());
        return roleEntity;
    }
}
