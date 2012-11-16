package src.tpchat;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServeurChat extends UnicastRemoteObject implements InterfaceRMIChat {
     String message;
    // Implémentation du constructeur
    public ServeurChat(String msg) throws java.rmi.RemoteException {
        message = msg;
    }
   // Implémentation de la méthode distante
    public void printMessage() throws java.rmi.RemoteException {
    	System.out.println(message);
    }