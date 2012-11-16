package src.tpchat;

import graphic.ConnectionPanel;
import graphic.TchatPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bean.TchatMessage;

import net.TchatClient;
import net.TchatClient.StatusClient;

/**
*
* @author Gaëtan ARRONDEAU, Nora EL KOURSI, Amandine LAVERGNE, Tony MARTIN
*/

public class TchatMainWindow extends JFrame {

        private static final long serialVersionUID = 1L;
        private TchatPanel mTchatPanel;
        private ConnectionPanel mConnectionPanel;
        private Map<String, TchatWindow> mPrivatesTchatsWindows;
        private TchatClient mTchatClient;
        private String mClientNom;

        public TchatMainWindow() {
                setTitle("Connexion au Tchat");
                setSize(550, 550);

                initConnectionPanel();

                addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent pEvent) {
                                mTchatClient.setStatusClient(StatusClient.stopped);

                                for (TchatWindow vPrivateTchatWindowTmp : mPrivatesTchatsWindows.values()) {
                                        vPrivateTchatWindowTmp.dispose();
                                }
                        }
                });

                mTchatClient = new TchatClient();
                mPrivatesTchatsWindows = new HashMap<String, TchatWindow>();

                setVisible(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        private void initConnectionPanel() {
                setLayout(new FlowLayout());

                mConnectionPanel = new ConnectionPanel();
                mConnectionPanel.addListenerToButtonTchatter(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                mClientNom = JOptionPane.showInputDialog(TchatMainWindow.this, "Veuillez entrer un pseudo pour tchatter :");
                                mTchatClient.launchTchatClient(mClientNom);

                                checkAuthentificationClientStatus();
                        }
                });

                add(mConnectionPanel);
        }

        private void initTchatPanel() {
                setTitle("Bienvenue " + mClientNom);
                remove(mConnectionPanel);
                setLayout(new BorderLayout(5, 5));

                mTchatPanel = new TchatPanel();
                mTchatPanel.addListenerToSendMessageButton(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                TchatMessage vTchatMessage = new TchatMessage();
                                vTchatMessage.setEmetteur(mClientNom);
                                vTchatMessage.setMessage(mTchatPanel.getMessageTextArea().getText());
                                mTchatClient.getMessagesToSend().add(vTchatMessage);

                                mTchatPanel.displayTchatMessageInTchatArea(vTchatMessage);
                                mTchatPanel.getMessageTextArea().setText("");
                        }
                });

                mTchatPanel.addListenerToClientsConnected(new MouseAdapter() {
                        public void mouseClicked(MouseEvent pEvent) {
                                if (pEvent.getClickCount() > 1) {
                                        final String vSelectedDestinataire = new String((String) mTchatPanel.getClientsConnected().getSelectedValue());

                                        if (vSelectedDestinataire != null
                                                && !mClientNom.equals(vSelectedDestinataire)
                                                && !mPrivatesTchatsWindows.containsKey(vSelectedDestinataire)) {
                                                createPrivateTchatWindow(vSelectedDestinataire);
                                        }
                                }
                        }
                });


                add(mTchatPanel, BorderLayout.CENTER);
                mTchatPanel.updateUI();

                checkTchatClientStatus();
        }

        private void createPrivateTchatWindow(final String pDestinataire) {
                TchatWindow vTchatWindow = new TchatWindow();
                vTchatWindow.setTchatClient(mTchatClient);
                vTchatWindow.setClientNom(mClientNom);
                vTchatWindow.setPrivateDestinataire(pDestinataire);
                vTchatWindow.changeTitle();
                vTchatWindow.displayClientsConnected();

                vTchatWindow.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent pEvent) {
                                mPrivatesTchatsWindows.remove(pDestinataire);
                        }
                });

                mPrivatesTchatsWindows.put(pDestinataire, vTchatWindow);
        }

        private void displayMessagesReceived() {
                Map<String, List<TchatMessage>> vMessagesReceived = mTchatClient.sortMessagesReceivedByEmetteur();
                mTchatPanel.displayTchatsMessagesInTchatArea(vMessagesReceived.get("all"));
                vMessagesReceived.remove("all");

                for (String vDestinataireTmp : vMessagesReceived.keySet()) {
                        if (!mPrivatesTchatsWindows.containsKey(vDestinataireTmp)) {
                                createPrivateTchatWindow(new String(vDestinataireTmp));
                        }

                        mPrivatesTchatsWindows.get(vDestinataireTmp).getTchatPanel().displayTchatsMessagesInTchatArea(vMessagesReceived.get(vDestinataireTmp));
                }

                mTchatClient.getMessagesReceived().clear();
        }

        private void displayClientsConnected() {
                List<String> vClientsConnected = new ArrayList<String>(mTchatClient.getClientsConnected());
                mTchatPanel.displayClientsConnected(vClientsConnected);

                for (String vDestinataireTmp : mPrivatesTchatsWindows.keySet()) {
                        if (!vClientsConnected.contains(vDestinataireTmp)) {
                                mPrivatesTchatsWindows.get(vDestinataireTmp).removePrivateDestinataire();
                                mPrivatesTchatsWindows.get(vDestinataireTmp).getTchatPanel().getButtonSendMessage().setEnabled(false);
                        }
                }

        }

        private void checkAuthentificationClientStatus() {
                Thread vThread = new Thread() {
                        public void run() {
                                while (true) {
                                        if (mTchatClient.getStatusClient() == StatusClient.success) {
                                                initTchatPanel();
                                                return;
                                        } else if (mTchatClient.getStatusClient() == StatusClient.connectionFailed) {
                                                JOptionPane.showMessageDialog(TchatMainWindow.this, "Connection au serveur impossible !", "Erreur", JOptionPane.ERROR_MESSAGE);
                                                return;
                                        } else if (mTchatClient.getStatusClient() == StatusClient.authentificationFailed) {
                                                JOptionPane.showMessageDialog(TchatMainWindow.this, "Ce nom existe deja ou est incorrect !", "Warning", JOptionPane.WARNING_MESSAGE);
                                                return;
                                        }

                                        try {
                                                Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                };

                vThread.start();
        }

        private void checkTchatClientStatus() {
                Thread vThread = new Thread() {
                        public void run() {
                                while (mTchatClient.getStatusClient() == StatusClient.success) {
                                        displayMessagesReceived();
                                        displayClientsConnected();

                                        try {
                                                Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }
                                }

                                if (mTchatClient.getStatusClient() == StatusClient.connectionFailed) {
                                        JOptionPane.showMessageDialog(TchatMainWindow.this, "Connection au serveur interrompue !", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                        }
                };

                vThread.start();
        }
}
