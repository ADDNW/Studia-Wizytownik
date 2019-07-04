package manage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public abstract class Export {
	public void ExportData(String path, ArrayList<Card> data) throws IOException  {
	
		BufferedWriter file = new BufferedWriter(new FileWriter(new File(path)));
		WriteData(file, data);
		file.close();
	}
	
	public abstract void WriteData(BufferedWriter file, ArrayList<Card> data) throws IOException;
}
