package com.sloon.employee.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer empId;
	
	@JsonProperty(namespace = "first_name")
	private String fname;
	
	@JsonProperty(namespace = "last_name")
	private String lname;
	
	private String email;
	
	@JsonProperty(namespace = "mobile_number")
	private String mobileNo;
	
	@JsonProperty(namespace = "date_of_birth")
	private LocalDate dob;
	
	private String gender;
	
	@JsonProperty(namespace = "date_of_joining")
	private LocalDate dateOfJoining;
	
	@JsonProperty(namespace = "last_working_day")
	private LocalDate lastWorkingdate;
	
	private String pancard;
	
	private String aadharcard;
	
	@OneToOne(cascade = {CascadeType.ALL, CascadeType.ALL}, fetch = FetchType.EAGER)
	private Role role;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;
	
//	private String familyDetails;
	
	private String photo;
	
//	private String attendance;
	
	
}
