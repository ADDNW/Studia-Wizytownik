package manage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVExport extends Export {

	public void WriteData(BufferedWriter file, ArrayList<Card> data) throws IOException  {

		//Card Version informator
		file.write("Version:");
		file.write(String.valueOf(Card.getVersion()));
		file.newLine();

		String[] fields = Card.getDataFormat();
		if(fields.length == 0) return;

		for(int i = 0;i<data.size();i++){
			String[] card = data.get(i).getCard();
			file.write(card[0]);
			for(int j = 1;j<card.length;j++){
				file.write(",");
				file.write(card[j]);
			}
			if(i<data.size()-1) file.newLine();			
		}
	}
}
