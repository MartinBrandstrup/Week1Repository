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
import javax.persistence.Persistence;

/**
 *
 * @author Brandstrup
 */
public class FacadeTester
{

    public static void main(String[] args)
    {
        //Sets up singleton
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        EntityManager em = emf.createEntityManager();

        //Populating DB since persistence is set to Drop & Create
        Customer c1 = new Customer("Jens", "Sørensen");
        Customer c2 = new Customer("Ib", "Flemmingsen");
        Customer c3 = new Customer("Marie", "Flemmingsen");
        Customer c4 = new Customer("Janne", "Flemmingsen");
        Customer c5 = new Customer("Bo", "Pedersen");
        Customer c6 = new Customer("David", "Pedersen");
        try
        {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);
            em.persist(c6);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }

        //Testing getNumberOfCustomers and addCustomer
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());
        System.out.println("Creating new customer: " + facade.addCustomer("Hans", "Hansen").toString());
        System.out.println("Number of customers after new addition: " + facade.getNumberOfCustomers());

        //Testing getAllCustomers
        System.out.println("List of all customers: ");
        List<Customer> listAll = facade.getAllCustomers();
        for (Customer customer : listAll)
        {
            System.out.println(customer.toString());
        }

        //Testing findByLastName
        System.out.println("List of all customers wiht last name Flemmingsen: ");
        List<Customer> listFlemmingsen = facade.findByLastName("Flemmingsen");
        for (Customer customer : listFlemmingsen)
        {
            System.out.println(customer.toString());
        }
    }
}
