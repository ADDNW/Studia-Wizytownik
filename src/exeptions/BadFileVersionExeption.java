package exeptions;

public class BadFileVersionExeption extends CardManagerExeptions {

	private static final long serialVersionUID = 1L;

	@Override
	public String getExeptionMessage() {
		return "This file isin`t compatible with current version";
	}

	
	
}
