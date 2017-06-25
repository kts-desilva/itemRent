package edu.ijse.cmjd.itemrent.observer.impl;

import edu.ijse.cmjd.itemrent.observer.ClientObserver;
import edu.ijse.cmjd.itemrent.ui.Client;
import java.rmi.*;
import java.rmi.server.*;

public class ClientObserverImpl extends UnicastRemoteObject implements ClientObserver {

    private Client client;

    public ClientObserverImpl(Client client) throws RemoteException {
        this.client = client;
    }

    @Override
    public void update(String msg) throws RemoteException {
        client.setMessage(msg);
    }
}
