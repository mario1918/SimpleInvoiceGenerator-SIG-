/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mario
 */
public class InvoiceHeader {
    private int num;
    private String customerName;
    private Date invoiceDate;
    private ArrayList<InvoiceItems> items;
    private DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
    
    public InvoiceHeader() 
    {
        
    }

    public InvoiceHeader(int num, String customerName, Date invoiceDate) {
        this.num = num;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
    }

    public InvoiceHeader(int invoiceNum, Date invoiceDate, String name_str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<InvoiceItems> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        
        return items;
    }

    public void setItems(ArrayList<InvoiceItems> items) {
        this.items = items;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }
    
    public double getInvoiceTotal() {
        double totalInvoicePrice = 0.0;
        /* Calculation of the total of the invoice */
        for(int i = 0; i < getItems().size(); i++)
        {
            totalInvoicePrice += getItems().get(i).getItemTotal();
        }
        return totalInvoicePrice;
    }

    @Override
    public String toString() {
        return num + "," + date.format(invoiceDate) + "," + customerName;
    }
}
