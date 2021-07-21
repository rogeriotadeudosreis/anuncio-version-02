package com.rogerioreis.anuncio02.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.rogerioreis.anuncio02.entity.Profile;
import com.rogerioreis.anuncio02.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O preenchimento do campo nome é obrigatório.")  
	@Size (min = 3, message = "O nome deve ter no mínimo 03 letras.")
	private String name;

	@Email(message = "O formato do email está incorreto - Verifique.")
	@NotBlank(message = "O email é preenchimento obrigatório.")
	@Size(min = 12, message = "O email informado deve ter no mínimo 12 caracteres.")
	private String email;
	
	@NotBlank(message = "O preenchimento do campo senha é obrigatório.")
	@Size(min = 6, message ="A senha deve ter no mínimo 06 dígitos.")
	private String password;
	
	private LocalDateTime dtRegister = LocalDateTime.now();
	
	private List<Profile> profiles = new ArrayList<>();

	public UserInsertDto(User userReturnDataBase) {
		this.name = userReturnDataBase.getName();
		this.email = userReturnDataBase.getEmail();
		this.password = userReturnDataBase.getPassword();
		this.profiles = userReturnDataBase.getProfiles();		
	}

}
