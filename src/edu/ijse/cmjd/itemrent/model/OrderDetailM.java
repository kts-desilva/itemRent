package edu.ijse.cmjd.itemrent.model;

import java.io.*;

public class OrderDetailM implements Serializable {

    private String orderID;
    private String id;
    private String des;
    private int qty;
    private double price;
    private double total;

    public OrderDetailM() {
    }

    public OrderDetailM(String orderID,String id, String des, int qty, double price, double total) {
        this.orderID=orderID;
        this.id = id;
        this.des = des;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

}
