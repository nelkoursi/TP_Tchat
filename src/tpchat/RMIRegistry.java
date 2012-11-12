/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpchat;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 *
 * @author Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
 */
public class RMIRegistry implements Registry{
        
        private String[] listeMachines;

        @Override
        public Remote lookup(String string) throws RemoteException, NotBoundException, AccessException {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void bind(String string, Remote remote) throws RemoteException, AlreadyBoundException, AccessException {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void unbind(String string) throws RemoteException, NotBoundException, AccessException {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void rebind(String string, Remote remote) throws RemoteException, AccessException {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String[] list() throws RemoteException, AccessException {
                return listeMachines;
        }
        
}
