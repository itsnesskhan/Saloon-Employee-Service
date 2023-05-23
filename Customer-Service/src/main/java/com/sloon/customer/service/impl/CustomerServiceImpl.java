package com.sloon.customer.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sloon.customer.dao.CustomerRepository;
import com.sloon.customer.entity.Customer;
import com.sloon.customer.payloads.CommonResponse;
import com.sloon.customer.payloads.CustomerDTO;
import com.sloon.customer.payloads.EmployeeDTO;
import com.sloon.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public CommonResponse addCustomer(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		customer = customerRepository.save(customer);
		return CommonResponse.builder().data(modelMapper.map(customer, CustomerDTO.class)).timeStamp(LocalDate.now())
				.status(true).build();
	}

	@Override
	public CommonResponse getAllCustomes() {
		List<Customer> all = customerRepository.findAll();
	   List<CustomerDTO> allCustomerDto = all.stream().map(c-> modelMapper.map(c,CustomerDTO.class)).collect(Collectors.toList());
		HttpEntity<Object> httpEntity = new HttpEntity<>(null);
	   ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange("http://localhost:8080/api/v1/employee/get-all", HttpMethod.GET,httpEntity ,CommonResponse.class);
	   CommonResponse commonResponse = responseEntity.getBody();
	   List data = (List) commonResponse.getData();
	   
		List<EmployeeDTO> employeeDtos = (List<EmployeeDTO>) data.stream().map(t -> {
			return  objectMapper.convertValue(t, EmployeeDTO.class);
		}).collect(Collectors.toList());
	   
	   for (EmployeeDTO employeeDTO : employeeDtos) {
		 for (CustomerDTO customerDTO : allCustomerDto) {
			if (customerDTO.getEmployee().equals(employeeDTO.getEmpId())) {
				customerDTO.setEmployeeDTO(employeeDTO);
			}
		}
	}
	   return CommonResponse.builder().data(allCustomerDto).timeStamp(LocalDate.now()).status(true).build();
	}

	@Override
	public CommonResponse getCustomerById(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not foud with id"+id));
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange("http://localhost:8080/api/v1/employee/get/"+id, HttpMethod.GET, new HttpEntity<>(null), CommonResponse.class);
		CommonResponse commonResponse = responseEntity.getBody();
		
		EmployeeDTO employeeDTO = objectMapper.convertValue(commonResponse.getData(), EmployeeDTO.class);
		
		return CommonResponse.builder().data(employeeDTO).timeStamp(LocalDate.now()).status(true).build();
		
	}

}
