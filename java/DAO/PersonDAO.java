package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

public class PersonDAO {
    private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());
    private static List<Person> people = new ArrayList<>();;
    
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();

    static {
        people.add(new Person(1, "K.G.M.Athukorala","0774822299", "Piliyandala,Colombo"));
       
    }

    // CRUD methods
    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonById(int id) {
        try {
            for (Person person : people) {
                if (person.getId() == id) {
                    return person;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving person by ID", e);
        }
        return null;
    }

    public void addPerson(Person person) {
        int id = person.getId();
        for (Person existingPerson : people) {
            if (existingPerson.getId() == id) {
                logger.log(Level.INFO, "Person with ID " + id + " already exists");
                return; // Exit the method if the ID already exists
            }
        }
        
        try {
            people.add(person);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding person", e);
        }
    }

    public void updatePerson(Person updatedPerson) {
        try {
            for (Person person : people) {
                if (person.getId() == updatedPerson.getId()) {
                    person.setName(updatedPerson.getName());
                    person.setContactInfo(updatedPerson.getContactInfo());
                    person.setAddress(updatedPerson.getAddress());
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating person", e);
        }
    }

    public void deletePerson(int id) {
        try {
            people.removeIf(person -> person.getId() == id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting person", e);
        }
    }

    public int getNextPersonID() {
        int maxId = 0;
        for (Person person : people) {
            if (person.getId() > maxId) {
                maxId = person.getId();
            }
        }
        // Increment the maximum ID to get the next available ID
        return maxId + 1;
    }
}
