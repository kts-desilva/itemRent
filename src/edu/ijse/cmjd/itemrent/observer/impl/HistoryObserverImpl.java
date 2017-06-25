/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.observer.impl;

import edu.ijse.cmjd.itemrent.observer.HistoryObserver;
import edu.ijse.cmjd.itemrent.ui.History;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author User
 */
public class HistoryObserverImpl extends UnicastRemoteObject implements HistoryObserver{
    
    private History history;
    
    public HistoryObserverImpl(History history)throws RemoteException{
        this.history=history;
    }

    @Override
    public void update() throws RemoteException {
        history.setMessage();
    }
}
