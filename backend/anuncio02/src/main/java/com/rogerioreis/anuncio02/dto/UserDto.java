package com.rogerioreis.anuncio02.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rogerioreis.anuncio02.entity.Profile;
import com.rogerioreis.anuncio02.entity.User;

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
	private List<Profile> profiles = new ArrayList<>();
	private boolean active;
	private LocalDateTime dataRegister;
	private LocalDateTime dataRegisterUpdate;
	

	public UserDto(User userReturnDataBase) {
		this.id = userReturnDataBase.getId();
		this.name = userReturnDataBase.getName();
		this.email = userReturnDataBase.getEmail();
		this.profiles = userReturnDataBase.getProfiles();
		this.active = userReturnDataBase.isActive();
		this.dataRegister = userReturnDataBase.getDtRegister();
		this.dataRegisterUpdate = userReturnDataBase.getDtRegisterUpdate();
		
	}

}
