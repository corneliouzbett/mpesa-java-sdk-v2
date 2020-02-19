package com.corneliouzbett.mpesasdk.exception;

public class MpesaException extends RuntimeException {

	private static final long serialVersionUID = 2516935680980388130L;

	public MpesaException(final String message) {
		super(message);
	}

	public MpesaException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
