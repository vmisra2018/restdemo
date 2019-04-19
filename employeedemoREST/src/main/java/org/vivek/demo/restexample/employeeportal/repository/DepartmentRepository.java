package org.vivek.demo.restexample.employeeportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.demo.restexample.employeeportal.model.Department;
import org.vivek.demo.restexample.employeeportal.model.Employee;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
