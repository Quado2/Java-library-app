package libview;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import libmodel.Student;

public class RegisteredTableModel extends AbstractTableModel{
	
	private List<Student> students;
	private String[] name = {"NAME","REG NO","LEVEL","DEPARTMENT","FACULTY","PHONE NO","EMAIL"};
	
	public RegisteredTableModel() {
		
		
	}
	
	
	
	@Override
	public String getColumnName(int colIndex) {
		return name[colIndex];
	}



	public void setData(List<Student> students) {
		
		this.students = students;
	}

	
	@Override
	public int getColumnCount() {
		return 7;
	}

	
	@Override
	public int getRowCount() {
		return students.size();
	}
	

	@Override
	public Object getValueAt(int row, int col) {
			Student student = students.get(row);
			switch(col) {
			case 0: return student.getName();
			case 1: return student.getRegNumber();
			case 2:return student.getLevel();
			case 3: return student.getDepartment();
			case 4: return student.getFaculty();
			case 5: return student.getPhoneNo();
			case 6: return student.getEmail();
			}
			
			return null;
			
			
	}
	
	 @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        
	         switch(columnIndex){
	            case 0:
	                return Integer.class;
	            case 1:
	                return String.class;
	            case 2:
	                return String.class;
	            case 3:
	                return String.class;
	            case 4:
	                return String.class;
	            case 5:
	                return String.class;
	            case 6:
	                return String.class;
	            case 7:
	                return Boolean.class;
	            case 8:
	                return String.class;
	            default: return null;
	            
	         
	    }
	    }

}
