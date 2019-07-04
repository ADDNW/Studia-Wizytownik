package manage;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import exeptions.BadDataFormatExeption;
import exeptions.BadFileVersionExeption;
import exeptions.InterpreterNotFoundExeption;


public class JSONImport extends Import {

	@Override
	public void InterprateData(ArrayList<String> fileData, ArrayList<Card> data) throws BadFileVersionExeption, ParseException, BadDataFormatExeption, InterpreterNotFoundExeption, IOException {
		//Check version:
		String Version = "Version:" + String.valueOf(Card.getVersion());
		if(Version.compareTo(fileData.get(0)) != 0) throw new BadFileVersionExeption();
		
		JSONParser parser = new JSONParser();
		
		JSONArray cards = (JSONArray)parser.parse(fileData.get(1));

		ArrayList<Card> tempData = new ArrayList<Card>();

		for(int i=0; i < cards.size(); i++){
			String[] cardData = new String[Card.getDataFormat().length];
			JSONObject card = (JSONObject) ((JSONObject) (cards.get(i))).get("CARD");
			for(int j=0; j < cardData.length; j++){
				cardData[j] = (String) card.get(Card.getDataFormat()[j]);  	
			}
			tempData.add(new Card(cardData));
		}
		data.clear();
		for(int i = 0;i<tempData.size();i++) data.add(tempData.get(i));
	}
}
