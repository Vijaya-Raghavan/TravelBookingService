package com.cloudbees.traveller.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "travel_booking")
@Getter
@Setter
@SequenceGenerator(name="TRAVEL_BOOKING_ID_SEQUENCE_GENERATOR", sequenceName="travel_booking_id_seq", initialValue = 500001, allocationSize = 1)
public class TravelBooking {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAVEL_BOOKING_ID_SEQUENCE_GENERATOR")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "train_number")
	private Train train;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "source_station")
	private Station sourceStation;

	@ManyToOne
	@JoinColumn(name = "destination_station")
	private Station destinationStation;

	@Column(name ="travel_date")
	private LocalDate travelDate;

	@Column(name ="booking_date")
	private LocalDateTime bookingDate;

	@Column(name ="booking_fare")
	private BigDecimal bookingFare;

	@Column(name ="booking_status")
	private String bookingStatus;

	@Column(name ="travel_status")
	private String travelStatus;

	@OneToMany(mappedBy = "travelBooking", cascade = CascadeType.PERSIST)
	private Set<Passenger> passengers;

}
