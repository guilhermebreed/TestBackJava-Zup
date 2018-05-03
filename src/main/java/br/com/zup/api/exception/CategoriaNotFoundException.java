package br.com.zup.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoriaNotFoundException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7719771588247197559L;

	public CategoriaNotFoundException(final String exception) {
		super(exception);
	}
}
