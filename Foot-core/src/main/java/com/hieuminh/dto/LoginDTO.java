package com.hieuminh.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 8835146939192307340L;

    private Integer idLogin;
    private UserDTO idLoginUserEntity;
    private String nameLogin;
    private String passWord;
    private Boolean status;
    public LoginDTO() {

    }

    public UserDTO getIdLoginUserEntity() {
        return idLoginUserEntity;
    }

    public void setIdLoginUserEntity(UserDTO idLoginUserEntity) {
        this.idLoginUserEntity = idLoginUserEntity;
    }

    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
