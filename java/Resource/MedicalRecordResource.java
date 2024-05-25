package Resource;

import DAO.MedicalRecordDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MedicalRecord;

@Path("/medical-records")
public class MedicalRecordResource {
    private static final Logger log = Logger.getLogger(MedicalRecordResource.class.getName());
    private final MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords();
            return Response.ok(medicalRecords).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching all medical records", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all medical records: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordById(@PathParam("id") int id) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(id);
            if (medicalRecord != null) {
                return Response.ok(medicalRecord).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Medical record with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching medical record by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching medical record by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity("Medical record added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding medical record", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding medical record: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(id);
            if (existingMedicalRecord != null) {
                updatedMedicalRecord.setId(id);
                medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);
                return Response.ok("Medical record with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Medical record with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating medical record with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating medical record with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        try {
            medicalRecordDAO.deleteMedicalRecord(id);
            return Response.ok("Medical record with ID " + id + " deleted successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting medical record with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting medical record with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
