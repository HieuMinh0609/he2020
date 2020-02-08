package com.hieuminh.converter;

import com.hieuminh.dto.UserDTO;
import com.hieuminh.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static UserDTO entityToDto(UserEntity userEntity) {
        UserDTO userDTO= new UserDTO();
        userDTO.setIdUser(userEntity.getIdUser());
        userDTO.setIdRoleEntity(RoleConverter.entityToDto(userEntity.getIdRoleEntity()));
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setNameFull(userEntity.getNameFull());
        userDTO.setPlace(userEntity.getPlace());
        userDTO.setSex(userEntity.getSex());
        userDTO.setCardId(userEntity.getCardId());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setBlock(userEntity.getBlock());
        return userDTO;

    }

    public static UserEntity dtoToEntity(UserDTO userDTO) {
        UserEntity userEntity= new UserEntity();
        userEntity.setIdUser(userDTO.getIdUser());
        userEntity.setIdRoleEntity(RoleConverter.dtoToEntity(userDTO.getIdRoleEntity()));
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setNameFull(userDTO.getNameFull());
        userEntity.setPlace(userDTO.getPlace());
        userEntity.setSex(userDTO.getSex());
        userEntity.setCardId(userDTO.getCardId());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setBlock(userDTO.getBlock());


        return userEntity;

    }

}
