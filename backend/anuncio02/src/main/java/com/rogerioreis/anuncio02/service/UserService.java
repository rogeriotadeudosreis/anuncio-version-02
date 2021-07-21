package com.rogerioreis.anuncio02.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.exceptions.BusinessException;
import com.rogerioreis.anuncio02.exceptions.ObjectAlreadyExistsException;
import com.rogerioreis.anuncio02.exceptions.ObjectNotFoundException;
import com.rogerioreis.anuncio02.repository.UserRepository;

@Service
public class UserService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository repository;

	/*
	 * Buscando uma lista de usuários na base de dados
	 */
	public Page<User> listUsers(Pageable pagination) {   


		Page<User> listUsersDataBase = repository.findAll(pagination);

		if (listUsersDataBase.isEmpty()) {
			throw new ObjectNotFoundException("A lista de usuários está vazia.");
		}

		return listUsersDataBase;
	}

	/*
	 * Criando um usuário na base de dados
	 */
	public User createUser(User user) {       

		verifyEmailExistence(user.getEmail());

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		return repository.save(user);
	}

	/*
	 * Atualizando um usuário
	 */
	public User updateUser(User user) {  
		
		this.validUserUpdate(user);
		
		User userDatabase = this.findById(user.getId());
		
		userDatabase.setName(user.getName());
		userDatabase.setEmail(user.getEmail());
		userDatabase.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDatabase.setProfiles(user.getProfiles());
		userDatabase.setActive(user.isActive());
		userDatabase.setDtRegisterUpdate(user.getDtRegisterUpdate());

		return userDatabase;

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
	public User findById(Long id) {    
		verifyUserExistence(id);
		Optional<User> userOptional = repository.findById(id);
		User user = userOptional.get();
		return user;
	}

	/*
	 * Buscando um usuário na base de dados pelo email
	 */
	public User findByEmail(String email) { 

		if (isValidEmailAddress(email)) {

			Optional<User> userByEmailOptional = repository.findByEmail(email);

			if (!userByEmailOptional.isPresent()) {
				throw new ObjectNotFoundException("Não existe nenhum usuário com o email: " + email);
			}

			return userByEmailOptional.get();
		}
		throw new BusinessException("O email: " + email + " não é um email válido");
	}

	/*
	 * método que verifica se usuário existe
	 */
	private void verifyUserExistence(Long id) {      

		if (id.equals(null)) {
			throw new ObjectNotFoundException("O id deve ser informado");
		}

		Optional<User> userDataBase = repository.findById(id);

		if (!userDataBase.isPresent()) {
			throw new ObjectNotFoundException("Usuário com ID: " + id + " não existe");
		}
	}

	/*
	 * método que verifica se tal email existe na base de dados
	 */
	private void verifyEmailExistence(String email) {         

		if (!isValidEmailAddress(email)) {

		throw new BusinessException("O email: " + email + " não é um email válido.");
		}

		List<User> listUsers = new ArrayList<>();

		listUsers = repository.findAll();

		for (User user : listUsers) {

			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new ObjectAlreadyExistsException(
						"O email: " + email + " já existe para o usuário: " + user.getName());
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
	public Page<User> listAllUsersByName(String name, Pageable pagination) {   

		Page<User> listDataBase = repository.findByName(name, pagination);

		if (listDataBase.isEmpty()) {
			throw new ObjectNotFoundException("O usuário com o nome: " + name + ", não consta na base de dados.");
		}

		return listDataBase;
	}

	/*
	 * Método para validar um usuario no update
	 * 
	 */
	private void validUserUpdate(User user) {       

		verifyUserExistence(user.getId());
		
		List<User> listUserDataBase = new ArrayList<>();
		
		listUserDataBase = repository.findAll();
		
		for (User userDataBase : listUserDataBase) {
			if (!userDataBase.getId().equals(user.getId())
					&& userDataBase.getEmail().equals(user.getEmail())) {
				throw new ObjectAlreadyExistsException("Este Email pertence a outro cadastro. Verifique");
			}
		}
		
		isValidEmailAddress(user.getEmail());
		
		if (user.getProfiles().toString().isBlank()) {
			throw new BusinessException("O perfil do usuário deve ser informado. Verifique.");
		}
		
		

	}

}
