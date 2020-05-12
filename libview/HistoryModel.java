package libview;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import libmodel.BookHistory;

public class HistoryModel extends AbstractTableModel{
	
	List<BookHistory> bookHistory;
	
	public HistoryModel () {
		
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setData(List<BookHistory> bookHistory) {
		
		this.bookHistory = bookHistory;
	}

}
