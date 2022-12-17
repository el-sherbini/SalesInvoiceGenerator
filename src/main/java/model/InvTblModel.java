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
public class InvTblModel extends AbstractTableModel {
    private ArrayList<InvHeader> invs;
    private String[] headers = {"No.", "Date", "Customer Name", "Total"};

    public InvTblModel(ArrayList<InvHeader> invs) {
        this.invs = invs;
    }

    @Override
    public int getRowCount() {
        return invs.size();
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
        InvHeader inv = invs.get(rowIndex);
        
        return switch (columnIndex) {
            case 0 -> inv.getInvNum();
            case 1 -> inv.getInvDate();
            case 2 -> inv.getCustomerName();
            case 3 -> inv.getTotal();
            default -> "";
        };
    }
    
     
}
