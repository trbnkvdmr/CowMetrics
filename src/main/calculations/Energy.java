package main.calculations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import main.settings.Settings;

/**
 * Энергия считается как сумма 3600 векторов.
 * Складываются значения только выше порогового значения 100 - фильтрация.
 */
public class Energy {
	int sum = 0;
	/**
	 * 
	 * @param ints Массив векторов
	 * @return Массив значений энергий за выборку
	 */
	public ArrayList<Integer> calcEnergy(int[] ints){
		Settings settings = new Settings();
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
			Energy.add(sum);
			System.out.println(ae + " - The array of vectors is not a multiple of the sample, data loss is possible");
		}
		settings.setEnergy_Array(Energy);
		System.out.printf("\nEnergy: %s",settings.Energy_array);
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
				Energy_over_time.add((sum/Sample_of_average_energy));		
			}	
		}catch(ArrayIndexOutOfBoundsException ae) {
			Energy_over_time.add(sum/Sample_of_average_energy);	
			System.out.println(ae + " - The array of Average Energy is not a multiple of the sample, data loss is possible");
		}
		settings.setAverageEnergyArray(Energy_over_time);
		System.out.printf("\nAverage Energy of time: %s", settings.Average_Energy_Array);
		return Energy_over_time;
	}
	
	public void get_Energy(int[] ints, int time) {
		Settings settings = new Settings();
		Energy energy = new Energy();
		
		ArrayList<Integer> energy_s1 = energy.calcEnergy(ints);
		ArrayList<Integer> energy_average_s2 = energy.calcAverageEnergyOverTime(ints,time);
		
		int[] energy_s1_ints = (energy_s1).stream().mapToInt(i->i).toArray();
		double[] energy_s1_doubles = Arrays.stream(energy_s1_ints).asDoubleStream().toArray();
		
		int[] energy_average_s2_ints = (energy_average_s2).stream().mapToInt(i->i).toArray();
		double[] energy_average_s2_ints_doubles = Arrays.stream(energy_average_s2_ints).asDoubleStream().toArray();
		
		Plot2DPanel plot = new Plot2DPanel();
		plot.plotToolBar.setBackground(Color.WHITE);
		
		double[][] XY_average_energy = new double[2][2];
		
		ArrayList<Integer> energy_array = energy.calcEnergy(ints);
		
		int[] energy_array_int = (energy_array).stream().mapToInt(i->i).toArray();
				
		int Sample_of_average_energy = (time * 60*60*4)/(settings.Sample_of_Energy);
		int k = 0;
		
		//////////////////////////////////////////////////////////////////////// усреднение по 24 часа - 2 точки частный случай
		// to do рефакторим в универсальную функцию 
		
		try {
			for (int i = 0; i<=energy_array_int.length;i+= Sample_of_average_energy) {
				k=0;
				for(int j = i; j<=i+Sample_of_average_energy; j++) {
					k++;
					if (i == 0){
						
						XY_average_energy[0][0] = (Sample_of_average_energy/2);
					}
				}
				XY_average_energy[1][0] = k+((energy_array_int.length - k)/2)-1;
			}
		}catch(ArrayIndexOutOfBoundsException ae) {
		}

		//XY_average_energy[0][0] = 0.0;
		XY_average_energy[0][1] = energy_average_s2_ints_doubles[0];
		//XY_average_energy[1][0] = energy_s1_doubles.length-1;
		XY_average_energy[1][1] = energy_average_s2_ints_doubles[1];
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		plot.addLinePlot("Energy", new Color(255,68,68), energy_s1_doubles);
		plot.addLinePlot("Average Energy", new Color(51,181,229), XY_average_energy);
		
		plot.addLegend("SOUTH");
		
        Settings SETTINGS = new Settings();
        ImageIcon icon = new ImageIcon(SETTINGS.MAINWINDOW_ICO);
        JFrame frame = new JFrame("Energy");
        frame.setSize(600, 600);
        frame.setContentPane(plot);       
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);
	}
}
