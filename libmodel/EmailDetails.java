package libmodel;

import java.util.Iterator;

public class EmailDetails {
  private String name,bookTitle,author,seriaNo,bDate,regNo,emailAddress;
  private double dateValue;
		
	public EmailDetails(String name,String regNo,String bookTitle,String author,String serialNo,String bDate,String emailAddress,double dateValue) {
		
		this.name = name;
		this.bookTitle = bookTitle;
		this.author = author;
		this.regNo = regNo;
		this.seriaNo = serialNo;
		this.bDate = bDate;
		this.emailAddress = emailAddress;
		this.dateValue = dateValue;
				
	}
		
	
	public double getDateValue() {
		return dateValue;
	}


	public void setDateValue(double dateValue) {
		this.dateValue = dateValue;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSeriaNo() {
		return seriaNo;
	}

	public void setSeriaNo(String seriaNo) {
		this.seriaNo = seriaNo;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	


	
	
}
