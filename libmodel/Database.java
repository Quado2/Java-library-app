package libmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import controller.Controller;


public class Database {
	private List<Student> students;
	private List<Staff> staffs;
	private Connection con;
	private List<BorrowedBook> bBooks;
	private List<BookHistory> bHistory;
	private List<BorrowedBook> allBorrowed;
	private List<Transactions> trans;
	private List<Received> received;
	private TransReceived transReceived;
	
	
	public Database() throws SQLException {
		//controller = new Controller();
		try {
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		students = new LinkedList<Student>();
		staffs =  new LinkedList<Staff>();
		
		reload();
		reloadBorrowed();
		
		}
	
	
	
	private void reloadBorrowed() throws SQLException {
		
			allBorrowed = new LinkedList<>();
		
		String sql = "select * from borrowed_books";
		 Statement selectStatement = con.createStatement();
		 
		 ResultSet results = selectStatement.executeQuery(sql);
		 while(results.next()){
		 
		  
		  String name = results.getString("Name");
		  String regNumber = results.getString("Reg_Number");
		  String serialId = results.getString("Serial_id");
		  String author = results.getString("Author");
		  String bDate = results.getString("B_date");
		  double dateValue = results.getDouble("Date_int");
		  boolean sent = results.getBoolean("sent");
		  

		  BorrowedBook bBook = new BorrowedBook(regNumber,name,author,serialId,bDate,dateValue);
		  bBook.setSent(sent);
		  
		  allBorrowed.add(bBook);
		 
		  }

		
	}

     public List<BorrowedBook> getAllBorrowed(){
    	 return allBorrowed;
     }

	public int saveStudentToDb(Student student) throws SQLException {
		
		
		PreparedStatement checkStatement = con.prepareStatement("SELECT COUNT(*) AS COUNT FROM registered_students WHERE Reg_Number = ?");
		PreparedStatement insertStatement = con.prepareStatement("INSERT INTO registered_students (Name,Reg_Number,Level,Department,Faculty,Phone_No,Email) value(?,?,?,?,?,?,?)");
		
		
	   checkStatement.setString(1,student.getRegNumber());
	   ResultSet checkResult = checkStatement.executeQuery();
	   
	 
	    checkResult.next();
	    int count = checkResult.getInt(1);
	    
	   if(count == 1) {
		   return count;
		}
	     
	   else {
		   
	    	 System.out.println("Inserting a a new student into the database");
	    	 int col = 1;
		    	insertStatement.setString(col++, student.getName());
		    	System.out.println(student.getName());
		    	insertStatement.setString(col++,student.getRegNumber());
		    	Level level = student.getLevel();
		    	System.out.println(level);
		    	insertStatement.setString(col++,level.name());
		    	insertStatement.setString(col++,student.getDepartment());
		    	insertStatement.setString(col++,student.getFaculty());
		    	insertStatement.setString(col++, student.getPhoneNo());
		    	insertStatement.setString(col++, student.getEmail());
	    	 insertStatement.executeUpdate();
	    	 System.out.println("here babay");
	    	 checkStatement.close();
	    	 insertStatement.close();
	    	 
	    	 return count;
	    	 
	    }
		
	}
	
	public void updateStudentToDb(Student student) throws SQLException {
		
		PreparedStatement updateStatement = con.prepareStatement("UPDATE registered_students SET Name =?,Reg_Number =?, Level = ?, Department =?, Faculty = ?,Phone_No = ?,Email =? WHERE Reg_Number = ?");
		// controller.warn();
    	System.out.println("Updating the Student information");
    	int col = 1;
    	updateStatement.setString(col++, student.getName());
    	updateStatement.setString(col++,student.getRegNumber());
    	updateStatement.setString(col++,student.getLevel().name());
    	updateStatement.setString(col++,student.getDepartment());
    	updateStatement.setString(col++,student.getFaculty());
    	updateStatement.setString(col++, student.getPhoneNo());
    	updateStatement.setString(col++, student.getEmail());
    	updateStatement.setString(col++,student.getRegNumber());
    	
    	updateStatement.execute();
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	
	public void reload() throws SQLException {
		students = new LinkedList<Student>();
		
		String sql = "select * from registered_students order by id desc";
		 Statement selectStatement = con.createStatement();
		 
		 ResultSet results = selectStatement.executeQuery(sql);
		 
		 while(results.next()){
		 
		  int id = results.getInt("ID");
		  String name = results.getString("Name");
		  String regNumber = results.getString("Reg_Number");
		  String level = results.getString("Level");
		  String department = results.getString("Department");
		  String faculty = results.getString("Faculty");
		  String phoneNo = results.getString("Phone_No");
		  String email = results.getString("Email");
		  

		  Student student = new Student(name,regNumber,Level.valueOf(level),department,faculty,phoneNo,email);
		  
		  students.add(student);
		  
		 }

			 String sql2 = "select * from registered_workers";
			 Statement selectStatement2 = con.createStatement();
			 ResultSet results2 = selectStatement2.executeQuery(sql2);
			
			 while(results2.next()){
				 
				 
				  String name1 = results2.getString("Name");
				  String staffNo = results2.getString("StaffNo");
				  String desig = results2.getString("designation");
				  String phone = results2.getString("Phone");
				  String email2 = results2.getString("Email");
				  String username = results2.getString("username");
				  String password = results2.getString("Password");
				  

				  Staff staff = new Staff(name1,staffNo,desig,username,password,phone,email2);
				  
				  staffs.add(staff); 
				  }
		 
		  
		
	}
	
	public void removeStudent(int index) {
		students.remove(index);
	}
	
	public List<Student> getStudents(){
		
		return Collections.unmodifiableList(students);
	}
	
	
	public void connect() throws Exception {
		 if(con!=null) return;
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not Found");
		}	
		
		String url = "jdbc:mysql://localhost:3306/library_manager";
		con = DriverManager.getConnection(url, "sid", "32890603");
		System.out.println("Connection is "+ con);
	}
	
	public void createStudentTables(String regNumber) throws SQLException {
		
		String books = "b_"+regNumber;
		String history = "h_"+regNumber;
		
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		int rs = stmt.executeUpdate("CREATE TABLE "+books+" (id SERIAL,book_name VARCHAR(200) NOT NULL,author VARCHAR(100) NOT NULL, serial_id VARCHAR(100) NOT NULL, stamp TIMESTAMP )");
		System.out.println("table creation: "+rs);
		
		int rs2 = stmt.executeUpdate("CREATE TABLE "+history+" (id SERIAL,book_name VARCHAR(200) NOT NULL,author VARCHAR(100) NOT NULL, serial_id VARCHAR(100) NOT NULL, stamp TIMESTAMP, return_date VARCHAR(50))");
		System.out.println(rs2);
		
		
	}



	public Student retrieveStudent(String regNo) throws SQLException {
		String sql = "select * from registered_students where Reg_Number = "+regNo+"";
		 Statement selectStatement = con.createStatement();
		 
		 ResultSet results = selectStatement.executeQuery(sql);
			results.next();
		  
		  String name = results.getString("Name");
		  String regNumber = results.getString("Reg_Number");
		  String level = results.getString("Level");
		  String department = results.getString("Department");
		  String faculty = results.getString("Faculty");
		  String phoneNo = results.getString("Phone_No");
		  String email = results.getString("Email");
		  

		  Student student = new Student(name,regNumber,Level.valueOf(level),department,faculty,phoneNo,email);
		
		  return student;
	}



	public List<BorrowedBook> retrieveBorrowed(String regNumber) throws SQLException {
			bBooks = new LinkedList<>();
		
		String sql = "select * from borrowed_books where Reg_Number = "+regNumber;
		 Statement selectStatement = con.createStatement();
		 
		 ResultSet results = selectStatement.executeQuery(sql);
		 while(results.next()){
		 
		  String name = results.getString("Name");
		  String regNo = results.getString("Reg_number");
		  double dateValue = results.getDouble("Date_int");	  
		  String bookAuthor = results.getString("Author");
		  String bookSerial = results.getString("Serial_Id");
		  String bDate = results.getString("B_date");
		  

		  BorrowedBook bBook = new BorrowedBook(regNo,name,bookAuthor,bookSerial,bDate,dateValue);
		  
		  bBooks.add(bBook);
		 }
		return bBooks;
	}



	public List<BookHistory> retrieveHistory(String regNumber) throws SQLException {
		bHistory = new LinkedList<>();
		
		String sql = "select * from book_history where Reg_Number = "+regNumber;
		 Statement selectStatement = con.createStatement();
		 
		 ResultSet results = selectStatement.executeQuery(sql);
		 while(results.next()){
		 
		  String name = results.getString("Name");
		  String regNo = results.getString("Reg_number");
		 String bDate = results.getString("Borrowed_date");	  
		  String bookAuthor = results.getString("Author");
		  String bookSerial = results.getString("Serial_Id");
		  String rDate = results.getString("Return_date");
		  

		  BookHistory hBook = new BookHistory(regNo,name,bookAuthor,bookSerial,bDate,rDate);
		  
		  bHistory.add(hBook);
		 }
		return bHistory;
	}



	public void borrow(String regNumber, String bookTitle, String bookAuthor, String serialId) throws SQLException {
		
		PreparedStatement insertStatement = con.prepareStatement("INSERT INTO borrowed_books (Name,Author,Serial_Id,Reg_Number,Date_int) value(?,?,?,?,?)");
		
		 System.out.println("Inserting borrowed Books");
    	 int col = 1;
	    	insertStatement.setString(col++, bookTitle);
	    	insertStatement.setString(col++,bookAuthor);
	    	insertStatement.setString(col++,serialId);
	    	insertStatement.setString(col++,regNumber);
	    	insertStatement.setDouble(col++,new Date().getTime());
	    	
    	 insertStatement.executeUpdate();
    	 System.out.println("we are done");
    	 insertStatement.close();
		 
		
	}



	public void returnBook(String regNo, double dateValue) throws SQLException {
		String sql = "select * from borrowed_books where Reg_Number = "+ regNo +" AND Date_int = " + dateValue;
				
		 Statement selectStatement = con.createStatement();
		 
		 String name = null,regN = null,bookAuthor = null,bookSerial = null,bDate = null;
		 
		 ResultSet results = selectStatement.executeQuery(sql);
		 while(results.next()){
		 
		  name = results.getString("Name");
		   regN = results.getString("Reg_number"); 
		   bookAuthor = results.getString("Author");
		  bookSerial = results.getString("Serial_Id");
		  bDate = results.getString("B_date");
		  
		 }
		 
		 
		  PreparedStatement insertStatement = con.prepareStatement("INSERT INTO book_history (Name,Author,Serial_Id,Reg_Number,Borrowed_date) value(?,?,?,?,?)");
			
			 System.out.println("Inserting borrowed Books");
	    	 int col = 1;
		    	insertStatement.setString(col++, name);
		    	insertStatement.setString(col++,bookAuthor);
		    	insertStatement.setString(col++,bookSerial);
		    	insertStatement.setString(col++,regN);
		    	insertStatement.setString(col++,bDate);
		    	
		    	insertStatement.executeUpdate();
		    	System.out.println("History Added");
		    	
		    	results.close();
		    	selectStatement.close();
		    	insertStatement.close();
		    	
				 sql = "delete from borrowed_books where Reg_Number = "+regNo +" AND Date_int = " + dateValue +" limit 1";
				
				 selectStatement = con.createStatement();
				 
				 int num = selectStatement.executeUpdate(sql);
				 System.out.println("deleted rows: " +num);
				 selectStatement.close();
		    	
		    	
		    	
		    	
		 
		 }



	public void alterSent(String regNo, double dateValue) throws SQLException {

		Statement updateStatement = con.createStatement();
		String updateSql = "update borrowed_books SET sent = true WHERE Reg_number = "+regNo+" AND Date_int = "+dateValue;
		int num = updateStatement.executeUpdate(updateSql);
		System.out.println("Email sent to "+regNo+", therefor, the sent column has been changed to true");
		
	}



	public int loginCheck(String username, String password) throws SQLException {
		
		PreparedStatement checkStatement = con.prepareStatement("SELECT COUNT(*) AS COUNT FROM registered_workers WHERE username = ? AND  password = ?");

		   checkStatement.setString(1,username);
		   checkStatement.setString(2, password);
		   ResultSet checkResult = checkStatement.executeQuery();
		   
		 
		    checkResult.next();
		    int count = checkResult.getInt(1);
		    
		   if(count == 1) {
			   
			   String sql = "select * from registered_workers where username = '"+username+"'";
				 Statement selectStatement = con.createStatement();
				 
				 ResultSet results = selectStatement.executeQuery(sql);
					results.next();
				  
				   
			   String desig = results.getString("designation");
			   System.out.println(desig);
			   if(desig.equals("worker")) {
				   System.out.println("He is a worker");
				   return 2;
				   }
			   
			   else
			   return 3;
			  }
				  
			  
		   else {		   
			   
			   return 0;
		   }
		   
		   }



	public void saveTransaction(String returnRegNo, String returnTitle, int amount, String loggedAs) throws SQLException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/YYYY");
		String date = sdf.format(new Date().getTime());
		
		
		
		PreparedStatement insertStatement = con.prepareStatement("INSERT INTO transactions (Reg_no,Book_title,Amount_collected,worker,Date) value(?,?,?,?,?)");
		
		 System.out.println("Inserting borrowed Books");
    	 int col = 1;
	    	insertStatement.setString(col++, returnRegNo);
	    	insertStatement.setString(col++,returnTitle);
	    	insertStatement.setInt(col++,amount);
	    	insertStatement.setString(col++,loggedAs);
	    	insertStatement.setString(col++,date);
	    	
    	 insertStatement.executeUpdate();
    	 System.out.println("we are done");
    	 insertStatement.close();
		
	}



