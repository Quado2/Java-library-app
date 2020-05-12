package libview;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import libmodel.BorrowedBook;
import libmodel.Staff;

public class staffListRenderer implements ListCellRenderer {
	JPanel panel;
	JLabel label;
	private Color normalColor;
	private Color selectedColor;
	
	 public staffListRenderer() {
		 
		     label = new JLabel();
			panel = new JPanel();
			normalColor = Color.white;
			selectedColor = new Color(150,243,255);
			
			label.setFont(new Font("Times new Roman",Font.BOLD,16));
			label.setIcon(Utill.createIcon("/images/worker.png"));
			
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			panel.setBorder(BorderFactory.createDashedBorder(Color.CYAN, 5, 5));
			panel.add(label); 
	 }
	
	
	
	
	
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Staff staff = (Staff)value;
		label.setText(staff.getName() + ": " + staff.getUsername());
		
		panel.setBackground(cellHasFocus? selectedColor: normalColor);
		label.setBackground(cellHasFocus? selectedColor: normalColor);
		
		
		return panel;

	}

}
