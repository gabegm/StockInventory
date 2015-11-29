/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Date;

/**
 *
 * @author gabegm
 */
public class FoodItem extends Item {
    private Date expiryDate;
    private int discount;

    public FoodItem() {
        super();
        this.expiryDate = null;
        this.discount = 0;
    }

    public FoodItem(int id, String name, String desc, double price, int markupPercentage, int stockQuantity, Date expiryDate, int discount) {
        super(id, name, desc, price, markupPercentage, stockQuantity);
        this.expiryDate = expiryDate;
        this.discount = discount;
    }

    @Override
    public double calcPrice() {
        return super.calcPrice() - discount;
    }

    @Override
    public String toString() {
        return super.toString() + "expiryDate=" + expiryDate + ", discount=" + discount;
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
}
