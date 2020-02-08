package com.hieuminh.converter;

import com.hieuminh.dto.LoginDTO;
import com.hieuminh.entity.LoginEntity;

public class LoginConverter {

    public static LoginDTO entityToDto(LoginEntity loginEntity) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdLogin(loginEntity.getIdLogin());
        loginDTO.setIdLoginUserEntity(UserConverter.entityToDto(loginEntity.getIdLoginUserEntity()));
        loginDTO.setNameLogin(loginEntity.getNameLogin());
        loginDTO.setPassWord(loginEntity.getPassWord());

        return loginDTO;
    }

    public static LoginEntity dtoToEntity(LoginDTO loginDTO) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setIdLogin(loginDTO.getIdLogin());
        loginEntity.setIdLoginUserEntity(UserConverter.dtoToEntity(loginDTO.getIdLoginUserEntity()));
        loginEntity.setNameLogin(loginDTO.getNameLogin());
        loginEntity.setPassWord(loginDTO.getPassWord());
        return loginEntity;
    }
}
