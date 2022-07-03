/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.controller;

import com.invoice.model.InvoiceHeader;
import com.invoice.model.InvoiceItems;
import com.invoice.model.ItemsTableModel;
import com.invoice.view.MainJFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mario
 */
public class invoiceTableSelectionController implements ListSelectionListener{

    private MainJFrame frame;

    public invoiceTableSelectionController(MainJFrame frame) {
        this.frame = frame;
    }
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = frame.getjTable2().getSelectedRow();
        System.out.println("Invoice Selected: " + selectedInvIndex);
        if(selectedInvIndex != -1)
        {
        InvoiceHeader selectedInvoiceHeader = frame.getInvoicesHeaderArray().get(selectedInvIndex);
        
        //For the items table
        ArrayList<InvoiceItems> items = selectedInvoiceHeader.getItems();
        ItemsTableModel itemsTableModel = new ItemsTableModel(items);
        frame.setItemsArray(items);
        frame.getjTable1().setModel(itemsTableModel);
        
        //Getting invoice details and assigning it to frame objects
        frame.getCustomerName_textbox().setText(selectedInvoiceHeader.getCustomerName());
        frame.getInvoiceNo_label().setText(String.valueOf(selectedInvoiceHeader.getNum()));
        frame.getInvoiceTotal_label().setText(String.valueOf(selectedInvoiceHeader.getInvoiceTotal()));
        frame.getInvoiceDate_textbox().setText(MainJFrame.df.format(selectedInvoiceHeader.getInvoiceDate()));
        }
    }
    
}
