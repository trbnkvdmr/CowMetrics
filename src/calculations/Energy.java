package calculations;

import java.util.ArrayList;
import java.util.List;

/**
 * Энергия считается как сумма 3600 векторов.
 * Складываются значения только выше порогового значения - фильтрация.
 * 
 * 
 */


public class Energy {
	int sum;
	
	public ArrayList<Integer> getEnergy(int[] ints){
		ArrayList<Integer> Energy = new ArrayList<Integer>();

		for(int i = 0;i<=ints.length-3601;i =+ 3600) {
			sum = 0;
			for(int j = i; j<i+3600-1;j++) {
				if (Math.abs(ints[j]) > 100) {
					sum = sum + Math.abs(ints[i]);
			}
				Energy.add(sum);
			}
		}
		System.out.println(Energy);
		return Energy;
	}
	
	
	
}
