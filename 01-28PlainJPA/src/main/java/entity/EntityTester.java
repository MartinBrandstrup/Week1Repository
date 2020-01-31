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
public class EntityTester
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Book b1 = new Book("J.K. Rowling");
        Book b2 = new Book("George R. R. Martin");
        System.out.println("Pre-persistence:");
        System.out.println("Book1: " + b1.toString());
        System.out.println("Book2: " + b2.toString());
        try
        {
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
        System.out.println("\nPost-persistence:");
        System.out.println("Book1: " + b1.toString());
        System.out.println("Book2: " + b2.toString());
        
        
    }
}
