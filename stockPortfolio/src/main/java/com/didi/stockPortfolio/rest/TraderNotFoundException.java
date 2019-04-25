package com.didi.stockPortfolio.rest;

public class TraderNotFoundException extends RuntimeException {

	public TraderNotFoundException() {
	}

	public TraderNotFoundException(String message) {
		super(message);
	}

	public TraderNotFoundException(Throwable cause) {
		super(cause);
	}

	public TraderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TraderNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}