/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpchat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gaëtan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
 */
public class ServeurChat implements Serveur {

        private List<String> utilisateurs;
        private List<String> messages;

        public ServeurChat() {
                utilisateurs = new ArrayList<String>();
        }

        @Override
        public void connect(String id) throws IDIndisponibleException{
                if (utilisateurs.contains(id)) {
                        throw new IDIndisponibleException();
                }
        }

        @Override
        public void send(String id) {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void bye(String id) {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String[] who() {
                throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String getMessage(int i) {
                throw new UnsupportedOperationException("Not supported yet.");
        }

}
