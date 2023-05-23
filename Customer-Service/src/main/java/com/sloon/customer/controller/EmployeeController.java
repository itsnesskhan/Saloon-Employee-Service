package com.sloon.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sloon.customer.payloads.CommonResponse;
import com.sloon.customer.payloads.CustomerDTO;
import com.sloon.customer.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class EmployeeController {

	@Autowired
	private CustomerService customerService;

	public EmployeeController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/save")
	ResponseEntity<CommonResponse> createEmployee(@RequestBody CustomerDTO customerDTO) {
		CommonResponse apiResponse = customerService.addCustomer(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@GetMapping("/get-all")
	ResponseEntity<CommonResponse> getAllEmployee() {
		CommonResponse apiResponse = customerService.getAllCustomes();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/get/{id}")
	ResponseEntity<CommonResponse> getEmployeeById(@PathVariable Integer id) {
		CommonResponse apiResponse = customerService.getCustomerById(id);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
