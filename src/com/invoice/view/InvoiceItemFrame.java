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
public class InvoiceItemFrame extends JDialog{
    private JTextField itemName_txtbox;
    private JTextField itemCount_txtbox;
    private JTextField itemPrice_txtbox;
    private JLabel itemName_label;
    private JLabel itemCount_label;
    private JLabel itemPrice_label;
    private JButton add_btn;
    private JButton cancel_btn;
    
    public InvoiceItemFrame(MainJFrame frame) {
        itemName_txtbox = new JTextField(20);
        itemName_label = new JLabel("Item Name");
        
        itemCount_txtbox = new JTextField(20);
        itemCount_label = new JLabel("Item Count");
        
        itemPrice_txtbox = new JTextField(20);
        itemPrice_label = new JLabel("Item Price");
        
        add_btn = new JButton("Add");
        cancel_btn = new JButton("Cancel");
        
        add_btn.setActionCommand("newItem_add");
        cancel_btn.setActionCommand("newItem_cancel");
        
        add_btn.addActionListener(frame.getActionListener());
        cancel_btn.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(4, 2));
        
        add(itemName_label);
        add(itemName_txtbox);
        add(itemCount_label);
        add(itemCount_txtbox);
        add(itemPrice_label);
        add(itemPrice_txtbox);
        add(add_btn);
        add(cancel_btn);
        
        pack();
    }

    public JTextField getItemName_txtbox() {
        return itemName_txtbox;
    }

    public JTextField getItemCount_txtbox() {
        return itemCount_txtbox;
    }

    public JTextField getItemPrice_txtbox() {
        return itemPrice_txtbox;
    }

    
}
