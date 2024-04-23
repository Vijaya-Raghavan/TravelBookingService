package com.cloudbees.traveller.exception;

public class TravelApplicationException extends Exception {

	public TravelApplicationException(final String message) {
		super(message);
	}

	public TravelApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TravelApplicationException(final Throwable cause) {
		super(cause);
	}
}
