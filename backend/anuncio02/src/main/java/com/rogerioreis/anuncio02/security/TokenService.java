package com.rogerioreis.anuncio02.security;

import java.util.Date;  

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${anuncio02.jwt.expiration}")
	private Long expiration;

	@Value("${anuncio02.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {

		User logged = (User) authentication.getPrincipal();
		Date today = new Date();
		Date dateExpiration = new Date(today.getTime() + expiration);

		return Jwts.builder()
				.setIssuer("API Anuncio02 Version 2")
				.setSubject(logged.getId().toString())
				.setIssuedAt(today)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;	
		} catch (Exception e) {
			return false;
		}		
	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}






