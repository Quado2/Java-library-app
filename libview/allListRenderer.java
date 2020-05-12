package libview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import libmodel.BorrowedBook;

public class allListRenderer implements ListCellRenderer {
	
	private JPanel panel;
	private JLabel label;
	private Color normalColor;
	private Color selectedColor;
		
		public allListRenderer () {
			label = new JLabel();
			panel = new JPanel();
			normalColor = Color.white;
			selectedColor = new Color(160,250,255);
			
			label.setFont(new Font("Poor Richard",Font.BOLD,16));
			label.setIcon(Utill.createIcon("/images/book.png"));
			
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
			panel.add(new JScrollPane(label),BorderLayout.CENTER);
		
		
		}
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		BorrowedBook bBook = (BorrowedBook)value;
		label.setText(bBook.getRegNumber() + ":  "+bBook.getBookTitle() + "  by  "+bBook.getBookAuthor()+ ": " + bBook.getDate());
		
		double days = (new Date().getTime() - bBook.getDateValue())/(24*60*60*1000);
		
		panel.setBackground(cellHasFocus? selectedColor: days > 4 ? new Color(200,130,210):normalColor);
		label.setBackground(cellHasFocus? selectedColor: days> 4 ? new Color(200,130,210):normalColor);
		
		
		return panel;
	}

}
