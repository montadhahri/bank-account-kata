package com.montadhr.bankkata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 7428279561375909688L;

	public NotAllowedException(String message) {
		super(message);
	}
}