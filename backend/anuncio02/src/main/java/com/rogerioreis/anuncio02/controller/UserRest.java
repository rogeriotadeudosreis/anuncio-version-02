package com.rogerioreis.anuncio02.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rogerioreis.anuncio02.dto.UserDto;
import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.exceptions.AnuncioExceptionHandler;
import com.rogerioreis.anuncio02.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserRest {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<UserDto> create(@Valid @RequestBody User user, HttpServletResponse response, BindingResult br) {
		
		if (br.hasErrors()) {
			throw new AnuncioExceptionHandler(br.getAllErrors().get(0).getDefaultMessage());
		}
		
		service.createUser(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		UserDto dto = new UserDto(user);
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> list = service.listUsers();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
		UserDto usuarioDto = service.findById(id);
		return ResponseEntity.ok().body(usuarioDto);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<UserDto> getByEmail(@PathVariable("email") String email){
		UserDto userDto = service.findByEmail(email);
		return ResponseEntity.ok().body(userDto);
	}	
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<List<UserDto>> getAllByName(@PathVariable("name") String name){
		List<UserDto> list = service.listAllUsersByName(name);
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping(value = "/{id}")
	public void deletarUsuario(@PathVariable Long id) {
		service.delete(id);
	}	

}
