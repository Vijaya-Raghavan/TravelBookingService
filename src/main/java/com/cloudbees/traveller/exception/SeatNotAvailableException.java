package com.cloudbees.traveller.exception;

import java.text.MessageFormat;

public class SeatNotAvailableException extends TravelApplicationException {

	public SeatNotAvailableException(final Integer coachNumber, final Integer seatNumber) {
		super(MessageFormat.format("Seat number {0} not available in coach {1}", seatNumber, coachNumber));
	}
}