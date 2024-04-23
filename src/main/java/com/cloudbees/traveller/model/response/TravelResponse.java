package com.cloudbees.traveller.model.response;

import com.cloudbees.traveller.model.PassengerBooking;
import com.cloudbees.traveller.model.TravelBooking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TravelResponse {

	private TravelBooking travelBookingInfo;
	private List<PassengerBooking> passengersBookingInfo;
}
