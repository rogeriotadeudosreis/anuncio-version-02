package com.rogerioreis.anuncio02.controller;

import java.io.Serializable;
import java.util.Date;

public class StandarError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private Integer status;
	private String message;
	private String path;
	private Date timestamp;
	
	public StandarError() {
		
	}

	public StandarError(Date timesTamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timesTamp;
		this.status = status;
		this.title = error;
		this.message = message;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
	


	
	
	
	

}
