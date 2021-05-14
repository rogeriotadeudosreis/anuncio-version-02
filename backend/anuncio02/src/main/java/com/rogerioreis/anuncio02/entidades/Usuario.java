package com.rogerioreis.anuncio02.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rogerioreis.anuncio02.enumeration.EnumPerfil;
import com.rogerioreis.anuncio02.enumeration.EnumStatus;

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
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String senha;
	private EnumPerfil perfil;
	private EnumStatus status;
	private LocalDate dtCadastro;
	
	public Usuario(Usuario usuario) {
		super();
		this.id = usuario.id;
		this.nome = usuario.nome;
		this.email = usuario.email;
		this.telefone = usuario.telefone;
		this.perfil = usuario.perfil;
		this.status = usuario.status;
		this.dtCadastro = usuario.dtCadastro;
	}

	public Usuario(Long id, String nome, String email, String telefone, String senha, EnumPerfil perfil,
			EnumStatus status, LocalDate dtCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.perfil = perfil;
		this.status = status;
		this.dtCadastro = dtCadastro;
	}

	
	
	
	
	

}

