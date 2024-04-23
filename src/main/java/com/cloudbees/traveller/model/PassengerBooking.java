package com.cloudbees.traveller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerBooking extends Passenger {

	private Long passengerId;
	private Integer coachNumber;
	private Integer seatNumber;
	private String travelStatus;
	private String seatStatus;
}
