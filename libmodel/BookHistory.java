package libmodel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookHistory {
	
	private String returnDate,borrowDate,bookTitle,bookAuthor,bookSerialNo,regNumber;
	Date date;
	SimpleDateFormat sdf;
	
	public BookHistory(String bookTitle,String bookAuthor,String bookSerialNo,String borrowDate, String returnDate) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookSerialNo = bookSerialNo;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		
	}
	
	public BookHistory(String regNumber,String bookTitle,String bookAuthor,String bookSerialNo,String borrowDate, String returnDate) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookSerialNo = bookSerialNo;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.regNumber = regNumber;
	}
	
	

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookSerialNo() {
		return bookSerialNo;
	}

	public void setBookSerialNo(String bookSerialNo) {
		this.bookSerialNo = bookSerialNo;
	}

	
	
}