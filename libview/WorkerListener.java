package libview;

public interface WorkerListener {

	void correct();

	void register(String name, String staffNo, String designation, String username, String password, String phoneNo,
			String email);

	void updated();

	void done();

}
