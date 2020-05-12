package libview;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libmodel.BorrowedBook;

public class AllBorrowedPanel extends JPanel{
	private JList allBorrowedList;
	private DefaultListModel allBorrowedModel;
	private JTextArea textArea;
	
	public AllBorrowedPanel(List<BorrowedBook> allBorrowed) {
		
		setLayout(new BorderLayout());
		textArea = new JTextArea();
		allBorrowedModel = new DefaultListModel();
		allBorrowedList = new JList();
		allBorrowedList.setModel(allBorrowedModel);
		
		allBorrowedList.setCellRenderer(new allListRenderer());
		JPanel panel = new JPanel();
		panel.add(allBorrowedList);
		add(new JScrollPane(panel),BorderLayout.CENTER);
		JPanel panel2  = new JPanel();
		panel2.add(textArea);
		add(panel2,BorderLayout.SOUTH);
		
        for(int i = 0;i<allBorrowed.size();i++) {
			
			allBorrowedModel.addElement(allBorrowed.get(i));
		}
        
        allBorrowedList.addListSelectionListener(new ListSelectionListener() {
            
			   
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				BorrowedBook bBook = (BorrowedBook)allBorrowedList.getSelectedValue();
				int days = (int)(new Date().getTime() - bBook.getDateValue())/(1000*60*60*24);
				 
				 textArea.setFont(new Font("Serif",Font.BOLD,18));
				
				textArea.setText(bBook.getBookTitle() + "   by  " + bBook.getBookAuthor() + ", with serial No: "+bBook.getSerialNo() + "\n"
									+" Borrowed on "+ bBook.getDate()+".     Used for "+days +"day(s)");
				textArea.setEditable(false);
			
				
				
			}
			
		});
	}

}
