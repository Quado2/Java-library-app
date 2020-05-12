package libview;

import java.awt.image.BufferedImage;

public interface RegisterListener {
	public void register(String name,String regNumber,String level,String department,String faculty,String phoneNo, String email);

	public void correct();

	public void done();

	public int update();

	public void updated();

	


}
