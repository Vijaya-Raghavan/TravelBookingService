package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationDataAccess extends CrudRepository<Station, Integer> {

	@Query("SELECT s FROM Station s WHERE s.code = ?1")
	public Optional<Station> findByStationCode(final String code);
}
