package tester;

import static utils.HibernateUtils.getFactory;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.EmployeeDaoimpl;
import pojos.Employee;

public class GetAllPermanent {

	public static void main(String[] args) {
		try (SessionFactory session = getFactory();Scanner sc=new Scanner(System.in)) {
			
			 EmployeeDaoimpl em=new EmployeeDaoimpl();
			 List<Employee> emp=em.getAllPermanent();
			 emp.forEach(s->System.out.println("Id:"+s.getEmpid()+" Firstname:"+s.getFirstName()
			 +" Lastname:"+s.getLastName()+" Salary:"+s.getSalary()));
			 
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


