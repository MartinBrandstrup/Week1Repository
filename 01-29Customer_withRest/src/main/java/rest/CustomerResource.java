/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.CustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Brandstrup
 */
@Path("customer")
public class CustomerResource
{

    @Context
    private UriInfo context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource()
    {
    }

    @GET
    public String getJson()
    {
        return "Hello from my second(?) web service";
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomersToJson()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        return gson.toJson(facade.getAllCustomers());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerByIdToJson(@PathParam("id") int customerId)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        return gson.toJson(facade.getCustomerById(customerId));
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCustomerToJson()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        return gson.toJson(facade.getRandomCustomer());
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
