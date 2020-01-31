/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Brandstrup
 */
public class CustomerFacade
{

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade()
    {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findById(int id)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Customer customer = em.find(Customer.class, id);
            return customer;
        }
        finally
        {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :name", Customer.class)
                            .setParameter("name", name);
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public long getNumberOfCustomers()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            long customerCount = (long) em.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult();
            return customerCount;
        }
        finally
        {
            em.close();
        }
    }

    public List<Customer> getAllCustomers()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName)
    {
        Customer customer = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }
        finally
        {
            em.close();
        }
    }
}
