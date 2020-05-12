package libview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import libmodel.BookHistory;
import libmodel.BorrowedBook;
import libmodel.Student;

public class MainClass extends JFrame{
	
	private ToolBar toolBar;
	private JSplitPane splitPane;
	private RegisterPanel registerPanel;
	private BRPanel borrowPanel;
	private ReturnPanel returnPanel;
	private Controller controller;
	private RegisteredTablePanel rTablePanel;
	private List<BorrowedBook> allBorrowed;
	private AllBorrowedPanel abPanel;
	private JTabbedPane tab;
	private StudentBooksPanel sbPanel;
	private LoginPanel loginPanel;
	private AdminLPanel adminLPanel;
	private WorkerRegister workerRegister;
	public static String loggedAs;
	private AdminWelcom adminWelcom;
	private TransactionPanel transPanel;
	
	public static int update = 5;
	
	
	public MainClass()  {
		super("Library Manager");
		setVisible(true);
		setSize(1500,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setJMenuBar(createMenuBar());
		
		
	//	UIManager.put("nimbusBase", Color.CYAN);
		//UIManager.put("nimbusBlueGrey", Color.CYAN);
		//UIManager.put("control", Color.PINK);
		
		for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
			if("Nimbus".equals(info.getName())) {
				
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Could not set the selected look and feel");
				}
		}}
		
		
		
		 loggedAs = "";
		 loginPanel = new LoginPanel();
		 toolBar = new ToolBar();

