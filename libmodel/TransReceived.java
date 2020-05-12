package libmodel;

import java.util.List;

public class TransReceived {
	
	List<Received> received;
	List<Transactions> trans;
	
	public TransReceived(List<Transactions> trans,List<Received> received) {
		
		this.received = received;
		this.trans = trans;
	}

	public List<Received> getReceived() {
		return received;
	}

	public void setReceived(List<Received> received) {
		this.received = received;
	}

	public List<Transactions> getTrans() {
		return trans;
	}

	public void setTrans(List<Transactions> trans) {
		this.trans = trans;
	}



}
