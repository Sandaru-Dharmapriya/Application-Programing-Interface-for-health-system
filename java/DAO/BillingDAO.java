package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Billing;
import model.Patient;

public class BillingDAO {
    private static final Logger logger = Logger.getLogger(BillingDAO.class.getName());

    private static List<Billing> billings = new ArrayList<>();
    
    
    private static PatientDAO patientDAO = new PatientDAO();
    

    static {
        Patient patient = (Patient) patientDAO.getPatientById(1);
        billings.add(new Billing(1 ,patient, 2000,1000));
    }

    // CRUD methods

    public void addBilling(Billing billing) {
        int id = billing.getId();
        for (Billing existingBilling : billings) {
            if (existingBilling.getId() == id) {
                logger.log(Level.INFO, "Bill with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            billings.add(billing);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding billing", e);
        }
    }

    public Billing getBillingById(int id) {
        try {
            for (Billing billing : billings) {
                if (billing.getId() == id) {
                    return billing;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving billing by ID", e);
        }
        return null;
    }

    public List<Billing> getAllBillings() {
        return billings;
    }

    public void updateBilling(Billing updatedBilling) {
        try {
            for (Billing billing : billings) {
                if (billing.getId() == updatedBilling.getId()) {
                    billing.setInvoice(updatedBilling.getInvoice());
                    billing.setPatient(updatedBilling.getPatient());
                    billing.setPayments(updatedBilling.getPayments());
                    billing.setOutstandBalance(updatedBilling.getOutstandBalance());
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating billing", e);
        }
    }

    public void deleteBilling(int id) {
        try {
            billings.removeIf(billing -> billing.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting billing", e);
        }
    }
}
