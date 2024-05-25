/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author UseR
 */
public class Prescription {
    private int id;
    private String medication;
    private String dosage;
    private String instructions;
    private Patient patient;

    public Prescription(int id,Patient patient, String medication, String dosage, String instructions) {
        this.id = id;
        this.patient=patient;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public Prescription() {
    }
    

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
