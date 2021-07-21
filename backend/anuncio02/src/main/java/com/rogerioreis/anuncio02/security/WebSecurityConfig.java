package com.rogerioreis.anuncio02.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * https://programadev.com.br/spring-security-jwt/
 * https://andreybleme.com/2017-04-01/autenticacao-com-jwt-no-spring-boot/
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired

	// Configuration for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// cria uma conta default para autenticar
		auth.inMemoryAuthentication()
		.withUser("admin").password("admin").roles("ADMIN", "USER");

	}

	// Configuration for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().headers().frameOptions().disable();

	}

	// Configuration for static resources, como CSS, JS, HTML,
	// mas não usaremos neste projeto, por se tratar de uma API REST.
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
//		return new BCryptPasswordEncoder();
	}

}
