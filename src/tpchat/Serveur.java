/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpchat;

import java.rmi.Remote;

/**
*
* @author Ga�tan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
*/
public interface Serveur extends Remote{
        public void afficheMessage();
}
