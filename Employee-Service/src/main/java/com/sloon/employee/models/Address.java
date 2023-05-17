package com.sloon.employee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {

	@Id
	private Integer aid;
	
	private String localStreet;
	
	private String city;
	
	private String state;
}
