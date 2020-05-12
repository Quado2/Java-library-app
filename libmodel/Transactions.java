package libmodel;

public class Transactions {
	String name,regNo,date;
	int amount;
	
	public Transactions(String name, String regNo, String date, int amount) {
		
		this.name = name;
		this.regNo = regNo;
		this.date = date;
		this.amount = amount;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
}
