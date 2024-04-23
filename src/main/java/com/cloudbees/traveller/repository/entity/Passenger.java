package com.cloudbees.traveller.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passenger", uniqueConstraints = {@UniqueConstraint(columnNames = { "booking_id", "coach_id", "seat_number" }) })
@Getter
@Setter
@SequenceGenerator(name="PASSENGER_ID_SEQUENCE_GENERATOR", sequenceName="passenger_id_seq", initialValue = 1, allocationSize = 1)
public class Passenger {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PASSENGER_ID_SEQUENCE_GENERATOR")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "booking_id", referencedColumnName = "id", nullable=false)
	private TravelBooking travelBooking;

	@ManyToOne
	@JoinColumn(name = "coach_id", referencedColumnName = "id", nullable=false)
	private TrainCoach trainCoach;

	@Column(name = "seat_number")
	private Integer seatNumber;

	@Column(name = "passenger_first_name")
	private String passengerFirstName;

	@Column(name = "passenger_last_name")
	private String passengerLastName;

	@Column(name = "passenger_age")
	private Integer passengerAge;

	@Column(name = "travel_status")
	private String travelStatus;

	@Column(name = "seat_status")
	private String seatStatus;

}