		 try {
				controller = new Controller();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		add(toolBar,BorderLayout.PAGE_START);
		add(loginPanel,BorderLayout.CENTER);
		
		
		  /////// HANDLING LOGIN PANEL //////////////////////
		  loginPanel.setLoginListener(new LoginListener() {

				@Override
				public int loginCheck(String username, String password) throws SQLException {
					
				int check = controller.loginCheck(username,password);
				
				if(check == 2) {
					
					loginPanel.setVisible(false);
					ToolBar.userLabel.setText(" "+username+"  ");
					
					loggedAs = username;
					
					renew();
					sbPanel =null;
					toolBar.setReturnButton(false);
					
					add(splitPane,BorderLayout.CENTER);
					splitPane.setVisible(true);
					
					System.out.println(loggedAs);
					
				}
				
				else if(check == 3) {
					
					loginPanel.setVisible(false);
					ToolBar.userLabel.setText(" "+username+"  ");
					
					loggedAs = username;
					
					renew();
					sbPanel =null;
					toolBar.setReturnButton(true);
					
					add(splitPane,BorderLayout.CENTER);
					splitPane.setVisible(true);
					
					System.out.println(loggedAs);
					
				}
					return check;
				}
		    	
		    	
		    });
		
	}
	
	
	public void renew() {
		
		
		registerPanel = new RegisterPanel();
	    borrowPanel = new BRPanel();
	    returnPanel = new ReturnPanel();
	    tab = new JTabbedPane();
	    allBorrowed = controller.getAllBorrowed();
	    abPanel = new AllBorrowedPanel(allBorrowed);
	    adminLPanel = new AdminLPanel();
	    workerRegister = new WorkerRegister();
	    adminWelcom = new AdminWelcom();
	    
	    rTablePanel = new RegisteredTablePanel();
	    rTablePanel.setData(controller.getStudents());
		rTablePanel.resizeColumnWidth(RegisteredTablePanel.registeredTable);
		rTablePanel.refresh();
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,borrowPanel,sbPanel);
		
		
		adminLPanel.setAdminListener(new AdminListener() {

			@Override
			public void registerClicked() {
				splitPane.setRightComponent(workerRegister);
			}

			@Override
			public void accountClicked() {
				transPanel = new TransactionPanel(controller.getStaffs());
				splitPane.setRightComponent(transPanel);
				
			}
			
			
			
		});
		
		
		workerRegister.setWorkerListener(new WorkerListener() {

			@Override
			public void correct() {
				JOptionPane.showMessageDialog(MainClass.this, "Please Check the fields to ensure \n that they are filled correctly.\n Also check that the image is taken", "Empty Field Notification", JOptionPane.INFORMATION_MESSAGE);
				
			}

			@Override
			public void register(String name, String staffNo, String designation, String username, String password,
					String phoneNo, String email) {
				try {
					controller.addWorker(name,staffNo,designation,username,password,phoneNo,email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void updated() {
				JOptionPane.showMessageDialog(MainClass.this, "The worker's Info has been updated succesfully ", "Info Update Notification", JOptionPane.INFORMATION_MESSAGE,Utill.createIcon("/images/succes.png"));
				
			}

			@Override
			public void done() {
				JOptionPane.showMessageDialog(MainClass.this, "The worker has been succesfully registered", "Registration Notification", JOptionPane.INFORMATION_MESSAGE,Utill.createIcon("/images/succes.png"));
				
			}
			
			
			
		});
		
		 borrowPanel.setBRListener(new BRListener() {

				@Override
				public void retrieveAction(String regNumber) throws SQLException {
					List<BookHistory> bookHistory = controller.retrieveHistory(regNumber);
					List<BorrowedBook> borrowedBook = controller.retrieveBorrowed(regNumber);
					Student student = controller.retrieveStudent(regNumber);
					
					sbPanel = new StudentBooksPanel(student,borrowedBook,bookHistory);
					
					splitPane.setRightComponent(sbPanel);
					
				}

				@Override
				public void borrowAction(String regNumber, String bookTitle, String bookAuthor, String serialId) throws SQLException {
					controller.borrow(regNumber,bookTitle,bookAuthor,serialId);
					
				}

				@Override
				public void returnAction(String regNo,double dateValue) throws SQLException {
					controller.returnBook(regNo,dateValue);
					
				}

				@Override
				public void borrowNotification() {
				JOptionPane.showMessageDialog(MainClass.this, " Book Succesfully Borrowed", "Borrow Notification",JOptionPane.INFORMATION_MESSAGE, Utill.createIcon("/images/succes.png"));

					
				}

				@Override
				public void returnNotification() {
					JOptionPane.showMessageDialog(MainClass.this, " Book Succesfully Returned", "Book Return Notification",JOptionPane.INFORMATION_MESSAGE, Utill.createIcon("/images/succes.png"));

					
				}

				@Override
				public void saveTransaction(String returnRegNo, String returnTitle, int amount) {
					try {
						controller.saveTransaction(returnRegNo,returnTitle,amount,loggedAs);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				@Override
				public void correct() {
					JOptionPane.showMessageDialog(MainClass.this, " Check the fields and make sure \n they are not empty", "Empty Field Notification",JOptionPane.INFORMATION_MESSAGE);

				}
				
				
			});
		    
		   
		
		   
			
			toolBar.setToolBarListener(new ToolBarListener() {

				@Override
				public void registerAction() {
					splitPane.setLeftComponent(registerPanel);
					
					tab.add("Registered Students",rTablePanel);
					
					tab.add("All Borrowed Books",abPanel);
					splitPane.setRightComponent(tab);
				}

				@Override
				public void borrowAction() {
					splitPane.setLeftComponent(borrowPanel);
					splitPane.setRightComponent(sbPanel);
				}

				@Override
				public void returnAction() {
					splitPane.setLeftComponent(adminLPanel);	
					splitPane.setRightComponent(adminWelcom);
				}
				
				
			});
			
			registerPanel.setRegisterListener(new RegisterListener() {
				@Override
				public void register(String name, String regNumber,String level, String department, String faculty,
						String phoneNo, String email) {
					// TODO Auto-generated method stub
					try {
						controller.addStudent(name,regNumber,level,department,faculty,phoneNo,email);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rTablePanel.setData(controller.getStudents());
					rTablePanel.resizeColumnWidth(RegisteredTablePanel.registeredTable);
					rTablePanel.refresh();
				}

				@Override
				public void correct() {
					JOptionPane.showMessageDialog(MainClass.this, "Check the Reg No, Name, Phone Number, Email and Picture \ninput fields to ensure that they are correctly filled", "Details Check", JOptionPane.OK_OPTION & JOptionPane.INFORMATION_MESSAGE, Utill.createIcon("/images/info.png"));
					
					
				}

				@Override
				public void done() {
					
					JOptionPane.showMessageDialog(MainClass.this, " Student Succesfully Registered", "Registration Progress",JOptionPane.INFORMATION_MESSAGE, Utill.createIcon("/images/succes.png"));
					
				}

				@Override
				public int update() {
					System.out.println("We are in MainClass");
	                    return 0;
				}

				@Override
				public void updated() {
					JOptionPane.showMessageDialog(MainClass.this, " Student Succesfully Updated", "Registration Progress",JOptionPane.INFORMATION_MESSAGE, Utill.createIcon("/images/good.png"));
					
				}

			});
			
			
	               controller.setControllerListener(new ControllerListener() {
				
				@Override
				public int promptAction() {
					
					 update = (JOptionPane.showConfirmDialog(MainClass.this,"A student with same Reg Number already exists, \n You sure wanna update the details","Update Student Confirmation ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE));
					return update;
				}

				@Override
				public int promptAction2() {
					update = (JOptionPane.showConfirmDialog(MainClass.this,"A staff with same Staff Number already exists, \n You sure wanna update the details","Update Staff Confirmation ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE));
					return update;
				}
				
			});
			
		
		
	}
	
	
	
	
	public JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("file");
		JMenu exit = new JMenu("exit");
		JMenuItem logout = new JMenuItem("Log out");
		
		menuBar.add(file);
		menuBar.add(exit);
		file.add(logout);
		
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int log = (JOptionPane.showConfirmDialog(MainClass.this,"You sure wanna log out","Logout Confirmation ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE));
                 if(log == JOptionPane.OK_OPTION) {
                	 
                	 loginPanel.setVisible(true);
     				ToolBar.userLabel.setText("");
     				
     				loggedAs = "";
     				splitPane.setVisible(false);
     				//add(loginPanel,BorderLayout.CENTER);
                	 
                 }
				
			}
			
			
		});
			
	
	return menuBar;
		
	}
	

}
