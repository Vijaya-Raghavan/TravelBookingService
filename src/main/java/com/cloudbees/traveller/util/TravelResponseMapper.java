package com.cloudbees.traveller.util;

import com.cloudbees.traveller.model.PassengerBooking;
import com.cloudbees.traveller.model.Station;
import com.cloudbees.traveller.model.Train;
import com.cloudbees.traveller.model.TravelBooking;
import com.cloudbees.traveller.model.response.TravelResponse;

import java.util.ArrayList;
import java.util.List;

public class TravelResponseMapper {

	public static TravelResponse getTicketResponse(final com.cloudbees.traveller.repository.entity.TravelBooking travelBookingData) {
		TravelResponse ticketResponse = new TravelResponse();
		TravelBooking travelBookingInfo = new TravelBooking();
		travelBookingInfo.setBookingId(travelBookingData.getId());
		travelBookingInfo.setTravelDate(travelBookingData.getTravelDate());
		travelBookingInfo.setBookingDate(travelBookingData.getBookingDate());
		Station sourceStation = new Station();
		sourceStation.setCode(travelBookingData.getSourceStation().getCode());
		sourceStation.setName(travelBookingData.getSourceStation().getName());
		travelBookingInfo.setFrom(sourceStation);
		Station destinationStation = new Station();
		destinationStation.setCode(travelBookingData.getDestinationStation().getCode());
		destinationStation.setName(travelBookingData.getDestinationStation().getName());
		travelBookingInfo.setTo(destinationStation);
		Train train = new Train();
		Station trainSourceStation = new Station();
		trainSourceStation.setCode(travelBookingData.getTrain().getSource().getCode());
		trainSourceStation.setName(travelBookingData.getTrain().getSource().getName());
		train.setNumber(travelBookingData.getTrain().getNumber());
		train.setSource(trainSourceStation);
		Station trainDestinationStation = new Station();
		trainDestinationStation.setCode(travelBookingData.getTrain().getDestination().getCode());
		trainDestinationStation.setName(travelBookingData.getTrain().getDestination().getName());
		train.setDestination(trainDestinationStation);
		train.setName(travelBookingData.getTrain().getName());
		train.setDepartureTime(travelBookingData.getTrain().getDepartureTime());
		train.setArrivalTime(travelBookingData.getTrain().getArrivalTime());
		travelBookingInfo.setTrain(train);
		travelBookingInfo.setBookingId(travelBookingData.getId());
		travelBookingInfo.setBookingId(travelBookingData.getId());
		travelBookingInfo.setTravelStatue(travelBookingData.getTravelStatus());
		travelBookingInfo.setBookingStatus(travelBookingData.getBookingStatus());
		travelBookingInfo.setTravelFare(travelBookingData.getBookingFare());
		ticketResponse.setTravelBookingInfo(travelBookingInfo);
		Iterable<com.cloudbees.traveller.repository.entity.Passenger> passengersData = travelBookingData.getPassengers();
		List<PassengerBooking> passengerBookingInfos = new ArrayList<>();
		for (com.cloudbees.traveller.repository.entity.Passenger passengerData : passengersData) {
			PassengerBooking passengerBookingInfo = new PassengerBooking();
			passengerBookingInfo.setFirstName(passengerData.getPassengerFirstName());
			passengerBookingInfo.setLastName(passengerData.getPassengerLastName());
			passengerBookingInfo.setAge(passengerData.getPassengerAge());
			passengerBookingInfo.setCoachNumber(passengerData.getTrainCoach().getId());
			passengerBookingInfo.setSeatNumber(passengerData.getSeatNumber());
			passengerBookingInfo.setTravelStatus(passengerData.getTravelStatus());
			passengerBookingInfo.setSeatStatus(passengerData.getSeatStatus());
			passengerBookingInfo.setPassengerId(passengerData.getId());
			passengerBookingInfos.add(passengerBookingInfo);
		}
		ticketResponse.setPassengersBookingInfo(passengerBookingInfos);
		return ticketResponse;
	}
}
