package Resource;

import DAO.PersonDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

@Path("/people")
public class PersonResource {
    private static final Logger log = Logger.getLogger(PersonResource.class.getName());
    private final PersonDAO personDAO = new PersonDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPeople() {
        try {
            List<Person> people = personDAO.getAllPeople();
            return Response.ok(people).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching all people", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all people: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) {
        try {
            Person person = personDAO.getPersonById(id);
            if (person != null) {
                return Response.ok(person).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Person with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching person by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching person by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        try {
            personDAO.addPerson(person);
            return Response.status(Response.Status.CREATED).entity("Person added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding person", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding person: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        try {
            Person existingPerson = personDAO.getPersonById(id);
            if (existingPerson != null) {
                updatedPerson.setId(id);
                personDAO.updatePerson(updatedPerson);
                return Response.ok("Person with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Person with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating person with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating person with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            personDAO.deletePerson(id);
            return Response.ok("Person with ID " + id + " deleted successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting person with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting person with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
