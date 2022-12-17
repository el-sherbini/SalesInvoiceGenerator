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
public class NewInvWindow extends JDialog{
    private final JLabel customerNameLbl = new JLabel("Customer Name:");
    private final JTextField customerNameTxtField = new JTextField(30);
    private final JLabel invDateLbl = new JLabel("Invoice Date:");
    private final JTextField invDateTxtField = new JTextField(25);
    private final JButton addBtn = new JButton("Add");
    private final JButton cancelBtn = new JButton("Cancel");

    public NewInvWindow(MainWindow MainWindow) {
        addBtn.setActionCommand("Add New Invoice");
        cancelBtn.setActionCommand("Cancel New Invoice");
        
        addBtn.addActionListener(MainWindow.getActionHandler());
        cancelBtn.addActionListener(MainWindow.getActionHandler());
        
        setLayout(new GridLayout(3, 2));
        setTitle("Create New Invoice:");
        
        add(customerNameLbl);
        add(customerNameTxtField);
        add(invDateLbl);
        add(invDateTxtField);
        add(addBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getCustomerNameTxtField() {
        return customerNameTxtField;
    }

    public JTextField getInvDateTxtField() {
        return invDateTxtField;
    }
}
