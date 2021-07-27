package com.rogerioreis.anuncio02.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rogerioreis.anuncio02.entity.User;
import com.rogerioreis.anuncio02.repository.UserRepository;

/*
 * Este classe é a responsável por recuperar e validar o token gerado, conforme aula anterior.
 *   
 *  Conforme a configuração na classe SecurityConfigurations, a cada requisição, deve ser enviado
 *  o token no header da requisição.
 *  A idéia é que esta recuperação seja feita antes da requisição chegar em nossa AutenticacaoController.
 */

// Observação importante, nesta classe não é possível instanciar com o @Autowired, pois o spring não monitora esta classe
// Para isso é necessário colocar como parâmetro dentro do construtor desta classe e instanciar na SecurityConfigurations.  
public class AutenticationWithTokenFilter  extends OncePerRequestFilter{
	
	private TokenService tokenService;
	
	private UserRepository userRepository;
	
	public AutenticationWithTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Recuperando o token
		String token = recoveryToken(request);
		
		// Validando o token
		boolean valido = tokenService.isTokenValid(token);
		
		if (valido) {
			autenticateClient(token);
		}
		
		
		// Filtrando cada requisição
		filterChain.doFilter(request, response);
		
	}

	/*
	 * Após recuperar e validar o token, fazemos a autenticação do client
	 */
	private void autenticateClient(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = userRepository.findById(idUser).get(); 
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recoveryToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}











