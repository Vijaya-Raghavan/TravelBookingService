package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.Passenger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerDataAccess extends CrudRepository<Passenger, Long> {

	@Query("SELECT p FROM Passenger p WHERE p.travelBooking.id = ?1")
	public Iterable<Passenger> findByBookingId(final Long bookingId);

	@Query("SELECT p FROM Passenger p WHERE p.travelBooking.id IN :bookingIds")
	public Iterable<Passenger> findByBookingIdIn(@Param("bookingIds") final List<Long> bookingIds);

	@Query("SELECT p FROM Passenger p WHERE p.travelBooking.id = ?1 and p.id = ?2")
	public Optional<Passenger> findByBookingIdAndPassengerId(final Long bookingId, Long id);
}
