package src.tpchat;

import src.tpchat.TchatPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import bean.TchatMessage;

import net.TchatClient;

public class TchatWindow extends JFrame {

   private static final long serialVersionUID = 1L;
   private TchatPanel mTchatPanel;
   
   private TchatClient mTchatClient;
   
   private String mPrivateDestinataire;
   private String mClientNom;
      
   public TchatWindow() {
      setSize(350, 350);
      setLayout(new BorderLayout(5, 5));
      
      mTchatPanel = new TchatPanel();
      mTchatPanel.addListenerToSendMessageButton(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            TchatMessage vTchatMessage = new TchatMessage();
            vTchatMessage.setEmetteur(mClientNom);
            vTchatMessage.setDestinataire(mPrivateDestinataire);
            vTchatMessage.setMessage(mTchatPanel.getMessageTextArea().getText());
            mTchatClient.getMessagesToSend().add(vTchatMessage);
                        
            mTchatPanel.displayTchatMessageInTchatArea(vTchatMessage);
            mTchatPanel.getMessageTextArea().setText("");            
         }      
         
      });
      
      add(mTchatPanel, BorderLayout.CENTER);
      
      setVisible(true); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
   }

   public TchatPanel getTchatPanel() {
      return mTchatPanel;
   }

   public void setTchatClient(TchatClient tchatClient) {
      mTchatClient = tchatClient;
   }

   public void setClientNom(String clientNom) {
      mClientNom = clientNom;
   }

   public void setPrivateDestinataire(String privateDestinataire) {
      mPrivateDestinataire = privateDestinataire;
   }
   
   public void changeTitle() {
      setTitle("Conversation avec " + mPrivateDestinataire);
   }
   
   public void displayClientsConnected() {
      ArrayList<String> vClientsConnected = new ArrayList<String>();
      vClientsConnected.add(mClientNom);
      vClientsConnected.add(mPrivateDestinataire);
      mTchatPanel.displayClientsConnected(vClientsConnected);
   }
   
   public void removePrivateDestinataire() {
      ArrayList<String> vClientsConnected = new ArrayList<String>();
      vClientsConnected.add(mClientNom);
      mTchatPanel.displayClientsConnected(vClientsConnected);
   }
   
}