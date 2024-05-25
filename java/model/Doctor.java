/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author UseR
 */
public class Doctor extends Person {
    private String specialization;

    public Doctor(int id, String name, String contactInfo, String address, String specialization) {
        super(id, name, contactInfo, address);
        this.specialization = specialization;
    }

    public Doctor() {
    }
    

    // Getter and Setter
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}