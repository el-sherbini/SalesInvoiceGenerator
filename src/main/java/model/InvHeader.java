/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import view.MainWindow;

/**
 *
 * @author Mohamed Emad
 */
public class InvHeader {
    private int invNum;
    private Date invDate;
    private String customerName;
    private ArrayList<InvLine> lines;
    private MainWindow mainWindow;

    public InvHeader(int invNum, Date invDate, String customerName, MainWindow mainWindow) {
        this.invNum = invNum;
        this.invDate = invDate;
        this.customerName = customerName;
        this.mainWindow = mainWindow;
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
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
        double total = 0.0;
        
        for (InvLine l: getLines()) {
            total += l.getTotal();
        }
        
        return total;
    }
    
    public String getCSVFormat() {
        return invNum + "," + mainWindow.dateFormat.format(invDate) + "," + customerName;
    }
    
}
