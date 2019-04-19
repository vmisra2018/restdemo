package org.vivek.demo.restexample.employeeportal.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")

public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	/*@OneToMany(mappedBy = "department")
	private Collection<Employee> employeeCollection;
	*/
	

	public Department() {
	}

	public Department(String name) {

		this.name = name;
	}

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	/*public Collection<Employee> getEmployeeCollection() {
		return employeeCollection;
	}

	public void setEmployeeCollection(Collection<Employee> employeeCollection) {
		this.employeeCollection = employeeCollection;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
