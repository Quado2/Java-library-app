package libview;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import libmodel.BorrowedBook;

public class BorrowedModel extends AbstractTableModel {
	
	public BorrowedModel () {
		
	}
	
	List<BorrowedBook> bBooks;
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
	
	public void setData(List<BorrowedBook> bBooks) {
		this.bBooks = bBooks;
		
	}
}
