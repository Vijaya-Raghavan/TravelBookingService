package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.TrainPrice;
import com.cloudbees.traveller.repository.entity.TrainPriceId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainPriceDataAccess  extends CrudRepository<TrainPrice, TrainPriceId>  {

	@Query("SELECT tp FROM TrainPrice tp WHERE tp.trainPriceId.train.number = ?1")
	public Iterable<TrainPrice> findByTrainNumber(final Integer trainNumber);

	@Query("SELECT tp FROM TrainPrice tp WHERE tp.trainPriceId.train.number = ?1 and tp.trainPriceId.coachType.id = ?2")
	public Optional<TrainPrice> findByTrainNumberAndCoachId(final Integer trainNumber, final Integer coachId);

}
