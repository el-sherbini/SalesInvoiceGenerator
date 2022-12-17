/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.InvHeader;
import model.InvLine;
import model.InvTblModel;
import view.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohamed Emad
 */
public class ActionHandler implements ActionListener {
    private MainWindow mainWindow;
    
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
        }
    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        
        try {
            
            if (fc.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                Path headerPath = Paths.get(fc.getSelectedFile().getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<InvHeader> invHeaders = new ArrayList<>();

                for (String line: headerLines) {
                    String[] headers = line.split(",");

                    int invNum = Integer.parseInt(headers[0]);
                    String invDate = headers[1];
                    String customerName = headers[2];

                    InvHeader newHeader = new InvHeader(invNum, invDate, customerName);
                    invHeaders.add(newHeader);
                }

                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    Path linePath = Paths.get(fc.getSelectedFile().getAbsolutePath());
                    List<String> itemLines = Files.readAllLines(linePath);
                    InvHeader invs = null;

                    for (String line: itemLines) {
                        String[] lineParts = line.split(",");
                        
                        int invNum = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        double price = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);

                        for (InvHeader invoice : invHeaders) {
                            if (invoice.getInvNum()== invNum) {
                                invs = invoice;
                                break;
                            }
                        }

                        InvLine newLine = new InvLine(itemName, price, count, invs);
                        invs.getLines().add(newLine);
                    }
                }

                mainWindow.setInvs(invHeaders);
                InvTblModel invTable = new InvTblModel(invHeaders);
                mainWindow.setInvTblModel(invTable);
                mainWindow.getInvsTbl().setModel(invTable);
                mainWindow.getInvTblModel().fireTableDataChanged();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile() {
        
    }

    private void createNewInvoice() {
    }

    private void deleteInvoice() {
        
    }

    
}
