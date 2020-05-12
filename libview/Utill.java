package libview;

import java.net.URL;

import javax.swing.ImageIcon;

public class Utill {
	
	public Utill() {
			
	}
	
	public static ImageIcon createIcon(String path) {
			
	URL url = System.class.getResource(path);
	if(url==null) {
		
		System.err.println("Unable ot load file "+path);
	}
	ImageIcon icon = new ImageIcon(url);
	return icon;
	
	}

}
