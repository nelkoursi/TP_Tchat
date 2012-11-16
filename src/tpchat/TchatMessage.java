package src.tpchat;

import java.io.Serializable;

/**
*
* @author Gaetan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
*/

public class TchatMessage implements Serializable {
   
   private static final long serialVersionUID = 1L;
   
   private String mMessage;
   private String mEmetteur;
   private String mDestinataire;
   
   public String getMessage() {
      return mMessage;
   }
   
   public void setMessage(String pMessage) {
      mMessage = pMessage;
   }
   
   public String getEmetteur() {
      return mEmetteur;
   }
   
   public void setEmetteur(String pEmetteur) {
      mEmetteur = pEmetteur;
   }

   public String getDestinataire() {
      return mDestinataire;
   }

   public void setDestinataire(String pDestinataire) {
      mDestinataire = pDestinataire;
   }   
   
}