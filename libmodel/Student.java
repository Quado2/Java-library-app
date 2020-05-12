package libmodel;

import java.awt.image.BufferedImage;
import java.util.List;

public class Student {

	private String name,regNumber,phoneNo,email,department,faculty;
	private Level level;
	private int id;
	static int count = 1;
	
	public Student(String name,String regNumber,Level level,String department,String faculty,String phoneNo,String email) {
		
		this.name = name;
		this.regNumber = regNumber;
		this.level = level;
		this.department = department;
		this.faculty = faculty;
		this.phoneNo = phoneNo;
		this.email = email;
		this.id = count;
		count++;
		
	}
    
	


	public void setName(String name) {
		this.name = name;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public String getDepartment() {
		return department;
	}

	public String getFaculty() {
		return faculty;
	}

	public Level getLevel() {
		return level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
