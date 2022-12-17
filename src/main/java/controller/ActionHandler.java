/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.InvHeader;
import model.InvLine;
import model.InvTblModel;
import model.LinesTblModel;
import view.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.NewInvWindow;

/**
 *
 * @author Mohamed Emad
 */
public class ActionHandler implements ActionListener {
    private final MainWindow mainWindow;
    private NewInvWindow newInvWindow;
    
    public ActionHandler(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Load File" -> loadFile();
            case "Save File" -> saveFile();
            case "Create New Invoice" -> createNewInvoice();
            case "Delete Invoice" -> deleteInvoice();
            case "Add New Invoice" -> addNewInvoice();
            case "Cancel New Invoice" -> cleanWindow(newInvWindow);
            case "Delete Item" -> deleteItem();
        }
    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        
        try {
            
            if (fc.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                Path headerPath = Paths.get(fc.getSelectedFile().getAbsolutePath()); // Getting the path of the header file
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<InvHeader> invHeaders = new ArrayList<>();

                for (String line: headerLines) {
                    String[] headerArr = line.split(","); // Getting every item individual in an array [Invoice Number, Invoice Date, Customer Name]

                    // Adding a new row of invoice header from the file
                    InvHeader newHeader = new InvHeader(Integer.parseInt(headerArr[0]), headerArr[1], headerArr[2]);
                    invHeaders.add(newHeader);
                }

                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    Path linePath = Paths.get(fc.getSelectedFile().getAbsolutePath()); // Getting the path of the line file
                    List<String> itemLines = Files.readAllLines(linePath);
                    InvHeader invs = null;

                    for (String line: itemLines) {
                        String[] lineArr = line.split(","); // Getting every item individual in an array [Item Name, Item Price, Count, InvHeader Model]

                        for (InvHeader invoice : invHeaders) {
                            if (invoice.getInvNum()== Integer.parseInt(lineArr[0])) {
                                invs = invoice;
                                break;
                            }
                        }

                        // Adding a new row of invoice line from the file
                        InvLine newLine = new InvLine(lineArr[1], Double.parseDouble(lineArr[2]), Integer.parseInt(lineArr[3]), invs);
                        invs.getLines().add(newLine);
                    }
                }

                InvTblModel invTblModel = new InvTblModel(invHeaders);
                
                mainWindow.setInvs(invHeaders);
                mainWindow.setInvTblModel(invTblModel);
                mainWindow.getInvsTbl().setModel(invTblModel);
                
                mainWindow.getInvTblModel().fireTableDataChanged();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile() {
        
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
        
        if (invs != null) {
            // Getting the number of the new invoice according to the last invoice number (if there are any invoices)
            InvHeader lastInv = invs.get(invs.size() - 1);
            invNum = lastInv.getInvNum() + 1;
        }
        
        String invDate = newInvWindow.getInvDateTxtField().getText();
        String customerName = newInvWindow.getCustomerNameTxtField().getText();
        
        // Adding new invoice to the table
        mainWindow.getInvs().add(new InvHeader(invNum, invDate, customerName));
        mainWindow.getInvTblModel().fireTableDataChanged();
        
        cleanWindow(newInvWindow);
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
    
    private void cleanWindow(JDialog window) {
        // Cleaning the window
        window.setVisible(false);
        window.dispose();
        window = null;
    }

    
}
