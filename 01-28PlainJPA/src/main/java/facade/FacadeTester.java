/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import java.util.List;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BookFacade facade = BookFacade.getBookFacade(emf);
        Book b1 = facade.addBook("Author1");
        Book b2 = facade.addBook("Author2");
        //Find book by ID
        System.out.println("Book1: " + facade.findBook(b1.getId()).getAuthor());
        System.out.println("Book2: " + facade.findBook(b2.getId()).getAuthor());
        //Find all books
        System.out.println("Number of books: " + facade.getAllBooks().size());

        List<Book> books = facade.getAllBooks();
        for (Book b : books)
        {
            System.out.println(b.toString());
        }
    }
}
