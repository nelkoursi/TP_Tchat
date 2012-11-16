package tpchat;

import java.rmi.*;
public class ClientServeur {
	public static void main(String args[]) {
		try {
			// Récupération d'un stub sur l'objet serveur.
			InterfaceRMIChat obj = (InterfaceRMIChat) Naming.lookup("//ma_machine/mon_serveur");
			// Appel d'une méthode sur l'objet distant.
			obj.printMessage();
			} 
		catch (Exception exc) {
		}
  }
}