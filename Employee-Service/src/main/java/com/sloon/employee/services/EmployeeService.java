package com.sloon.employee.services;

import com.sloon.employee.payloads.CommonResponse;
import com.sloon.employee.payloads.EmployeeDTO;

public interface EmployeeService {

	CommonResponse SaveEmployee(EmployeeDTO employeeDTO);
	
	CommonResponse getAllEmployees();
	
	CommonResponse getEmployeeById(Integer id);
}
