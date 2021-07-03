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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rogerioreis.anuncio02.enumeration.EnumUserProfile;

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

	@NotBlank(message = "Nome do usuário é obrigatório.")
	@Size(min = 2, max = 80, message = "Nome do usuário deve ter entre 2 e 80 letras.")
	private String name = "";

	@NotBlank(message = "Email do usuário é obrigatorio.")
	private String email = "";

	@NotBlank(message = "Senha do usuário é obrigatória.")
	@Size(min = 6, message = "Senha do usuário deve ter no mínimo 6 caracteres.")
	private String password = "";

	@NotNull(message = "Perfil do usuário deve ser informado.")
	private EnumUserProfile profile = EnumUserProfile.ADMINISTRADOR;

	private boolean active = false;

	@Column(name = "date_register")
	@NotNull(message = "Data de cadastro é obrigatória.")
	private LocalDate dtRegister = LocalDate.now();

	@Column(name = "date_update")
	@NotNull(message = "Data de atualização é obrigatória.")
	private LocalDate dtRegisterUpdate = LocalDate.now();

	public User(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.profile = user.getProfile();
		this.active = user.isActive();
		this.dtRegister = user.getDtRegister();
		this.dtRegisterUpdate = user.getDtRegisterUpdate();
	}

}
