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
@Table(name = "station")
@SequenceGenerator(name="STATION_ID_SEQUENCE_GENERATOR", sequenceName="station_id_seq", initialValue = 101, allocationSize = 1)
public class Station {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATION_ID_SEQUENCE_GENERATOR")
	private Integer id;

	private String name;

	private String code;

}
