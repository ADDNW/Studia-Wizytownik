package exeptions;

public class InterpreterNotFoundExeption extends CardManagerExeptions {

	private static final long serialVersionUID = 1L;

	@Override
	public String getExeptionMessage() {
		return "Program not preapered to work with that extension";
	}

	
	
}
