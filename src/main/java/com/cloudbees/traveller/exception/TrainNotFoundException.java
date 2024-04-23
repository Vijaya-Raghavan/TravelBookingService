package com.cloudbees.traveller.exception;

import java.text.MessageFormat;

public class TrainNotFoundException extends TravelApplicationException {

	public TrainNotFoundException(final Integer trainNumber) {
		super(MessageFormat.format("Train not found for number {0}", trainNumber));
	}

}
