package com.cloudbees.traveller.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Seat {

	private Integer coachNumber;
	private Integer seatNumber;
}
