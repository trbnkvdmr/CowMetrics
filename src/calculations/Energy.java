package calculations;

import java.util.ArrayList;

import Settings.Settings;



/**
 * Энергия считается как сумма 3600 векторов.
 * Складываются значения только выше порогового значения 100 - фильтрация.
 */
public class Energy {
	Settings settings = new Settings();
	int sum = 0;
	
	public ArrayList<Integer> getEnergy(int[] ints){
		ArrayList<Integer> Energy = new ArrayList<Integer>();

		try {
			for(int i = 0;i<=ints.length;i += settings.Sample_of_Energy) {
				
				for(int j = i; j<=i+settings.Sample_of_Energy;j++) {
					if (Math.abs(ints[j]) > settings.Energy_filtration_threshold_value) {
						sum += + Math.abs(ints[j]);
					}
				}
				Energy.add(sum);
			}	
		}catch(ArrayIndexOutOfBoundsException ae) {
			  System.out.println(ae + " - Массив векторов не кратен выборке, возможны потери данных");
		}
		return Energy;
	}
}
