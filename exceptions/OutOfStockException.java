/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author gabegm
 */
public class OutOfStockException extends Exception {

    public OutOfStockException() {
        super("WARNING STOCK QUANTITY HAS FALLEN BELOW 3");
    }

    public OutOfStockException(String message) {
        super(message);
    }
    
}
