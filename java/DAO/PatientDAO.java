package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Patient;
import model.Person;

public class PatientDAO extends PersonDAO {
    private static final Logger logger = Logger.getLogger(PatientDAO.class.getName());

    private static List<Person> patients = new ArrayList<>();
    private static int nextID = 1;

    static {
        // Adding a Patient object to the patients list in the constructor
            patients.add(new Patient(1, "K.P.R. Jayawardhana", "0774822299", "Balangoda, Kirimetithenna", "No significant medical history", "Stable Condition"));
            patients.add(new Patient(2, "S.M. Perera", "0712345678", "Colombo", "Allergies: Pollen, Dust", "Recovering from flu"));
            patients.add(new Patient(3, "N.D. Silva", "0765432109", "Kandy", "Hypertension, Diabetes", "Undergoing treatment for hypertension"));
            
        
        
    }   

    // Additional CRUD methods specific to patients
    public List<Person> getAllPatients() {
        return patients;
    }
    
    public void addPatient(Person patient) {
        int id = patient.getId();
        for (Person existingpatient : patients) {
            if (existingpatient.getId() == id) {
                logger.log(Level.INFO, "Patient with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            super.addPerson(patient); // Add patient using superclass method to the person list
            patients.add(patient); // Add patient to patients list
           
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding patient", e);
        }
    }

    public Person getPatientById(int id) {
        try {
            for (Person patient : patients) {
                if (patient.getId() == id) {
                    return patient;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving patient by ID", e);
        }
        return null;
    }

    public void updatePatient(Patient updatedPatient) {
        try {
            for (Person person : patients) {
                if (person instanceof Patient) { // Check if the person is a Patient
                    Patient patient = (Patient) person; // Type cast to Patient
                    if (patient.getId() == updatedPatient.getId()) {
                        patient.setName(updatedPatient.getName());
                        patient.setContactInfo(updatedPatient.getContactInfo());
                        patient.setAddress(updatedPatient.getAddress());
                        patient.setMedicalHistory(updatedPatient.getMedicalHistory());
                        patient.setCurrentHealthStatus(updatedPatient.getCurrentHealthStatus());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating patient", e);
        }
    }

    public void deletePatient(int id) {
        try {
            patients.removeIf(patient -> patient.getId() == id);
            super.deletePerson(id); // Delete patient from superclass method
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting patient", e);
        }
    }

    public int getNextPatientID() {
        return super.getNextPersonID(); // Use superclass method to get next available ID
    }
    
    public static void main(String[] args) {
        System.out.println(patients);
    }
}
