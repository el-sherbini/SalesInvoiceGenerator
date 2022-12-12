/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Mohamed Emad
 */
public class InvLine {
    int invNum;
    String item;
    double price;
    int count;
    private InvHeader invoiceModel;

    public InvLine() {
    }

    public InvLine(int invNum, String item, double price, int count, InvHeader invoiceModel) {
        this.invNum = invNum;
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoiceModel = invoiceModel;
    }

    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
}
