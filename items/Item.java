/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.io.Serializable;

/**
 *
 * @author gabegm
 */
public abstract class Item implements Serializable {
    private int id;
    private String name;
    private String desc;
    private double price;
    private int markupPercentage;
    private int stockQuantity;

    public Item() {
        this.id = 0;
        this.name = null;
        this.desc = null;
        this.price = 0;
        this.markupPercentage = 0;
        this.stockQuantity = 0;
    }

    public Item(int id, String name, String desc, double price, int markupPercentage, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.markupPercentage = markupPercentage;
        this.stockQuantity = stockQuantity;
    }
    
    public double calcPrice() {
        return price*markupPercentage;
    }
    
    public void sale(int amount) {
        stockQuantity -= amount;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", desc=" + desc + ", price=" + price + ", markupPercentage=" + markupPercentage + ", stockQuantity=" + stockQuantity;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the markupPercentage
     */
    public int getMarkupPercentage() {
        return markupPercentage;
    }

    /**
     * @param markupPercentage the markupPercentage to set
     */
    public void setMarkupPercentage(int markupPercentage) {
        this.markupPercentage = markupPercentage;
    }

    /**
     * @return the stockQuantity
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * @param stockQuantity the stockQuantity to set
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
}
