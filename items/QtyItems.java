/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author gabegm
 */
public class QtyItems extends Item {
    private int qtyPerPackage;

    public QtyItems() {
        super();
        this.qtyPerPackage = 0;
    }

    public QtyItems(int id, String name, String desc, double price, int markupPercentage, int stockQuantity, int qtyPerPackage) {
        super(id, name, desc, price, markupPercentage, stockQuantity);
        this.qtyPerPackage = qtyPerPackage;
    }

    @Override
    public String toString() {
        return super.toString() + "qtyPerPackage=" + qtyPerPackage;
    }

    /**
     * @return the qtyPerPackage
     */
    public int getQtyPerPackage() {
        return qtyPerPackage;
    }

    /**
     * @param qtyPerPackage the qtyPerPackage to set
     */
    public void setQtyPerPackage(int qtyPerPackage) {
        this.qtyPerPackage = qtyPerPackage;
    }
    
}
