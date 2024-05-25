/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author UseR
 */
public class MedicalRecord {
    private int id;
    private String diagnosis;
    private Patient patient;
    private Prescription prescription;

    public MedicalRecord(int id,Patient patient , String diagnosis, Prescription prescription) {
        this.id = id;
        this.patient=patient;
        this.diagnosis = diagnosis;
        this.prescription=prescription;
    }

    public MedicalRecord() {
    }
    

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

   

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

   
}
