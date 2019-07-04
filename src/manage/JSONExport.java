package manage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class JSONExport extends Export {

	public void WriteData(BufferedWriter file, ArrayList<Card> data) throws IOException  {
		file.write("Version:" + Card.getVersion());
		file.newLine();
		
		//here we put Cards
		JSONArray JSONdata = new JSONArray();

		for(int i = 0; i < data.size(); i++){
			JSONObject card = new JSONObject();
			JSONObject cardFields = new JSONObject();
			for(int j =0; j < Card.getDataFormat().length; j++){
				cardFields.put(Card.getDataFormat()[j],(data.get(i)).getCard()[j]);
			}
			card.put("CARD", cardFields);
			JSONdata.add(card);
		}

		file.write(JSONdata.toJSONString());
	}
}
