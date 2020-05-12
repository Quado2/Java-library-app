package controller;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import libmodel.BookHistory;
import libmodel.BorrowedBook;
import libmodel.Database;
import libmodel.EmailDetails;
import libmodel.Level;
import libmodel.Staff;
import libmodel.Student;
import libmodel.TransReceived;
import libmodel.Transactions;
import libview.ControllerListener;
import javax.mail.PasswordAuthentication;



public class Controller implements Iterable<BorrowedBook>{
	Database db;
	ControllerListener controllerListener;
	List<BorrowedBook> allBorrowed;
	List<EmailDetails> emailList;
	
	
	
	public Controller() throws SQLException {
		db = new Database();
		
		emailList = checkBorrowed();
		
		sendEmail(emailList);
		
	}

private void sendEmail(List<EmailDetails> emailList) throws SQLException {
       if(emailList != null) { 
	String username = "engchikwas404@gmail.com";
	String password = "2034chi4955";
	
	Properties props = new Properties();
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port","587");
	
	Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication(username,password);
		}
	});
	
	for(EmailDetails eD: emailList) {
		System.out.println("succesfully gotten "+ eD.getBookTitle());
		   
	try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		System.out.println("here 1");
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(eD.getEmailAddress()));
		System.out.println("here 2");
		message.setSubject("Kindly Return the Book Borrowed");
		message.setText("Dear " + eD.getName() + ",\n This mail is to remind you that the book '" +eD.getBookTitle()+"' by '"+eD.getAuthor()+"' which you borrowed on "+ eD.getbDate()+" will expire tommorow.\n Kindly "
				+ "	return the mentioned book by tommorow to avoid being surcharged.\n Do have a wonderful today." );
		
		message.setSentDate(new Date());
		
		System.out.println("here 3");
		Transport.send(message);
		
		System.out.println("Mail sent to "+eD.getName());
		
		db.alterSent(eD.getRegNo(),eD.getDateValue());
	}
		
	catch(MessagingException e) {
		System.out.println("Could not send email to: "+eD.getName()+ ": "+ e.getMessage());
				}
		
       		}
       }
     }

private List<EmailDetails> checkBorrowed() throws SQLException {
		allBorrowed = db.getAllBorrowed();
		List<EmailDetails> emailList = new LinkedList<EmailDetails>();
		
		for(BorrowedBook borrowed: allBorrowed) {
			
			System.out.println("this motherfuclker borrowed: "+borrowed.getRegNumber());
			
			if((new Date().getTime() - borrowed.getDateValue())/(1000*60*60*24) > 4) {
				if(!borrowed.isSent()) {
				
			System.out.println("this person don enter: "+borrowed.getRegNumber());
			
			String regNo = borrowed.getRegNumber();
			String bookTitle = borrowed.getBookTitle();
			String author = borrowed.getBookAuthor();
			String serialNo = borrowed.getSerialNo();
			String bDate = borrowed.getDate();
			double dateValue = borrowed.getDateValue();
		
			Student student = db.retrieveStudent(regNo);
			
			String emailAddress = student.getEmail();
			String name = student.getName();
			
			EmailDetails emailDetails = new EmailDetails(name,regNo,bookTitle,author,serialNo,bDate,emailAddress,dateValue); 
			emailList.add(emailDetails);
			
				}
			}
		}
		
		return emailList;
		
	}

public void addStudent(String name,String regNo, String level,String department,String faculty,String phoneNo,String email) throws SQLException  {
	    System.out.println("Controller:"+level);
		Level lev;
    if(level.equalsIgnoreCase(" 100"))
        lev = Level._100;
    
    else  if(level.equalsIgnoreCase(" 200"))
        lev = Level._200;
    
    else  if(level.equalsIgnoreCase(" 300"))
        lev = Level._300;
    else  if(level.equalsIgnoreCase(" 400"))
        lev = Level._400;
    else  if(level.equalsIgnoreCase(" 500"))
        lev = Level._500;
    else  if(level.equalsIgnoreCase(" 600"))
        lev = Level._600;
    else lev = Level.extraYear;
    
Student student = new Student(name,regNo,lev,department,faculty,phoneNo,email);
	
	
	
	//db.createStudentTables(student.getRegNumber());
	
	int save = db.saveStudentToDb(student);
	db.reload();
	
	if(save == 1) {
		
		System.out.println("We might update");
		
		if(controllerListener!=null) {
			
			if(controllerListener.promptAction()==0) {
			
			db.updateStudentToDb(student);
			db.reload();
			
			}
		}
	}
	
	db.reload();

}
	
	



public void removeStudent(int index) {
	db.removeStudent(index);
}


public List<Student> getStudents(){
	
	return (db.getStudents());
}

public Student retrieveStudent(String regNumber) throws SQLException{
	Student student = db.retrieveStudent(regNumber);
	return student;
}

public List<BorrowedBook> retrieveBorrowed(String regNumber) throws SQLException{
	List<BorrowedBook> bBooks  = db.retrieveBorrowed(regNumber);
	return bBooks;
}

public List<BookHistory> retrieveHistory(String regNumber) throws SQLException{
	List<BookHistory> bHistory = db.retrieveHistory(regNumber);
	return bHistory;
}


public void setControllerListener(ControllerListener listener) {
	
	this.controllerListener = listener;
}

public void borrow(String regNumber, String bookTitle, String bookAuthor, String serialId) throws SQLException {
	db.borrow(regNumber,bookTitle,bookAuthor,serialId);
	
}

public void returnBook(String regNo, double dateValue) throws SQLException {
	db.returnBook(regNo,dateValue);
	
}

@Override
public Iterator<BorrowedBook> iterator() {
	// TODO Auto-generated method stub
	return allBorrowed.iterator();
}

public List<BorrowedBook> getAllBorrowed() {
	return db.getAllBorrowed();
}

public int loginCheck(String username, String password) throws SQLException {
	
	return(db.loginCheck(username,password));	
}

public void saveTransaction(String returnRegNo, String returnTitle, int amount, String loggedAs) throws SQLException {
	db.saveTransaction(returnRegNo,returnTitle,amount,loggedAs);
	
}



public void addWorker(String name, String staffNo, String designation, String username, String password, String phoneNo,
		String email) throws SQLException {
	
	String desig;
	
if(designation.equalsIgnoreCase(" worker")) 
	 desig = "worker";
	   
else
    desig = "admin";

Staff staff = new Staff(name,staffNo,desig,username,password,phoneNo,email);



//db.createStudentTables(student.getRegNumber());

int save = db.saveStaffToDb(staff);
db.reload();

if(save == 1) {
	
	if(controllerListener!=null) {
		
		if(controllerListener.promptAction2()==0) {
		
		db.updateStaffToDb(staff);
		db.reload();
		
		}
	}
}

db.reload();

	// TODO Auto-generated method stub
	
}

public List<Staff> getStaffs() {
	// TODO Auto-generated method stub
	return db.getStaffs();
}



public TransReceived getTransaction(String username) throws SQLException {

	return db.getTransaction(username);
	
}

public void receive(String username, int total) {
	try {
		db.receive(username,total);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



}
