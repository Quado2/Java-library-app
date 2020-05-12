package libview;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class LibraryRunner {

	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {

		@Override
		public void run() {
			
				new MainClass();
			
		}
		});
	}
}
