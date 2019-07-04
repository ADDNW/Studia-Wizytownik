
package manage;

import java.util.ArrayList;

public abstract class DataFind {
		
	public static int Find(ArrayList<Card> data, int column, String toFind) {
		for(int i = 0; i < data.size(); i ++) {
			if(data.get(i).getCard()[column].equalsIgnoreCase(toFind)) {
				return i;
			}
		}
		return -1;
	}
}
