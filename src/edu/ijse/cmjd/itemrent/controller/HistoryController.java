/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.controller;

import edu.ijse.cmjd.itemrent.model.HistoryM;
import edu.ijse.cmjd.itemrent.observer.HistoryObserver;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author User
 */
public interface HistoryController extends Remote{
    public void addHistory(HistoryM historyM)throws RemoteException,ClassNotFoundException,IOException;
    public List<HistoryM> viewHistory()throws RemoteException,ClassNotFoundException,IOException;
    public void addHistoryObserver(HistoryObserver observer)throws RemoteException;
    public void removeHistoryObserver(HistoryObserver observer)throws RemoteException;
}
