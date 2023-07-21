package main.csv;

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

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import main.settings.Settings;

public class CsvParser {
	
	public void MergeCsv(String FOLDER_PATH, String OUTPUTCSV_PATH) throws FileNotFoundException{
		Settings SETTINGS = new Settings();
		File FOLDER = new File(FOLDER_PATH);
		File OUTPUTCSV = new File(OUTPUTCSV_PATH + SETTINGS.CSVOUTFILENAME);
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
	
	 public List<String[]> ReadCSV(String OUTPUTCSV_PATH) throws IOException{
		 Settings SETTINGS = new Settings();
		 Reader reader = Files.newBufferedReader(Paths.get(OUTPUTCSV_PATH + SETTINGS.CSVOUTFILENAME));
		 
		 CSVParser parser = new CSVParserBuilder()
			        .withSeparator(';')
			        .build();
		 CSVReader csvReader = new CSVReaderBuilder(reader)
			        .withCSVParser(parser)
			        .build();
		 List<String[]> records = csvReader.readAll();

		return (records);
	}		
}

