package Resource;

import DAO.PrescriptionDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Prescription;

@Path("/prescriptions")
public class PrescriptionResource {
    private static final Logger log = Logger.getLogger(PrescriptionResource.class.getName());
    private final PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
            return Response.ok(prescriptions).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching all prescriptions", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all prescriptions: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("id") int id) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(id);
            if (prescription != null) {
                return Response.ok(prescription).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Prescription with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching prescription by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching prescription by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity("Prescription added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding prescription", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding prescription: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        try {
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(id);
            if (existingPrescription != null) {
                updatedPrescription.setId(id);
                prescriptionDAO.updatePrescription(updatedPrescription);
                return Response.ok("Prescription with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Prescription with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating prescription with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating prescription with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        try {
            prescriptionDAO.deletePrescription(id);
            return Response.ok("Prescription with ID " + id + " deleted successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting prescription with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting prescription with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
