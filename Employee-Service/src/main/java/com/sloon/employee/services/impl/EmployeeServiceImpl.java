package com.sloon.employee.services.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sloon.employee.models.Employee;
import com.sloon.employee.models.Role;
import com.sloon.employee.payloads.CommonResponse;
import com.sloon.employee.payloads.EmployeeDTO;
import com.sloon.employee.repo.EmployeeRepository;
import com.sloon.employee.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommonResponse SaveEmployee(EmployeeDTO employeeDTO) {
//		Employee employee=null;
//		Optional<Employee> employeeOptional = employeeRepository.findByEmail(employeeDTO.getEmail());
//		
//		if (!employeeOptional.isEmpty()) {
//			throw new RuntimeException("employee already exist with email");
//		}
//		employee = employeeOptional.get();
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
	    Role role = new Role(101, "EMPLOYEE");
		employee = modelMapper.map(employeeDTO, Employee.class);
		employee.setRole(role);
		employee = employeeRepository.save(employee);
		
		employeeDTO = modelMapper.map(employee, employeeDTO.getClass());
		
		return CommonResponse.builder().data(employee).timeStamp(LocalDate.now()).status(true).build();
	}

	@Override
	public CommonResponse getAllEmployees() {
		List<Employee> allemployees = employeeRepository.findAll();
		List<EmployeeDTO> allemployeeDtos = Arrays.asList(modelMapper.map(allemployees, EmployeeDTO[].class));
		return CommonResponse.builder().data(allemployeeDtos).status(true).timeStamp(LocalDate.now()).build();
	}

	@Override
	public CommonResponse getEmployeeById(Integer id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("No Employee found with id "+id));
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		return CommonResponse.builder().data(employeeDTO).status(true).timeStamp(LocalDate.now()).build();
	}

}
