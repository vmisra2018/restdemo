package org.vivek.demo.restexample.employeeportal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.demo.restexample.employeeportal.exception.ResourceNotFoundException;
import org.vivek.demo.restexample.employeeportal.model.Employee;
import org.vivek.demo.restexample.employeeportal.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() throws ResourceNotFoundException {	
		List<Employee> el= employeeService.getAllEmployees();
		 checkResourceFound(el);
		 return el;
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {		
		Employee employee = this.employeeService.getEmployee(employeeId);
		checkResourceFound(employee);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody Employee employee, HttpServletRequest request,
			HttpServletResponse response) {
		Employee createdEmployee = this.employeeService.createEmployee(employee);
		response.setHeader("Location", request.getRequestURL().append("/").append(createdEmployee.getId()).toString());
		return createdEmployee;
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails, HttpServletRequest request, HttpServletResponse response)
			throws ResourceNotFoundException {
		Employee employee = this.employeeService.getEmployee(employeeId);
		checkResourceFound(employee);
		/* IMPORTANT - To prevent the overriding of the existing value of the variables in the database, 
         * if that variable is not coming in the @RequestBody annotation object. */    
        if(employeeDetails.getFirstName() == null || employeeDetails.getFirstName().isEmpty())
            employeeDetails.setFirstName(employee.getFirstName());
        if(employeeDetails.getLastName() == null || employeeDetails.getLastName().isEmpty())
      
        employeeDetails.setId(employee.getId());
		employee = this.employeeService.getEmployee(employeeId);
		
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		if(employeeDetails.getDepartment()!=null){
		employee.setDepartment(employeeDetails.getDepartment());
		}
		Employee updatedEmployee = this.employeeService.updateEmployee(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = this.employeeService.getEmployee(employeeId);
		checkResourceFound(employee);
		this.employeeService.deleteEmployee(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	public static <T> T checkResourceFound(final T resource) throws ResourceNotFoundException {
		if (resource == null) {
			throw new ResourceNotFoundException("resource not found");
		}
		return resource;
	}
}
