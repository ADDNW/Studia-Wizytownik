package exeptions;

public class BadDataFormatExeption extends CardManagerExeptions {

	private static final long serialVersionUID = 1L;

	@Override
	public String getExeptionMessage() {
		return "Card data format is not compatible with given data";
	}

	
	
}
