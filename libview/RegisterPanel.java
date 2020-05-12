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

public class RegisterPanel extends JPanel {
	private JTextField nameField;
	private JTextField regNumberField;
	private JTextField phoneNumberField,emailAddressField;
	private JTextField departmentField;
	private JTextField facultyField;

	private JLabel nameLabel,levelLabel,photoLabel,picLabel;
	private JLabel regNumberLabel,departmentLabel,facultyLabel;
	private JLabel phoneNumberLabel,emailAddressLabel;
	
	private JButton registerButton,chooseButton;
	JButton snapButton;
	
	private JComboBox levelCombo,facultyCombo,deptCombo,artCombo;
	private DefaultComboBoxModel levelComboModel,facultyModel,deptModel,artModel;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    private BufferedImage image;
	private static BufferedImage cImage;
    
    private RegisterListener registerListener;
	
	
	public RegisterPanel() {
		upperPanel = new JPanel();
		lowerPanel = new JPanel(new GridBagLayout());
		picLabel =  new JLabel();
		
		
		
		
		
		
		Border outerBorder = BorderFactory.createEtchedBorder(20, Color.CYAN, Color.ORANGE);
		TitledBorder innerBorder = BorderFactory.createTitledBorder("Student info");
		upperPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		upperPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////////////First row//////////////////////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,10,0);
		gc.anchor = GridBagConstraints.LINE_END;
		
		nameLabel = new JLabel("Name:  ");
		
		upperPanel.add(nameLabel,gc);
		
		nameField = new JTextField(150);
		nameField.setMinimumSize(new Dimension(200,30));
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		
		upperPanel.add(nameField,gc);
		
		///////////////next row////////////////////
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		regNumberField = new JTextField(200);
		regNumberField.setMinimumSize(new Dimension(200,30));
		upperPanel.add(regNumberField,gc);
	
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		regNumberLabel = new JLabel("Reg Number:  ");
		upperPanel.add(regNumberLabel,gc);
		
		///////////////next row////////////////////
	    gc.gridx= 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		levelLabel = new JLabel("Level:  ");
		upperPanel.add(levelLabel,gc);
	
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		
		levelComboModel = new DefaultComboBoxModel();
		
		levelComboModel.addElement(" 100");
		levelComboModel.addElement(" 200");
		levelComboModel.addElement(" 300");
		levelComboModel.addElement(" 400");
		levelComboModel.addElement(" 500");
		levelComboModel.addElement(" 600");
		levelComboModel.addElement(" Extra year");
		
		levelCombo = new JComboBox();
		levelCombo.setModel(levelComboModel);
		
		upperPanel.add(levelCombo,gc);
		
		
		
	///////////////next row////////////////////
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		facultyLabel = new JLabel("Faculty:  ");
		upperPanel.add(facultyLabel,gc);
	
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		
        facultyModel = new DefaultComboBoxModel();
		
		facultyModel.addElement(" Agriculture");
		facultyModel.addElement(" Arts");
		facultyModel.addElement(" Basic Medical Sciences");
		facultyModel.addElement(" Bio-Sciences");
		facultyModel.addElement(" Engineering");
		facultyModel.addElement(" Environmental Sciences");
		facultyModel.addElement(" Health Sciences and Technology");
		facultyModel.addElement(" Law");
		facultyModel.addElement(" Management Sciences");
		facultyModel.addElement(" Medicine");
		facultyModel.addElement(" Pharmaceutical Sciences");
		facultyModel.addElement(" Physical Sciences");
		facultyModel.addElement(" social Sciences");
		
		facultyCombo = new JComboBox();
		facultyCombo.setModel(facultyModel);
		upperPanel.add(facultyCombo,gc);
		

		///////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			departmentLabel = new JLabel("Department:  ");
			upperPanel.add(departmentLabel,gc);
		
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			
			facultyField = new JTextField(200);
			
			deptModel = new DefaultComboBoxModel();
			
			deptModel.addElement(" Agric Econs and Extension");
			deptModel.addElement(" Animal Science and Production");
			deptModel.addElement(" Crop Sciece and Horticulture");
			deptModel.addElement(" Fisheries and Aquaculture Technology");
			deptModel.addElement(" Forestry and Wildlife");
			deptModel.addElement(" Soil Science and Land Resource Management");
			
			deptCombo = new JComboBox();
			deptCombo.setModel(deptModel);
			
			upperPanel.add(deptCombo,gc); 
			
			facultyCombo.addActionListener(new ActionListener() {
 
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("somethin selected");
					
					if(facultyCombo.getSelectedIndex() == 0) {
						deptModel = new DefaultComboBoxModel();
						
						deptModel.addElement(" Agric Econs and Extension");
						deptModel.addElement(" Animal Science and Production");
						deptModel.addElement(" Crop Sciece and Horticulture");
						deptModel.addElement(" Fisheries and Aquaculture Technology");
						deptModel.addElement(" Forestry and Wildlife");
						deptModel.addElement(" Soil Science and Land Resource Management");
						
						deptCombo.setModel(deptModel);
						
						
					}
					
					else if(facultyCombo.getSelectedIndex() == 1) {
						
						deptModel = new DefaultComboBoxModel();
						
						deptModel.addElement(" Chinese Studies");
						deptModel.addElement(" English Language and Literature");
						deptModel.addElement(" History and International Relations");
						deptModel.addElement(" Igbo African and Asian Studies");
						deptModel.addElement(" Linguistics");
						deptModel.addElement(" Modern European Languages");
						deptModel.addElement(" Music");
						deptModel.addElement(" Philosophy");
						deptModel.addElement(" Religion and Human Relations");
						deptModel.addElement(" Theatre and Film Studies");
						
						deptCombo.setModel(deptModel);
						
							
					}
					
					else if(facultyCombo.getSelectedIndex() == 2) {
						
						deptModel = new DefaultComboBoxModel();
						
						deptModel.addElement(" Anatomy");
						deptModel.addElement(" Human Biochemistry");
						deptModel.addElement(" Human Phisiology");
						
						deptCombo.setModel(deptModel);
							
					}
					
					else if(facultyCombo.getSelectedIndex() == 3) {
						
						deptModel = new DefaultComboBoxModel();
						
						deptModel.addElement(" Applied Biochemistry");
						deptModel.addElement(" Applied Microbiology and Brewing");
						deptModel.addElement(" Botany");
						deptModel.addElement(" Parasitology and Entomology");
						deptModel.addElement(" Zoology");
						
						
						deptCombo.setModel(deptModel);
							
					}
					
					else if(facultyCombo.getSelectedIndex() == 4) {
						
						deptModel = new DefaultComboBoxModel();
						
						deptModel.addElement(" Agric and Bioresourse Engineering");
						deptModel.addElement(" Chemical Engineering");
						deptModel.addElement(" Civil Engineering");
						deptModel.addElement(" Electrical Engineering");
						deptModel.addElement(" Electronic and Computer Engineering");
						deptModel.addElement(" Industrial and Productin Egineering");
						deptModel.addElement(" Mechanical Engineering");
						deptModel.addElement(" Metallurgical and Material Engineering");
						deptModel.addElement(" Polymer and Textile Engineering");
						
						deptCombo.setModel(deptModel);
							
					}
					
					else if(facultyCombo.getSelectedIndex() == 5) {
	
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" Architecture");
						deptModel.addElement(" Building");
						deptModel.addElement(" Environmental Management");
						deptModel.addElement(" Estate Management");
						deptModel.addElement(" Fine and applied Arts");
						deptModel.addElement(" Geography and Meterology");
						deptModel.addElement(" Quantity Surveying");
						deptModel.addElement("Surveying and Geoinformatics");
	
							deptCombo.setModel(deptModel);
		
                    }
					
					else if(facultyCombo.getSelectedIndex() == 6) {
						
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" Environmental Health and Technology");
						deptModel.addElement(" Environmental Health Sciences");
						deptModel.addElement(" Medical Laboratory Scientist");
						deptModel.addElement(" Medical Rehabilitation");
						deptModel.addElement(" Nursing Sciences");
						deptModel.addElement("Radiography");
						
						deptCombo.setModel(deptModel);
		
                    }
					
					else if(facultyCombo.getSelectedIndex() == 7) {
						
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" Commercial and Property Law");
						deptModel.addElement(" International Law");
						deptModel.addElement(" Law");
						deptModel.addElement(" Public and Private law");
	
							deptCombo.setModel(deptModel);
		
                    }
					
