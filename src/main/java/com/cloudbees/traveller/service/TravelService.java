package com.cloudbees.traveller.service;

import com.cloudbees.traveller.exception.PassengerNotFoundException;
import com.cloudbees.traveller.exception.SeatNotAvailableException;
import com.cloudbees.traveller.exception.TicketNotFoundException;
import com.cloudbees.traveller.exception.TrainNotFoundException;
import com.cloudbees.traveller.exception.TravelApplicationException;
import com.cloudbees.traveller.model.Passenger;
import com.cloudbees.traveller.model.Seat;
import com.cloudbees.traveller.model.request.TravelRequest;
import com.cloudbees.traveller.model.request.TravelUpdateRequest;
import com.cloudbees.traveller.model.response.TravelResponse;
import com.cloudbees.traveller.repository.PassengerDataAccess;
import com.cloudbees.traveller.repository.StationDataAccess;
import com.cloudbees.traveller.repository.TrainCoachDataAccess;
import com.cloudbees.traveller.repository.TrainDataAccess;
import com.cloudbees.traveller.repository.TrainPriceDataAccess;
import com.cloudbees.traveller.repository.TravelBookingDataAccess;
import com.cloudbees.traveller.repository.UserDataAccess;
import com.cloudbees.traveller.repository.entity.TrainCoach;
import com.cloudbees.traveller.repository.entity.User;
import com.cloudbees.traveller.util.TravelResponseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class TravelService {

	@Autowired
	private TravelBookingDataAccess travelBookingDataAccess;

	@Autowired
	private PassengerDataAccess passengerDataAccess;

	@Autowired
	private UserDataAccess userDataAccess;

	@Autowired
	private TrainCoachDataAccess trainCoachDataAccess;

	@Autowired
	private TrainPriceDataAccess trainPriceDataAccess;

	@Autowired
	private StationDataAccess stationDataAccess;

	@Autowired
	private TrainDataAccess trainDataAccess;


	public List<TravelResponse> getAllTickets(final String email) {
		List<TravelResponse> ticketResponses = null;
		Optional<User> userData = userDataAccess.findUserByEmail(email);
		Iterable<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingsData = travelBookingDataAccess.findByUserId(userData.get().getId());
		if (travelBookingsData != null) {
			Iterator<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingIterator = travelBookingsData.iterator();
			ticketResponses = new ArrayList<>();
			while (travelBookingIterator.hasNext()) {
				com.cloudbees.traveller.repository.entity.TravelBooking travelBookingData = travelBookingIterator.next();
				TravelResponse ticketResponse = TravelResponseMapper.getTicketResponse(travelBookingData);
				ticketResponses.add(ticketResponse);
			}
		}
		return ticketResponses;
	}

	public TravelResponse getTicket(final String email, final Long bookingId) throws TravelApplicationException {
		try {
			TravelResponse ticketResponse = null;
			Optional<User> userData = userDataAccess.findUserByEmail(email);
			Optional<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingsData = travelBookingDataAccess.findByBookingIdAndUserId(bookingId, userData.get().getId());
			if (travelBookingsData.isPresent()) {
				com.cloudbees.traveller.repository.entity.TravelBooking travelBookingInfo = travelBookingsData.get();
				ticketResponse = TravelResponseMapper.getTicketResponse(travelBookingInfo);
			} else {
				throw new TicketNotFoundException(bookingId);
			}
			return ticketResponse;
		} catch (Exception e) {
			throw new TravelApplicationException(e.getMessage(), e.getCause());
		}
	}

	public TravelResponse bookTicket(final String email, @RequestBody final TravelRequest ticketRequest) throws TravelApplicationException {
		Optional<com.cloudbees.traveller.repository.entity.User> user = userDataAccess.findUserByEmail(email);
		com.cloudbees.traveller.repository.entity.TravelBooking newTravelBooking = new com.cloudbees.traveller.repository.entity.TravelBooking();
		newTravelBooking.setUser(user.get());
		Optional<com.cloudbees.traveller.repository.entity.Station> fromStation = stationDataAccess.findByStationCode(ticketRequest.getTravelInfo().getFrom().getCode());
		if (!fromStation.isPresent()) {
			throw new TravelApplicationException(MessageFormat.format("Source Station {0} Not Found.", ticketRequest.getTravelInfo().getFrom()));
		}
		Optional<com.cloudbees.traveller.repository.entity.Station> toStation = stationDataAccess.findByStationCode(ticketRequest.getTravelInfo().getTo().getCode());
		if (!toStation.isPresent()) {
			throw new TravelApplicationException(MessageFormat.format("Destination Station {0} Not Found.", ticketRequest.getTravelInfo().getTo()));
		}
		newTravelBooking.setSourceStation(fromStation.get());
		newTravelBooking.setDestinationStation(toStation.get());
		newTravelBooking.setTravelDate(ticketRequest.getTravelInfo().getTravelDate());
		Optional<com.cloudbees.traveller.repository.entity.Train> trainData = trainDataAccess.findById(ticketRequest.getTravelInfo().getTrain().getNumber());
		if (!trainData.isPresent()) {
			throw new TrainNotFoundException(ticketRequest.getTravelInfo().getTrain().getNumber());
		}
		newTravelBooking.setTrain(trainData.get());
		newTravelBooking.setBookingDate(LocalDateTime.now(ZoneOffset.UTC));
		newTravelBooking.setBookingStatus("BOOKING_CONFIRMED");
		newTravelBooking.setTravelStatus("PENDING_TRAVEL");
		List<Seat> seats = getSeatsByPassengerCount(ticketRequest);
		newTravelBooking.setBookingFare(getPrice(ticketRequest, seats));

		int index = 0;
		Set<com.cloudbees.traveller.repository.entity.Passenger> newPassengers = new HashSet<>();
		for (Passenger passenger: ticketRequest.getPassengersInfo()) {
			com.cloudbees.traveller.repository.entity.Passenger passengerData = new com.cloudbees.traveller.repository.entity.Passenger();
			passengerData.setPassengerFirstName(passenger.getFirstName());
			passengerData.setPassengerLastName(passenger.getLastName());
			passengerData.setPassengerAge(passenger.getAge());
			Seat seat = seats.get(index);
			Optional<com.cloudbees.traveller.repository.entity.TrainCoach> trainCoachData = trainCoachDataAccess.findById(seat.getCoachNumber());
			passengerData.setTravelBooking(newTravelBooking);
			passengerData.setSeatNumber(seat.getSeatNumber());
			passengerData.setTrainCoach(trainCoachData.get());
			index++;
			passengerData.setTravelStatus("PENDING_TRAVEL");
			passengerData.setSeatStatus("SEAT_CONFIRMED");
			newPassengers.add(passengerData);
		}
		newTravelBooking.setPassengers(newPassengers);
		newTravelBooking = travelBookingDataAccess.save(newTravelBooking);

		TravelResponse ticketResponse = TravelResponseMapper.getTicketResponse(newTravelBooking);
		return ticketResponse;
	}

	public TravelResponse cancelTicket(final String email, final Long bookingId) throws TravelApplicationException {
		try {
			TravelResponse ticketResponse = null;
			Optional<User> userData = userDataAccess.findUserByEmail(email);
			Optional<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingsData = travelBookingDataAccess.findByBookingIdAndUserId(bookingId, userData.get().getId());
			if (travelBookingsData.isPresent()) {
				com.cloudbees.traveller.repository.entity.TravelBooking travelBookingInfo = travelBookingsData.get();
				travelBookingInfo.setTravelStatus("CANCELLED");
				travelBookingInfo.setBookingStatus("CANCELLED");
				Iterable<com.cloudbees.traveller.repository.entity.Passenger> passengersData = travelBookingsData.get().getPassengers();
				for (com.cloudbees.traveller.repository.entity.Passenger passengerData : passengersData) {
					passengerData.setTravelStatus("CANCELLED");
					passengerData.setSeatStatus("CANCELLED");
				}
				travelBookingDataAccess.save(travelBookingInfo);
				ticketResponse = TravelResponseMapper.getTicketResponse(travelBookingInfo);
			} else {
				throw new TicketNotFoundException(bookingId);
			}
			return ticketResponse;
		} catch (Exception e) {
			throw new TravelApplicationException(e.getMessage(), e.getCause());
		}
	}

	public TravelResponse updateTicket(final String email, @RequestBody final TravelUpdateRequest travelUpdateRequest) throws TravelApplicationException {
		try {
			TravelResponse ticketResponse = null;
			Optional<User> userData = userDataAccess.findUserByEmail(email);
			Optional<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingsData = travelBookingDataAccess.findByBookingIdAndUserId(travelUpdateRequest.getBookingId(), userData.get().getId());
			if (travelBookingsData.isPresent()) {
				com.cloudbees.traveller.repository.entity.TravelBooking travelBookingInfo = travelBookingsData.get();

				List<Seat> availableSeats = getAllAvailableSeats(travelBookingInfo.getTrain().getNumber(), travelBookingInfo.getTravelDate());
				List<com.cloudbees.traveller.repository.entity.Passenger> passengersData = new ArrayList<>();
				for (Map.Entry<Long, Seat> passengerIdSeat : travelUpdateRequest.getPassengerSeat().entrySet()) {
					Long passengerId = passengerIdSeat.getKey();
					Seat passengerSeat = passengerIdSeat.getValue();
					Optional<com.cloudbees.traveller.repository.entity.Passenger> passenger = passengerDataAccess.findByBookingIdAndPassengerId(travelUpdateRequest.getBookingId(), passengerId);
					if (passenger.isPresent()) {
						com.cloudbees.traveller.repository.entity.Passenger passengerData = passenger.get();
						if (availableSeats.contains(passengerSeat)) {
							passengerData.setSeatNumber(passengerSeat.getSeatNumber());
							Optional<TrainCoach> coach = trainCoachDataAccess.findById(passengerSeat.getCoachNumber());
							passengerData.setTrainCoach(coach.get());
							passengerData.setSeatStatus("SEAT_CHANGE");
						} else {
							throw new SeatNotAvailableException(passengerSeat.getCoachNumber(), passengerSeat.getSeatNumber());
						}
						passengersData.add(passengerData);
					} else {
						throw new PassengerNotFoundException(passengerId);
					}
				}
				if (CollectionUtils.isNotEmpty(passengersData)) {
					passengerDataAccess.saveAll(passengersData);
					travelBookingsData = travelBookingDataAccess.findByBookingIdAndUserId(travelUpdateRequest.getBookingId(), userData.get().getId());
				}
				ticketResponse = TravelResponseMapper.getTicketResponse(travelBookingsData.get());
			} else {
				throw new TicketNotFoundException(travelUpdateRequest.getBookingId());
			}
			return ticketResponse;
		} catch (Exception e) {
			throw new TravelApplicationException(e.getMessage(), e.getCause());
		}
	}

	public List<Seat> getAllAvailableSeats(final TravelRequest ticketRequest) {
		return getAllAvailableSeats(ticketRequest.getTravelInfo().getTrain().getNumber(), ticketRequest.getTravelInfo().getTravelDate());
	}

	private List<Seat> getAllAvailableSeats(final Integer trainNumber, LocalDate travelDate) {
		List<Seat> totalSeats = null;
		Iterable<com.cloudbees.traveller.repository.entity.TrainCoach> trainCoachDatas = trainCoachDataAccess.findAllByTrainNumber(trainNumber);
		if (trainCoachDatas != null) {
			totalSeats = new ArrayList<>();
			Iterator<com.cloudbees.traveller.repository.entity.TrainCoach> trainCoachIterator = trainCoachDatas.iterator();
			while (trainCoachIterator.hasNext()) {
				com.cloudbees.traveller.repository.entity.TrainCoach trainCoachData = trainCoachIterator.next();
				for (int i=0; i<trainCoachData.getTotalSeats(); i++) {
					Seat seat = new Seat();
					seat.setCoachNumber(trainCoachData.getId());
					seat.setSeatNumber(i+1);
					totalSeats.add(seat);
				}
			}
		}
		Iterable<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingDatas = travelBookingDataAccess.findByTrainNumberAndDate(trainNumber, travelDate);
		if (travelBookingDatas != null) {
			List<Seat> bookedSeats = new ArrayList<>();
			Iterator<com.cloudbees.traveller.repository.entity.TravelBooking> travelBookingIterator = travelBookingDatas.iterator();
			while (travelBookingIterator.hasNext()) {
				com.cloudbees.traveller.repository.entity.TravelBooking travelBookingData = travelBookingIterator.next();
				Iterable<com.cloudbees.traveller.repository.entity.Passenger> passengerDatas = passengerDataAccess.findByBookingId(travelBookingData.getId());
				if (passengerDatas != null) {
					Iterator<com.cloudbees.traveller.repository.entity.Passenger> passengerDataIterator = passengerDatas.iterator();
					while (passengerDataIterator.hasNext()) {
						com.cloudbees.traveller.repository.entity.Passenger passengerData = passengerDataIterator.next();
						Seat seat = new Seat();
						seat.setCoachNumber(passengerData.getTrainCoach().getId());
						seat.setSeatNumber(passengerData.getSeatNumber());
						bookedSeats.add(seat);
					}
				}
			}
			totalSeats.removeAll(bookedSeats);
		}
		return totalSeats;
	}

	public BigDecimal getPrice(final TravelRequest ticketRequest) throws TravelApplicationException {
		List<Seat> seats = getSeatsByPassengerCount(ticketRequest);
		return getPrice(ticketRequest, seats);
	}

	private List<Seat> getSeatsByPassengerCount(final TravelRequest ticketRequest) throws TravelApplicationException {
		List<Seat> seats = getAllAvailableSeats(ticketRequest);
		if (CollectionUtils.isNotEmpty(seats) && seats.size() > ticketRequest.getPassengersInfo().size()) {
			seats.removeAll(seats.subList(ticketRequest.getPassengersInfo().size(), seats.size()));
		} else {
			throw new TravelApplicationException("Seats not available");
		}
		return seats;
	}

	private BigDecimal getPrice(final TravelRequest ticketRequest, List<Seat> seats) {
		BigDecimal price = new BigDecimal(0);
		for (Seat seat: seats) {
			Optional<com.cloudbees.traveller.repository.entity.TrainCoach> trainCoachData = trainCoachDataAccess.findById(seat.getCoachNumber());
			Optional<com.cloudbees.traveller.repository.entity.TrainPrice> trainPriceData = trainPriceDataAccess.findByTrainNumberAndCoachId(ticketRequest.getTravelInfo().getTrain().getNumber(), trainCoachData.get().getCoachType().getId());
			if (trainPriceData.isPresent()) {
				price = price.add(trainPriceData.get().getPrice());
			}
		}
		price.setScale(2, RoundingMode.HALF_UP);
		return price;
	}
}
