package com.cloudbees.traveller.exception;

import java.text.MessageFormat;

public class PassengerNotFoundException extends TravelApplicationException {

	public PassengerNotFoundException(final Long id) {
		super(MessageFormat.format("Passenger not found for id {0}", id));
	}
}
