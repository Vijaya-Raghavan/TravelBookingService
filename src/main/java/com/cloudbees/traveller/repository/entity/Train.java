package com.cloudbees.traveller.repository.entity;

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

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "train")
@SequenceGenerator(name="TRAIN_NUMBER_SEQUENCE_GENERATOR", sequenceName="train_number_seq", initialValue = 60001, allocationSize = 1)
public class Train {

	@Id
	@Column(name = "number")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAIN_NUMBER_SEQUENCE_GENERATOR")
	private Integer number;
	private String name;

	@ManyToOne
	@JoinColumn(name = "source_station", referencedColumnName = "id")
	private Station source;

	@ManyToOne
	@JoinColumn(name = "destination_station")
	private Station destination;

	@Column(name = "source_departure_time")
	private LocalTime departureTime;

	@Column(name = "destination_arrival_time")
	private LocalTime arrivalTime;

	@OneToMany(mappedBy = "train")
	private List<TrainCoach> coaches;
}
