/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mohamed Emad
 */
public class InvHeader {
    private int invNum;
    private String invDate;
    private String customerName;
    private ArrayList<InvLine> lines;

    public InvHeader(int invNum, String invDate, String customerName) {
        this.invNum = invNum;
        this.invDate = invDate;
        this.customerName = customerName;
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
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
