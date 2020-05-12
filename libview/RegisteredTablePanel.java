package libview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import libmodel.Student;

public class RegisteredTablePanel extends JPanel {
	
	public static JTable registeredTable;
	private  RegisteredTableModel registeredModel;
	
	
	public RegisteredTablePanel() {
		
	
		registeredModel = new RegisteredTableModel();
		
		registeredTable = new JTable(registeredModel);
		registeredTable.setAlignmentY(JTable.LEFT_ALIGNMENT);
		registeredTable.setRowHeight(30);
		registeredTable.setRowMargin(3);
		registeredTable.setBackground(new Color(214,255,255));
		registeredTable.setFont(new Font("sanserif",Font.BOLD,13));
		
		
		setLayout(new BorderLayout()) ;
		
		add(new JScrollPane(registeredTable),BorderLayout.CENTER);
		
	}
  
	public void setData(List<Student> students) {
		registeredModel.setData(students);
	}
	
	public void refresh() {
		registeredModel.fireTableDataChanged();
		
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	    
	    DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
	    DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
	    
	    leftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
	    table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
	    
	    centreRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	    table.getColumnModel().getColumn(1).setCellRenderer(centreRenderer);
	    table.getColumnModel().getColumn(2).setCellRenderer(centreRenderer);
	    table.getColumnModel().getColumn(5).setCellRenderer(centreRenderer);
	
	}

}
