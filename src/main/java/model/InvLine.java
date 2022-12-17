/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mohamed Emad
 */
public class InvLine {
    private String itemName;
    private double price;
    private int count;
    private InvHeader invHeader;

    public InvLine(String itemName, double price, int count, InvHeader InvHeader) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.invHeader = InvHeader;
    }

    public InvHeader getInvHeader() {
        return invHeader;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
    
    public double getTotal() {
        return price * count;
    }
    
    public String getCSVFormat() {
        return invHeader.getInvNum() + "," + itemName + "," + price + "," + count;
    }
}
