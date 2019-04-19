package org.vivek.demo.restexample.employeeportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vivek.demo.restexample.employeeportal.exception.ResourceNotFoundException;
import org.vivek.demo.restexample.employeeportal.model.Department;
import org.vivek.demo.restexample.employeeportal.model.Employee;
import org.vivek.demo.restexample.employeeportal.repository.DepartmentRepository;
import org.vivek.demo.restexample.employeeportal.repository.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmenrRepository;
	public EmployeeService() {
    }

    public Employee createEmployee(Employee employee) {
    	Department department = employee.getDepartment();
    	departmenrRepository.save(department );
        return employeeRepository.save(employee);
    }

    public Employee getEmployee( Long employeeId) throws ResourceNotFoundException {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    }
    
    
    public List<Employee> getAllEmployees() throws ResourceNotFoundException {
    	List<Employee> employees= employeeRepository.findAll();
    	return employees;
       
    }
    

    public Employee updateEmployee(Employee employee) {
    	return employeeRepository.save(employee);
    }
    
    public void deleteEmployee(Employee employee) {
    	employeeRepository.delete(employee);
    }
}
