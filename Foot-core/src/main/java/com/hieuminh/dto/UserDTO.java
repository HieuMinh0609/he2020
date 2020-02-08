package com.hieuminh.dto;

public class UserDTO extends AbstractDTO<UserDTO> {

    private Integer idUser;
    private RoleDTO idRoleEntity;
    private String nameFull;
    private String place;
    private Integer sex;
    private String email;
    private String  cardId;
    private String phoneNumber;
    private Integer block;

    public UserDTO() {

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public RoleDTO getIdRoleEntity() {
        return idRoleEntity;
    }

    public void setIdRoleEntity(RoleDTO idRoleEntity) {
        this.idRoleEntity = idRoleEntity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }
}
