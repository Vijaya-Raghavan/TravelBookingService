package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.TravelBooking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TravelBookingDataAccess extends CrudRepository<TravelBooking, Long> {

	@Query("SELECT t FROM TravelBooking t WHERE t.user.id = ?1")
	public Iterable<TravelBooking> findByUserId(final Long id);

	@Query("SELECT t FROM TravelBooking t WHERE t.id = ?1 and t.user.id = ?2")
	public Optional<TravelBooking> findByBookingIdAndUserId(final Long bookingId, final Long userId);

	@Query("SELECT t FROM TravelBooking t WHERE t.train.number = ?1 and t.travelDate = ?2")
	public Iterable<TravelBooking> findByTrainNumberAndDate(final Integer trainNumber, final LocalDate localDate);

}
