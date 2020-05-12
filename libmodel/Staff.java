package libmodel;

public class Staff {
	String name,staffNo,username,password,desig,phoneNo,emailAddress;
	
	public Staff(String name,String staffNo,String desig, String username,String password,String phoneNo,String emailAddress) {
		this.name = name;
		this.staffNo = staffNo;
		 this.desig = desig;
		 this.username = username;
		  this.password = password;
		   this.phoneNo = phoneNo;
		    this.emailAddress = emailAddress;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	
}
