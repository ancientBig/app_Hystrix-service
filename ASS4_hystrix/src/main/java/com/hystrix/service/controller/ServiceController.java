package com.hystrix.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.hystrix.service.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class ServiceController {
	
	//generate random service failure
	public boolean getRandomBoolean() {
	       return Math.random() < 0.5;
	   }
	
	//hystrix
	//service end points
	@RequestMapping(value="/getEmployee", produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack",commandProperties= {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000"),//close circuit after 3 sec
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="20")/*over 20% failure open circuit*/
			})
	  public ResponseEntity<Object> getEmployee ()
	  {
		List<Employee> entities = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setName("employee data");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		entities.add(emp);
		
		if(getRandomBoolean() )//generate random true or false
		{
			throw new RuntimeException();
		}
		
		return new ResponseEntity<Object>(entities, HttpStatus.OK);
		
	  }

	// when there is a failure call
	public ResponseEntity<Object> getDataFallBack() {
		
		Employee emp = new Employee();
		emp.setName("fallback employee data!");
		emp.setDesignation("fallback-manager");
		emp.setEmpId("fallback-1");
		emp.setSalary(3000);

		return new ResponseEntity<Object>(emp, HttpStatus.OK);
	}

}
