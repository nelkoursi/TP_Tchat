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
public interface Serveur extends Remote{
        public void afficheMessage();
}
