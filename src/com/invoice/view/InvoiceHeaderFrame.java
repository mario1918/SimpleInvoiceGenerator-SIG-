/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class InvoiceHeaderFrame extends JDialog {
    private JTextField customerName_addNewInvoice_txtbox;
    private JTextField invDate_addNewInvoice_txtbox;
    private JLabel customerName_label;
    private JLabel invDate_label;
    private JButton add_btn;
    private JButton cancel_btn;

    public InvoiceHeaderFrame(MainJFrame frame) {
        customerName_label = new JLabel("Customer Name:");
        customerName_addNewInvoice_txtbox = new JTextField(20);
        invDate_label = new JLabel("Invoice Date:");
        invDate_addNewInvoice_txtbox = new JTextField(20);
        add_btn = new JButton("Add");
        cancel_btn = new JButton("Cancel");
        
        add_btn.setActionCommand("addNewInvoice_OK");
        cancel_btn.setActionCommand("addNewInvoice_Cancel");
        
        add_btn.addActionListener(frame.getActionListener());
        cancel_btn.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(invDate_label);
        add(invDate_addNewInvoice_txtbox);
        add(customerName_label);
        add(customerName_addNewInvoice_txtbox);
        add(add_btn);
        add(cancel_btn);
        
        pack();
        
    }

    public JTextField getCustomerName_addNewInvoice_txtbox() {
        return customerName_addNewInvoice_txtbox;
    }

    public JTextField getInvDate_addNewInvoice_txtbox() {
        return invDate_addNewInvoice_txtbox;
    }

   
    
}
