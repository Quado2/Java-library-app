package libview;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;
 
public class TextPanel extends JPanel{
	private JTextArea textArea;
	
	public TextPanel() {
		setOpaque(false);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setForeground(Color.black);
		textArea.setFont(new Font("Serif",Font.BOLD,12));
		add(textArea);
	}	
		
	public void setText(String text) {
		textArea.setText(text);
	}
	
}
