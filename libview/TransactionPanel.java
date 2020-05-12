package libview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import controller.Controller;
import libmodel.Staff;
import libmodel.TransReceived;
import libmodel.Transactions;

public class TransactionPanel extends JPanel {
	
	JList<Staff> staffList;
	DefaultListModel staffModel;
	JPanel lPanel,rPanel;
	JLabel info,info2,empty;
	Controller controller;
	JLabel sum;
	JButton receive;
	Staff staff;
	TransReceived transreceived;
	JTable table,table2;
	JScrollPane scroll2,scroll;
	int total = 0;
	
	
	public TransactionPanel(List<Staff> staffs) {
		
		setLayout(new GridBagLayout());
		staffModel = new DefaultListModel();
		try {
			controller = new Controller();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		staffList = new JList<Staff>(staffModel);
		lPanel = new JPanel();
		rPanel = new JPanel();
		rPanel.setLayout(new GridBagLayout());
		
		info = new JLabel("The Transaction details of a selected");
		info2 = new JLabel("worker will show here.");
		receive = new JButton("Receive total from worker");
		empty = new JLabel();
		info.setFont(new Font("Sanserrif",Font.BOLD,20));
		info2.setFont(new Font("Sanserrif",Font.BOLD,20));
		
	    GridBagConstraints gc = new GridBagConstraints();
	    gc.gridx = 0;
	    gc.gridy = 0;
	    gc.weightx = 0.3;
	    gc.insets = new Insets(70,0,0,0);
	    rPanel.add(info,gc);
	    
	    gc.gridy = 1;
	    gc.weighty = 2;
	    gc.insets = new Insets(30,0,0,0);
	    rPanel.add(info2,gc);
		
		staffModel.removeAllElements();
		
		for(Staff staff: staffs) {
			staffModel.addElement(staff);
		}
		staffList.setCellRenderer(new staffListRenderer());
		
		
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.gridx = 0;
		gc2.gridy = 0;
		gc2.weighty = 0.2;
		gc2.anchor = GridBagConstraints.NORTH;
		lPanel.add(staffList,gc2);
		
		gc2.gridy++;
		gc.weighty = 1;
		lPanel.add(empty,gc);
		
		
		gc2.gridx = 0;
		gc2.gridy = 0;
		gc2.weightx = 0.2;
		gc2.anchor = GridBagConstraints.NORTH;
		add(lPanel,gc2);
		
		gc2.gridx = 1;
		gc2.weightx = 1;
		gc.anchor = GridBagConstraints.NORTH;
		add(rPanel,gc2);
		
		
	
		staffList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				 staff = (Staff)staffList.getSelectedValue();
				
				List<Transactions> trans;
				
					try {
						retrieve();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				JPanel rMidPanel = new JPanel(new FlowLayout());
				rMidPanel.add(sum);
				rMidPanel.add(receive);
				rPanel.add(rMidPanel,BorderLayout.CENTER);
				
				JPanel rDownPanel = new JPanel(new BorderLayout());
				JLabel settled = new JLabel("Remitted Transactions");
				settled.setFont(new Font("Sanserriff",Font.BOLD,20));
				rDownPanel.add(settled,BorderLayout.NORTH);
				rDownPanel.add(scroll2,BorderLayout.CENTER);
				
				rPanel.add(rDownPanel,BorderLayout.SOUTH);
				rPanel.revalidate();
				rPanel.repaint();
				
				
			}
			
			
		});
		
		receive.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int response = JOptionPane.showConfirmDialog(TransactionPanel.this, "Do you confirm that you have received \n"+total+" from this worker ?", "Received Comfirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
				controller.receive(staff.getUsername(),total);
				try {
					retrieve();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
			
			
		});
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
	    table.getColumnModel().getColumn(3).setCellRenderer(centreRenderer);
	    
	
	}
	
	public void retrieve() throws SQLException {
		
		transreceived = controller.getTransaction(staff.getUsername());
		TransModel transModel =  new TransModel();
		transModel.setData(transreceived.getTrans());
		table = new JTable(transModel);
		table.setAlignmentY(JTable.LEFT_ALIGNMENT);
		table.setRowHeight(30);
		table.setRowMargin(3);
		table.setBackground(new Color(214,255,255));
		table.setFont(new Font("sanserif",Font.BOLD,13));
		
		resizeColumnWidth(table);
		
		ReceivedModel receivedModel = new ReceivedModel();
		receivedModel.setData(transreceived.getReceived());
		table2 = new JTable(receivedModel);
		table2.setAlignmentY(JTable.LEFT_ALIGNMENT);
		table2.setRowHeight(30);
		table2.setRowMargin(3);
		table2.setBackground(new Color(214,255,255));
		table2.setFont(new Font("sanserif",Font.BOLD,13));
		
	    DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
	    centreRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	    table2.getColumnModel().getColumn(1).setCellRenderer(centreRenderer);
	    table2.getColumnModel().getColumn(0).setCellRenderer(centreRenderer);
	    
		scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(800,200));

		
		 scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(800,300));
		rPanel.removeAll();
		rPanel.setLayout(new BorderLayout());
		rPanel.add(scroll,BorderLayout.NORTH);
		
		total = 0;
		for(Transactions tran: transreceived.getTrans()) {
			total  += tran.getAmount();	
		}
		
		
		sum = new JLabel("Total sum to be remitted by this worker: N" +total);
		sum.setFont(new Font("Sanserrif",Font.BOLD,16));
		
		
	}

}