	public int saveStaffToDb(Staff staff) throws SQLException {



		PreparedStatement checkStatement = con.prepareStatement("SELECT COUNT(*) AS COUNT FROM registered_workers WHERE StaffNo = ?");
		PreparedStatement insertStatement = con.prepareStatement("INSERT INTO registered_workers (Name,Phone ,Email,username,Password,designation,StaffNo) value(?,?,?,?,?,?,?)");
		
		
	   checkStatement.setString(1,staff.getStaffNo());
	   ResultSet checkResult = checkStatement.executeQuery();
	   
	 
	    checkResult.next();
	    int count = checkResult.getInt(1);
	    
	   if(count == 1) {
		   return count;
		}
	     
	   else {
		   
	    	 System.out.println("Inserting a a new Staff into the database");
	    	 int col = 1;
		    	insertStatement.setString(col++, staff.getName());
		    	System.out.println("Passed name");
		    	insertStatement.setString(col++,staff.getPhoneNo());
		    	System.out.println("Passed phone");
		    	insertStatement.setString(col++,staff.getEmailAddress());
		    	insertStatement.setString(col++,staff.getUsername());
		    	insertStatement.setString(col++,staff.getPassword());
		    	insertStatement.setString(col++, staff.getDesig());
		    	insertStatement.setString(col++, staff.getStaffNo());
	    	 insertStatement.executeUpdate();
	    	 System.out.println("Staff Added");
	    	 checkStatement.close();
	    	 insertStatement.close();
	    	 
	    	 return count;
	    	 
	    }
		
		
		
	}