					else if(facultyCombo.getSelectedIndex() == 9) {
						
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" (Medical) Pharmacology and Therapeutic");
						deptModel.addElement(" Anaesthesiology");
						deptModel.addElement(" Chemical Pathology");
						deptModel.addElement(" Community Medicine");
						deptModel.addElement(" Haemathology");
						deptModel.addElement(" Histopathology");
						deptModel.addElement(" Immunology");
						deptModel.addElement(" Internal Medicine");
						deptModel.addElement(" Medical Library");
						deptModel.addElement(" Obstetrics and Gynaecology");
						deptModel.addElement(" Opthamology");
						deptModel.addElement(" Paediatrics");
						deptModel.addElement(" Primary Health Care");
						deptModel.addElement(" Radiology");
						deptModel.addElement(" Surgery");
	
							deptCombo.setModel(deptModel);
		
                    }
					
					else if(facultyCombo.getSelectedIndex() == 10) {
						
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" Clinical Pharmacy and Pharmacy Management");
						deptModel.addElement(" Pharmaceutical and Medicinal Chemistry");
						deptModel.addElement(" Pharmaceutical Microbiology and Biotechnology");
						deptModel.addElement("Pharmaceutics and Pharmaceutical Technology");
						deptModel.addElement("Pharmacognosy and Traditional Medicine");
						deptModel.addElement("Pharmacology and Toxicology");
						
							deptCombo.setModel(deptModel);
		
                    }
					
					else if(facultyCombo.getSelectedIndex() == 11) {
						
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" Computer Science");
						deptModel.addElement(" Geological Sciences");
						deptModel.addElement(" Mathematics");
						deptModel.addElement(" Physics and Industrial Physics");
						deptModel.addElement(" Pure and Industrial Chemistry");
						deptModel.addElement(" Statics");
	
							deptCombo.setModel(deptModel);
		
                    }
					
					else if(facultyCombo.getSelectedIndex() == 12) {
						
						deptModel = new DefaultComboBoxModel();
	
						deptModel.addElement(" Economics");
						deptModel.addElement(" Mass Communication");
						deptModel.addElement(" Political Science");
						deptModel.addElement(" Psychology");
						deptModel.addElement(" Sociology/Anthropology");
						
	
							deptCombo.setModel(deptModel);
		
                    }
					
				}
				
			});
			
			
			
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			phoneNumberLabel = new JLabel("Phone Number:  ");
			upperPanel.add(phoneNumberLabel,gc);
		
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			phoneNumberField = new JTextField(200);
			phoneNumberField.setMinimumSize(new Dimension(200,30));
			
			upperPanel.add(phoneNumberField,gc);
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			emailAddressLabel = new JLabel("Email Address:  ");
			upperPanel.add(emailAddressLabel,gc);
		
			
			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			
			emailAddressField = new JTextField(200);
			emailAddressField.setMinimumSize(new Dimension(200,30));
			
			upperPanel.add(emailAddressField,gc);
			
			/////////////next row////////////////////
		    gc.gridx= 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.LINE_END;
			photoLabel = new JLabel("Photo:  ");
			upperPanel.add(photoLabel,gc);
		
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
			
			
			
			
			
			
			regNumberField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent ev) {
				if(regNumberField.getText().length() > 9) {
					
					getToolkit().beep();
					ev.consume();
				}
				}
				});
			
			
			registerButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String name = nameField.getText();
					String regNumber = regNumberField.getText();
					String level = (String)levelCombo.getSelectedItem();
					String department = (String)deptCombo.getSelectedItem();
					String faculty = (String)facultyCombo.getSelectedItem();
					String phoneNo = phoneNumberField.getText();
					String email = emailAddressField.getText();
					
					if(name.length()<1||regNumber.length()<10||phoneNo.length()<1||email.length() < 1||image == null) {
						registerListener.correct();
					}
					
					else if(registerListener != null) {
						
						String PATH = "/Users/Quado/Documents/profile images/";
				        String directoryName = PATH.concat(this.getClass().getName());
					File file = new File(String.valueOf(directoryName));
					if(!file.exists()) {
						
						file.mkdirs();
						
					}
					
					try {
						ImageIO.write(image, "PNG", new File("/Users/Quado/Documents/profile images/"+regNumber+".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						registerListener.register(name, regNumber, level, department, faculty, phoneNo, email);
						
						image = null;
						
						if(MainClass.update == 0) {
						registerListener.updated();
						}
						
						if(MainClass.update != 0 && MainClass.update!=2 && MainClass.update != -1) {
					    registerListener.done();
						}
						
						MainClass.update = 5;
						
						nameField.setText("");
						regNumberField.setText("");
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
					if(fileChooser.showOpenDialog(RegisterPanel.this)==JFileChooser.APPROVE_OPTION) {
						
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
	
	
	public void setRegisterListener(RegisterListener registerListener) {
		
		this.registerListener = registerListener;
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
