package libmodel;

public class BorrowedBook {
	
	private String bookAuthor,bookTitle,serialNo,regNumber,date;
	private double dateValue;
	private boolean sent;
	
	public BorrowedBook(String regNumber,String bookTitle,String bookAuthor,String serialNo,String date, double dateValue) {
		this.regNumber = regNumber;
		this.bookAuthor = bookAuthor;
		this.bookTitle = bookTitle;
		this.serialNo = serialNo;
		this.dateValue = dateValue;
		this.date = date;
		this.sent = false;
		
		this.serialNo = serialNo;
	}

	
	public String getDate() {
		return date;
	}



	public boolean isSent() {
		return sent;
	}


	public void setSent(boolean sent) {
		this.sent = sent;
	}


	public void setDate(String date) {
		this.date = date;
	}



	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public double getDateValue() {
		return dateValue;
	}

	public void setDateValue(double dateValue) {
		this.dateValue = dateValue;
	}

	
	
}
