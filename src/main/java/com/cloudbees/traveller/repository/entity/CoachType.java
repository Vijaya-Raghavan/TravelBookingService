package com.cloudbees.traveller.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coach_type")
@SequenceGenerator(name="COACH_TYPE_ID_SEQUENCE_GENERATOR", sequenceName="coach_type_id_seq", initialValue = 11, allocationSize = 1)
public class CoachType {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COACH_TYPE_ID_SEQUENCE_GENERATOR")
	private Integer id;

	private String type;
}
