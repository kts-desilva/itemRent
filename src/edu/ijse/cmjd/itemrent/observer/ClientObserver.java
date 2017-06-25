package edu.ijse.cmjd.itemrent.observer;

import java.rmi.*;

public interface ClientObserver extends Remote{
	public void update(String message)throws RemoteException;
}
