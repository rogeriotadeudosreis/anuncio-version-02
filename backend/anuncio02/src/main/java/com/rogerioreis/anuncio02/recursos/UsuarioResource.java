package com.rogerioreis.anuncio02.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rogerioreis.anuncio02.entidades.Usuario;
import com.rogerioreis.anuncio02.servicos.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> list = service.ListarUsuarios();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable("id") Long id) {
		Usuario usuario = service.buscarPorId(id);
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> inserirAnuncio(@RequestBody Usuario usuario) {
		Usuario usuarioCriado;
		try {
			usuarioCriado = service.inserirUsuario(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(usuarioCriado.getId()).toUri();
			return ResponseEntity.created(uri).body(usuarioCriado);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}

	}

}
