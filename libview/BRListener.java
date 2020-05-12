package libview;

import java.sql.SQLException;

public interface BRListener {
	
	public void retrieveAction(String regNumber) throws SQLException;
	public void borrowAction(String regNumber,String bookTitle,String bookAuthor,String serialId) throws SQLException;
	public void returnAction(String returnTitle, double dateValue) throws SQLException;
	public void borrowNotification();
	public void returnNotification();
	public void saveTransaction(String returnRegNo, String returnTitle, int amount);
	public void correct();

}
