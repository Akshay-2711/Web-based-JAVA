package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.EmployeeDaoimpl;
public class DeleteAllTempEmp {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)){
		
			EmployeeDaoimpl empdao=new EmployeeDaoimpl();
			empdao.DeleteTemporary();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
		

	}

}
