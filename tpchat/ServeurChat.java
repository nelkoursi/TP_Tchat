/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gaëtan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
 */
public class ServeurChat implements Serveur {

        private HashMap<String, Client> utilisateurs;
        
        //getteurs et setteurs de notre HashMap d'utilisateurs
        public HashMap<String, Client> getUtilisateurs() {
                return utilisateurs;
        }

        public void setUtilisateurs(HashMap<String, Client> utilisateurs) {
                this.utilisateurs = utilisateurs;
        }
        
        private LinkedList<String> messages;

        //getteurs et setteurs de notre liste de messages
        public LinkedList<String> getMessages() {
                return messages;
        }

        public void setMessages(LinkedList<String> messages) {
                this.messages = messages;
        }
        
        private int port;
       
        //getteurs et setteurs de port
        public int getPort() {
                return port;
        }

        public void setPort(int port) {
                this.port = port;
        }

        private String url;
        
        //getteurs et setteurs de l'url
        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        //constructeur de la classe
        public ServeurChat() {
                utilisateurs = new HashMap<String, Client>();
                messages = new LinkedList<String>();
        }

        //méthode pour se connecter au serveur
        @Override
        public void connect(String id, Client client) throws IDIndisponibleException {
                if (utilisateurs.containsKey(id)) {
                        throw new IDIndisponibleException();
                } else {
                        addClient(id, client);
                }
        }

        //méthode pour envoyer un message
        @Override
        public void send(String msg, Client client) throws RemoteException {
                addMessage(msg, client);
        }

        //méthode pour enlever un client
        @Override
        public void bye(Client client) throws RemoteException {
                utilisateurs.remove(client);
        }

        //méthode pour identifier quels sont les clients qui sont connectés
        @Override
        public String who() {
                String clients = "Utilisateurs connectés : \n";
                for (String id : utilisateurs.keySet()) {
                        clients = clients + id + "\n";
                }
                return clients;
        }

        //getteur d'un message
        @Override
        public String getMessage(int i) throws RemoteException {
                return messages.get(i);
        }
        
        //méthode pour ajouter un client
        private void addClient(String id, Client client) {
                utilisateurs.put(id, client);
        }
        
        //méthode pour ajouter un message
        private void addMessage(String msg, Client client) {
            messages.addLast(client + " a écrit : " + msg);
        }
        

        public static void main(String args[]) {
                int port;
                String URL;
                try { // transformation d ’une chaîne de caractères en entier
                        Integer I = new Integer(args[0]);
                        port = I.intValue();
                } 
                catch (Exception ex) {
                        System.out.println(" Please enter: Server <port>");
                        BufferedReader portKeyboard = new BufferedReader(new InputStreamReader(System.in));
                        String portString = null;
                        try {
                                portString = portKeyboard.readLine();
                        } 
                        catch (IOException ex1) {
                                Logger.getLogger(ServeurChat.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        port = Integer.parseInt(portString);        
                        return;
                }
                try {
                        // Création du serveur de nom - rmiregistry
                        Registry registry = LocateRegistry.createRegistry(port);
                        // Création d ’une instance de l’objet serveur
                        ServeurChat obj = new ServeurChat();
                        // Calcul de l’URL du serveur
                        URL = "//" + InetAddress.getLocalHost().getHostName() + ":"
                                + port + "/mon_serveur";
                        Naming.rebind(URL, obj);
                } 
                catch (Exception exc) {
                }
        }
}
