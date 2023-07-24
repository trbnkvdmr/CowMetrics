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
	Settings SETTINGS = new Settings();
	
	ArrayList<Integer> Energy = new ArrayList<Integer>();
	ArrayList<Integer> Energy_array = new ArrayList<Integer>();
	ArrayList<Integer> Average_Energy_X = new ArrayList<Integer>();
	ArrayList<Integer> Average_Energy_Array = new ArrayList<Integer>();
	int sum = 0;
	int dot = 0;
	
	/**
	 * 
	 * @param ints Массив векторов
	 * @return Массив значений энергий за выборку
	 */
	public ArrayList<Integer> calcEnergy(int[] ints){

		try {
			for(int i = 0;i<=ints.length;i += SETTINGS.Sample_of_Energy) {
				sum = 0;
				for(int j = i; j<=i+SETTINGS.Sample_of_Energy;j++) {
					if (Math.abs(ints[j]) > SETTINGS.Energy_filtration_threshold_value) {
						sum += Math.abs(ints[j]);
					}
				}
				Energy.add(sum);
			}	
		}catch(ArrayIndexOutOfBoundsException ae) {
			Energy.add(sum);
			System.out.println(ae + " - The array of vectors is not a multiple of the sample, data loss is possible");
		}
		SETTINGS.setEnergy_Array(Energy);
		System.out.printf("\nEnergy: %s",SETTINGS.Energy_array);
		return Energy;
	}
	
	/**
	 * 
	 * @param ints Массив енергии из векторов
	 * @param time Период времени в часах
	 * @return Массив средних значений энергии за период времени
	 */
	public ArrayList<Integer> calcAverageEnergyOverTime(int[] ints, int time){
			
		Energy_array = calcEnergy(ints);
		
		int[] energy_array_int = (Energy_array).stream().mapToInt(i->i).toArray();
				
		int Sample_of_average_energy = (time * 60*60*4)/(SETTINGS.Sample_of_Energy);
		
		try {
			for (int i = 0; i<=energy_array_int.length;i+= Sample_of_average_energy) {
				sum = 0;
				dot = (i + Sample_of_average_energy/2);
				Average_Energy_X.add(dot);
				for(int j = i; j<=i+Sample_of_average_energy; j++) {
					sum += energy_array_int[j];
				}
				Average_Energy_Array.add((sum/Sample_of_average_energy));		
			}	
		}catch(ArrayIndexOutOfBoundsException ae) {
			Average_Energy_Array.add(sum/Sample_of_average_energy);
			SETTINGS.setAverageEnergyArray(Average_Energy_Array);
			System.out.println(ae + " - The array of Average Energy is not a multiple of the sample, data loss is possible");
		}
		SETTINGS.setAverageEnergyArray(Average_Energy_Array);
		SETTINGS.setAverage_Energy_X(Average_Energy_X);
		SETTINGS.setAverage_Energy_X2(Average_Energy_X);
		System.out.printf("\nAverage Energy of time: %s", SETTINGS.Average_Energy_Array);
		return Average_Energy_Array;
	}
	
	public void get_Energy(int[] ints, int time, int time2) {
 		ArrayList<Integer> energy_g1 = calcEnergy(ints);
		ArrayList<Integer> energy_average_g1 = calcAverageEnergyOverTime(ints,time);
		
		int[] energy_g1_ints = (energy_g1).stream().mapToInt(i->i).toArray();
		double[] energy_g1_ints_doubles = Arrays.stream(energy_g1_ints).asDoubleStream().toArray();
		
		int[] energy_average_g1_ints = (energy_average_g1).stream().mapToInt(i->i).toArray();
		double[] energy_average_g1_ints_doubles = Arrays.stream(energy_average_g1_ints).asDoubleStream().toArray(); //Y
		
		ArrayList<Integer> X = SETTINGS.getAverage_Energy_X();
		
		int[] X_ints = (X).stream().mapToInt(i->i).toArray();
		double[] X_ints_doubles = Arrays.stream(X_ints).asDoubleStream().toArray(); //X
		
		//g2
		Average_Energy_Array.clear();
		ArrayList<Integer> energy_average_g2 = calcAverageEnergyOverTime(ints,time2);
		int[] energy_average_g2_ints = (energy_average_g2).stream().mapToInt(i->i).toArray();
		double[] energy_average_g2_ints_doubles = Arrays.stream(energy_average_g2_ints).asDoubleStream().toArray(); //Y
		
		ArrayList<Integer> X2 = SETTINGS.getAverage_Energy_X();
		
		int[] X2_ints = (X2).stream().mapToInt(i->i).toArray();
		double[] X2_ints_doubles = Arrays.stream(X2_ints).asDoubleStream().toArray(); //X		

				
		Plot2DPanel plot = new Plot2DPanel();
		plot.plotToolBar.setBackground(Color.WHITE);
		plot.addLinePlot("Energy", new Color(255,68,68), energy_g1_ints_doubles);
		plot.addLinePlot("Average Energy", new Color(51,181,229), X_ints_doubles, energy_average_g1_ints_doubles);
		plot.addLinePlot("Average Ener2gy", new Color(170,102,204), X2_ints_doubles, energy_average_g2_ints_doubles);
		
		plot.addLegend("SOUTH");
		
        
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

