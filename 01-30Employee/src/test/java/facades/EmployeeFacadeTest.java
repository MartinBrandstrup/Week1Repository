/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
////import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author thomas
 */
public class EmployeeFacadeTest {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade EF = EmployeeFacade.getEmployeeFacade(EMF);
    
    private static int numberOfDummies = 10;
    Employee employeeForIdTest;
    
    public EmployeeFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = EMF.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();

            for (int i = 0; i < numberOfDummies; i++)
            {
                String name = "name" + i;
                String address = "address" + i;
                float salary = 1000 + i;
                em.persist(new Employee(name, address, salary));
            }
            employeeForIdTest = new Employee("nameForId", "addressForId", 100);
            em.persist(employeeForIdTest);
            
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> employeeList = EF.getAllEmployees();
        assertFalse(employeeList == null);
        assertFalse(employeeList.isEmpty());
        assertEquals(numberOfDummies+1, employeeList.size(), "Expects " + numberOfDummies+1 + " rows in the database");
    }

    @Test
    public void getEmployeeByIdTest()
    {
        List<Employee> employeeList = EF.getAllEmployees();
        assertFalse(employeeList == null);
        assertFalse(employeeList.isEmpty());
        assertTrue(employeeList.get(0).getId() > 0);
        assertEquals(EF.getEmployeeById(employeeForIdTest.getId()).toString(), employeeForIdTest.toString());
    }
    
}
