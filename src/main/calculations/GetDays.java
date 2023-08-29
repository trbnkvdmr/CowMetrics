package main.calculations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetDays {
	
	public ArrayList<String> getDays(List<String[]> records) throws IOException {
		
		ArrayList<String> records_days = new ArrayList<String>();
		
		for (String[] record : records) {
			String day = record[0].substring(8,10);
			records_days.add(day);
		}
		return records_days;
	}
}