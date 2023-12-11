package main.calculations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetDays {
	/**
	 * @param records прочитаный файл таблицы
	 * @return Парсим дни
	 * @throws IOException
	 */
	public ArrayList<String> getDays(List<String[]> records) throws IOException {
		ArrayList<String> RecordsDays = new ArrayList<String>();
		
		for (String[] record : records) {
			String day = record[0].substring(8,10);
			RecordsDays.add(day);
		}
		return RecordsDays;
	}
}