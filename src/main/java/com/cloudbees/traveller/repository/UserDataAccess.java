package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataAccess extends CrudRepository<User, Long> {

	@Query("SELECT u.password FROM User u WHERE u.email = ?1")
	public Optional<String> getPassword(final String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public Optional<User> findUserByEmail(final String email);

}
