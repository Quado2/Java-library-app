package libview;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminWelcom extends JPanel {

	JLabel adminPanel;
	JLabel welcom;
	
	 public AdminWelcom() {
		 setBackground(Color.black);
		 setLayout(new GridBagLayout());
		 
		 adminPanel =  new JLabel("ADMIN  PANEL");
		 welcom =  new JLabel("WELCOME !!");
		 
		 welcom.setFont(new Font("Sanseriff",Font.ITALIC,70));
		 welcom.setForeground(Color.cyan);
		 adminPanel.setFont(new Font("Sanseriff",Font.BOLD,60));
		 adminPanel.setForeground(Color.cyan);
		 
		 GridBagConstraints gc =  new GridBagConstraints();
		 gc.gridx =  0;
		 gc.gridy = 0;
		 gc.anchor = GridBagConstraints.NORTH;
		 gc.insets = new Insets(100,0,0,0);
		 gc.weighty = 0.2;
		 add(adminPanel,gc);
			 
	     gc.gridy++;
	     gc.weighty = 1;
	     gc.anchor = GridBagConstraints.NORTH;
		 gc.insets = new Insets(0,0,0,30);
	     add(welcom,gc);
		 
	 }
	
	
}
