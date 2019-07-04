package exeptions;

public class BadFileFormatExeption extends CardManagerExeptions {

	private static final long serialVersionUID = 1L;

	@Override
	public String getExeptionMessage() {
		return "File hasn`t compatible data format";
	}

	
	
}
