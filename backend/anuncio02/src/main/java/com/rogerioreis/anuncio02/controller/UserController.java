package com.rogerioreis.anuncio02.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rogerioreis.anuncio02.dto.UserDto;
import com.rogerioreis.anuncio02.dto.UserInsertDto;
import com.rogerioreis.anuncio02.dto.UserUpdateDto;
import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/anuncio02/user")
@Api(value = "API anúncio de imóveis protótipo")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ApiOperation(value = "Método de inserir um usuário")
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserInsertDto userForm,
			UriComponentsBuilder uriBuilder) {

		User userSave = modelMapper.map(userForm, User.class);

		service.createUser(userSave);

		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(userSave.getId()).toUri();

		return ResponseEntity.created(uri).body(new UserDto(userSave));

	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Método para Atualizar um usuário.")
	@Transactional
	public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userForm,
			UriComponentsBuilder uriBuilder) {

		User userReceiver = modelMapper.map(userForm, User.class);

		User userAtualizado = service.updateUser(userReceiver);

		return ResponseEntity.ok(new UserDto(userAtualizado));

	}

	@GetMapping
	@ApiOperation(value = "Método para consultar todos usuários na base de dados.")
	public ResponseEntity<Page<UserDto>> getAll(@RequestParam(required = false) String name, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {

		// aqui abaixo uma primeira forma de fazer ordenação
//	public ResponseEntity<Page<UserDto>> getAll(@RequestParam(required = false) String name,
//			@RequestParam int pagina,
//			@RequestParam int qtd,
//			@RequestParam String ordering)

//		Pageable pagination = PageRequest.of(pagina, qtd, Direction.DESC, ordering);

		if (name == null) {
			Page<User> list = service.listUsers(pagination);
			Page<UserDto> listDto = list.map(UserDto::new);
			return ResponseEntity.ok().body(listDto);

		} else {
			Page<User> list = service.listAllUsersByName(name, pagination);
			Page<UserDto> listDto = list.map(UserDto::new);
			return ResponseEntity.ok().body(listDto);
		}

	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Método para consultar um usuário pelo id.")
	public ResponseEntity<UserDto> getById(@PathVariable Long id) {

		User userReceiver = service.findById(id);

		return ResponseEntity.ok().body(new UserDto(userReceiver));
	}

	@GetMapping(value = "/email/{email}")
	@ApiOperation(value = "Método para consultar um usuário pelo email.")
	public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {

		User userReceiver = service.findByEmail(email);

		return ResponseEntity.ok().body(new UserDto(userReceiver));
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Método para deletar um usuário.")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {

		service.delete(id);

		return ResponseEntity.ok().build();
	}

}
