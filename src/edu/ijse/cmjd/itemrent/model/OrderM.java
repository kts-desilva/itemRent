package edu.ijse.cmjd.itemrent.model;

import java.io.*;

public class OrderM implements Serializable {

    private String iD;
    private String date;
    private String user;
    private String client;
    private String rDate;
    private String state;

    public OrderM() {
    }

    public OrderM(String iD, String date, String user, String client, String rDate,String state) {
        this.iD = iD;
        this.date = date;
        this.user = user;
        this.client = client;
        this.rDate = rDate;
        this.state=state;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
