/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.observer.impl;

import edu.ijse.cmjd.itemrent.ui.Item;
import edu.ijse.cmjd.itemrent.observer.ItemObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author User
 */
public class ItemObserverImpl extends UnicastRemoteObject implements ItemObserver{
    
    private Item item;
    
    public ItemObserverImpl(Item item) throws RemoteException{
        this.item=item;
    }

    @Override
    public void update(String message) throws RemoteException {
        item.setMessage(message);
    }
    
}
