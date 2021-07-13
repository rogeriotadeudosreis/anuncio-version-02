package com.rogerioreis.anuncio02.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rogerioreis.anuncio02.dtoresponse.UserDto;
import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.exceptions.ConstraintException;
import com.rogerioreis.anuncio02.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/anuncio02")
@Api(value = "API anúncio de imóveis protótipo")
@CrossOrigin(origins = "*")
public class UserRest {

	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value = "/home")
	public String home() {
		return "Aqui é apenas uma página de teste de rota autorizada";
	}
	
	@PostMapping(value = "/user")
	@ApiOperation(value = "Método de inserir um usuário")
	public ResponseEntity<UserDto> create(@Valid @RequestBody User user, HttpServletResponse response, BindingResult br) {  

		if (br.hasErrors()) {
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}

		service.createUser(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(modelMapper.map(user, UserDto.class));

	}

	@PutMapping(value = "/userupdate/{id}")
	@ApiOperation(value = "Método para Atualizar um usuário.")
	public ResponseEntity<UserDto> update(@Valid @PathVariable("id") Long id, @RequestBody User user,HttpServletResponse response, BindingResult br) { 
  
		
		if (br.hasErrors()) {
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}

		service.updateUser(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(modelMapper.map(user, UserDto.class));
		
	}

	@GetMapping(value ="/user")
	@ApiOperation(value = "Método para consultar todos usuários na base de dados.")
	public ResponseEntity<List<UserDto>> getAll() {  
		
		List<User> list = service.listUsers();
		
		List<UserDto> listDto = list.stream().map(obj -> new UserDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/userid/{id}")
	@ApiOperation(value = "Método para consultar um usuário pelo id.")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {

		
		User user = service.findById(id);
		
		return ResponseEntity.ok().body(modelMapper.map(user, UserDto.class));
	}
  
	@GetMapping(value = "/useremail/{email}")
	@ApiOperation(value = "Método para consultar um usuário pelo email.")
	public ResponseEntity<UserDto> getByEmail(@PathVariable("email") String email) {  
		
		User user = service.findByEmail(email);
		
		return ResponseEntity.ok().body(modelMapper.map(user, UserDto.class));
	}

	@GetMapping(value = "/username/{name}")
	@ApiOperation(value = "Método para consultar um usuário pelo nome.")
	public ResponseEntity<List<UserDto>> getAllByName(@PathVariable("name") String name) {   
		
		List<User> list = service.listAllUsersByName(name);
		
		List<UserDto> listDto = list.stream().map(obj -> new UserDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
		
	}

	@DeleteMapping(value = "/userdelete/{id}")
	@ApiOperation(value = "Método para deletar um usuário.")
	public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) { 
		
		service.delete(id);
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
