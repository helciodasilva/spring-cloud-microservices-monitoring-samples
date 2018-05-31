package com.helciodasilva.employee.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.helciodasilva.employee.client.model.Employee;
import com.helciodasilva.employee.client.restclient.EmployeeRestClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRestClient employeeRestClient;

	@GetMapping(value = "/employees/{employeeId}")
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public ResponseEntity<Employee> getStudents(@PathVariable("employeeId") Long employeeId) {
		Employee employee = employeeRestClient.findById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(employee);

	}

	public ResponseEntity<Employee> fallbackMethod(Long employeeid) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
	}

}
