package com.rogerioreis.anuncio02.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rogerioreis.anuncio02.exceptions.ConstraintException;
import com.rogerioreis.anuncio02.exceptions.ObjectAlreadyExistsException;
import com.rogerioreis.anuncio02.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class HandlerControllerException {
	
	LocalDateTime dateSystem = LocalDateTime.now();

	/*
	 * Esta exceção trata:
	 * 
	 * Entrada de valores em branco, nulo e com tamanho menor do que o especificado
	 * Entrada de valores inválidos, como por ex: email com a sintaxe incorreta
	 */
	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<StandartError> handlerInputException(ConstraintException e,
			HttpServletRequest request) {
		StandartError error = new StandartError(dateSystem, HttpStatus.BAD_REQUEST.value(),
				"Restrição na entrada de dados", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	/*
	 * Esta exceção trata:
	 * 
	 * Entrada de valores de objetos que já existam na base de dados, por exemplo:
	 * Pode ser informado um email já existente para um usuário cadastrado,
	 *
	 */
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public ResponseEntity<StandartError> handlerAlreadyExists(ObjectAlreadyExistsException o,
			HttpServletRequest request) {
		StandartError error = new StandartError(dateSystem, HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", o.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	/*
	 * Esta exceção trata:
	 * 
	 * Valores não encontrados na base de dados
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> handlerObjectNotFound(ObjectNotFoundException o,
			HttpServletRequest request) {
		StandartError error = new StandartError(dateSystem, HttpStatus.NOT_FOUND.value(),
				"Registro não encontrado", o.getMessage(), request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
