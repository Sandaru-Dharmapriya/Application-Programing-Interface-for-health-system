package Resource;

import DAO.AppointmentDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Appointment;

@Path("/appointments")
public class AppointmentResource {
    private static final Logger log = Logger.getLogger(AppointmentResource.class.getName());
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            return Response.ok(appointments).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching all appointments", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all appointments: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("id") int id) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            if (appointment != null) {
                return Response.ok(appointment).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Appointment with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching appointment by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching appointment by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity("Appointment added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding appointment", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding appointment: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
        try {
            Appointment existingAppointment = appointmentDAO.getAppointmentById(id);
            if (existingAppointment != null) {
                updatedAppointment.setId(id);
                appointmentDAO.updateAppointment(updatedAppointment);
                return Response.ok("Appointment with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Appointment with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating appointment with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating appointment with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        try {
            appointmentDAO.deleteAppointment(id);
            return Response.ok("Appointment with ID " + id + " deleted successfully").build();
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting appointment with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting appointment with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
