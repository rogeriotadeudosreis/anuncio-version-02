package com.rogerioreis.anuncio02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnuncioExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnuncioExceptionHandler(String message) {
		super(message);
	}

	

}
