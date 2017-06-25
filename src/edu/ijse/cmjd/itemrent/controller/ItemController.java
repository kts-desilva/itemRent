package edu.ijse.cmjd.itemrent.controller;

import edu.ijse.cmjd.itemrent.model.ItemM;
import edu.ijse.cmjd.itemrent.exception.DuplicateKeyException;
import edu.ijse.cmjd.itemrent.observer.ItemObserver;
import java.rmi.*;
import java.util.*;
import java.io.*;

public interface  ItemController extends Remote{
	public boolean addItem(ItemM model) throws RemoteException,ClassNotFoundException,IOException,DuplicateKeyException;
	public boolean editItem(ItemM model)throws RemoteException,ClassNotFoundException,IOException;
	public boolean deleteItem(String id)throws RemoteException,ClassNotFoundException,IOException;
	public ItemM serachItem(String id)throws RemoteException,ClassNotFoundException,IOException;
	public ArrayList<ItemM> getAllItems()throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<ItemM> getItemFilteredDesc(String desc)throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<ItemM> getItemFilteredBrand(String brand)throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<ItemM> getItemFilteredSport(String sport)throws RemoteException,ClassNotFoundException,IOException;
        public String getLastID()throws RemoteException,ClassNotFoundException,IOException;
        public boolean reserveItem(String id) throws RemoteException; 
	public boolean releaseItem(String id) throws RemoteException; 
	public void addItemObserver(ItemObserver observer) throws RemoteException; 
	public void removeItemObserver(ItemObserver observer) throws RemoteException; 
	public void setMessage(String message) throws RemoteException; 
}
