package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Doctor;
import model.Person;

public class DoctorDAO extends PersonDAO {
    private static final Logger logger = Logger.getLogger(DoctorDAO.class.getName());

    private static List<Doctor> doctors= new ArrayList<>();

    static {
        doctors.add(new Doctor(1, "L.P. Kulathunga", "0774822299", "Nugegoda, Colombo", "Eye Surgeon"));
        doctors.add(new Doctor(2, "R.M. Perera", "0712345678", "Kandy", "Pediatrician"));
        doctors.add(new Doctor(3, "S.H. Fernando", "0755555555", "Galle", "Cardiologist"));
        
    }

    // Additional CRUD methods specific to doctors
    public List<Doctor> getAllDoctors() {
        return doctors;
    }
    
    public void addDoctor(Doctor doctor) {
        int id = doctor.getId();
        for (Doctor existingDoctor : doctors) {
            if (existingDoctor.getId() == id) {
                logger.log(Level.INFO, "Doctor with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            super.addPerson(doctor); // Add doctor using superclass method to the person list
            doctors.add(doctor); // Add doctor to doctors list
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding doctor", e);
        }
    }

    public Doctor getDoctorById(int id) {
        try {
            for (Doctor doctor : doctors) {
                if (doctor.getId() == id) {
                    return doctor;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving doctor by ID", e);
        }
        return null;
    }

    public void updateDoctor(Doctor updatedDoctor) {
        try {
            for (Doctor doctor : doctors) {
                if (doctor.getId() == updatedDoctor.getId()) {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setContactInfo(updatedDoctor.getContactInfo());
                    doctor.setAddress(updatedDoctor.getAddress());
                    doctor.setSpecialization(updatedDoctor.getSpecialization());
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating doctor", e);
        }
    }

    public void deleteDoctor(int id) {
        try {
            doctors.removeIf(doctor -> doctor.getId() == id);
            super.deletePerson(id); // Delete doctor from superclass method
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting doctor", e);
        }
    }

    public int getNextDoctorID() {
        return super.getNextPersonID(); // Use superclass method to get next available ID
    }
}
