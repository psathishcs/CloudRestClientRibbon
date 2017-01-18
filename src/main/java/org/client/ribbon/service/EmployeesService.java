package org.client.ribbon.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.client.ribbon.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EmployeesService {
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "readEmployeeListCB")
	public List<Employees> readEmployeeList(){
		URI uri = URI.create("http://ping-server/hr/api/employees");
		List<Employees> employees = (List<Employees>) restTemplate.getForObject(uri, List.class);
		return employees;
	}
	
	public List<Employees> readEmployeeListCB(){
		List<Employees> employees = new ArrayList<Employees>();
		Employees employee = new Employees();
		employees.add(employee);
		return employees;
	}

}
