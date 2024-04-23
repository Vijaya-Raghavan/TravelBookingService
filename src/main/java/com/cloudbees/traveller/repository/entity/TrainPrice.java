package com.cloudbees.traveller.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "train_price")
@Getter
@Setter
public class TrainPrice {

	@EmbeddedId
	private TrainPriceId trainPriceId;

	@Column(name = "price")
	private BigDecimal price;
}
