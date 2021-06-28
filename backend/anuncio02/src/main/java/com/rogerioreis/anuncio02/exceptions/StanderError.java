package com.rogerioreis.anuncio02.exceptions;

import java.io.Serializable;

public class StanderError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StanderError() {
		
	}

	public StanderError(Long timesTamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timesTamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimesTamp() {
		return timestamp;
	}

	public void setTimesTamp(Long timesTamp) {
		this.timestamp = timesTamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
