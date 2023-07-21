package main.calculations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetVector {

	 public ArrayList<Integer> getVector(List<String[]> records) throws IOException{
		 
		 ArrayList<Integer> records_int_vector = new ArrayList<Integer>();
		 for (String[] record : records) {
			 int x = Integer.parseInt(record[1]);
			 int y = Integer.parseInt(record[2]);
			 int z = Integer.parseInt(record[3]);
			 int vect = ((int) Math.sqrt(x*10*x*10+y*10*y*10+z*10*z*10))-1000;
			 records_int_vector.add(vect);
		 }
		return (records_int_vector);
	}	
}
