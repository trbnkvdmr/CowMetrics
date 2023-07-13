package MainApp;

import java.io.FileNotFoundException;

import Gui.mainwindow;
import Settings.Settings;
import csvParser.CsvParser;

public class mainApp {
	public static void main(String[] args) throws FileNotFoundException {
		
		mainwindow window = new mainwindow();
		window.Gui();
				
//		Settings settings = new Settings();
//		CsvParser csvparse = new CsvParser();
		/////////////////////////////////////
		
		//csvparse.MergeCsv(settings.FOLDER,settings.OUTPUTCSV);
	}
}