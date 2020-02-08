package com.hieuminh.entity;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity {

	private static final long serialVersionUID = -3641402942916578699L;

	@Column(name="[type]")
	private String type;

	@Column(name="[name]")
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="[key]")
	private String key;

	@Column(name="[value]")
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
