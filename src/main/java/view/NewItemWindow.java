/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Mohamed Emad
 */
public class NewItemWindow extends JDialog{
    private final JLabel itmNameLbl = new JLabel("Item Name");
    private final JTextField itmNameTxtField = new JTextField(25);
    private final JLabel countLbl = new JLabel("Item Count");
    private final JTextField countTxtField = new JTextField(20);
    private final JLabel priceLbl = new JLabel("Item Price");
    private final JTextField priceTxtField = new JTextField(20);
    private final JButton addBtn = new JButton("Add");
    private final JButton cancelBtn  = new JButton("Cancel");
    
    public NewItemWindow(MainWindow mainWindow) {
        addBtn.setActionCommand("Add Item");
        cancelBtn.setActionCommand("Cancel Add Item");
        
        addBtn.addActionListener(mainWindow.getActionHandler());
        cancelBtn.addActionListener(mainWindow.getActionHandler());
        
        setLayout(new GridLayout(4, 2));
        setTitle("Create New Item:");
        
        add(itmNameLbl);
        add(itmNameTxtField);
        add(countLbl);
        add(countTxtField);
        add(priceLbl);
        add(priceTxtField);
        add(addBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItmNameTxtField() {
        return itmNameTxtField;
    }

    public JTextField getCountTxtField() {
        return countTxtField;
    }

    public JTextField getPriceTxtField() {
        return priceTxtField;
    }

}
