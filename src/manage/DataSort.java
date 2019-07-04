
package manage;

import java.util.ArrayList;

public abstract class DataSort {
	private static int column;
	private static boolean doIncreasing;
	
	public static void StartSort(ArrayList<Card> data, int columnSort, boolean doIncreasingSort) {
		column = columnSort;
		doIncreasing = doIncreasingSort;
		Sort(data,0,data.size()-1);
	}

	private static void Sort(ArrayList<Card> table, int l, int r) {
		if(l < r) {
			int i = divideTable(table, l, r);
			Sort(table, l, i-1);
			Sort(table,i+1,r);
		}
	}

	private static int divideTable(ArrayList<Card> table, int l, int r) {
		//pivot
		int indexMiddle = (l + (r-1))/2; 
		String dataMiddle = table.get(indexMiddle).getCard()[column];
		changeData(table,indexMiddle,r);
		
		//division
		int current = l;
		for(int i = l; i <= r-1; i++) {
			if(doIncreasing) {
				if(((String)(table.get(i).getCard()[column])).compareToIgnoreCase(dataMiddle)<0) { 
					changeData(table, i, current);
					current++;
				}
			}
			else {
				if(((String)(table.get(i).getCard()[column])).compareToIgnoreCase(dataMiddle)>0) { 
					changeData(table, i, current);
					current++;
				}
			}
			
		}
		changeData(table, current, r);
		return current;
	}
	
	private static void changeData(ArrayList<Card> table, int index1, int index2) {
		Card temp = table.get(index1);
		table.set(index1, table.get(index2));
		table.set(index2, temp);
	}
}

		
		



