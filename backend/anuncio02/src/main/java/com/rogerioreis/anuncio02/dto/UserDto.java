package com.rogerioreis.anuncio02.dto;

import java.time.LocalDate;

import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.enumeration.EnumPerfil;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	private String email;
	private EnumPerfil profile;
	private boolean active;
	private LocalDate dtRegister;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.profile = user.getProfile();
		this.active = user.isActive();
		this.dtRegister = user.getDtRegister();
	}
	

}
