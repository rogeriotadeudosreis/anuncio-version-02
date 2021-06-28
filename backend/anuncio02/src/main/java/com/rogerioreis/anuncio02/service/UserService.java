package com.rogerioreis.anuncio02.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.dto.UserDto;
import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.exceptions.AnuncioExceptionHandler;
import com.rogerioreis.anuncio02.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * Buscando uma lista de usuários na base de dados
	 */
	public List<UserDto> listUsers() {

		List<User> listUsersDataBase = repository.findAll();

		if (listUsersDataBase.isEmpty()) {
			throw new AnuncioExceptionHandler("A lista de usuários está vazia.");
		}

		List<UserDto> listUsersDto = new ArrayList<>();

		for (User user : listUsersDataBase) {

			listUsersDto.add(new UserDto(user));
		}

		return listUsersDto;
	}

	/*
	 * Criando um usuário na base de dados
	 */
	public UserDto createUser(User user) {

		this.verifyEmailExistence(user.getEmail());

		User userSaveDataBase = new User();
		userSaveDataBase.setName(user.getName());
		userSaveDataBase.setEmail(user.getEmail());
		userSaveDataBase.setPassword(passwordEncoder.encode(user.getPassword()));
		userSaveDataBase.setProfile(user.getProfile());
		userSaveDataBase.setActive(user.isActive());
		userSaveDataBase.setDtRegister(user.getDtRegister());

		UserDto userDto = new UserDto(repository.save(userSaveDataBase));

		return userDto;

	}

	/*
	 * Deletando um usuário na base de dados
	 */
	public void delete(Long id) {
		verifyUserExistence(id);
		repository.deleteById(id);
	}

	/*
	 * Buscando um usuário pelo id
	 */
	public UserDto findById(Long id) {
		Optional<User> userOptional = repository.findById(id);
		verifyUserExistence(id);
		User user = userOptional.get();
		return new UserDto(user);
	}

	/*
	 * Buscando um usuário na base de dados pelo email
	 */
	public UserDto findByEmail(String email) {

		if (isValidEmailAddress(email)) {

			try {
				User userByEmail = new User();
				userByEmail = repository.findByEmail(email);
				return new UserDto(userByEmail);
			} catch (Exception e) {
				throw new AnuncioExceptionHandler("Não existe nenhum usuário com o email: " + email);
			}

		}
		throw new AnuncioExceptionHandler("O email: " + email + " não é um email válido");
	}

	/*
	 * método que verifica se usuário existe
	 */
	private void verifyUserExistence(Long id) {

		Optional<User> userDataBase = repository.findById(id);

		if (!userDataBase.isPresent()) {
			throw new AnuncioExceptionHandler("Usuário com ID: " + id + " não existe");
		}
	}

	/*
	 * método que verifica se tal email existe na base de dados
	 */
	private void verifyEmailExistence(String email) {

		if (!isValidEmailAddress(email)) {

			throw new AnuncioExceptionHandler("O email: " + email + " não é um email válido.");
		}

		List<User> listUsers = new ArrayList<>();

		listUsers = repository.findAll();

		for (User user : listUsers) {

			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new AnuncioExceptionHandler("O email: " + email + " já existe para o usuário: " + user.getName());
			}
		}
	}

	/*
	 * método para saber se a sintaxe do email fornecido pelo usuário é válido.
	 */
	private static boolean isValidEmailAddress(String email) {

		boolean isEmailValid = false;

		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailValid = true;
			}
		}
		return isEmailValid;
	}

	/*
	 * método para buscar uma lista de usuários por nome
	 */
	public List<UserDto> listAllUsersByName(String name) {

		List<User> listDataBase = new ArrayList<>();

		listDataBase = repository.findAll();

		if (listDataBase.isEmpty()) {
			throw new AnuncioExceptionHandler("Nenhum usuário cadastrado no momento");
		}

		List<UserDto> listNamesFind = new ArrayList<>();
		
		for (User user : listDataBase) {
			if (user.getName().contains(name)) {
				listNamesFind.add(new UserDto(user));
			}
		}
		
		return listNamesFind;

	}

}
