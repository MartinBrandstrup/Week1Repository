package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("employee")
public class EmployeeResource
{

    @Context
    private UriInfo context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    private List<Employee> empList;

    public EmployeeResource()
    {
        populateList();
    }

    private void populateList()
    {
        List<Employee> emps = new ArrayList<>();
        emps.add(new Employee("Fillip", "Vej1", 5000));
        emps.add(new Employee("Janne", "Vej2", 6000));
        emps.add(new Employee("Kim", "Vej3", 3000));
        emps.add(new Employee("Flemming", "Vej4", 2000));
        emps.add(new Employee("Flemming", "Vej5", 4000));
        
        this.empList = emps;
    }
    
    @GET
    @Produces(
            {
                MediaType.APPLICATION_JSON
            })
    public String demo()
    {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployeesToJson()
    {
        List<EmployeeDTO> DTOList = new ArrayList<>();
        List<Employee> EntityList = facade.getAllEmployees();

        for (Employee employee : EntityList)
        {
            DTOList.add(new EmployeeDTO(employee));
        }
        return gson.toJson(DTOList);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByIdToJson(@PathParam("id") long empId)
    {
        Employee emp = facade.getEmployeeById(empId);
        EmployeeDTO empDTO = new EmployeeDTO(emp);
        return gson.toJson(empDTO);
    }
    
    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeWithHighestSalaryToJson()
    {
        Employee emp = facade.getEmployeeWithHighestSalary();
        EmployeeDTO empDTO = new EmployeeDTO(emp);
        return gson.toJson(empDTO);
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByNameToJson(@PathParam("name") String empName)
    {
        List<EmployeeDTO> DTOList = new ArrayList<>();
        List <Employee> EntityList = facade.getEmployeesByName(empName);

        for (Employee employee : EntityList)
        {
            DTOList.add(new EmployeeDTO(employee));
        }
        
        return gson.toJson(DTOList);
    }

    @POST
    @Consumes(
            {
                MediaType.APPLICATION_JSON
            })
    public void create(Employee entity)
    {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes(
            {
                MediaType.APPLICATION_JSON
            })
    public void update(Employee entity, @PathParam("id") int id)
    {
        throw new UnsupportedOperationException();
    }
}
