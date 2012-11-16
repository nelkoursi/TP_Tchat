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
    
    public static void main(String args[]) {
    	try {
    		// Crée une instance de l ’objet serveur.
    		InterfaceRMIChat obj = new ServeurChat();
    		 
    		// Enregistre l'objet créer auprès du serveur de noms.
    		Naming.rebind("//ma_machine/mon_serveur", obj);
    		System.out.println("ServeurChat " + " bound in registry");
    		} 
    		catch (Exception exc) {… }
    	}
    }
}