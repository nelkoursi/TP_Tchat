package src.tpchat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConnectionPanel extends JPanel {

   private static final long serialVersionUID = 1L;
   
   private JButton mButtonTchatter;
   
   public ConnectionPanel() {
      setLayout(new BorderLayout());
      
      JPanel vEmptyPanel = new JPanel();
      vEmptyPanel.setPreferredSize(new Dimension(200, 220));
            
      mButtonTchatter = new JButton("Tchatter");
      
      add(vEmptyPanel, BorderLayout.PAGE_START);
      add(mButtonTchatter, BorderLayout.CENTER);
   }
   
   public void addListenerToButtonTchatter(ActionListener pListener) {
      mButtonTchatter.addActionListener(pListener);
   }
   
}

