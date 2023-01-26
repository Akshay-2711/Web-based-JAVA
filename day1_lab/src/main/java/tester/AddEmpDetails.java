package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.EmployeeDao;
import dao.EmployeeDaoimpl;
import pojos.Department;
import pojos.Employee;

public class AddEmpDetails {

	public static void main(String[] args) {

		try (SessionFactory s = getFactory(); Scanner sc = new Scanner(System.in)) {
			System.out.println(" Add Emp dtails:firstName,  lastName,  dept,  salary,  dob, isPermanent");

			// create transient emp using constructor

			EmployeeDaoimpl empdao = new EmployeeDaoimpl();
			Employee emp = new Employee(sc.next(), sc.next(), Department.valueOf(sc.next().toUpperCase()),
					sc.nextDouble(), LocalDate.parse(sc.next()), sc.nextBoolean());

			// invole Dao's method
			System.out.println(empdao.addEmpDetails(emp));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}