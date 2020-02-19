package com.corneliouzbett.mpesasdk.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 2516935680980388130L;

	public AuthenticationException(final String message) {
		super(message);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
