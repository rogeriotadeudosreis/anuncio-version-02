package com.rogerioreis.anuncio02.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.rogerioreis.anuncio02.enumeration.EnumPerfil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_usuario")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank(message = "O nome do usuário deve ser informado.")
	private String name = "";
	
	@NotBlank(message = "Ops! informe - email por favor.")
	private String email = "";
	
	@NotBlank(message = "Informe uma senha para o cadastro.")
	private String password = "";
	
	private EnumPerfil profile = EnumPerfil.ADMINISTRADOR;	
	
	private boolean active = false;
	
	@Column(name = "date_register")
	private LocalDate dtRegister = LocalDate.now();
	
	public User(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.profile = user.getProfile();
		this.active = user.isActive();
		this.dtRegister = user.getDtRegister();
	}

	public User(Long id, String name, String email, String password, EnumPerfil profile,
			boolean active, LocalDate dtRegister) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.active = active;
		this.dtRegister = dtRegister;
	}

	
	
	
	
	

}

