package window;

import manage.Card;
import manage.CardManager;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import exeptions.*;

public class DataTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames; 
	private ArrayList<Card> data;

	public DataTableModel() throws BadCardFormatExeption{
		//CardManager.fillData();
		data = CardManager.getData();

		columnNames = Card.getDataFormat();
		
		//TEST CARD
		Card testCard = new Card();
			if(!(testCard.getCard().length == columnNames.length)){
				BadCardFormatExeption e = new BadCardFormatExeption();
				throw e;
			}
	}

	public void updateData(){
		data = CardManager.getData();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public String getValueAt(int row, int col) {
		return data.get(row).getCard()[col];
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c) {
		return getValueAt(0,c).getClass();
	}

	
	
}
