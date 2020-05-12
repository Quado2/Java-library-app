package libview;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class WorkerRegister extends JPanel{
	

	private JTextField nameField,usernameField,passwordField;
	private JTextField staffNoField;
	private JTextField phoneNumberField,emailAddressField;
	

	private JLabel nameLabel,designationLabel,photoLabel,picLabel;
	private JLabel staffNoLabel,usernameLabel,passwordLabel;
	private JLabel phoneNumberLabel,emailAddressLabel;
	
	private JButton registerButton,chooseButton;
	JButton snapButton;
	
	private JComboBox designation;
	private DefaultComboBoxModel designationComboModel;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    
    private BufferedImage image;
	private static BufferedImage cImage;
	private WorkerListener workerListener;
    
	
	
	public WorkerRegister() {
		
		upperPanel = new JPanel();
		lowerPanel = new JPanel(new GridBagLayout());
		picLabel =  new JLabel();
		setBackground(Color.black);
		
		
		Border outerBorder = BorderFactory.createEtchedBorder(20, Color.CYAN, Color.ORANGE);
		TitledBorder innerBorder = BorderFactory.createTitledBorder("Student info");
		upperPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		upperPanel.setLayout(new GridBagLayout());
		upperPanel.setOpaque(false);
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////////////First row//////////////////////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,10,0);
		gc.anchor = GridBagConstraints.LINE_END;
		
		nameLabel = new JLabel("Name:  ");
		nameLabel.setForeground(Color.cyan);
		
		upperPanel.add(nameLabel,gc);
		
		nameField = new JTextField(150);
		nameField.setMinimumSize(new Dimension(400,30));
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		
		upperPanel.add(nameField,gc);
		
		///////////////next row////////////////////
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		staffNoField = new JTextField(200);
		staffNoField.setMinimumSize(new Dimension(400,30));
		upperPanel.add(staffNoField,gc);
	
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		staffNoLabel = new JLabel("Staff Number:  ");
		upperPanel.add(staffNoLabel,gc);
		staffNoLabel.setForeground(Color.CYAN);
		
		///////////////next row////////////////////
		
	    gc.gridx= 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		designationLabel = new JLabel("Designation:  ");
		upperPanel.add(designationLabel,gc);
		designationLabel.setForeground(Color.cyan);
	
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		
		designationComboModel = new DefaultComboBoxModel();
		
		designationComboModel.addElement(" worker");
		designationComboModel.addElement(" admin");
		
		designation = new JComboBox();
		designation.setModel(designationComboModel);
		
		upperPanel.add(designation,gc);
		
		
		
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			phoneNumberLabel = new JLabel("Phone Number:  ");
			upperPanel.add(phoneNumberLabel,gc);
		    phoneNumberLabel.setForeground(Color.cyan);
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			phoneNumberField = new JTextField(200);
			phoneNumberField.setMinimumSize(new Dimension(400,30));
			
			upperPanel.add(phoneNumberField,gc);
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			emailAddressLabel = new JLabel("Email Address:  ");
			upperPanel.add(emailAddressLabel,gc);
			emailAddressLabel.setForeground(Color.cyan);
		
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			emailAddressField = new JTextField(200);
			emailAddressField.setMinimumSize(new Dimension(400,30));
			
			upperPanel.add(emailAddressField,gc);
			
			
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			usernameLabel = new JLabel("Choose Username:  ");
			upperPanel.add(usernameLabel,gc);
			usernameLabel.setForeground(Color.cyan);
		
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			usernameField = new JTextField(200);
			usernameField.setMinimumSize(new Dimension(400,30));
			
			upperPanel.add(usernameField,gc);
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			passwordLabel = new JLabel("Choose Password:  ");
			upperPanel.add(passwordLabel,gc);
			passwordLabel.setForeground(Color.cyan);
		
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			passwordField = new JTextField(200);
			passwordField.setMinimumSize(new Dimension(400,30));
			
			upperPanel.add(passwordField,gc);
			
			
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			photoLabel = new JLabel("Photo:  ");
			upperPanel.add(photoLabel,gc);
			photoLabel.setForeground(Color.cyan);
		
			JPanel photoPanel =  new JPanel(new FlowLayout());
			snapButton = new JButton();
			snapButton.setIcon(Utill.createIcon("/images/cam.PNG"));
			snapButton.setOpaque(true);
			snapButton.setToolTipText("Capture");
			
			snapButton.setContentAreaFilled(false);
			snapButton.setBorderPainted(false);
			
			photoPanel.add(snapButton);
			chooseButton = new JButton();
			chooseButton.setIcon(Utill.createIcon("/images/file select.jpg"));
			chooseButton.setToolTipText("Select picture from files");
			chooseButton.setContentAreaFilled(false);
			chooseButton.setBorderPainted(false);
			photoPanel.add(chooseButton);
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			upperPanel.add(photoPanel,gc);
			
			
			/////////////// LOWER PANEL ///////////////////
			GridBagConstraints gc2 =  new GridBagConstraints();
			lowerPanel.setOpaque(false);
			
			gc2.gridx = 0;
			gc2.gridy = 0;
			gc2.insets = new Insets(0,0,5,0);
			gc2.weighty = 1;
            lowerPanel.add(picLabel,gc2);
            
            
            gc2.gridx = 0;
			gc2.gridy = 1;
			gc2.insets = new Insets(10,5,0,0);
			gc2.weighty = 1;
			registerButton = new JButton("Register");
			lowerPanel.add(registerButton,gc2);
			
			
			
			gc.gridx = 0;
			gc.gridy = 0;
			gc.weighty = 0.1;
			add(upperPanel,gc);
			
			gc.gridy++;
			gc.weighty = 1;
			gc.insets = new Insets(0,0,5,0);
			gc.anchor = GridBagConstraints.NORTH;
			add(lowerPanel,gc);
			
			
			
			
			
			
			staffNoField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent ev) {
				if(staffNoField.getText().length() > 9) {
					
					getToolkit().beep();
					ev.consume();
				}
				}
				});
			
			
			registerButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String name = nameField.getText();
					String staffNo = staffNoField.getText();
					String desig = (String)designation.getSelectedItem();
					String phoneNo = phoneNumberField.getText();
					String email = emailAddressField.getText();
					String password = passwordField.getText();
					String username = usernameField.getText();
					
					if(name.length()<1||staffNo.length()<10||phoneNo.length()<1||email.length() < 1||image == null) {
							workerListener.correct();
					
					}
					
					else if(workerListener != null) {
						
						String PATH = "/Users/Quado/Documents/profile images/";
				        String directoryName = PATH.concat(this.getClass().getName());
					File file = new File(String.valueOf(directoryName));
					if(!file.exists()) {
						
						file.mkdirs();
						
					}
					
					try {
						ImageIO.write(image, "PNG", new File("/Users/Quado/Documents/profile images/"+staffNo+".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						workerListener.register(name, staffNo, desig, username, password, phoneNo, email);
						
						image = null;
						
						if(MainClass.update == 0) {
						workerListener.updated();
						}
						
						if(MainClass.update != 0 && MainClass.update!=2 && MainClass.update != -1) {
					    workerListener.done();
						}
						
						MainClass.update = 5;
						
						nameField.setText("");
						staffNoField.setText("");
						phoneNumberField.setText("");
						emailAddressField.setText("");
						image = null;
						picLabel.setIcon(null);
						
						
					}
				}	});
			
			
			chooseButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser fileChooser = new JFileChooser();
					if(fileChooser.showOpenDialog(WorkerRegister.this)==JFileChooser.APPROVE_OPTION) {
						
						try {
							FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile());
							// ImageInputStream ois = ImageInputStream.class(fis);
							BufferedImage image1 =  ImageIO.read(fis);
							image = createResizedCopy(image1,240,210,false);
							picLabel.setIcon(new ImageIcon(image));
							
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("Didnt load the correct thing ");
							
							
						}
					}
	
				}
				
				
			});
			
			snapButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					prepareCamera();
					
				}
				
				
			});
	}

	BufferedImage createResizedCopy(BufferedImage originalImage, 
            int scaledWidth, int scaledHeight, 
            boolean preserveAlpha)
    {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
        g.dispose();
        return scaledBI;
    }
	
	
	public void setWorkerListener(WorkerListener workerListener) {
		
		this.workerListener = workerListener;
	}
	
	
	public void prepareCamera() {
		
		Webcam webcam = Webcam.getDefault();
		System.out.println(webcam);
		webcam.setViewSize(WebcamResolution.QVGA.getSize());

		WebcamPanel panel = new WebcamPanel(webcam);
		//panel.setFPSDisplayed(true);
		//panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);
		
		
		JButton capButton = new JButton("Capture");
		JButton saveButton = new JButton("Continue");
		JPanel btnPanel = new JPanel(new FlowLayout());
		btnPanel.add(capButton);
		btnPanel.add(saveButton);
		
		JPanel webPanel = new JPanel(new BorderLayout());
		webPanel.add(panel,BorderLayout.NORTH);
		
		
		webPanel.add(btnPanel,BorderLayout.CENTER);
		
		JFrame window = new JFrame("Test webcam panel");
		window.add(webPanel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
		JLabel label  =  new JLabel();;
		capButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			
				 cImage = webcam.getImage();
				 
				image = createResizedCopy(cImage,240,210,false);
				
				picLabel.setIcon(new ImageIcon(image));
					
					
				
			}
			
			
		});
		
		
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
                 
             
				webcam.close();
				window.dispose();
				
			}
			
		});
		
		
	}
}
