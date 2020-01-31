package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EmployeeFacade
{

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    private EmployeeFacade()
    {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(long id)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Employee employee = em.find(Employee.class, id);
            return employee;
        }
        finally
        {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
                            .setParameter("name", name);
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public List<Employee> getAllEmployees()
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public Employee getEmployeeWithHighestSalary()
    {
        EntityManager em = getEntityManager();
        try
        {
            //            Query query
//                    = em.createQuery("SELECT e FROM Employee e HAVING MAX(e.salary)");
//            return (Employee) query.getSingleResult();
            //I am not experienced enough with JPQL to know how to do the proper query, thus this workaround:
            List<Employee> employeeList = getAllEmployees();
//            int employeeIndex = 0;
//            for (int i = 0; i < employeeList.size(); i++)
//            {
//                float highestSalary = 0;
//                if (employeeList.get(i).getSalary() > highestSalary)
//                {
//                    highestSalary = employeeList.get(i).getSalary();
//                    employeeIndex = i;
//                }
//            }
//            return employeeList.get(employeeIndex);
            TypedQuery<Double> query = em.createQuery("Select MAX(e.salary) from Employee e", Double.class);
            for (Employee empl : employeeList)
            {
                if (empl.getSalary() == query.getSingleResult())
                {
                    return empl;
                }
            }
            return null;
        }
        finally
        {
            em.close();
        }

    }

    public Employee createEmployee(String name, String address, float salaray)
    {
        Employee employee = new Employee(name, address, salaray);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }
        finally
        {
            em.close();
        }
    }

}
