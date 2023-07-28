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
	ArrayList<Integer> Average_Energy_X = new ArrayList<Integer>();
	int sum = 0;
	double sum_AB = 0;
	int dot = 0;
	double dot_AB = 0;
	
	/**
	 * @param ints Массив векторов
	 * @return Массив значений энергий за выборку
	 */
	public ArrayList<Integer> calcEnergy(int[] ints){
		ArrayList<Integer> Energy = new ArrayList<Integer>();
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
			System.out.println("\n" + ae + " - The array of vectors is not a multiple of the sample, data loss is possible");
		}
		System.out.printf("\nEnergy: %s",Energy);
		return Energy;
	}
	
	public ArrayList<Double> calcEnergy_AB(int[] ints){
		ArrayList<Integer> Energy = calcEnergy(ints);
		int[] energy_ints = (Energy).stream().mapToInt(i->i).toArray();
		double[] energy_ints_doubles = Arrays.stream(energy_ints).asDoubleStream().toArray();
		ArrayList<Double> Energy_AB = new ArrayList<Double>();
		double X = 0;
		double A = 0.91;
		double B = 0.09;
		
		for (int i = 0; i <= energy_ints_doubles.length-1; i++) {
			X = (A * X) + (B * energy_ints_doubles[i]);
			Energy_AB.add(X);
		}
		
		return Energy_AB;
	}
	
	public ArrayList<Double> calcEnergy_AB_anyH(int[] ints){
		ArrayList<Integer> Energy = calcEnergy(ints);
		int[] energy_ints = (Energy).stream().mapToInt(i->i).toArray();
		double[] energy_ints_doubles = Arrays.stream(energy_ints).asDoubleStream().toArray();
		ArrayList<Double> Energy_AB = new ArrayList<Double>();
		double X = 0;
		double A = 0.995;
		double B = 1 - A;
		
		for (int i = 0; i <= energy_ints_doubles.length-1; i++) {
			X = (A * X) + (B * energy_ints_doubles[i]);
			Energy_AB.add(X);
		}
		
		return Energy_AB;
	}

	// Array for Plot Energy
	public double[] getEnergyArrayForLinePlot(int[] ints){
		ArrayList<Integer> Energy = calcEnergy(ints);
		
		int[] energy_ints = (Energy).stream().mapToInt(i->i).toArray();
		double[] energy_ints_doubles = Arrays.stream(energy_ints).asDoubleStream().toArray();
		return energy_ints_doubles;
	}
	
	public double[] getEnergyArrayForLinePlot_AB(int[] ints){
		ArrayList<Double> Energy_AB = calcEnergy_AB(ints);
		
		double[] energy_double = (Energy_AB).stream().mapToDouble(d->d).toArray();
		return energy_double;
	}
	
	public double[] getEnergyArrayForLinePlot_AB_anyH(int[] ints){
		ArrayList<Double> Energy_AB_anyH = calcEnergy_AB_anyH(ints);
		
		double[] energy_double = (Energy_AB_anyH).stream().mapToDouble(d->d).toArray();
		return energy_double;
	}
	
	/**
	 * @param ints Массив енергии из векторов
	 * @param time Период времени в часах
	 * @return Массив средних значений энергии за период времени
	 */
	public ArrayList<Integer> calcAverageEnergyOverTime(int[] ints, int time){
		Average_Energy_X.clear();
		ArrayList<Integer> Energy = calcEnergy(ints);
		ArrayList<Integer> Average_Energy_Y = new ArrayList<Integer>();
		
		int[] energy_array_int = (Energy).stream().mapToInt(i->i).toArray();
				
		int Sample_of_average_energy = (time * 60*60*4)/(SETTINGS.Sample_of_Energy);
		
		try {
			for (int i = 0; i<=energy_array_int.length;i+= 1 ) {
				sum = 0;
				dot = (i + Sample_of_average_energy);
				Average_Energy_X.add(dot);
				for(int j = i; j<=i+Sample_of_average_energy; j++) {
					sum += energy_array_int[j];
				}
				Average_Energy_Y.add((sum/Sample_of_average_energy));		
			}	
		}catch(ArrayIndexOutOfBoundsException ae) {
			Average_Energy_Y.add((sum/Sample_of_average_energy));	
			System.out.println("\n"+ae + " - The array of Average Energy is not a multiple of the sample, data loss is possible");
		}
		System.out.printf("\nAverage Energy of time Y: %s", Average_Energy_Y);
		System.out.printf("\nAverage Energy of time X: %s", Average_Energy_X);
		return Average_Energy_Y;
	}
		
	// Array[][] for Plot Average Energy
	public double[][] getAverageEnergyArrayForLinePlot(int[] ints,int time){
		ArrayList<Integer> energy_average = calcAverageEnergyOverTime(ints,time);
		int[] energy_average_ints = (energy_average).stream().mapToInt(i->i).toArray();
		double[] energy_average_ints_doubles = Arrays.stream(energy_average_ints).asDoubleStream().toArray(); //Y
		
		ArrayList<Integer> X = Average_Energy_X;
		int[] X_ints = (X).stream().mapToInt(i->i).toArray();
		double[] X_ints_doubles = Arrays.stream(X_ints).asDoubleStream().toArray(); //X
		
		double[][] YX_Average_energy = new double[2][1];
		YX_Average_energy[0] = energy_average_ints_doubles;
		YX_Average_energy[1] = X_ints_doubles;
		
		return YX_Average_energy;
	}
		
    public double[][] turnToRight(double[][] array) {
    	double[][] resultArray = new double[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
               resultArray[j][array.length - i - 1] = array[i][j];
            }
        }
        return resultArray;
    }
	
	public void getEnergyAllPlot(int[] ints, int time, int time2) {
		double[] Energy = getEnergyArrayForLinePlot(ints);
		double[][] AverageEnergy_96h = turnToRight(getAverageEnergyArrayForLinePlot(ints,time));
		double[][] AverageEnergy_4h =  turnToRight(getAverageEnergyArrayForLinePlot(ints,time2));
		//double[][] AverageEnergy_1h =  turnToRight(getAverageEnergyArrayForLinePlot(ints,time3)); 1h
		
		double[] Energy_AB = getEnergyArrayForLinePlot_AB(ints);
		double[] Energy_AB_anyH = getEnergyArrayForLinePlot_AB_anyH(ints);
				
		Plot2DPanel plot = new Plot2DPanel();
		plot.plotToolBar.setBackground(Color.WHITE);
		plot.setBackground(Color.WHITE);
		plot.addLegend("SOUTH");
		plot.addLinePlot("Energy", new Color(255, 0, 0), Energy);
		plot.addLinePlot("Energy_AB", new Color(0, 255, 0), Energy_AB);
		plot.addLinePlot("Energy_AB_anyH", new Color(210,105,30), Energy_AB_anyH);
		
		plot.addLinePlot("Average Energy 96h", new Color(30, 144, 255), AverageEnergy_96h);
		plot.addLinePlot("Average Energy 4h",  new Color(255, 0, 255), AverageEnergy_4h);
		//plot.addLinePlot("Average Energy 1h",  new Color(0, 0, 255), AverageEnergy_1h); 1h
		
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

