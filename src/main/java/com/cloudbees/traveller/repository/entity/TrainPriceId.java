package com.cloudbees.traveller.repository.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TrainPriceId implements Serializable {

	@ManyToOne
	@JoinColumn(name="train_number", nullable=false)
	private Train train;

	@ManyToOne
	@JoinColumn(name = "coach_id", nullable=false)
	private CoachType coachType;

}
