package libview;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminLPanel extends JPanel {
	JButton registerButton,accountButton;
	AdminListener adminListener;
	
	public AdminLPanel() {
		
		registerButton =  new JButton("Register a new worker");
		accountButton = new JButton("Take account from a worker");
		setBackground(Color.black);
		
	     setLayout(new GridBagLayout());
	     GridBagConstraints gc = new GridBagConstraints();
	     
	     gc.gridx = 0;
	     gc.gridy = 0;
	     gc.weighty = 0.2;
	     add(registerButton,gc);
	     
	     
	     gc.gridy++;
	     gc.weighty = 2;
	     gc.anchor = GridBagConstraints.NORTH;
	     gc.insets = new Insets(0,10,0,5);
	     add(accountButton,gc);
	     
	     registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminListener.registerClicked();
				
			}
	    	 
	    	 
	    	 
	     });
	     
	     accountButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminListener.accountClicked();
				
			}
	    	 
	    	 
	     });
				
	}
	
	public void setAdminListener(AdminListener adminListener) {
		this.adminListener = adminListener;
		
	}

}
