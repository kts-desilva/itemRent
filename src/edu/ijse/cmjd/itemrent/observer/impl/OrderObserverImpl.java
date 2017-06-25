/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.observer.impl;

import edu.ijse.cmjd.itemrent.ui.Order;
import edu.ijse.cmjd.itemrent.observer.OrderObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author User
 */
public class OrderObserverImpl extends UnicastRemoteObject implements OrderObserver{
    
    private Order order;
    
    public OrderObserverImpl(Order order) throws RemoteException{
        this.order=order;
    }

    @Override
    public void update(String message) throws RemoteException {
        order.setMessage(message);
    }
}
