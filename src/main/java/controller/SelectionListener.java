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
        int selectedInvRow = mainWindow.getInvsTbl().getSelectedRow();
        
        if (selectedInvRow != -1){
            InvHeader selectedInv = mainWindow.getInvs().get(selectedInvRow);
            
            // Getting data of invoice to show in fields
            mainWindow.getInvNumLbl().setText(Integer.toString(selectedInv.getInvNum()));
            mainWindow.getInvoiceDateTxtField().setText(mainWindow.dateFormat.format(selectedInv.getInvDate()));
            mainWindow.getCustomerNameTxtField().setText(selectedInv.getCustomerName());
            mainWindow.getInvTotalLbl().setText(""+selectedInv.getTotal());
            
            mainWindow.getCreateNewItemBtn().setEnabled(true);
            mainWindow.getDeleteItemBtn().setEnabled(true);

            // Getting the lines to fill items table
            LinesTblModel linesTblModel = new LinesTblModel(selectedInv.getLines());
            mainWindow.getInvItemsTbl().setModel(linesTblModel);
        }
    }
    
}
