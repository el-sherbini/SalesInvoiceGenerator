/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.model.InvHeader;
import com.model.InvLine;
import com.model.InvTable;
import com.model.LinesTable;
import com.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mohamed Emad
 */
public class ActionHandler implements ActionListener, ListSelectionListener {
    private MainView mainView;
    
    public ActionHandler(MainView mainView){
        this.mainView = mainView;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = mainView.getInvoicesTbl().getSelectedRow();
        InvHeader currentInv = mainView.getInvoices().get(selectedIndex);
        mainView.getInvoiceNumLbl().setText(""+currentInv.getInvNumber());
        mainView.getInvoiceDateTxtField().setText(currentInv.getInvDate());
        mainView.getCustomerNameTxtField().setText(currentInv.getCustomerName());
        mainView.getInvoiceTotalLbl().setText(""+currentInv.getTotal());
        
        LinesTable linesTable = new LinesTable(currentInv.getLines());
        mainView.getInvoiceItemsTbl().setModel(linesTable);
        linesTable.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        
        switch (ac) {
            case "loadFile" -> loadFileAction();
            case "saveFile" -> saveFileAction();
            case "createNewInvoice" -> createNewInvoiceAction();
            case "deleteInvoice" -> deleteInvoiceAction();
            case "createNewItem" -> createNewItemAction();
            case "deleteItem" -> deleteItemAction();
        }
    }

    private void loadFileAction() {
        JFileChooser fc = new JFileChooser();
        
        try {
            
        int res = fc.showOpenDialog(mainView);
        
        if (res == JFileChooser.APPROVE_OPTION) {
            File hFile = fc.getSelectedFile();
            Path hPath = Paths.get(hFile.getAbsolutePath());
            List<String> hLines = Files.readAllLines(hPath);
            ArrayList<InvHeader> invoices = new ArrayList<InvHeader>();
            
            for (String hLine: hLines) {
                String[] hColumns = hLine.split(",");
                
                int invNumber = Integer.parseInt(hColumns[0]);
                String invDate = hColumns[1];
                String customerName = hColumns[2];
                
                InvHeader newHeader = new InvHeader(invNumber, invDate, customerName);
                invoices.add(newHeader);
            }
            
            res = fc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File lFile = fc.getSelectedFile();
                Path lPath = Paths.get(lFile.getAbsolutePath());
                List<String> lines = Files.readAllLines(lPath);
                
                for (String line: lines) {
                    String[] lColumns = line.split(",");
                    int invNumber = Integer.parseInt(lColumns[0]);
                    String item = lColumns[1];
                    double price = Double.parseDouble(lColumns[2]);
                    int count = Integer.parseInt(lColumns[3]);
                    InvHeader invoice = null;
                    
                    
                    for (InvHeader inv : invoices) {
                            if (inv.getInvNumber()== invNumber) {
                                invoice = inv;
                                break;
                            }
                        }
                        
                        InvLine newLine = new InvLine(item, price, count, invoice);
                        invoice.getLines().add(newLine);
                }
            }
            
            mainView.setInvoices(invoices);
            InvTable invTable = new InvTable(invoices);
            mainView.setInvTable(invTable);
            mainView.getInvoicesTbl().setModel(invTable);
            mainView.getInvTable().fireTableDataChanged();
        }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void saveFileAction() {
    }

    private void createNewInvoiceAction() {
    }

    private void deleteInvoiceAction() {
    }

    private void createNewItemAction() {
    }

    private void deleteItemAction() {
    } 

    
}
