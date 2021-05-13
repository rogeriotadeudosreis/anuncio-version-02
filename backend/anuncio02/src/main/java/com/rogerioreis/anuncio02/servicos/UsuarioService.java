package com.rogerioreis.anuncio02.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.entidades.Usuario;
import com.rogerioreis.anuncio02.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> ListarUsuarios() {
		return repository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	public Usuario inserirUsuario(Usuario usuario) throws Exception {
		Usuario usuarioBanco = repository.buscarPorEmail(usuario.getEmail());
		if (usuarioBanco != null) {
			throw new Exception("O email informado já existe. Verifique");
		}
		return repository.save(usuario);
	}
}








