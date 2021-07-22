package com.rogerioreis.anuncio02.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.repository.UserRepository;

/*
 *  Essa classe é a responsável pela autenticação do usuario.
 *  Não é preciso de uma controller, pois o próprio spring faz automaticamente
 */
@Service
public class AutenticationService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	/*
	 * Esse método é o responsavel de receber os dados do usuário para autenticação
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = repository.findByEmail(username);

		if (user.isPresent()) {
			System.out.println("Tentando autenticar o usuário: " + user.get().getEmail() + "/" +
					user.get().getPassword());
			return user.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!.");
	}
}
