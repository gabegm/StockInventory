/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockinventory;

import items.*;
import exceptions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabegm
 */
public class StockInventory {
    public ArrayList<Item> itemList = new ArrayList();
    public Scanner scan;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StockInventory stockInventory = new StockInventory();
        int choice = 0;
        do {
            choice = stockInventory.promptUser("1. Add item\n2. List all items\n3. Search for item\n4. Sell item\n5. Delete item\n6. Edit item\n7. Save to file\n8. Load from file\n9. Exit").nextInt();
            switch(choice) {
                case 1:
                    stockInventory.addItem();
                    break;
                case 2:
                    stockInventory.listAllItems();
                    break;
                case 3:
                    System.out.println(stockInventory.searchForItemById());
                    break;
                case 4:
                    stockInventory.sellItem();
                    break;
                case 5:
                    stockInventory.deleteItem();
                    break;
                case 6:
                    stockInventory.editItem();
                    break;
                case 7:
                    stockInventory.saveItemToDisk();
                    break;
                case 8:
                    stockInventory.loadItemFromDisk();
                    break;
                case 9:
                    //let the do-while loop do all the work 
                    break;
            } 
        }while(choice != 9);
    }
    
    public Scanner promptUser(String message) {
        System.out.println(message);
        return scan = new Scanner(System.in);
    }
    
    public void addItem() {
        int choice = promptUser("1. Food item\n2. Quantity item").nextInt();
        int id = promptUser("Enter item id").nextInt();
        String name = promptUser("Enter item name").next();
        String desc = promptUser("Enter item description").next();
        Double price = promptUser("Enter item price").nextDouble();
        int markupPercentage = promptUser("Enter item markup percentage").nextInt();
        int stockQuantity = promptUser("Enter item stock quantity").nextInt();
        
        switch(choice) {
            case 1:
                String expiryDate = promptUser("Enter item expiry date").next();
                int discount = promptUser("Enter item discount").nextInt();
                
                FoodItem foodItem = new FoodItem(id, name, desc, price, markupPercentage, stockQuantity, convertStringToDate(expiryDate), discount);
                
                itemList.add(foodItem);
                
                break;
            case 2:
                int qtyPerPacket = promptUser("Enter item quantity per packet").nextInt();
                
                QtyItems qtyItems = new QtyItems(id, name, desc, price, markupPercentage, stockQuantity, qtyPerPacket);
                
                itemList.add(qtyItems);
                
                break;
            default:
                System.out.println("Input not recognised");
                break;
        }
    }
    
    public void listAllItems() {
        for(Item i: itemList) {
            System.out.println(i);
        }
    }
    
    public Item searchForItemById() {
        int id = promptUser("Enter item ssn").nextInt();
        
        for(Item i: itemList) {
            if(i.getId() == id) {
                return i;
            }
        }
        return null;
    }
    
    public void sellItem() {
        Item i = searchForItemById();
        int amount = promptUser("Enter amount").nextInt();
            
        i.sale(amount);
        try {    
            isStockLowerThanThree(i.getStockQuantity());
        } catch (OutOfStockException ex) {
            Logger.getLogger(StockInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteItem() {
        itemList.remove(searchForItemById());
    }
    
    public void editItem() {
        Item i = searchForItemById();
        itemList.remove(i);
        
        i.setId(promptUser("Enter item id").nextInt());
        i.setName(promptUser("Enter item name").next());
        i.setDesc(promptUser("Enter item description").next());
        i.setPrice(promptUser("Enter item price").nextDouble());
        i.setMarkupPercentage(promptUser("Enter item markup percentage").nextInt());
        i.setStockQuantity(promptUser("Enter item stock quantity").nextInt());
        
        if(i instanceof FoodItem) {
            FoodItem foodItem = (FoodItem)i;
            
            foodItem.setExpiryDate(convertStringToDate(promptUser("Enter item expiry date").next()));
            foodItem.setDiscount(promptUser("Enter item discount").nextInt());
            
            itemList.add(foodItem);
        }else if(i instanceof QtyItems) {
            QtyItems qtyItems = (QtyItems)i;
            
            qtyItems.setQtyPerPackage(promptUser("Enter item quantity per packet").nextInt());
            
            itemList.add(qtyItems);
        }
    }
    
    public void saveItemToDisk() {
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream("items.itm"));
            objectOutput.writeObject(itemList);
            objectOutput.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StockInventory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StockInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadItemFromDisk() {
        try {
            File file = new File("items.itm");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            itemList = (ArrayList<Item>)objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(StockInventory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isStockLowerThanThree(int stockQuantity) throws OutOfStockException {
        if(stockQuantity < 3) {
            throw new OutOfStockException();
        }
    }
    
    public Date convertStringToDate(String expiryDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(expiryDate);
        } catch (ParseException ex) {
            Logger.getLogger(StockInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
