package com.hieuminh.dto;

public class RoleDTO extends AbstractDTO<RoleDTO> {
	
	private static final long serialVersionUID = 5830885581031027382L;

	private Integer idRole;
	private String roleName;

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
