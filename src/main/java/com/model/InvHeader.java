/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.ArrayList;

/**
 *
 * @author Mohamed Emad
 */
public class InvHeader {
    int invNumber;
    String invDate;
    String customerName;
    private ArrayList<InvLine> lines;

    public InvHeader(int invNumber, String invDate, String customerName) {
        this.invNumber = invNumber;
        this.invDate = invDate;
        this.customerName = customerName;
    }

    public int getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(int invNumber) {
        this.invNumber = invNumber;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invoiceDate) {
        this.invDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }    

    public ArrayList<InvLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }
    
    public double getTotal() {
        double t = 0.0;
        
        for (InvLine l: getLines()) {
            t += l.getTotal();
        }
        
        return t;
    }
    
}
