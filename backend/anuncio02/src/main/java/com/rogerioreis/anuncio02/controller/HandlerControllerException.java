package com.rogerioreis.anuncio02.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rogerioreis.anuncio02.exceptions.BusinessException;
import com.rogerioreis.anuncio02.exceptions.ObjectAlreadyExistsException;
import com.rogerioreis.anuncio02.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class HandlerControllerException {
	
	@Autowired
	private MessageSource messageSource;
	
	private LocalDateTime dateSystem = LocalDateTime.now();

	/*
	 * Esta exceção trata:
	 * 
	 * Entrada de valores em branco, nulo e com tamanho menor do que o especificado
	 * Entrada de valores inválidos, como por ex: email com a sintaxe incorreta
	 */
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<StandartError> handlerObjectInputException(MethodArgumentNotValidException exception,HttpServletRequest request) {   
		
		String title = "Atenção no preenchimento dos campos !!!";
		
		List<StandartError> listErrors = new ArrayList<>();
		
		List<FieldError> fieldErrors =  exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			
			StandartError erro = new StandartError(dateSystem, title, e.getField(), mensagem, request.getRequestURI());
			
			listErrors.add(erro);
		});
		
		return listErrors;
	}

	/*
	 * Esta exceção trata:
	 * 
	 * Entrada de valores de objetos que já existam na base de dados, por exemplo:
	 * Pode ser informado um email já existente para um usuário cadastrado,
	 *
	 */
	
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public ResponseEntity<StandartError> handlerObjectAlreadyExists(ObjectAlreadyExistsException e, HttpServletRequest request) {
		
		String title = "Integridade de dados";
		
		StandartError error = new StandartError(dateSystem, title, "field.getField()", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	/*
	 * Esta exceção trata:
	 * 
	 * Valores não encontrados na base de dados
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> handlerObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) { 
		
		String title = "Recurso não Encontrado";
		
		StandartError error = new StandartError(dateSystem, title, "Verifique",e.getMessage(), request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandartError> handlerBusinessException(BusinessException business, HttpServletRequest request){
		
		String title = "Regras de Negócio";
		
		StandartError error = new StandartError(dateSystem, title, "Verifique", business.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
