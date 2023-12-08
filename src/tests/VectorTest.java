package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import main.calculations.GetVector;

/**
 * Тестовый тест математики вектора
 * 
 * @author Dmitry_Sergeevich
 */
public class VectorTest {
	ArrayList<Integer> vector;

	@Test
	public void getVector() {
		GetVector getvector = new GetVector();
		
		List<String[]> records = new LinkedList<>();
		String[] string = {"null","100","100","100"};
		records.add(string);
		
		try {
			vector = getvector.getVector(records);
		} catch (IOException e) {
			System.out.println(e.getMessage());;
		}
		
		String actual = vector.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
		String expected = "732";
		assertEquals(expected, actual);
	}
}
