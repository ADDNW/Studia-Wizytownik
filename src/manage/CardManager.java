package manage;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import exeptions.BadDataFormatExeption;
import exeptions.BadFileVersionExeption;
import exeptions.InterpreterNotFoundExeption;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class CardManager {
    
    private static String currentFile = "";
    private static String fileType = "";
    private static ArrayList<Card> data = new ArrayList<Card>();


    public static String getCurrentFile(){
        return currentFile;
    }

    public static ArrayList<Card> getData(){
       return data;
    }

	public static String[] getCard(int row) {
		return data.get(row).getCard();
	}

	public static void setRow(String[] value, int row) throws BadDataFormatExeption, InterpreterNotFoundExeption, IOException {
		data.get(row).setCard(value);
		exportData();
    }
	
	public static void addRow(String[] value) throws BadDataFormatExeption, InterpreterNotFoundExeption, IOException {
		data.add(new Card(value));
		exportData();
	}
	
	public static void deleteRow(int row ) throws InterpreterNotFoundExeption, IOException {
		data.remove(row);
		exportData();
	}

    //Sort data, by column
    public static void setSorting(int column, boolean doIncreasing) {
		DataSort.StartSort(data, column, doIncreasing);
	}

    //Find first Card matching conditions
	public static int findData(String toFind, int column) {
		return DataFind.Find(data, column, toFind);
	}

    //Import data from file (and start auto exporting to this location)
	public static void importData(String path, String type) throws InterpreterNotFoundExeption, IOException, BadFileVersionExeption, BadDataFormatExeption, FileNotFoundException, ParseException {
		Import importer;
		switch(type){
			case "json":

				importer = new JSONImport();
                importer.ImportData(path, data);
                fileType = type;
                currentFile = path;
				break;
			case "csv":
				importer = new CSVImport();
				importer.ImportData(path, data);
				fileType = type;
				currentFile = path;
				break;
			default:
				throw new InterpreterNotFoundExeption();
				
				
		}
	}

    //Auto export, after data changed
    public static void exportData() throws InterpreterNotFoundExeption, IOException {
        if(currentFile.isEmpty() || fileType.isEmpty());
        else{
            exportData(currentFile, fileType);
        }
    }

    //Export to new file location
	public static void exportData(String path, String type) throws InterpreterNotFoundExeption, IOException {
		Export exporter;
		switch(type){
			
			case "json":
				exporter = new JSONExport();
                exporter.ExportData(path, data);
                fileType = type;
                currentFile = path;
				break;
			case "csv":
				exporter = new CSVExport();
				exporter.ExportData(path, data);
				fileType = type;
				currentFile = path;
				break;
			default:
				throw new InterpreterNotFoundExeption();
				
				
        }
	}
}