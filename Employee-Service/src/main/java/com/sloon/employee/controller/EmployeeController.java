package com.sloon.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sloon.employee.payloads.CommonResponse;
import com.sloon.employee.payloads.EmployeeDTO;
import com.sloon.employee.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


	@Autowired
	private EmployeeService employeeService;
	

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping("/save")
	ResponseEntity<CommonResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		CommonResponse apiResponse = employeeService.SaveEmployee(employeeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@GetMapping("/get-all")
	ResponseEntity<CommonResponse> getAllEmployee() {
	  CommonResponse apiResponse = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@GetMapping("/get/{id}")
	ResponseEntity<CommonResponse> getEmployeeById(@PathVariable Integer id) {
	  CommonResponse apiResponse = employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
