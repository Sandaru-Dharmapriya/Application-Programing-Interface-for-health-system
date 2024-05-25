package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Appointment;
import model.Doctor;
import model.Patient;

public class AppointmentDAO {
    private static final Logger logger = Logger.getLogger("AppointmentLogger");

    private static List<Appointment> appointments = new ArrayList<>();;

   
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();
    
    static {
    // Retrieve the patient and doctor objects from their respective DAOs
    Patient patient = (Patient) patientDAO.getPatientById(1); // Cast the Person object to Patient
    Doctor doctor = doctorDAO.getDoctorById(1);
    
    // Create the appointment using the retrieved patient and doctor objects
    appointments.add(new Appointment(1, "2022/16/23", "22.03", patient, doctor));
}

    // CRUD methods

    public void addAppointment(Appointment appointment) {
        int id = appointment.getId();
        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getId() == id) {
                logger.log(Level.INFO, "Appointment with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            appointments.add(appointment);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding appointment", e);
        }
    }

    public Appointment getAppointmentById(int id) {
        try {
            for (Appointment appointment : appointments) {
                if (appointment.getId() == id) {
                    return appointment;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving appointment by ID", e);
        }
        return null;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public void updateAppointment(Appointment updatedAppointment) {
        try {
            for (Appointment appointment : appointments) {
                if (appointment.getId() == updatedAppointment.getId()) {
                    appointment.setDate(updatedAppointment.getDate());
                    appointment.setTime(updatedAppointment.getTime());
                    appointment.setPatient(updatedAppointment.getPatient());
                    appointment.setDoctor(updatedAppointment.getDoctor());
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating appointment", e);
        }
    }

    public void deleteAppointment(int id) {
        try {
            appointments.removeIf(appointment -> appointment.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting appointment", e);
        }
    }
}
