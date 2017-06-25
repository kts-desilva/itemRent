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
public interface UserController extends Remote{
    public boolean addUser(String id,String password)throws RemoteException,IOException;
    public String getPassword(String id)throws RemoteException,IOException;
}
