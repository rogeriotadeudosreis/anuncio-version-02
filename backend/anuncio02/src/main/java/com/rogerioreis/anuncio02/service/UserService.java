package com.rogerioreis.anuncio02.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.exceptions.ConstraintException;
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
	public List<User> listUsers() {

		List<User> listUsersDataBase = repository.findAll(Sort.by(Sort.Direction.ASC, "name"));

		if (listUsersDataBase.isEmpty()) {
			throw new ObjectNotFoundException("A lista de usuários está vazia.");
		}

		return listUsersDataBase;
	}

	/*
	 * Criando um usuário na base de dados
	 */
	public User createUser(User user) { 

		verifyDateValidInsert(user.getDtRegister(), user.getDtRegisterUpdate());

		verifyEmailExistence(user.getEmail());

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		return repository.save(user);
	}

	/*
	 * Atualizando um usuário
	 */
	public User updateUser(User user) {    
		
		validUserUpdate(user);

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		return repository.save(user);

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
		Optional<User> userOptional = repository.findById(id);
		verifyUserExistence(id);
		return userOptional.get();
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
		throw new ConstraintException("O email: " + email + " não é um email válido");
	}

	/*
	 * método que verifica se usuário existe
	 */
	private void verifyUserExistence(Long id) {

		if (id.equals(null)) {
			throw new ConstraintException("O id deve ser informato");
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

			throw new ConstraintException("O email: " + email + " não é um email válido.");
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

		if (email.length() < 12) {
			throw new ConstraintException("O email informado deve ter no mínimo 12 letras.");
		}

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
	public List<User> listAllUsersByName(String name) {

		List<User> listDataBase = new ArrayList<>();

		listDataBase = repository.findByName(name);

		if (listDataBase.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum usuário cadastrado no momento.");
		}

		return listDataBase;
	}

	/*
	 * Verifica se a data de cadastro é válida
	 */
	private static void verifyDateValidInsert(LocalDate date, LocalDate dateUpdate) {

		if (date.toString().isBlank() || dateUpdate.toString().isBlank()) {
			throw new ConstraintException("A data de cadastro deve ser informada, verifique.");
		}

		if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.now()) || dateUpdate.isAfter(LocalDate.now())
				|| dateUpdate.isBefore(LocalDate.now())) {
			throw new ConstraintException("A data inserida não é uma data válida, verifique.");
		}

	}

	/*
	 * Verifica se a data de atualização é válida
	 */
	private void verifyDateValidUpdate(LocalDate date) { 

		if (date.toString().isBlank()) {
			throw new ConstraintException("A data de atualização deve ser informada, verifique.");
		}

		if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.now())) {
			throw new ConstraintException("A data de atualização inserida não é uma data válida, verifique.");
		}

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
		
		verifyDateValidUpdate(user.getDtRegisterUpdate());

		if (user.getName().isBlank()) {
			throw new ConstraintException("O nome do usuário deve ser informado. Verifique");
		}

		if (user.getName().length() < 2) {
			throw new ConstraintException("O nome do usuário deve ter no mínimo 02 letras");
		}
		
		if (user.getPassword().isBlank()) {
			throw new ConstraintException("A senha deve ser informada. Verifique.");
		}

		if (user.getPassword().length() < 6) {
			throw new ConstraintException("A senha informada deve ter no mínimo 6 caracteres. Verifique.");
		}
		
		if (user.getProfile().toString().isBlank()) {
			throw new ConstraintException("O perfil do usuário deve ser informado. Verifique.");
		}
		
		

	}

}
