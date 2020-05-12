package libview;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import libmodel.Received;

public class ReceivedModel extends AbstractTableModel {
	List<Received> received;
	
	private String[] name = {"                                                           Date" , "                                                      Amount Remitted"};

	public void setData(List<Received> received) {
		this.received = received;
	}
	
	
	
	@Override
	public String getColumnName(int col) {
		return name[col];
	}



	@Override
	public int getColumnCount() {
		return 2;
	}

	
	@Override
	public int getRowCount() {
		return received.size();
	}

	
	@Override
	public Object getValueAt(int row, int col) {
		Received rec = received.get(row);
		switch(col) {
		case 0: return rec.getDate();
		case 1: return rec.getAmount();
		
		}
		return null;
	}

}
