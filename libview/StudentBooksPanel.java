package libview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libmodel.BookHistory;
import libmodel.BorrowedBook;
import libmodel.Student;

public class StudentBooksPanel extends JPanel{
		private BorrowedModel borrowedModel;
		private HistoryModel historyModel;
		private  JList borrowedList;
		private  JList historyList;
	    private DefaultListModel borrowedListModel,historyListModel;
	
	private String name,regNumber,department,level,faculty,phone,email;
	private JLabel imageLabel,nameLabel,regLabel,phoneLabel,emailLabel,deptLabel,facLabel,levelLabel;
	private JPanel luPanel,ldPanel,lPanel,ruPanel,rdPanel,rmPanel,rPanel;
	private JPanel historyPanel;
	private JPanel borrowedPanel;
	private JSplitPane splitPane;
	private TextPanel textArea;
	public static String returnRegNo,returnTitle;
	public static double dateValue;
	public static int usedDays;
	
	
	
	public StudentBooksPanel(Student student,List<BorrowedBook> borrowedBooks, List<BookHistory> bookHistory) {
		
		historyPanel = new JPanel();
		borrowedPanel = new JPanel();
		rPanel = new JPanel(new GridBagLayout());
		rmPanel = new JPanel(new GridBagLayout());
		rdPanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		textArea = new TextPanel();
		
		rdPanel.setOpaque(false);
		rmPanel.setOpaque(false);
		rPanel.setOpaque(false);
		
		
		
		//rmPanel.setMinimumSize(new Dimension(400,600));
		
		//if(student == null) return;
		
		this.name = student.getName();
		this.regNumber = student.getRegNumber();
		this.department = student.getDepartment();
		this.faculty = student.getFaculty();
		this.phone = student.getPhoneNo();
		this.email = student.getEmail();
		this.level = student.getLevel().name();
		
		setLayout(new GridBagLayout());
		setPersonProfile();
		
		
		borrowedList = new JList();
		historyList  = new JList();
		borrowedListModel = new DefaultListModel();
		historyListModel = new DefaultListModel();
		historyList.setModel(historyListModel);
		borrowedList.setModel(borrowedListModel);
		
		borrowedListModel.removeAllElements();
		
		for(int i = 0;i<borrowedBooks.size();i++) {
			
			borrowedListModel.addElement(borrowedBooks.get(i));
		}
		
		for(int i = 0; i<bookHistory.size();i++) {
			
			historyListModel.addElement(bookHistory.get(i));
		}
		
		historyList.setCellRenderer(new HistoryListRenderer());
		borrowedList.setCellRenderer(new BorrowedListRenderer());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		ruPanel = new JPanel(new GridBagLayout());
		
		
		JLabel borrowLabel =  new JLabel("BORROWED BOOKS");
		borrowLabel.setForeground(Color.cyan);
		borrowLabel.setFont(new Font("VLadimir Scripts",Font.BOLD,20));		
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets =  new Insets(5,5,5,5);
		
		ruPanel.add(borrowLabel,gc);
		
		borrowedPanel.add(borrowedList);
		
		gc.gridy++;
		gc.insets = new Insets(5,0,5,0);
		
		ruPanel.add(borrowedPanel,gc);
		
		JLabel historyLabel = new JLabel("BOOK HISTORY");
		historyLabel.setForeground(Color.cyan);
		historyLabel.setFont(new Font("VLadimir Scripts",Font.BOLD,20));
		
		borrowedPanel.setOpaque(false);
		historyPanel.setOpaque(false);
		
		ruPanel.setOpaque(false);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets =  new Insets(5,5,5,5);
		
		rmPanel.add(historyLabel,gc);
		
		gc.gridy++;
		gc.insets = new Insets(5,0,5,0);
		
		JScrollPane scrollPane = new JScrollPane(historyList);
		scrollPane.setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(650,300));
		historyPanel.add(scrollPane);

		rmPanel.add(historyPanel,gc);
		
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets =  new Insets(5,5,5,5);
		
		rPanel.add(ruPanel,gc);
		
		gc.gridx = 0;
		gc.gridy++;
		gc.insets =  new Insets(5,5,5,5);
		rPanel.add(rmPanel,gc);
		
		
		gc.anchor = GridBagConstraints.SOUTH;
		gc.gridx = 0;
		gc.gridy++;
		gc.insets =  new Insets(5,5,10,5);
		rPanel.add(textArea,gc);
		
	    GridBagConstraints gc2 = new GridBagConstraints();
	    gc2.gridx = 0;
		gc2.gridy = 0;
		gc2.weightx = 0.5;
		gc2.anchor = GridBagConstraints.FIRST_LINE_START;
		
	    add(lPanel,gc2);
	    
	    
	    gc2.gridx++;
	    gc2.weightx = 1;
	    
	    add(rPanel,gc2);  
		
