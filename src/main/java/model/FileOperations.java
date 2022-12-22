/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.MainWindow;

/**
 *
 * @author Mohamed Emad
 */
public class FileOperations {
    private MainWindow mainWindow;
    
    public FileOperations(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    
    public void readFile() {
        String dir = System.getProperty("user.dir"); // Get the current directory
        
        try {
            JFileChooser fc = new JFileChooser();
            
            ArrayList<InvHeader> invHeaders = new ArrayList<>();
            
            if (mainWindow.getInvs().isEmpty()) {
                Path defaultHeaderPath = Paths.get(dir + "/InvoiceHeader.csv");
                Path defaultLinePath = Paths.get(dir + "/InvoiceLine.csv");
                invHeaders = getFileInvHeaders(defaultHeaderPath);
                setFileInvLines(defaultLinePath, invHeaders);
            } else if (fc.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                Path headerPath = Paths.get(fc.getSelectedFile().getAbsolutePath()); // Getting the path of the header file
                invHeaders = getFileInvHeaders(headerPath);
                
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    Path linePath = Paths.get(fc.getSelectedFile().getAbsolutePath()); // Getting the path of the line file
                    setFileInvLines(linePath, invHeaders);
                }
            }
            
            InvTblModel invTblModel = new InvTblModel(invHeaders, mainWindow);

            mainWindow.setInvs(invHeaders);
            mainWindow.setInvTblModel(invTblModel);
            mainWindow.getInvsTbl().setModel(invTblModel);

            mainWindow.getInvTblModel().fireTableDataChanged();
        } catch (IOException | ParseException ex) {
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void writeFile() {
        ArrayList<InvHeader> invs = mainWindow.getInvs();
        
        try {
            JFileChooser fc = new JFileChooser();
        
            if (fc.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                String invHeaders = "", invLines = "";

                for (InvHeader inv: invs) {
                    invHeaders += inv.getCSVFormat();
                    invHeaders += "\n";

                    for (InvLine line : inv.getLines()) {
                        invLines += line.getCSVFormat();
                        invLines += "\n";
                    }
                }

                File headerFile = fc.getSelectedFile();

                try (FileWriter headerFileWriter = new FileWriter(headerFile)) {
                    headerFileWriter.write(invHeaders);
                    headerFileWriter.flush();
                    headerFileWriter.close();
                }

                if (fc.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();

                    try (FileWriter lineFileWriter = new FileWriter(lineFile + ".csv")) {
                        lineFileWriter.write(invLines);
                        lineFileWriter.flush();
                        lineFileWriter.close();
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
    public ArrayList<InvHeader> getFileInvHeaders (Path path) throws IOException, ParseException{
        ArrayList<InvHeader> invHeaders = new ArrayList<>();
        List<String> headerLines = Files.readAllLines(path);

        for (String line: headerLines) {
            String[] headerArr = line.split(","); // Getting every item individual in an array [Invoice Number, Invoice Date, Customer Name]

            // Adding a new row of invoice header from the file
            InvHeader newHeader = new InvHeader(Integer.parseInt(headerArr[0]), mainWindow.dateFormat.parse(headerArr[1]), headerArr[2], mainWindow);
            invHeaders.add(newHeader);
        }
        
        return invHeaders;
    }
    
    public void setFileInvLines (Path path, ArrayList<InvHeader> invHeaders) throws IOException{
        List<String> itemLines = Files.readAllLines(path);
        InvHeader inv = null;

        for (String line: itemLines) {
            String[] lineArr = line.split(","); // Getting every item individual in an array [Item Name, Item Price, Count, InvHeader Model]

            for (InvHeader invoice : invHeaders) {
                if (invoice.getInvNum()== Integer.parseInt(lineArr[0])) {
                    inv = invoice;
                    break;
                }
            }

            // Adding a new row of invoice line from the file
            InvLine newLine = new InvLine(lineArr[1], Double.parseDouble(lineArr[2]), Integer.parseInt(lineArr[3]), inv);
            inv.getLines().add(newLine);
        }
    }
    
}
