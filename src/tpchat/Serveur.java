/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpchat;

import java.rmi.Remote;

/**
 *
 * @author Tony MARTIN
 */
public interface Serveur extends Remote {
        
        public void afficheMessage();

        public void connect (String id);
        
        public void send (String id);
        
        public void bye (String id);
        
        public String[] who ();
        
        public String getMessage(int i);
}
