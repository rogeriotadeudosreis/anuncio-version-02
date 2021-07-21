package com.rogerioreis.anuncio02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_profile")
public class Profile implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo com nome do perfil é preenchimento obrigatório.")
	@Column(name = "name_profile", nullable = false, unique = true)
	private String nameProfile;

	@Override
	public String getAuthority() {
		return nameProfile;
	}

}
