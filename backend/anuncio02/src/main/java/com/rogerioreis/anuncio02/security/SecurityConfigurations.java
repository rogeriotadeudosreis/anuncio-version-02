package com.rogerioreis.anuncio02.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rogerioreis.anuncio02.repository.UserRepository;

/*
 * https://programadev.com.br/spring-security-jwt/
 * https://andreybleme.com/2017-04-01/autenticacao-com-jwt-no-spring-boot/
 */

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticationService autenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configuration for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configuration for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/anuncio02/user").permitAll()
				.antMatchers(HttpMethod.GET, "/anuncio02/user/*").permitAll()
				.antMatchers(HttpMethod.POST, "/anuncio02/auth").permitAll()
				.antMatchers(HttpMethod.PUT, "/anuncio02/user/*").permitAll()
				.antMatchers(HttpMethod.POST, "/anuncio02/user/**").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.anyRequest().authenticated()
				.and().cors()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticationWithTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}

	// Configuration for static resources, como CSS, JS, HTML,
	// mas não usaremos neste projeto, por se tratar de uma API REST.
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
////		return new BCryptPasswordEncoder();
//	}

}
