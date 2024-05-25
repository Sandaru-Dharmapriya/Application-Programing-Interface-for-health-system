package Resource;

import DAO.BillingDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Billing;

@Path("/billings")
public class BillingResource {
    private static final Logger log = Logger.getLogger(BillingResource.class.getName());
    private final BillingDAO billingDAO = new BillingDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            return Response.ok(billings).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching all billings", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching all billings: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("id") int id) {
        try {
            Billing billing = billingDAO.getBillingById(id);
            if (billing != null) {
                return Response.ok(billing).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Billing with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching billing by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching billing by ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity("Billing added successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error adding billing", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding billing: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") int id, Billing updatedBilling) {
        try {
            Billing existingBilling = billingDAO.getBillingById(id);
            if (existingBilling != null) {
                updatedBilling.setId(id);
                billingDAO.updateBilling(updatedBilling);
                return Response.ok("Billing with ID " + id + " updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Billing with ID " + id + " not found").build();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error updating billing with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating billing with ID: " + id + ": " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            billingDAO.deleteBilling(id);
            return Response.ok("Billing with ID " + id + " deleted successfully").build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error deleting billing with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting billing with ID: " + id + ": " + e.getMessage()).build();
        }
    }
}
