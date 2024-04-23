package com.cloudbees.traveller.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class Train {

	private Integer number;
	private String name;
	private Station source;
	private Station destination;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
}
