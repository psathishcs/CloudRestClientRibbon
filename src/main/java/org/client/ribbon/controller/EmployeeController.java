package org.client.ribbon.controller;

import java.util.List;

import org.client.ribbon.conf.RibbonConfiguration;
import org.client.ribbon.entity.Employees;
import org.client.ribbon.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(
		name="ping-a-server", configuration=RibbonConfiguration.class)
public class EmployeeController {
	
	@LoadBalanced
	@Bean
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	@Autowired
	private EmployeesService service;
	@RequestMapping("/getEmployees")
	public List<Employees> getEmployee() {
		return service.readEmployeeList();
	}
}
