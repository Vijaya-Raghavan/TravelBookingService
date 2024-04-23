package com.cloudbees.traveller.controller;

import com.cloudbees.traveller.exception.TicketNotFoundException;
import com.cloudbees.traveller.model.Seat;
import com.cloudbees.traveller.model.request.TravelRequest;
import com.cloudbees.traveller.model.request.TravelUpdateRequest;
import com.cloudbees.traveller.model.response.TravelResponse;
import com.cloudbees.traveller.service.TravelService;
import com.cloudbees.traveller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

	@Autowired
	private TravelService ticketService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Object> getTickets(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth) {
		ResponseEntity<Object> response;
		try {
			userService.validate(auth);
			String user = userService.getUserNameFromAuth(auth);
			response = ResponseEntity.ok(ticketService.getAllTickets(user));
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@GetMapping("/{booking_id}")
	public ResponseEntity<Object> getTickets(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth, @PathVariable("booking_id") final Long bookingId) {
		ResponseEntity<Object> response;
		try {
			userService.validate(auth);
			String user = userService.getUserNameFromAuth(auth);
			response = ResponseEntity.ok(ticketService.getTicket(user, bookingId));
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@PostMapping
	public ResponseEntity<Object> bookTicket(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth, @RequestBody final TravelRequest ticketRequest) {
		ResponseEntity<Object> response;
		try {
			userService.validate(auth);
			String user = userService.getUserNameFromAuth(auth);
			TravelResponse travelResponse = ticketService.bookTicket(user, ticketRequest);
			response = ResponseEntity.status(HttpStatus.CREATED).body(travelResponse);
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@PutMapping
	public ResponseEntity<Object> updateTicket(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth, @RequestBody final TravelUpdateRequest travelUpdateRequest) {
		ResponseEntity<Object> response;
		try {
			userService.validate(auth);
			String user = userService.getUserNameFromAuth(auth);
			response = ResponseEntity.ok(ticketService.updateTicket(user, travelUpdateRequest));
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@DeleteMapping("/{booking_id}")
	public ResponseEntity<Object> cancelTicket(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth, @PathVariable("booking_id") final Long bookingId) {
		ResponseEntity<Object> response;
		try {
			userService.validate(auth);
			String user = userService.getUserNameFromAuth(auth);
			response = ResponseEntity.ok(ticketService.cancelTicket(user, bookingId));
		} catch (TicketNotFoundException e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@GetMapping("/availability")
	public List<Seat> getAvailability(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth, @RequestBody final TravelRequest ticketRequest) {
		return ticketService.getAllAvailableSeats(ticketRequest);
	}

}
