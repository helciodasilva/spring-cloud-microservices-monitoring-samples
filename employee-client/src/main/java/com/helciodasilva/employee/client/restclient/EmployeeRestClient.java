package com.helciodasilva.employee.client.restclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.helciodasilva.employee.client.model.Employee;

@FeignClient("employee-server")
public interface EmployeeRestClient {

	@GetMapping("employees/{employeeId}")
	public Employee findById(@PathVariable("employeeId") Long employeeId);

}
