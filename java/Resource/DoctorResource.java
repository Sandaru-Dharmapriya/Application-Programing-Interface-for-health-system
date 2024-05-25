package Resource;

import DAO.DoctorDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Doctor;

@Path("/doctors")
public class DoctorResource {
    private static final Logger log = Logger.getLogger(DoctorResource.class.getName());
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            return Response.ok(doctors).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching all doctors", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all doctors: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") int id) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(id);
            if (doctor != null) {
                return Response.ok(doctor).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Doctor with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching doctor by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching doctor by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity("Doctor added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding doctor: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        try {
            Doctor existingDoctor = doctorDAO.getDoctorById(id);
            if (existingDoctor != null) {
                updatedDoctor.setId(id);
                doctorDAO.updateDoctor(updatedDoctor);
                return Response.ok("Doctor with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Doctor with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating doctor with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating doctor with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        try {
            doctorDAO.deleteDoctor(id);
            return Response.ok("Doctor with ID " + id + " deleted successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting doctor with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting doctor with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
