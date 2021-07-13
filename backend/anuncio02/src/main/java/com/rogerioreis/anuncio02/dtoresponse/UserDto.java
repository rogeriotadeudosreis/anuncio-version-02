package com.rogerioreis.anuncio02.dtoresponse;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.rogerioreis.anuncio02.entity.Role;
import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.enumeration.EnumUserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private EnumUserProfile profile;
	private boolean active;
	private LocalDate dtRegister;
	private LocalDate dtRegisterUpdate;
	
	private List<Role> listRoles;

	public UserDto(User userReturnDataBase) {
		this.id = userReturnDataBase.getId();
		this.name = userReturnDataBase.getName();
		this.email = userReturnDataBase.getEmail();
		this.profile = userReturnDataBase.getProfile();
		this.active = userReturnDataBase.isActive();
		this.dtRegister = userReturnDataBase.getDtRegister();
		this.dtRegisterUpdate = userReturnDataBase.getDtRegisterUpdate();
		this.listRoles = userReturnDataBase.getRoles();
		
	}

}
