package libview;

import java.sql.SQLException;

public interface LoginListener {
	
	public int loginCheck(String username, String password) throws SQLException;

}
