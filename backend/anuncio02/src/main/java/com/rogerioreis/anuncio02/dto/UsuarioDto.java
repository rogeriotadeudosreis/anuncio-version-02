package com.rogerioreis.anuncio02.dto;

import java.time.LocalDate;

import com.rogerioreis.anuncio02.entidades.Usuario;
import com.rogerioreis.anuncio02.enumeration.EnumPerfil;
import com.rogerioreis.anuncio02.enumeration.EnumStatus;

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
public class UsuarioDto {

	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private EnumPerfil perfil;
	private EnumStatus status;
	private LocalDate dtCadastro;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.perfil = usuario.getPerfil();
		this.status = usuario.getStatus();
		this.dtCadastro = usuario.getDtCadastro();
	}
	

}
