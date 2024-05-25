package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MedicalRecord;
import model.Patient;
import model.Prescription;

public class MedicalRecordDAO {
    private static final Logger logger = Logger.getLogger(MedicalRecordDAO.class.getName());

    private static List<MedicalRecord> medicalRecords= new ArrayList<>();
    private static PatientDAO patientDAO = new PatientDAO();
    private static PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    static {
        Patient patient1 = (Patient) patientDAO.getPatientById(1);
        Patient patient2 = (Patient) patientDAO.getPatientById(2);
        Patient patient3 = (Patient) patientDAO.getPatientById(3);
        
        Prescription prescription1 = prescriptionDAO.getPrescriptionById(1);
        Prescription prescription2 = prescriptionDAO.getPrescriptionById(2);
        Prescription prescription3 = prescriptionDAO.getPrescriptionById(3);
        
       medicalRecords.add(new MedicalRecord(1,patient1, "Fever", prescription1 ));
       medicalRecords.add(new MedicalRecord(2,patient2, "Common Cold", prescription2));
       medicalRecords.add(new MedicalRecord(3,patient3, "Hypertension",prescription3));
    }

    // CRUD methods
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        int id = medicalRecord.getId();
        for ( MedicalRecord existingMedicalRecord : medicalRecords) {
            if (existingMedicalRecord.getId() == id) {
                logger.log(Level.INFO, "Medical-Record with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            medicalRecords.add(medicalRecord);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding medical record", e);
        }
    }

    public MedicalRecord getMedicalRecordById(int id) {
        try {
            for (MedicalRecord medicalRecord : medicalRecords) {
                if (medicalRecord.getId() == id) {
                    return medicalRecord;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving medical record by ID", e);
        }
        return null;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        try {
            for (MedicalRecord medicalRecord : medicalRecords) {
                
                if (medicalRecord.getId() == updatedMedicalRecord.getId()) {
                    medicalRecord.setPatient(updatedMedicalRecord.getPatient());
                    medicalRecord.setDiagnosis(updatedMedicalRecord.getDiagnosis());
                    medicalRecord.setPrescription(updatedMedicalRecord.getPrescription());
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating medical record", e);
        }
    }

    public void deleteMedicalRecord(int id) {
        try {
            medicalRecords.removeIf(medicalRecord -> medicalRecord.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting medical record", e);
        }
    }
}
