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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * https://programadev.com.br/spring-security-jwt/
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired

	//Configuration for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		// cria uma conta default para autenticar
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
		
	}

	//Configuration for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/home").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().headers().frameOptions().disable()
			
			.and()
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login"), authenticationManager(),
			UsernamePasswordAuthenticationFilter.class)

			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			
			.and().csrf().disable();
	}
	
	//Configuration for static resources, como CSS, JS, HTML,
	// mas não usaremos neste projeto, por se tratar de uma API REST.
	@Override
	public void configure(WebSecurity web) throws Exception{		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
