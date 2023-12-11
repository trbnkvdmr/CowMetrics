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
	ArrayList<Integer> Vector;

	@Test
	public void getVector() {
		GetVector getVector = new GetVector();
		
		List<String[]> Records = new LinkedList<>();
		String[] String = {"null","100","100","100"};
		Records.add(String);
		
		try {
			Vector = getVector.getVector(Records);
		} catch (IOException e) {
			System.out.println(e.getMessage());;
		}
		
		String Actual = Vector.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
		String Expected = "732";
		assertEquals(Expected, Actual);
	}
}
