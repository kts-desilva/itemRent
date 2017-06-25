package edu.ijse.cmjd.itemrent.controller;

import edu.ijse.cmjd.itemrent.observer.ClientObserver;
import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.exception.DuplicateKeyException;
import java.rmi.*;
import java.util.*;
import java.io.*;

public interface  ClientController extends Remote{
	public boolean addClient(ClientM model) throws RemoteException,ClassNotFoundException,IOException,DuplicateKeyException;
	public boolean editClient(ClientM model)throws RemoteException,ClassNotFoundException,IOException;
	public boolean deleteClient(String id)throws RemoteException,ClassNotFoundException,IOException;
	public ClientM serachClient(String id)throws RemoteException,ClassNotFoundException,IOException;
	public ArrayList<ClientM> getAllClients()throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<ClientM> getClientsFilteredName(String name)throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<ClientM> getClientsFilteredAddress(String address)throws RemoteException,ClassNotFoundException,IOException;
        public ArrayList<ClientM> getClientsFilteredPackage(String pack)throws RemoteException,ClassNotFoundException,IOException;
        public String getLastID()throws RemoteException,ClassNotFoundException,IOException;
	public boolean reserveClient(String id) throws RemoteException; 
	public boolean releaseClient(String id) throws RemoteException; 
	public void addClientObserver(ClientObserver observer) throws RemoteException; 
	public void removeClientObserver(ClientObserver observer) throws RemoteException; 
	public void setMessage(String message) throws RemoteException; 
	
}
