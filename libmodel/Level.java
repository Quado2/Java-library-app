package libmodel;

public enum Level {
	_100("100"),
	_200("200"),
	_300("300"),
	_400("400"),
	_500("500"),
	_600("600"),
	extraYear("Extra Year");
	
	private String text;
	
	private Level(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}


	
	

}
