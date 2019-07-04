package manage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import exeptions.BadDataFormatExeption;
import exeptions.BadFileVersionExeption;
import exeptions.InterpreterNotFoundExeption;

public abstract class Import {

	
	//To do not byte but throw!!!
	public void ImportData(String path, ArrayList<Card> data) throws IOException, FileNotFoundException, BadFileVersionExeption, BadDataFormatExeption, ParseException, InterpreterNotFoundExeption {

		BufferedReader file = new BufferedReader(new FileReader(new File(path)));
		ArrayList<String> fileData = new ArrayList<String>();
		String line = file.readLine();
		while(line != null) {
			fileData.add(line);
			line = file.readLine();
		}
		file.close();
		InterprateData(fileData, data);
	}
	
	public abstract void InterprateData(ArrayList<String> fileData, ArrayList<Card> data) throws BadFileVersionExeption, BadDataFormatExeption, ParseException, InterpreterNotFoundExeption, IOException;
}
