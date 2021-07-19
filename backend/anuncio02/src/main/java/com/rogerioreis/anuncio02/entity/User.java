package com.rogerioreis.anuncio02.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank(message = "Nome do usuário é obrigatório.")
	@Size(min = 2, max = 80, message = "Nome do usuário deve ter entre 2 e 80 letras.")
	private  String name = "";

	@NotBlank(message = "Email do usuário é obrigatorio.")
	@Column(name = "email")
	private String email = "";

	@NotBlank(message = "Senha do usuário é obrigatória.")
	@Size(min = 6, message = "Senha do usuário deve ter no mínimo 6 caracteres.")
	private String password = "";

	@ManyToMany
	@JoinTable(name = "tb_users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();
 
	private boolean active = false;

	@Column(name = "date_register")
	private LocalDateTime dtRegister = LocalDateTime.now();

	@Column(name = "date_update")
	private LocalDateTime dtRegisterUpdate = LocalDateTime.now();

	public User(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.active = user.isActive();
		this.dtRegister = user.getDtRegister();
		this.dtRegisterUpdate = user.getDtRegisterUpdate();
	}

}
