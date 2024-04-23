package com.cloudbees.traveller.model.request;

import com.cloudbees.traveller.model.Passenger;
import com.cloudbees.traveller.model.Travel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TravelRequest {

	private Travel travelInfo;
	private List<Passenger> passengersInfo;
}
