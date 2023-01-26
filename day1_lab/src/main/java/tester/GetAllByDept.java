package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.EmployeeDaoimpl;
import pojos.Department;

public class GetAllByDept {

	public static void main(String[] args) {
		
		
		try (SessionFactory s = getFactory();Scanner sc=new Scanner(System.in)) {
			System.out.println("Enter the department and salary");
			 EmployeeDaoimpl em=new EmployeeDaoimpl();
			 
			 em.getAllByDept(Department.valueOf(sc.next().toUpperCase()), sc.nextDouble()).
			 forEach(System.out::println);
			 
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}


