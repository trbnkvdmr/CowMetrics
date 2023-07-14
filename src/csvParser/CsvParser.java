package csvParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class CsvParser {
	public void MergeCsv(String FOLDER_PATH, String OUTPUTCSV_PATH) throws FileNotFoundException{
		File FOLDER = new File(FOLDER_PATH);
		File OUTPUTCSV = new File(OUTPUTCSV_PATH);
		List<String[]> lines = new ArrayList<String[]>();
		
		for (File file: FOLDER.listFiles()){
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] nextline = scanner.nextLine().split(",");
				lines.add(nextline);
			}		
			scanner.close();
		}
		
		try {
			FileWriter outputfile = new FileWriter(OUTPUTCSV);
			try (CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END)) {
				writer.writeAll(lines);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
