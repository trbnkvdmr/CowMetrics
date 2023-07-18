package csvParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CsvParser {
	public void MergeCsv(String FOLDER_PATH, String OUTPUTCSV_PATH) throws FileNotFoundException{
		File FOLDER = new File(FOLDER_PATH);
		File OUTPUTCSV = new File(OUTPUTCSV_PATH + "/allinone.csv");
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
	
	 public <T> void getDataforFFT(T OUTPUTCSV_PATH) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(OUTPUTCSV_PATH + "/allinone.csv"));
		try (CSVReader csvReader = new CSVReader(reader)) {
			String[] record;
			
			while((record = csvReader.readNext()) != null) {
				System.out.println(record[0]);
			}
		}		
	}
}
