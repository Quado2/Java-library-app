package libview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class BRPanel extends JPanel {
	private JLabel title,serialNo,author,regNumberLabel;
	private JTextField titleField,serialNoField,authorField,regNumberField;
	private JPanel upperPanel,lowerPanel,middlePanel;
	private JButton borrowButton,returnButton;
	 String word;
	BRListener brListener;
	
	public BRPanel() {
		lowerPanel = new JPanel();
		upperPanel = new JPanel();
		middlePanel = new JPanel();
	
		upperPanel.setOpaque(false);
		middlePanel.setOpaque(true);
		middlePanel.setBackground(new Color(200,255,155));
		lowerPanel.setOpaque(false);
	     
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
				titleField.setMinimumSize(new Dimension(200,28));
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
				authorField.setMinimumSize(new Dimension(200,28));
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
				serialNoField.setMinimumSize(new Dimension(200,28));
				gc.gridx++;
				gc.anchor = GridBagConstraints.LINE_START;
				middlePanel.add(serialNoField,gc);
				
				
				regNumberLabel = new JLabel("Reg Number: ");
				regNumberLabel.setFont(new Font("seriff",Font.BOLD,20));
				regNumberLabel.setForeground(Color.MAGENTA);
				regNumberField = new JTextField(100);
				regNumberField.setMinimumSize(new Dimension(150,35));
				regNumberField.setHorizontalAlignment(JTextField.CENTER);
				regNumberField.setFont(new Font("seriff",Font.BOLD|Font.ITALIC,20));
				
				gc.gridx = 0;
				gc.gridy=0;
				gc.anchor = GridBagConstraints.CENTER;
				upperPanel.add(regNumberLabel,gc);
				
				gc.gridy++;
				gc.anchor = GridBagConstraints.CENTER;
				upperPanel.add(regNumberField,gc);
				
				gc.gridy = 0;
				gc.gridx = 0;
				borrowButton = new JButton("Borrow");
				lowerPanel.add(borrowButton,gc);
				
				gc.gridy++;
				returnButton = new JButton("Return");
				lowerPanel.add(returnButton,gc);
				
				
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
				
				borrowButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if(titleField.getText().length()<1||authorField.getText().length()<1||serialNoField.getText().length() < 1) {
							brListener.correct();
						}
						
						else if(brListener!= null) 
						
						
						
							try {
								brListener.borrowAction(regNumberField.getText(), titleField.getText(), authorField.getText(), serialNoField.getText());
								brListener.retrieveAction(word);
								brListener.borrowNotification();
								 
								titleField.setText("");
								authorField.setText("");
								serialNoField.setText("");
							} catch (SQLException e) {
								e.printStackTrace();
							}
					
					}
					
					
				});
				
				returnButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(brListener!= null) {
							
							try {
								
								if(StudentBooksPanel.usedDays > 6)
								{    
									int extra = StudentBooksPanel.usedDays - 6;
									int amount = extra*5;
									int response = JOptionPane.showConfirmDialog(BRPanel.this, "This book has been used for extra "+extra+" days.\n "
											+ "the student should pay the sum of \n"+"\t\t N"+amount+"\nClicking OK implies that the student has paid the amount\n otherwise click CANCEL"
													, "Student Bursted", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,Utill.createIcon("/images/mon.png"));
									
								if(response == JOptionPane.OK_OPTION) {
									
									brListener.returnAction(StudentBooksPanel.returnRegNo,StudentBooksPanel.dateValue);
									brListener.retrieveAction(word);
									brListener.saveTransaction(StudentBooksPanel.returnRegNo,StudentBooksPanel.returnTitle,amount);
									brListener.returnNotification();
						             }
								
								
								}
								
								else {
									

									brListener.returnAction(StudentBooksPanel.returnRegNo,StudentBooksPanel.dateValue);
									brListener.retrieveAction(word);
									brListener.returnNotification();
									
								}
								
								
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
					}
				});
				
				
				
				regNumberField.addKeyListener(new KeyListener() {
					
					@Override
					public void keyPressed(KeyEvent k) {
						
						char w = k.getKeyChar();
						
						if( w==KeyEvent.VK_BACK_SPACE) {
							
							word = regNumberField.getText();
							word = word.substring(0, word.length()-1);
							
						}
						
						else {
						 word =  regNumberField.getText()+w;
						}
						
						if(word.length()==10){
							
							System.out.println("A complete reg Number");
							
						try {
							brListener.retrieveAction(word);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
						}
						  }

					@Override
					public void keyReleased(KeyEvent arg0) {
						//System.out.println("key is released");
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
							
					}
			});
		}
	
	

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent( g );
		  Graphics2D g2d = (Graphics2D) g;
		  try {
			  Image image = ImageIO.read(getClass().getResource("/images/zik lib.png"));
			  int height = image.getHeight(null);
			  int width = image.getWidth(null);
			  
			g2d.drawImage(image, 0, 0, this.getPreferredSize().width,720,75,0,width+500,height-10, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void setBRListener(BRListener brListener) {
		this.brListener = brListener;
	}

}
