package com.rogerioreis.anuncio02.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rogerioreis.anuncio02.entity.Role;
import com.rogerioreis.anuncio02.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String password;
	private List<Role> roles = new ArrayList<>();

	public UserInsertDto(User userReturnDataBase) {
		this.name = userReturnDataBase.getName();
		this.email = userReturnDataBase.getEmail();
		this.password = userReturnDataBase.getPassword();
		this.roles = userReturnDataBase.getRoles();
		
	}

}
