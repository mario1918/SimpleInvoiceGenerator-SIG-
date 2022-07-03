/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.model;

import com.invoice.model.InvoiceItems;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mario
 */
public class ItemsTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceItems> itemsArray;
    private String[] cols = {"Item Name", "Unit Price", "Customer Name", "Item Total"};

    public ItemsTableModel(ArrayList<InvoiceItems> itemsArray) {
        this.itemsArray = itemsArray;
    }

    public ItemsTableModel() {
        
    }

    
    
    @Override
    public int getRowCount() {
        return itemsArray == null ? 0 : itemsArray.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(itemsArray == null)
        {
            return null;
        }else {
           InvoiceItems item = itemsArray.get(rowIndex);
            switch(columnIndex)
            {
                case 0:
                    return item.getItem();
                case 1:
                    return item.getItemPrice();
                case 2:
                    return item.getItemCount();
                case 3:
                    return item.getItemTotal();
                default:
                    return "";
            } 
        }
        
    }
    
    @Override
    public String getColumnName(int col)
    {
        return cols[col];
    }
    
}
