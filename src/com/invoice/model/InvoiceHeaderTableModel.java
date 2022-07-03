/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.model;

import com.invoice.view.MainJFrame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mario
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{
    private ArrayList<InvoiceHeader> invoicesHeaderArray;
    private String []cols = {"Invoice No.", "Date", "Customer Name", "Invoice Total"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoicesHeaderArray) {
        this.invoicesHeaderArray = invoicesHeaderArray;
    }

    
    @Override
    public int getRowCount() {
        return invoicesHeaderArray.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv = invoicesHeaderArray.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return inv.getNum();
            case 1:
                return MainJFrame.df.format(inv.getInvoiceDate());
            case 2:
                return inv.getCustomerName();
            case 3:
                return inv.getInvoiceTotal();
        }
        
        return "";
    }
    
    @Override
    public String getColumnName(int col)
    {
        return cols[col];
    }
    
}
