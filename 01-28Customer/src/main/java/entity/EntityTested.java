/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Brandstrup
 */
public class EntityTested
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
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
    }
}
