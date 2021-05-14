package com.rogerioreis.anuncio02.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rogerioreis.anuncio02.dto.UsuarioDto;
import com.rogerioreis.anuncio02.entidades.Usuario;
import com.rogerioreis.anuncio02.servicos.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> getAll() {
		List<UsuarioDto> list = service.ListarUsuarios();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> getById(@PathVariable("id") Long id) {
		UsuarioDto usuarioDto = service.buscarPorId(id);
		return ResponseEntity.ok().body(usuarioDto);
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> create(@RequestBody Usuario usuario) {

		try {
			service.inserirUsuario(usuario);
			UsuarioDto dto = new UsuarioDto(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
					.toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public void deletarUsuario(@PathVariable ("id")Long id) {
		service.delete(id);
	}

}
