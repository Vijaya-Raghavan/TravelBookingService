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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "train_coach")
@SequenceGenerator(name="TRAIN_COACH_ID_SEQUENCE_GENERATOR", sequenceName="train_coach_id_seq", initialValue = 1001, allocationSize = 1)
public class TrainCoach {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAIN_COACH_ID_SEQUENCE_GENERATOR")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "train_number", referencedColumnName = "number", nullable=false)
	private Train train;

	@Column(name = "coach_name", nullable=false)
	private String coachName;

	@Column(name = "coach_position", nullable=false)
	private Integer coachPosition;

	@Column(name = "total_seats", nullable=false)
	private Integer totalSeats;

	@ManyToOne
	@JoinColumn(name = "coach_id", referencedColumnName = "id")
	private CoachType coachType;
}
