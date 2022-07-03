/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice.controller;

import com.invoice.model.InvoiceHeader;
import com.invoice.model.InvoiceHeaderTableModel;
import com.invoice.model.InvoiceItems;
import com.invoice.model.ItemsTableModel;
import com.invoice.view.InvoiceHeaderFrame;
import com.invoice.view.InvoiceItemFrame;
import com.invoice.view.MainJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class actionListenerController implements ActionListener{
    
    private MainJFrame frame;
    private InvoiceHeaderFrame headerFrame;
    private InvoiceItemFrame itemFrame;
            
    public actionListenerController(MainJFrame frame)
    {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save Invoice":
                saveFiles();
                break;

            case "Load Invoice":
        {
            try {
                loadInvoice();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Error", "Error in loading files", JOptionPane.ERROR_MESSAGE);
            }
        }
                break;

            case "Create New Invoice":
                createNewInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Add New Item":
                createNewItem();
                break;
                
            case "Delete Item":
                deleteItem();
                break;
                
            case "addNewInvoice_OK":
                addNewInvoiceOK();
                break;
                
            case "addNewInvoice_Cancel":
                addNewInvoiceCancel();
                break;
                
            case "newItem_add":
                addNewItemSave();
                break;
                
            case "newItem_cancel":
                addNewItemCancel();
                break;
                
        }
        
    }

    private void saveFiles() {
        ArrayList<InvoiceHeader> invArray = frame.getInvoicesHeaderArray();
        JFileChooser fc = new JFileChooser("invoices\\");
        
        try
        {
            JOptionPane.showMessageDialog(frame, "Please Select where do you want to save the header file", "Header File", JOptionPane.INFORMATION_MESSAGE);
            int result = fc.showSaveDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File headerFile = fc.getSelectedFile();
                FileWriter fw = new FileWriter(headerFile);
                String header = "";
                String items = "";
                for(InvoiceHeader invoice: invArray) 
                {
                    header += invoice.toString();
                    header += "\n";
                    for(InvoiceItems item: invoice.getItems())
                    {
                        items += item.toString();
                        items += "\n";
                    }
                }
                
                header = header.substring(0, header.length()-1);
                items = items.substring(0, items.length()-1);
                
                JOptionPane.showMessageDialog(frame, "Please Select where do you want to save the items file", "Items File", JOptionPane.INFORMATION_MESSAGE);
                result = fc.showSaveDialog(frame);
                File itemFile = fc.getSelectedFile();
                FileWriter fw1 = new FileWriter(itemFile);
                
                fw.write(header);
                fw1.write(items);
                fw.close();
                fw1.close();
                
                JOptionPane.showMessageDialog(frame, "Files saved successfully", "Saved Successfully", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(frame, "Error in saving the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadInvoice() throws ParseException {
        JOptionPane.showMessageDialog(frame, "Please choose the header file", "Header File", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fc = new JFileChooser("invoices\\");
        try
        {
        
            int result = fc.showOpenDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                // Reading header CSV file
                File headerInvoice = fc.getSelectedFile();
                Path headerPath = Paths.get(headerInvoice.getAbsolutePath());
                List<String> headerContents = Files.readAllLines(headerPath);
                ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
                for(String headerContent: headerContents)
                {
                  String header[] = headerContent.split(",");
                  //String num_str = header[0];
                  //String date_str = header[1];
                  String name_str = header[2];
                  int invoiceNum = Integer.parseInt(header[0]);
                  Date invoiceDate = MainJFrame.df.parse(header[1]);
                  InvoiceHeader newHeader = new InvoiceHeader(invoiceNum, name_str,invoiceDate);
                  invoiceHeaders.add(newHeader);

                }
            frame.setInvoicesHeaderArray(invoiceHeaders);
            
            
            //  Reading invoice items CSV
            JOptionPane.showMessageDialog(frame, "Please choose the items file", "Items File", JOptionPane.INFORMATION_MESSAGE);
            result = fc.showOpenDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File itemsFile = fc.getSelectedFile();
                Path itemsFilePath = Paths.get(itemsFile.getAbsolutePath());
                List<String> itemsLines = Files.readAllLines(itemsFilePath);
                ArrayList<InvoiceItems> invoiceItems = new ArrayList<>();
                
                for (String lineLine : itemsLines) 
                {
                    String[] arr = lineLine.split(",");
                    String str1 = arr[0];    // invoice num (int)
                    String str2 = arr[1];    // item name   (String)
                    String str3 = arr[2];    // price       (double)
                    String str4 = arr[3];    // count       (int)
                    int invoiceNum = Integer.parseInt(str1);
                    double itemPrice = Double.parseDouble(str3);
                    int itemCount = Integer.parseInt(str4);
                    InvoiceHeader header = frame.getInvoiceByNum(invoiceNum);
                    InvoiceItems line = new InvoiceItems(str2, itemPrice, itemCount, header);
                    header.getItems().add(line);
                }
                
            }
            
            // Table Model
            InvoiceHeaderTableModel headerTableModel = new InvoiceHeaderTableModel(invoiceHeaders);
            frame.setHeaderTableModel(headerTableModel);
            frame.getjTable2().setModel(headerTableModel);
            
            
            }
            
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error", "Error in reading header file", JOptionPane.ERROR_MESSAGE);
            }
        
        System.out.println("Files read successfully");
    }   

    private void createNewInvoice() {
        headerFrame = new InvoiceHeaderFrame(frame);
        headerFrame.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoice = frame.getjTable2().getSelectedRow();
        if(selectedInvoice != 1)
        {
            frame.getInvoicesHeaderArray().remove(selectedInvoice);
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getjTable1().setModel(new ItemsTableModel(null));
            
            frame.setItemsArray(null);

            //Getting invoice details and assigning it to frame objects
            frame.getCustomerName_textbox().setText("");
            frame.getInvoiceNo_label().setText("");
            frame.getInvoiceTotal_label().setText("");
            frame.getInvoiceDate_textbox().setText("");
        }
    }

    private void createNewItem() {
        itemFrame = new InvoiceItemFrame(frame);
        itemFrame.setVisible(true);
    }

    private void deleteItem() {
        // Removing item
        int selectedItem = frame.getjTable1().getSelectedRow();
        
        if(selectedItem != -1)
        {
            frame.getItemsArray().remove(selectedItem);
            //Update tables
            ItemsTableModel itemModel =(ItemsTableModel) frame.getjTable1().getModel();
            itemModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
        }
        
        
    }

    private void addNewInvoiceOK() {
        headerFrame.setVisible(false);
        
        String custName = headerFrame.getCustomerName_addNewInvoice_txtbox().getText();
        String date_str = headerFrame.getInvDate_addNewInvoice_txtbox().getText();
        
        // If date is entered wrongly, set today's date
        Date date = new Date();
        try {
            date = MainJFrame.df.parse(date_str);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Date is entered correctly. Setting to today's date","Error",  JOptionPane.ERROR_MESSAGE);
        }
        
        
        int invNumber = 0;
        for(InvoiceHeader invHead : frame.getInvoicesHeaderArray())
        {
            if(invHead.getNum() > invNumber)
            {
                invNumber = invHead.getNum();
            }
        }
        invNumber++;
        
        InvoiceHeader inv = new InvoiceHeader(0, custName, date);
        frame.getInvoicesHeaderArray().add(inv);
        frame.getHeaderTableModel().fireTableDataChanged();
        
        headerFrame.dispose();
        headerFrame = null;
    }

    private void addNewInvoiceCancel() {
        headerFrame.setVisible(false);
        headerFrame.dispose();
        headerFrame = null;
    }

    private void addNewItemSave() {
        itemFrame.setVisible(false);
        
        //Get data from frame
        String itemName = itemFrame.getItemName_txtbox().getText();
        String itemCount_str = itemFrame.getItemCount_txtbox().getText();
        String itemPrice_str = itemFrame.getItemPrice_txtbox().getText();
        int itemCount = 1;
        double itemPrice = 1;
        
        try
        {
            itemCount = Integer.parseInt(itemCount_str);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(frame, "Cannot convert numbers", "Invalid number formate", JOptionPane.ERROR_MESSAGE);
        }
        
        try
        {
            itemPrice = Double.parseDouble(itemPrice_str);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid price formate", JOptionPane.ERROR_MESSAGE);
        }
        
        //Write  data
        int selectedHeader = frame.getjTable2().getSelectedRow();
        if(selectedHeader != -1)
        {
            InvoiceHeader header =  frame.getInvoicesHeaderArray().get(selectedHeader);
            InvoiceItems item = new InvoiceItems(itemName, itemPrice, itemCount, header);
            //header.getItems().add(item);
            frame.getItemsArray().add(item);
             
            //Update tables
            ItemsTableModel itemModel =(ItemsTableModel) frame.getjTable1().getModel();
            itemModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();

        }
        
        itemFrame.dispose();
        itemFrame = null;
    }

    private void addNewItemCancel() {
        itemFrame.setVisible(false);
        itemFrame.dispose();
        itemFrame = null;
    }
    
    
    
   
    
}
