package com.cloudbees.traveller.exception;

public class UserCredentialsInvalidException extends TravelApplicationException {

	public UserCredentialsInvalidException() {
		super("Invalid user credentials");
	}

}
