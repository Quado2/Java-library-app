package libview;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	private ToolBarListener toolBarListener;

	private static final long serialVersionUID = -5253958368541959858L;
	private JButton borrowButton, returnButton, registerButton;
	public static JLabel userLabel;
	
	public ToolBar() {
		registerButton = new JButton();
		registerButton.setIcon(Utill.createIcon("/images/Capture1.PNG"));
		registerButton.setToolTipText("Register");
		registerButton.setContentAreaFilled(false);
		registerButton.setBorderPainted(false);
		
		userLabel = new JLabel();
		userLabel.setIcon(Utill.createIcon("/images/hum.jpg"));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolBarListener!= null) {
					
					toolBarListener.registerAction();
				}
			}	
		});
		
		borrowButton = new JButton();
		borrowButton.setIcon(Utill.createIcon("/images/reading-ebook1.png"));
		borrowButton.setToolTipText("Borrow/Return Book");
		borrowButton.setContentAreaFilled(false);
		borrowButton.setBorderPainted(false);
		
		borrowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolBarListener!= null) {
					
					toolBarListener.borrowAction();
				}
			}	
		});
		
		returnButton = new JButton();
		returnButton.setIcon(Utill.createIcon("/images/add-book1.png"));
		returnButton.setToolTipText("Admin Panel");
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		
		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolBarListener!= null) {
					
					toolBarListener.returnAction();
				}
			}	
		});
		
		JToolBar leftPanel = new JToolBar();
		leftPanel.setFloatable(false);
		leftPanel.add(registerButton);
		leftPanel.add(borrowButton);
		leftPanel.add(returnButton);
		
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.2;
		gc.anchor = GridBagConstraints.WEST;
		
		add(leftPanel,gc);
		
		gc.gridx = 1;
		gc.weightx = 2;
		gc.anchor = GridBagConstraints.EAST;
		add(userLabel,gc);
		
		
	}
	
	public void setToolBarListener(ToolBarListener toolBarListener) {
		
	this.toolBarListener = toolBarListener;
	
	}
	
	public void setReturnButton(boolean b) {
		returnButton.setEnabled(b);
		
	}
}
