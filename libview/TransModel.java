package libview;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import libmodel.Transactions;

public class TransModel extends AbstractTableModel {
	
	List<Transactions> trans; 
	private String[] name = {"                        Book Title","             User Reg No","             Date Returned","   Amount Collected"};

	
	
	@Override
	public String getColumnName(int columnIndex) {
		return name[columnIndex];
	}
	

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		
		return trans.size();
	}
	
	public void setData(List<Transactions> trans) {
		
		this.trans = trans;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Transactions tran  = trans.get(row);
		switch(col) {
		case 0: return tran.getName();
		case 1: return tran.getRegNo();
		case 2: return tran.getDate();
		case 3: return tran.getAmount();
		
		}
		return null;
	}

}
