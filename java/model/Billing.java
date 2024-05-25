/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author UseR
 */
public class Billing {
    private int id;
    private double invoice;
    private double payments;
    private double outstandBalance;
    private Patient patient;

    public Billing(int id, Patient patient,double invoice,double payments) {
        this.id = id;
        this.patient=patient;
        this.invoice=invoice;
        this.payments=payments;
        this.outstandBalance=invoice-payments;
        
        
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Billing() {
    }
    

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInvoice() {
        return invoice;
    }

    public double getPayments() {
        return payments;
    }

    public double getOutstandBalance() {
        return outstandBalance;
    }

    public void setInvoice(double invoice) {
        this.invoice = invoice;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public void setOutstandBalance(double outstandBalance) {
        this.outstandBalance = outstandBalance;
    }

    
}