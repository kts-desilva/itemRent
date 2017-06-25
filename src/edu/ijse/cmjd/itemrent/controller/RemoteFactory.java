/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author User
 */
public interface RemoteFactory extends Remote{
    public ClientController getClientController()throws RemoteException;
    public ItemController getItemController()throws RemoteException;
    public OrderController getOrderController()throws RemoteException;
    public OrderDetailController getOrderDetailController()throws RemoteException;
    public HistoryController getHistoryController()throws RemoteException;
    public UserController getUserController()throws RemoteException;
}
