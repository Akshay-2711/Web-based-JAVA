package dao;

import java.util.List;

import pojos.Department;
import pojos.Employee;

public interface EmployeeDao {
	 
	//add a method to save emp details
	String addEmpDetails(Employee newEmp);
	
	List<Employee> getAllByDept(Department dept,double sal);

	List<Employee> getAllPermanent();
	
	String  DeleteTemporary();
	
}
