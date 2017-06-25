package edu.ijse.cmjd.itemrent.model;

import java.io.*;

public class ItemM implements Serializable {

    private String ID;
    private String des;
    private String price;
    private String sport;
    private String brand;
    private String qty;
    private String since;
    private String length;
    private String width;
    private String radius;
    private String weight;

    public ItemM() {
    }

    public ItemM(String ID, String des, String price, String sport, String brand, String qty, String since, String length, String width, String radius, String weight) {
        this.ID = ID;
        this.des = des;
        this.price = price;
        this.sport = sport;
        this.brand = brand;
        this.qty = qty;
        this.since = since;
        this.length = length;
        this.width = width;
        this.radius = radius;
        this.weight = weight;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