	public void updateStaffToDb(Staff staff) throws SQLException {
		
		PreparedStatement updateStatement = con.prepareStatement("UPDATE registered_workers SET Name =?,Phone =?, Email = ?, username =?, Password = ?,designation = ?,StaffNo =? WHERE StaffNo = ?");
		// controller.warn();
    	System.out.println("Updating the Student information");
    	int col = 1;
    	updateStatement.setString(col++, staff.getName());
    	updateStatement.setString(col++,staff.getPhoneNo());
    	updateStatement.setString(col++,staff.getEmailAddress());
    	updateStatement.setString(col++,staff.getUsername());
    	updateStatement.setString(col++,staff.getPassword());
    	updateStatement.setString(col++, staff.getDesig());
    	updateStatement.setString(col++, staff.getStaffNo());
    	updateStatement.setString(col++,staff.getStaffNo());
    	
    	updateStatement.execute();
		
	}



	public List<Staff> getStaffs() {
		return staffs;
	}


	
	public TransReceived getTransaction(String username) throws SQLException {
		trans = new LinkedList<Transactions>();

		
		String sql = "select * from transactions where worker = '"+username+"'";
		 Statement selectStatement = con.createStatement();
		 
		 ResultSet results = selectStatement.executeQuery(sql);
		 while(results.next()){
		 
		  String title = results.getString("Book_title");
		  String regNo = results.getString("Reg_no");
		 String date = results.getString("date");	  
		  int amount = results.getInt("Amount_collected");
		 
		  Transactions tran = new Transactions(title,regNo,date,amount);
		  trans.add(tran);
		 }
		 
		 
	        selectStatement.close();
		    results.close();
		    received = new LinkedList<Received>();

			sql = "select * from received where username = '"+username+"'";
			  selectStatement = con.createStatement();
			 
			  results = selectStatement.executeQuery(sql);
			 while(results.next()){
			 
			  String user = results.getString("username");
			  String date = results.getString("date");	  
			  int amount = results.getInt("amount");
			 
			  

			  Received receive = new Received(user,date,amount);
			  received.add(receive);
			 }
		 
			 transReceived = new TransReceived(trans,received);
			 return transReceived;
		
		
	}



	public void receive(String username, int total) throws SQLException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/YYYY");
		String date = sdf.format(new Date().getTime());
		
		PreparedStatement insertStatement = con.prepareStatement("INSERT INTO received (username,date,amount) value(?,?,?)");
		
		 System.out.println("Inserting borrowed Books");
    	 int col = 1;
	    	insertStatement.setString(col++, username);
	    	insertStatement.setString(col++,date);
	    	insertStatement.setInt(col++,total);
	    	
    	 insertStatement.executeUpdate();
    	 System.out.println("inserted in the received");
    	 insertStatement.close();
    	 
    	 String sql = "delete from transactions where worker = '"+username+"'";
			
		 Statement selectStatement = con.createStatement();
		 
		 int num = selectStatement.executeUpdate(sql);
		 System.out.println("deleted rows from trans: " +num);
		 selectStatement.close();
	}

	}

