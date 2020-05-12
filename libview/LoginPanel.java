package libview;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	JLabel userLabel, passLabel;
	JTextField userField;
	JPasswordField passField;
	JButton loginButton ;
	JPanel upperPanel,lowerPanel;
	private LoginListener loginListener;
	
	public LoginPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc= new GridBagConstraints();
		upperPanel = new JPanel(new GridBagLayout());
		upperPanel.setOpaque(false);
		lowerPanel = new JPanel(new GridBagLayout());
		
		//////////////FIRST ROW //////////////////
		gc.insets = new Insets(10,5,10,5);
		gc.gridx = 0;
		gc.gridy =  0;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		userLabel = new JLabel( "Username: ");
		userLabel.setForeground(Color.CYAN);
		userLabel.setFont(new Font("Sanseriff",Font.BOLD,16));
		upperPanel.add(userLabel,gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		userField = new JTextField(20);
		upperPanel.add(userField,gc);
		
		///////////////NEXT ROW /////////////////
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		passLabel = new JLabel("Password: ");
		passLabel.setFont(new Font("Sanseriff",Font.BOLD,16));
		passLabel.setForeground(Color.cyan);
		upperPanel.add(passLabel,gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		passField =  new JPasswordField(20);
		upperPanel.add(passField,gc);
		
		//////////////NEXT ROW ///////////////////
		
		gc.gridx = 0;
		gc.gridy = 0;
	    loginButton  = new JButton("Login");
	    lowerPanel.add(loginButton,gc);
	    
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weighty = 0.1;
		add(upperPanel,gc);
		
		
		gc.gridy++;
		gc.weighty = 2;
		gc.anchor = GridBagConstraints.NORTH;
		add(lowerPanel,gc);
		
			loginButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					String username = userField.getText();  userField.setText("");
					String password =  passField.getText(); passField.setText("");
					System.out.println(password);
					
					try {
						int check = loginListener.loginCheck(username, password);
						
						if(check == 0) {
						
							JOptionPane.showMessageDialog(LoginPanel.this,"Password and Username\n   does not match", "Login Check",JOptionPane.INFORMATION_MESSAGE);
								passField.setText("");
							}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				
			});	
		
		
	}
	
	
	public void setLoginListener(LoginListener loginListener) {
		
		this.loginListener = loginListener;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		Graphics2D g2d = (Graphics2D) g;
		  try {
			  Image image = ImageIO.read(getClass().getResource("/images/uniziklogo.png"
			  		+ ""));
			  int height = image.getHeight(null);
			  int width = image.getWidth(null);
			  
			g2d.drawImage(image, 0, 0, LoginPanel.this.getSize().width + 800,LoginPanel.this.getSize().height,75,0,width+500,height-10, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
