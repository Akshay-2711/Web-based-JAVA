package tester;

import static utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

public class TestHibernate {

	public static void main(String[] args) {

		try (SessionFactory s = getFactory()) {
			System.out.println("Hibernate up and running");
						
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
