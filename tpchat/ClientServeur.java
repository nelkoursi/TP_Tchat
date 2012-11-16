package tpchat;
import java.io.Serializable;
import java.rmi.*;

/**
 *
 * @author Gaetan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
 */
public class ClientServeur  implements Serializable  {
	protected String id;
	protected boolean estConnecte = 0;
	protected InterfaceRMIChat interfaceChat;
	
	//getteur et setteur de l'identifiant du client
	public String getID() {
		return id;
	}
	public void setID(String iD) {
		this.id = iD;
	}
	
	//getteur et setteur de l'interface du serveur du chat
	public InterfaceRMIChat getInterfaceChat() {
		return interfaceChat;
	}
	public void setServChat(InterfaceRMIChat servChat) {
		this.interfaceChat = interfaceChat;
	}
    
	//getteur et setteur de estConnecté
	public boolean getEstConnecte() {
		return estConnecte;
	}
	public void setEstConnecte(boolean estConnecte) {
		this.estConnecte = estConnecte;
	}
	
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