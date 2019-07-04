package manage;

import java.util.ArrayList;

import exeptions.BadDataFormatExeption;
import exeptions.BadFileVersionExeption;

public class CSVImport extends Import {

	@Override
	public void InterprateData(ArrayList<String> fileData, ArrayList<Card> data) throws BadFileVersionExeption, BadDataFormatExeption {
		//Card Version check
		String Version = "Version:" + String.valueOf(Card.getVersion());
		if(Version.compareTo(fileData.get(0)) != 0) throw new BadFileVersionExeption();
		ArrayList<Card> tempData = new ArrayList<Card>();

		for(int i = 1;i<fileData.size();i++){
			String[] cardData = fileData.get(i).split(",");		
			tempData.add(new Card(cardData));
		}
		data.clear();
		for(int i = 0;i<tempData.size();i++) data.add(tempData.get(i));
	}
}
