package com.cloudbees.traveller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phone;
}
