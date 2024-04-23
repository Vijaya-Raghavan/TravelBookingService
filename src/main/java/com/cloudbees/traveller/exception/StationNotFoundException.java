package com.cloudbees.traveller.exception;

import java.text.MessageFormat;

public class StationNotFoundException extends TravelApplicationException {

	public StationNotFoundException(final String stationCode) {
		super(MessageFormat.format("Station found for code {0}", stationCode));
	}
}
