package com.cloudbees.traveller.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends TravelApplicationException {

	public UserNotFoundException(final String user) {
		super(MessageFormat.format("User {0} not found", user));
	}
}
