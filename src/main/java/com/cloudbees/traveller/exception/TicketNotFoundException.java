package com.cloudbees.traveller.exception;

import java.text.MessageFormat;

public class TicketNotFoundException extends TravelApplicationException {

	public TicketNotFoundException(final Long id) {
		super(MessageFormat.format("Ticket not found for number {0}", id));
	}

}
