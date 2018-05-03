package br.com.zup.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GastoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 703949687169753379L;

	public GastoNotFoundException(final String exception) {
		super(exception);
	}
}
