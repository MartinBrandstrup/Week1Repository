/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Brandstrup
 */
@Path("animal")
public class AnimalResource
{

    private final List<Animal> animalList = new ArrayList<>();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource()
    {
    }

    private void populateAnimalList()
    {
        this.animalList.add(new Animal("Duck", 2015, "Quack"));
        this.animalList.add(new Animal("Dog", 2008, "Bark"));
        this.animalList.add(new Animal("Cat", 2010, "Meow"));
        this.animalList.add(new Animal("Pig", 2017, "Oink"));
    }
     
    /**
     * Retrieves representation of an instance of com.mycompany.rest1.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String getJson()
    {
        return "Hello from my first web service";
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2()
    {
        populateAnimalList();
        Random rng = new Random();
        int r = rng.nextInt(4);
        return new Gson().toJson(animalList.get(r));

//        Response headers:
//        HTTP/1.1 200
//        Content-Type: application/json
//        Date: Fri, 23 Aug 2019 14:17:21 GMT
//        Transfer-Encoding: chunked
    }
    
    /**
     * PUT method for updating or creating an instance of AnimalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
