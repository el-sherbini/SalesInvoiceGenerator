/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.InvHeader;
import model.LinesTblModel;
import view.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.FileOperations;
import view.NewInvWindow;
import view.NewItemWindow;

/**
 *
 * @author Mohamed Emad
 */
public class ActionHandler implements ActionListener {
    private MainWindow mainWindow;
    private NewInvWindow newInvWindow;
    private NewItemWindow newItemWindow;
    private FileOperations fileOperations;
    
    public ActionHandler(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.fileOperations = fileOperations = new FileOperations(mainWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Load File" -> fileOperations.readFile();
            case "Save File" -> fileOperations.writeFile();
            case "Create New Invoice" -> createNewInvoice();
            case "Delete Invoice" -> deleteInvoice();
            case "Add New Invoice" -> addNewInvoice();
            case "Cancel New Invoice" -> cleanDialog(newInvWindow);
            case "Create New Item" -> createNewItem();
            case "Delete Item" -> deleteItem();
            case "Add New Item" -> addNewItem();
            case "Cancel New Item" -> cleanDialog(newItemWindow);
        }
    }

    private void createNewInvoice() {
        newInvWindow = new NewInvWindow(mainWindow);
        newInvWindow.setLocationRelativeTo(mainWindow);
        newInvWindow.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedRow = mainWindow.getInvsTbl().getSelectedRow();
        if (selectedRow != -1) {
            mainWindow.getInvs().remove(selectedRow);
            mainWindow.getInvTblModel().fireTableDataChanged();
        }
    }
    
    private void addNewInvoice() {
        ArrayList<InvHeader> invs = mainWindow.getInvs();
        int invNum = 1;
        
        if (invs != null && !invs.isEmpty()) {
            // Getting the number of the new invoice according to the last invoice number (if there are any invoices)
            InvHeader lastInv = invs.get(invs.size() - 1);
            invNum = lastInv.getInvNum() + 1;
        }
        
        
        try {
            String invDate = newInvWindow.getInvDateTxtField().getText();
            Date date = mainWindow.dateFormat.parse(invDate);
            
            String customerName = newInvWindow.getCustomerNameTxtField().getText();
        
            // Adding new invoice to the table
            mainWindow.getInvs().add(new InvHeader(invNum, date, customerName));
            mainWindow.getInvTblModel().fireTableDataChanged();
            
            cleanDialog(newInvWindow);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(mainWindow, "Date isn't correct.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewItem() {
        newItemWindow = new NewItemWindow(mainWindow);
        newItemWindow.setLocationRelativeTo(mainWindow);
        newItemWindow.setVisible(true);
    }
    
    private void deleteItem() {
        int selectedRow = mainWindow.getInvItemsTbl().getSelectedRow();
        
        if (selectedRow != -1) {
            LinesTblModel linesTblModel = (LinesTblModel) mainWindow.getInvItemsTbl().getModel();
            linesTblModel.getLines().remove(selectedRow);
            linesTblModel.fireTableDataChanged();
            mainWindow.getInvTblModel().fireTableDataChanged();
        }
    }
    
    private void addNewItem() {
        newItemWindow = new NewItemWindow(mainWindow);
        newItemWindow.setLocationRelativeTo(mainWindow);
        newItemWindow.setVisible(true);
    }
    
    private void cleanDialog(JDialog dialog) {
        // Cleaning the dialog
        dialog.setVisible(false);
        dialog.dispose();
        dialog = null;
    }
    
}
