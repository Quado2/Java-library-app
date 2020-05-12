package libview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import libmodel.BookHistory;

public class HistoryListRenderer implements ListCellRenderer {
	  private JLabel label;
	  private JPanel panel;
	  private Color normalColor,selectedColor;
	  
	public HistoryListRenderer() {
	   
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
		BookHistory hBook = (BookHistory)value;
		label.setText(hBook.getBookTitle() + ":  " + hBook.getBorrowDate()+ ":     "+ hBook.getReturnDate());
		
		
		panel.setBackground(cellHasFocus? selectedColor: normalColor);
		label.setBackground(cellHasFocus? selectedColor: normalColor);
		
		
		return panel;
	}

}
