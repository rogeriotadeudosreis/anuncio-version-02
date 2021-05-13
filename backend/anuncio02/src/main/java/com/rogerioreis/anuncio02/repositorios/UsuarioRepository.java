package com.rogerioreis.anuncio02.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rogerioreis.anuncio02.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario buscarPorEmail (String email);

}
