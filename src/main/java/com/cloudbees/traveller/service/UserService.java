package com.cloudbees.traveller.service;

import com.cloudbees.traveller.exception.TravelApplicationException;
import com.cloudbees.traveller.exception.UserCredentialsInvalidException;
import com.cloudbees.traveller.exception.UserNotFoundException;
import com.cloudbees.traveller.model.User;
import com.cloudbees.traveller.repository.UserDataAccess;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserDataAccess userDataAccess;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUser(final String email) throws TravelApplicationException {
		User user = null;
		if (StringUtils.isNotBlank(email)) {
			Optional<com.cloudbees.traveller.repository.entity.User> userData = userDataAccess.findUserByEmail(email.trim());
			if (userData.isPresent()) {
				user = new User();
				com.cloudbees.traveller.repository.entity.User userInfo = userData.get();
				user.setId(userInfo.getId());
				user.setEmail(userInfo.getEmail());
				user.setFirstName(userInfo.getFirstName());
				user.setLastName(userInfo.getLastName());
				user.setGender(userInfo.getGender());
				user.setPhone(userInfo.getPhone());
			} else {

			}
		} else {
			throw new TravelApplicationException("Valid username is required");
		}
		return user;
	}


	public String getPassword(final String email) throws TravelApplicationException {
		String password = null;
		if (StringUtils.isNotBlank(email)) {
			Optional<String> passwordData = userDataAccess.getPassword(email.trim());
			if (passwordData.isPresent()) {
				password = passwordData.get();
			} else {
				throw new UserNotFoundException(email);
			}
		} else {
			throw new TravelApplicationException("Valid username is required");
		}
		return password;
	}

	public void validate(final String auth) throws TravelApplicationException {
		String pair=new String(Base64.decodeBase64(auth.substring(6)));
		String userName=pair.split(":")[0];
		String password=pair.split(":")[1];
		String actualPassword = getPassword(userName);
		if (!passwordEncoder.matches(password, actualPassword)) {
			throw new UserCredentialsInvalidException();
		}
	}

	public String getUserNameFromAuth(final String auth) {
		String pair = getDecodedAuth(auth);
		return pair.split(":")[0];
	}

	public String getPasswordFromAuth(final String auth) {
		String pair = getDecodedAuth(auth);
		return pair.split(":")[1];
	}

	private String getDecodedAuth(final String auth) {
		return new String(Base64.decodeBase64(auth.substring(6)));
	}
}
