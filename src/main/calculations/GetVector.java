package main.calculations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetVector {
	/**
	 * @param records прочитаный файл таблицы
	 * @return Массив векторов
	 * @throws IOException
	 */
	 public ArrayList<Integer> getVector(List<String[]> records) throws IOException{
		 
		 ArrayList<Integer> RecordsIntVector = new ArrayList<Integer>();
		 for (String[] record : records) {
			 int x = Integer.parseInt(record[1]);
			 int y = Integer.parseInt(record[2]);
			 int z = Integer.parseInt(record[3]);
			 int vect = ((int) Math.sqrt(x*10*x*10+y*10*y*10+z*10*z*10))-1000;
			 RecordsIntVector.add(vect);
		 }
		return (RecordsIntVector);
	}	
}
