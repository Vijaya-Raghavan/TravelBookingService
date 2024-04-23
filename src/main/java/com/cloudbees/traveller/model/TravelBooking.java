package com.cloudbees.traveller.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TravelBooking extends Travel {

	private Long bookingId;
	private LocalDateTime bookingDate;
	private String bookingStatus;
	private String travelStatue;
	private BigDecimal travelFare;
}
