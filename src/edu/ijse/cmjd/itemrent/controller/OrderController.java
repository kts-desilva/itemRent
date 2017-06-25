package edu.ijse.cmjd.itemrent.controller;

import edu.ijse.cmjd.itemrent.model.OrderM;
import edu.ijse.cmjd.itemrent.model.OrderDetailM;
import edu.ijse.cmjd.itemrent.exception.DuplicateKeyException;
import edu.ijse.cmjd.itemrent.observer.OrderObserver;
import java.rmi.*;
import java.util.*;
import java.io.*;

public interface  OrderController extends Remote{
	public boolean addOrder(OrderM model,ArrayList<OrderDetailM> list) throws RemoteException,ClassNotFoundException,IOException,DuplicateKeyException;
	public boolean editOrder(OrderM model)throws RemoteException,ClassNotFoundException,IOException;
	public OrderM serachOrder(String id)throws RemoteException,ClassNotFoundException,IOException;
	public ArrayList<OrderM> getAllOrders()throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<OrderM> getOrdersFilteredDate(String date)throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<OrderM> getOrdersFilteredClient(String CID)throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<OrderM> getOrdersFilteredClientName(String name)throws RemoteException,ClassNotFoundException,IOException;
        public String getLastID()throws RemoteException,ClassNotFoundException,IOException;
        public boolean reserverOrder(String id)throws RemoteException;
        public boolean releaseOrder(String id)throws RemoteException;
        public void addOrderObserver(OrderObserver observer)throws RemoteException;
        public void removeOrderObserver(OrderObserver observer)throws RemoteException;
        public void setMessage(String message)throws RemoteException;
}
