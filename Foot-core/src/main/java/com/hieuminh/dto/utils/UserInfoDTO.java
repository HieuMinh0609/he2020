package com.hieuminh.dto.utils;

import com.hieuminh.dto.AbstractDTO;
import com.hieuminh.dto.RoleDTO;

public class UserInfoDTO extends AbstractDTO<UserInfoDTO> {

	private static final long serialVersionUID = -1478261594752770476L;
	private Integer idLogin;
	private Integer idUser;
	private Integer idRole;
	private RoleDTO idRoleEntity;
	private String nameFull;
	private String place;
	private int sex;

	private int block;
	private String email;
	private String cardId;
	private String phoneNumber;
	private Integer check;
	private String nameLogin;
	private String passWord;

	public void setBlock(int block) {
		this.block = block;
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

	public String getNameFull() {
		return nameFull;
	}

	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public int getBlock() {
		return block;
	}
}
