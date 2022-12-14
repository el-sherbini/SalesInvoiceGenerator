/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mohamed Emad
 */
public class InvTable extends AbstractTableModel {
    private ArrayList<InvHeader> invs;
    private String[] cols = {"No.", "Date", "Customer", "Total"};

    public InvTable(ArrayList<InvHeader> invs) {
        this.invs = invs;
    }

    @Override
    public int getRowCount() {
        return invs.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvHeader inv = invs.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return inv.getInvNumber();
            case 1: return inv.getInvDate();
            case 2: return inv.getCustomerName();
            case 3: return inv.getTotal();
            default: return "";
        }
    }
    
     
}
