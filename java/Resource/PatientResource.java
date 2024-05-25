package Resource;

import DAO.PatientDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Patient;
import model.Person;

@Path("/patients")
public class PatientResource {
    private static final Logger log = Logger.getLogger(PatientResource.class.getName());
    private final PatientDAO patientDAO = new PatientDAO();

   @GET
@Produces(MediaType.APPLICATION_JSON)
public Response getAllPatients() {
    try {
        List<Person> patients = patientDAO.getAllPatients();
        // Print the status code
        System.out.println("Status code: " + Response.Status.OK.getStatusCode());
        return Response.ok(patients).build();
        
    } catch (Exception e) {
        log.log(Level.SEVERE, "Error fetching all patients", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all patients: " + e.getMessage()).build();
    }
}


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("id") int id) {
        try {
            Person patient = patientDAO.getPatientById(id);
            if (patient != null) {
                return Response.ok(patient).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Patient with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching patient by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching patient by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            patientDAO.addPatient(patient);
            return Response.status(Response.Status.CREATED).entity("Patient added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding patient", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding patient: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        try {
            Person existingPatient = patientDAO.getPatientById(id);
            if (existingPatient != null) {
                updatedPatient.setId(id);
                patientDAO.updatePatient(updatedPatient);
                return Response.ok("Patient with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Patient with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating patient with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating patient with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        try {
            patientDAO.deletePatient(id);
            return Response.ok("Patient with ID " + id + " deleted successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting patient with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting patient with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
