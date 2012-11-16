package tpchat;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServeurChat extends UnicastRemoteObject implements InterfaceRMIChat {

        String message;
        // Implémentation du constructeur

        public ServeurChat(String msg) throws java.rmi.RemoteException {
                message = msg;
        }
        // Implémentation de la méthode distante

        @Override
        public void printMessage() throws java.rmi.RemoteException {
                System.out.println(message);
        }

        public static void main(String args[]) {
                int port;
                String URL;
                try { 
                        // transformation d ’une chaîne de caractères en entier
                        Integer I = new Integer(args[0]);
                        port = I.intValue();
                } catch (Exception ex) {
                        System.out.println(" Please enter: Server <port>");
                        return;
                }
                try {
                        // Création du serveur de nom - rmiregistry
                        Registry registry = LocateRegistry.createRegistry(port);
                        // Création d ’une instance de l’objet serveur
                        InterfaceRMIChat obj = new ServeurChat("Nouveau Serveur Obj");
                        // Calcul de l’URL du serveur
                        URL = "//" + InetAddress.getLocalHost().getHostName() + ":"
                                + port + "/mon_serveur";
                        Naming.rebind(URL, obj);
                } catch (Exception exc) {
                }
        }
}