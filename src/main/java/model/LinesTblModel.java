/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mohamed Emad
 */
public class LinesTblModel extends AbstractTableModel{
    private ArrayList<InvLine> lines;
    private String[] headers = {"No.", "Item Name", "Price", "Count", "Total"};
    
    public LinesTblModel(ArrayList<InvLine> lines) {
        this.lines = lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvLine line = lines.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return line.getInvHeader().getInvNum();
            case 1: return line.getItemName();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.getTotal();
            default : return "";
        }
    }

    public ArrayList<InvLine> getLines() {
        return lines;
    }
    
}
