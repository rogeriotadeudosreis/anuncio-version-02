package com.rogerioreis.anuncio02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_role")
public class Role {

	@Id
	@NotBlank(message = "Campo com nome da função, é preenchimento obrigatório.")
	@Column(nullable = false, unique = true)
	private String nameRole;

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

}
