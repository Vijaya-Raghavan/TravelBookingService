package com.cloudbees.traveller.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Travel {
	private Train train;
	private LocalDate travelDate;
	private Station from;
	private Station to;
}
