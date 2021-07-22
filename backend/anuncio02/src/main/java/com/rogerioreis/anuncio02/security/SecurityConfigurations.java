package com.rogerioreis.anuncio02.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * https://programadev.com.br/spring-security-jwt/
 * https://andreybleme.com/2017-04-01/autenticacao-com-jwt-no-spring-boot/
 */

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticationService autenticationService;

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
				.anyRequest().authenticated().and().formLogin();
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
