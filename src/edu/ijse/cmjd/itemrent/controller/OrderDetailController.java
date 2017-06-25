/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.controller;

import edu.ijse.cmjd.itemrent.model.OrderDetailM;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface OrderDetailController extends Remote{
    public boolean addOrderDetail(OrderDetailM m)throws RemoteException,ClassNotFoundException,IOException;
    public ArrayList<OrderDetailM> searchOrderDetail(String id)throws RemoteException,ClassNotFoundException,IOException;
    
}
