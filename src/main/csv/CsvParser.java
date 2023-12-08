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
import com.opencsv.exceptions.CsvException;

import main.settings.Settings;

/**
 * Работа с .Csv кэшем
 * <ul>
 * <li> MergeCsv - Склеить .csv 
 * <li> ReadCSV - Чтение файлы .csv
 * </ul>
 * 
 * @author Дмитрий Трубников
 */
public class CsvParser {
	Settings SETTINGS = new Settings();	
	
	/**
	 * @param FOLDER_PATH Рабочая область - папка
	 * @param OUTPUTCSV_PATH Куда сохранить
	 * @throws FileNotFoundException
	 */
	public void MergeCsv(String FOLDER_PATH, String OUTPUTCSV_PATH) throws FileNotFoundException{

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
	
	/**
	 * @param PATH расположение файла
	 * @return Буфер данных с файла .csv
	 */
	 public List<String[]> ReadCSV(String PATH){
		Reader reader;
		List<String[]> records = null;
		
		// FIXME Добавить проверку и выбор только .csv файликов
		
		try {
			 reader = Files.newBufferedReader(Paths.get(PATH + SETTINGS.CSVOUTFILENAME));
			 CSVParser parser = new CSVParserBuilder()
				        .withSeparator(';')
				        .build();
			 CSVReader csvReader = new CSVReaderBuilder(reader)
				        .withCSVParser(parser)
				        .build();
			 try {
				records = csvReader.readAll();
			} catch (CsvException e) {
				e.printStackTrace();
			}
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;
	}		
}