		borrowedList.addListSelectionListener(new ListSelectionListener() {
            
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				BorrowedBook bBook = (BorrowedBook)borrowedList.getSelectedValue();
				 usedDays = (int)((new Date().getTime() - bBook.getDateValue())/(1000*60*60*24));
				 
				
				textArea.setText(bBook.getBookTitle() + "   by  " + bBook.getBookAuthor() + ", with serial No: "+bBook.getSerialNo() + "\n"
									+" Borrowed on "+ bBook.getDate()+".     Used for "+ usedDays +" day(s)");
			//	textArea.setEditable(false);
				
				returnRegNo = bBook.getRegNumber();
				dateValue = bBook.getDateValue();
				returnTitle = bBook.getBookTitle();
				
			
				
			}
			
		});
		
		historyList.addListSelectionListener(new ListSelectionListener() {
            
			   
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				BookHistory bBook = (BookHistory)historyList.getSelectedValue();
				
				textArea.setText(bBook.getBookTitle() + "   by  " + bBook.getBookAuthor() + ", with serial No: "+bBook.getBookSerialNo() + "\n"
									+" Borrowed on "+ bBook.getBorrowDate()+".     Returned on "+bBook.getReturnDate());
				//textArea.setEditable(false);
			
				
				
			}
			
		});
		 
	}
	
	
	
	public void setPersonProfile() {
		String PATH = "/Users/Quado/Documents/profile images/";
        String fileName = PATH+regNumber+".png";
	File file = new File(String.valueOf(fileName));
	
	ImageIcon image;
	
	if(file.exists()) {
		
		 image = new ImageIcon(fileName);
	}
	else {
		image = Utill.createIcon("/images/human.jpg");
	}
	
	  	JLabel label = new JLabel("", image, JLabel.CENTER);
	    luPanel  = new JPanel(new BorderLayout());
	    luPanel.add(label, BorderLayout.CENTER);
	    luPanel.setOpaque(true);
			
			
	    ldPanel = new JPanel(new GridBagLayout());
	   
	    GridBagConstraints gc3 = new GridBagConstraints();
	    ldPanel.setOpaque(false);
	    gc3.gridx = 0;
	    gc3.gridy = 0;
	    gc3.insets = new Insets(7,6,7,6);
	    
	    nameLabel = new JLabel(name);
	    nameLabel.setFont(new Font("Candara",Font.BOLD,20));
	    nameLabel.setForeground(Color.CYAN);
	    ldPanel.add(nameLabel,gc3);
	    ldPanel.setOpaque(false);
	    
	   
	    gc3.gridy++;
	    regLabel = new JLabel(regNumber);
	    regLabel.setForeground(Color.CYAN);
	    regLabel.setFont(new Font("Marlet",Font.BOLD,20));
	    ldPanel.add(regLabel,gc3);
	    
	    gc3.gridy++;
	    levelLabel = new JLabel(level);
	    levelLabel.setForeground(Color.CYAN);
	    levelLabel.setFont(new Font("Onyx",Font.BOLD,20));
	    ldPanel.add(levelLabel,gc3);
	    
	    gc3.gridy++;
	    deptLabel = new JLabel(department);
	    deptLabel.setForeground(Color.CYAN);
	    deptLabel.setFont(new Font("Vladimir Scripts",Font.BOLD,15));
	    ldPanel.add(deptLabel,gc3);
	    
	    gc3.gridy++;
	    facLabel = new JLabel(faculty);
	    facLabel.setForeground(Color.CYAN);
	    facLabel.setFont(new Font("SimSun",Font.BOLD,20));
	    ldPanel.add(facLabel,gc3);
	    
	    gc3.gridy++;
	    phoneLabel = new JLabel(phone);
	    phoneLabel.setForeground(Color.CYAN);
	    phoneLabel.setFont(new Font("Vivaldi",Font.BOLD,17));
	    ldPanel.add(phoneLabel,gc3); 
	    
	    gc3.gridy++;
	    emailLabel = new JLabel(email);
	    emailLabel.setForeground(Color.CYAN);
	    emailLabel.setFont(new Font("Verdana",Font.BOLD,15));
	    ldPanel.add(emailLabel,gc3);
	    
	    ldPanel.setBorder(BorderFactory.createEtchedBorder(20,new Color(245,235,255), new Color(150,224,255)));
	     lPanel = new JPanel(new GridBagLayout());
	     lPanel.setOpaque(false);
	     
	     String lab = new String("STUDENT INFORMATION");
	     
	     
	    Border inner = BorderFactory.createEtchedBorder(20, Color.GRAY, Color.lightGray);
		 TitledBorder innerBorder = BorderFactory.createTitledBorder(lab);
		 
		lPanel.setBorder(BorderFactory.createCompoundBorder(inner, innerBorder));
		  
		gc3.gridx = 0;
		gc3.gridy = 0;
		
		lPanel.add(luPanel,gc3);
		gc3.gridy++;
		lPanel.add(ldPanel,gc3);
		 
		
	    
	    
	    
	    
		
	}
	
		@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent( g );
		  Graphics2D g2d = (Graphics2D) g;
		  try {
			  Image image = ImageIO.read(getClass().getResource("/images/shel22.jpg"));
			  int height = image.getHeight(null);
			  int width = image.getWidth(null);
			  
			g2d.drawImage(image, 0, 0, this.getPreferredSize().width+1010,720,75,0,width+500,height-10, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	
}


