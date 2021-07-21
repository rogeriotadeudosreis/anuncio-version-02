package com.rogerioreis.anuncio02.controller;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandartError implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime dateSystem;
	private String title;
	private String field;
	private String message;
	private String classPath;

	public StandartError() {
	}

	public StandartError(LocalDateTime dateSystem, String title, String field, String message, String classPath) {
		this.dateSystem = dateSystem;
		this.title = title;
		this.field = field;
		this.message = message;
		this.classPath = classPath;
	}

	public LocalDateTime getDateSystem() {
		return dateSystem;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public String getTitle() {
		return title;
	}
	
	public String getClassPath() {
		return classPath;
	}

}
