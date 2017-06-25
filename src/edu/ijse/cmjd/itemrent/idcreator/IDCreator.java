/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.idcreator;

import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author User
 */
public class IDCreator {

    private static String preID;

    public IDCreator() {
    }

    public IDCreator(Remote r) throws NotBoundException, MalformedURLException, RemoteException, ClassNotFoundException, IOException {

        if (r instanceof ClientController) {
            ClientController clientController = (ClientController) r;
            preID = clientController.getLastID();
        } else if (r instanceof ItemController) {
            ItemController itemController = (ItemController) r;
            preID = itemController.getLastID();
        } else if (r instanceof OrderController) {
            OrderController orderController = (OrderController) r;
            preID = orderController.getLastID();
        } else {
        }
    }

    public static String createID() {
        String newID = "";
        String[] split = preID.split("[A-Za-z]");
        char c=preID.charAt(0);
        int id=Integer.parseInt(split[1])+1;
        NumberFormat format=new DecimalFormat();
        format.setMinimumIntegerDigits(3);
        
        newID=c+format.format(id);
        return newID;
    }
}
