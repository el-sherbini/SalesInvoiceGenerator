/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.InvHeader;
import model.LinesTblModel;
import view.MainWindow;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mohamed Emad
 */
public class SelectionListener implements ListSelectionListener {
    private MainWindow mainWindow;

    public SelectionListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = mainWindow.getInvsTbl().getSelectedRow();
        
        if (selectedRow != -1){
            InvHeader selectedInv = mainWindow.getInvs().get(selectedRow);
            LinesTblModel linesTblModel = new LinesTblModel(selectedInv.getLines());
            
            mainWindow.getInvNumLbl().setText(Integer.toString(selectedInv.getInvNum()));
            mainWindow.getInvoiceDateTxtField().setText(selectedInv.getInvDate().toString());
            mainWindow.getCustomerNameTxtField().setText(selectedInv.getCustomerName());
            mainWindow.getInvTotalLbl().setText(""+selectedInv.getTotal());

            mainWindow.getInvItemsTbl().setModel(linesTblModel);
        }
    }
    
}
