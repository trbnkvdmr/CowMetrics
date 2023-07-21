package main.calculations;

import java.util.ArrayList;

import main.settings.Settings;

/**
 * Энергия считается как сумма 3600 векторов.
 * Складываются значения только выше порогового значения 100 - фильтрация.
 */
public class Energy {
	Settings settings = new Settings();
	int sum = 0;
	/**
	 * 
	 * @param ints Массив векторов
	 * @return Массив значений энергий за выборку
	 */
	public ArrayList<Integer> calcEnergy(int[] ints){
		ArrayList<Integer> Energy = new ArrayList<Integer>();

		try {
			for(int i = 0;i<=ints.length;i += settings.Sample_of_Energy) {
				sum = 0;
				for(int j = i; j<=i+settings.Sample_of_Energy;j++) {
					if (Math.abs(ints[j]) > settings.Energy_filtration_threshold_value) {
						sum += Math.abs(ints[j]);
					}
				}
				Energy.add(sum);
			}	
		}catch(ArrayIndexOutOfBoundsException ae) {
			  System.out.println(ae + " - The array of vectors is not a multiple of the sample, data loss is possible");
		}
		System.out.printf("\nEnergy: %s",Energy);
		settings.setEnergy_Array(Energy);
		return Energy;
	}
	
	/**
	 * 
	 * @param ints Массив енергии из векторов
	 * @param time Период времени в часах
	 * @return Массив средних значений энергии за период времени
	 */
	public ArrayList<Integer> calcAverageEnergyOverTime(int[] ints, int time){
		Settings settings = new Settings();
		Energy energy = new Energy();
		ArrayList<Integer> Energy_over_time = new ArrayList<Integer>();
			
		ArrayList<Integer> energy_array = energy.calcEnergy(ints);
		
		int[] energy_array_int = (energy_array).stream().mapToInt(i->i).toArray();
		
		int Sample_of_average_energy = (time * 60*60*4)/(settings.Sample_of_Energy);
		
		try {
			for (int i = 0; i<=energy_array_int.length;i+= Sample_of_average_energy) {
				sum =0;
				for(int j = i; j<=i+Sample_of_average_energy; j++) {
					sum += energy_array_int[j];
				}
				Energy_over_time.add(sum/Sample_of_average_energy);		
			}
			
		}catch(ArrayIndexOutOfBoundsException ae) {
			  System.out.println(ae + " - The array of vectors is not a multiple of the sample, data loss is possible");
		}
		
		System.out.printf("\nAverage Energy of time: %s", Energy_over_time);
		return Energy_over_time;
	}
	
	/**
	 * @param ints Массив вектора
	 * @param time Период времени в часах для средней энергии за время
	 */
	public void getEnergy(int[] ints){
		Energy energy = new Energy();
		energy.calcEnergy(ints);
	}
	
	public void AverageEnergyOverTime(int[] ints, int time) {
		Energy energy = new Energy();
		energy.calcAverageEnergyOverTime(ints, time);
	}
}
