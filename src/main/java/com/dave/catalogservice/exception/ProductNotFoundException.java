package com.dave.catalogservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
    }
 
    public ProductNotFoundException(String message) {
        super(message);
    }
 
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}
