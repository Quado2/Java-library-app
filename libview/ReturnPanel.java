package libview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ReturnPanel extends JPanel{
	
	private JLabel title,serialNo,author,regNumberLabel;
	private JTextField titleField,serialNoField,authorField,regNumberField;
	private JPanel upperPanel,lowerPanel,middlePanel;
	private JButton returnButton;
	
	public ReturnPanel() {
		
		lowerPanel = new JPanel();
		upperPanel = new JPanel();
		middlePanel = new JPanel();
	     
		 Border inner = BorderFactory.createEtchedBorder(20, Color.GRAY, Color.lightGray);
		 TitledBorder innerBorder = BorderFactory.createTitledBorder("Book info");
		 middlePanel.setBorder(BorderFactory.createCompoundBorder(inner, innerBorder));
		 
		setLayout(new GridBagLayout());
		middlePanel.setLayout(new GridBagLayout());
		upperPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		
				/// First Row////////////////////
				title = new JLabel(" Book Title:  ");
				gc.gridx = 0;
				gc.gridy++;
				gc.insets = new Insets(0,0,10,0);
				gc.anchor = GridBagConstraints.LINE_END;
				middlePanel.add(title,gc);
				
				titleField = new JTextField(200);
				titleField.setMinimumSize(new Dimension(200,20));
				gc.gridx = 1;
				middlePanel.add(titleField,gc);
				
				/// Next Row////////////////////
				author = new JLabel("Author:  ");
				gc.gridx = 0;
				gc.gridy++;
				gc.insets = new Insets(0,0,5,0);
				gc.anchor = GridBagConstraints.LINE_END;
				middlePanel.add(author,gc);
				
				authorField = new JTextField(200);
				authorField.setMinimumSize(new Dimension(200,20));
				gc.gridx = 1;
				gc.anchor = GridBagConstraints.LINE_START;
				middlePanel.add(authorField,gc);
		
				/// Next Row////////////////////
				serialNo = new JLabel("Serial Number:  ");
				gc.gridx = 0;
				gc.gridy++;
				gc.insets = new Insets(0,0,5,0);
				gc.anchor = GridBagConstraints.LINE_END;
				middlePanel.add(serialNo,gc);
				
				serialNoField = new JTextField(200);
				serialNoField.setMinimumSize(new Dimension(200,20));
				gc.gridx++;
				gc.anchor = GridBagConstraints.LINE_START;
				middlePanel.add(serialNoField,gc);
				
				
				regNumberLabel = new JLabel("Reg Number: ");
				regNumberLabel.setFont(new Font("seriff",Font.BOLD,20));
				regNumberLabel.setForeground(Color.MAGENTA);
				regNumberField = new JTextField(100);
				regNumberField.setMinimumSize(new Dimension(150,30));
				regNumberField.setHorizontalAlignment(JTextField.CENTER);
				regNumberField.setFont(new Font("seriff",Font.BOLD|Font.ITALIC,20));
				
				gc.gridx = 0;
				gc.gridy=0;
				gc.anchor = GridBagConstraints.CENTER;
				upperPanel.add(regNumberLabel,gc);
				
				gc.gridy++;
				gc.anchor = GridBagConstraints.CENTER;
				upperPanel.add(regNumberField,gc);
				
				returnButton = new JButton("Return");
				lowerPanel.add(returnButton);
				
				
				gc.gridx = 0;
				gc.gridy = 0;
				gc.weighty = 0.1;
				add(upperPanel,gc);
				
				gc.gridy++;
				add(middlePanel,gc);
				
				gc.weighty = 2;
				gc.gridy++;
				gc.anchor = GridBagConstraints.NORTH;
				gc.insets = new Insets(0,0,100,0);
				add(lowerPanel,gc);
				
						
	}
	}
