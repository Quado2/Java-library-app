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

public class BorrowedListRenderer implements ListCellRenderer {
	JPanel panel;
	JLabel label;
	private Color normalColor;
	private Color selectedColor;
	
	public BorrowedListRenderer() {
	    label = new JLabel();
		panel = new JPanel();
		normalColor = Color.white;
		selectedColor = new Color(150,243,255);
		
		label.setFont(new Font("Poor Richard",Font.BOLD,14));
		label.setIcon(Utill.createIcon("/images/book.png"));
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createDashedBorder(Color.CYAN, 5, 5));
		panel.add(label);
		
		
		
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		BorrowedBook bBook = (BorrowedBook)value;
		label.setText(bBook.getBookTitle() + ": " + bBook.getDate());
		double days = (new Date().getTime() - bBook.getDateValue())/(24*60*60*1000);
		
		panel.setBackground(cellHasFocus? selectedColor: days>3 ? new Color(255,110,180):Color.green);
		label.setBackground(cellHasFocus? selectedColor: days>3 ? new Color(255,110,180):Color.green);
		
		
		return panel;
	}

}
