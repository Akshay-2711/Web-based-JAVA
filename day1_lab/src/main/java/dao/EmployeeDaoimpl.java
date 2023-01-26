package dao;

import static utils.HibernateUtils.getFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Department;
import pojos.Employee;

public class EmployeeDaoimpl implements EmployeeDao {

	@Override
	public String addEmpDetails(Employee newEmp) {

		// newEmp :TRANSIENT
		String mesg = "Adding employee Failed!!";

		// 1. Get the hibernate session from SF
		Session session = getFactory().getCurrentSession();

		// 2. Begin transaction
		Transaction tx = session.beginTransaction();
		try {
			// Session APi: public Serializable save(Object transientObjRef) throw
			// HibernateException
			Long empId = (Long) session.save(newEmp);
			// newEmp: PERSISTENT =>entity ref added to L1 Cache
			tx.commit();// Hibernate perform dirty checking =>session.flush()
			/// DML -- insert --session.close()=>L1 cache destroyed,
			// pooled out connections returned to DBCP(db Connection pool)
			// newEmp:DETACHED from L1 cache
			mesg = "Added emp details ,Id" + empId;

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// rethrow the same exception to the caller:
				// so that the caller will know abt exception
				throw e;
			}
		}
		return mesg;
	}

	@Override
	public List<Employee> getAllByDept(Department dept, double salary) {

		List<Employee> employee = null;

		String jqpl = "select e from Employee e where e.dept=:dt and e.salary>:sal";

		// 1. Get the hibernate session from SF
		Session session = getFactory().getCurrentSession();

		// 2. Begin transaction
		Transaction tx = session.beginTransaction();
		try {
			employee = session.createQuery(jqpl, Employee.class).setParameter("dt", dept).setParameter("sal", salary)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return employee;
	}

	@Override
	public List<Employee> getAllPermanent() {
		
		List<Employee> emp=null;
		Session session = getFactory().getCurrentSession();
		String jpql="select new pojos.Employee(empid,firstName,lastName,salary) from Employee e where e.isPermanent=true";
		// 2. Begin transaction
		Transaction tx = session.beginTransaction();
		try {
			
			emp=session.createQuery(jpql,Employee.class).getResultList();
			tx.commit();
		}catch(RuntimeException e) 
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return emp;
	}

	@Override
	public String DeleteTemporary() {
		
		String mesg="Employee failed to delete!!";
		
		Session session =getFactory().getCurrentSession();
		//String jpql="delete from Employee e where e.isPermanent=:false";
		String jpql="delete from Employee e where e.isPermanent=false";
		
		Transaction tx =session.beginTransaction();
		try {
			
			int emp=session.createQuery(jpql).executeUpdate();
			
			tx.commit();
			mesg="DELETED SUCCESSFULLY!!!";
		}catch(RuntimeException e)
		{
			e.printStackTrace();
		}
		return mesg;
	}

}
