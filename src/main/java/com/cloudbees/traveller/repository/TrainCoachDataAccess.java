package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.TrainCoach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCoachDataAccess extends CrudRepository<TrainCoach, Integer> {

	@Query("SELECT tc FROM TrainCoach tc WHERE tc.train.number = ?1")
	public Iterable<TrainCoach> findAllByTrainNumber(final Integer trainNumber);
}