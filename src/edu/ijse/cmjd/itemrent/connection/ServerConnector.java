package edu.ijse.cmjd.itemrent.connection;

import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.OrderDetailController;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import edu.ijse.cmjd.itemrent.controller.HistoryController;
import edu.ijse.cmjd.itemrent.controller.UserController;
import edu.ijse.cmjd.itemrent.controller.RemoteFactory;
import java.net.MalformedURLException;
import java.rmi.*;

public class ServerConnector {

    private static ServerConnector sConnector;
    private ClientController controller;
    private ItemController itemController;
    private OrderController orderController;
    private OrderDetailController orderDetailController;
    private HistoryController historyController;
    private UserController userController;
    private RemoteFactory factory;

    private ServerConnector() throws NotBoundException, MalformedURLException, RemoteException {
        factory = (RemoteFactory) Naming.lookup("rmi://localhost:5050/Client");

    }

    public static ServerConnector serverConnection() throws NotBoundException, MalformedURLException, RemoteException {
        if (sConnector == null) {
            sConnector = new ServerConnector();
        }
        return sConnector;
    }

    public ClientController getServerConnection() throws RemoteException {
        if (controller == null) {
            controller = factory.getClientController();
        }
        return controller;
    }

    public ItemController getItemController() throws RemoteException {
        if (itemController == null) {
            itemController = factory.getItemController();
        }
        return itemController;
    }

    public OrderController getOrderController() throws RemoteException {
        if (orderController == null) {
            orderController = factory.getOrderController();
        }
        return orderController;
    }

    public OrderDetailController getOrderDetailController() throws RemoteException {
        if (orderDetailController == null) {
            orderDetailController = factory.getOrderDetailController();
        }
        return orderDetailController;
    }

    public HistoryController getHistoryController() throws RemoteException {
        if (historyController == null) {
            historyController = factory.getHistoryController();
        }
        return historyController;
    }

    public UserController getUserController() throws RemoteException {
        if (userController == null) {
            userController = factory.getUserController();
        }
        return userController;
    }
}
