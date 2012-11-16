/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Gaëtan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
 */
public interface Serveur extends Remote {

        public void connect (String id, Client client) throws IDIndisponibleException, RemoteException ;
        
        public void send (String msg, Client client) throws RemoteException;
        
        public void bye (Client client) throws RemoteException;
        
        public String who () throws RemoteException;
        
        public String getMessage(int i) throws RemoteException;
}
