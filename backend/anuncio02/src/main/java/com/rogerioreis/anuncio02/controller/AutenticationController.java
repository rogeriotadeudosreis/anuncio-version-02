package com.rogerioreis.anuncio02.controller;

import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerioreis.anuncio02.dto.TokenDto;
import com.rogerioreis.anuncio02.security.LoginForm;
import com.rogerioreis.anuncio02.security.TokenService;

@RestController
@RequestMapping("anuncio02/auth")
public class AutenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticate(@RequestBody @Valid LoginForm form) { 

		UsernamePasswordAuthenticationToken dataLogin = form.convert();

		try {
			Authentication authentication = authManager.authenticate(dataLogin);
			
			String token = tokenService.generateToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
