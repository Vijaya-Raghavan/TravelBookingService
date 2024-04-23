package com.cloudbees.traveller.model.request;

import com.cloudbees.traveller.model.Seat;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TravelUpdateRequest {

	private Long bookingId;
	private Map<Long, Seat> passengerSeat;

}
