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
public class LinesTable extends AbstractTableModel{
    private ArrayList<InvLine> lines;
    private String[] cols = {"No.", "Item", "Price", "Count", "Total"};
    
    public LinesTable(ArrayList<InvLine> lines) {
        this.lines = lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();
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
        InvLine line = lines.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return line.getInvoiceModel().getInvNumber();
            case 1: return line.getItem();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.getTotal();
            default : return "";
        }
    }
    
}
