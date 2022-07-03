    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.model;

/**
 *
 * @author Mario
 */
public class InvoiceItems {
    private String item;
    private double itemPrice;
    private int itemCount;
    private InvoiceHeader header;

    public InvoiceItems(String item, double itemPrice, int itemCount, InvoiceHeader header) {
        this.item = item;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
    
    public double getItemTotal() {
        return itemPrice * itemCount;
    }

    @Override
    public String toString() {
        return header.getNum() + "," + item + "," + itemPrice + "," + itemCount;
    }
}
