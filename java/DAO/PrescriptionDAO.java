package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Patient;
import model.Prescription;

public class PrescriptionDAO {
    private static final Logger logger = Logger.getLogger(PrescriptionDAO.class.getName());
    private static List<Prescription> prescriptions= new ArrayList<>();;
    
    private static PatientDAO patientDAO = new PatientDAO();

    static {
        
        Patient patient1 = (Patient) patientDAO.getPatientById(1);
        Patient patient2 = (Patient) patientDAO.getPatientById(2);
        Patient patient3 = (Patient) patientDAO.getPatientById(3);
        
        prescriptions.add(new Prescription(1,patient1, "Amoxicillin", "500 mg", "Take one capsule three times a day for 7 days to treat bacterial infection."));
        prescriptions.add(new Prescription(2, patient2,"Ventolin", "2 puffs", "Inhale two puffs every 4 to 6 hours as needed for asthma symptoms."));
        prescriptions.add(new Prescription(3,patient3, "Lisinopril", "10 mg", "Take one tablet daily in the morning to control blood pressure."));
    }    

    // CRUD methods
    public void addPrescription(Prescription prescription) {
        int id = prescription.getId();
        for (Prescription existingPrescription : prescriptions) {
            if (existingPrescription.getId() == id) {
                logger.log(Level.INFO, "Appointment with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            prescriptions.add(prescription);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding prescription", e);
        }
    }

    public Prescription getPrescriptionById(int id) {
        try {
            for (Prescription prescription : prescriptions) {
                if (prescription.getId() == id) {
                    return prescription;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving prescription by ID", e);
        }
        return null;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    public void updatePrescription(Prescription updatedPrescription) {
        try {
            for (Prescription prescription : prescriptions) {
                if (prescription.getId() == updatedPrescription.getId()) {
                    prescription.setMedication(updatedPrescription.getMedication());
                    prescription.setDosage(updatedPrescription.getDosage());
                    prescription.setInstructions(updatedPrescription.getInstructions());
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating prescription", e);
        }
    }

    public void deletePrescription(int id) {
        try {
            prescriptions.removeIf(prescription -> prescription.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting prescription", e);
        }
    }
}
