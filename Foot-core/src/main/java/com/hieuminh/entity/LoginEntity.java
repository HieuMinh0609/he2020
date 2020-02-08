package com.hieuminh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "login")
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="[idlogin]")
    private Integer idLogin;

    @ManyToOne
    @JoinColumn(name = "[iduser]")
    private UserEntity idLoginUserEntity;


    @Column(name = "[namelogin]")
    private String nameLogin;

    @Column(name = "[password]")
    private String passWord;


    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public UserEntity getIdLoginUserEntity() {
        return idLoginUserEntity;
    }

    public void setIdLoginUserEntity(UserEntity idLoginUserEntity) {
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




}
