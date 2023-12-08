package main.calculations;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;
import main.settings.Settings;

/**
 * Класс Энергии с реализованной математикой и возможностью построения графиков. 
 * <p>
 * calcEnergy - Посчитать массив значений энергии из выборки векторов.
 * <p>
 * calcEnergy_AB - Посчитать массив значений энергии из выборки векторов используя α-β фильтр.
 * <p>
 * calcAverageEnergyOverTime - Посчитать массив средних значений энергии за период времени.
 * <p>
 * getEnergyArrayForLinePlot - Посчитать значения для построения графиков.
 * <p>
 * getEnergyArrayForLinePlot_AB - Посчитать значения для построения графиков для α-β фильтра.
 * <p>
 * getAverageEnergyArrayForLinePlot - Посчитать значения для построения графиков для средних значений энергии за период времени.
 * <p>
 * getDaysOfEnergy - Получить значения дней.
 * <p>
 * huntingSearch - Поиск охоты.
 * <p>
 * getEnergyAllPlot - Построение графиков.
 * <p>
 * 
 * @author Дмитрий Трубников
 */

public class Energy {
	Settings SETTINGS = new Settings();
	ArrayList<Integer> Average_Energy_X = new ArrayList<Integer>();
	int sum = 0;
	double sum_AB = 0;
	int dot = 0;
	double dot_AB = 0;
	ArrayList<Integer> Days = new ArrayList<Integer>();
	int Sample_of_average_energy;
	double[] energy_average_ints_doubles;

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
	
	/**
	 * @param ints Массив векторов
	 * @return Массив значений энергий за выборку α-β фильтр.
	 */
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
	
	/**
	 * @param ints Массив векторов
	 * @return double[ ] массив векторов для построения графиков
	 */
	public double[] getEnergyArrayForLinePlot(int[] ints){
		ArrayList<Integer> Energy = calcEnergy(ints);
		
		int[] energy_ints = (Energy).stream().mapToInt(i->i).toArray();
		double[] energy_ints_doubles = Arrays.stream(energy_ints).asDoubleStream().toArray();
		return energy_ints_doubles;
	}

	/**
	 * @param ints Массив векторов
	 * @return double[ ] массив векторов для построения графиков α-β фильтр.
	 */
	public double[] getEnergyArrayForLinePlot_AB(int[] ints){
		ArrayList<Double> Energy_AB = calcEnergy_AB(ints);
		
		double[] energy_double = (Energy_AB).stream().mapToDouble(d->d).toArray();
		return energy_double;
	}
	
	/**
	 * @param ints Массив энергии из векторов
	 * @param time Период времени в часах
	 * @return Массив средних значений энергии за период времени
	 */
	public ArrayList<Integer> calcAverageEnergyOverTime(int[] ints, int time){
		Average_Energy_X.clear();
		ArrayList<Integer> Energy = calcEnergy(ints);
		ArrayList<Integer> Average_Energy_Y = new ArrayList<Integer>();
		
		int[] energy_array_int = (Energy).stream().mapToInt(i->i).toArray();
				
		Sample_of_average_energy = (time * 60*60*4)/(SETTINGS.Sample_of_Energy);
		
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
		
	/**
	 * @param ints Массив энергии из векторов
	 * @param time Период времени в часах
	 * @return double[ ][ ] массив средних значений энергии за период времени
	 */
	public double[][] getAverageEnergyArrayForLinePlot(int[] ints,int time){
		ArrayList<Integer> energy_average = calcAverageEnergyOverTime(ints,time);
		int[] energy_average_ints = (energy_average).stream().mapToInt(i->i).toArray();
		energy_average_ints_doubles = Arrays.stream(energy_average_ints).asDoubleStream().toArray(); //Y
		
		ArrayList<Integer> X = Average_Energy_X;
		int[] X_ints = (X).stream().mapToInt(i->i).toArray();
		double[] X_ints_doubles = Arrays.stream(X_ints).asDoubleStream().toArray(); //X
				
		double[][] YX_Average_energy = new double[2][1];
		YX_Average_energy[0] = energy_average_ints_doubles;
		YX_Average_energy[1] = X_ints_doubles;
		
		return YX_Average_energy;
	}
	
	/**
	 * @param aX_days Массив дней
	 * @return double[ ] Массив дней
	 */
	public double[] getDaysOfEnergy(int[] aX_days){
		double[] aX_days_doubles = Arrays.stream(aX_days).asDoubleStream().toArray();
		return aX_days_doubles;
	}
	
	/**
	 * 
	 * @param array матрица
	 * @return Поворачиваем матрицу
	 */
    public double[][] turnToRight(double[][] array) {
    	double[][] resultArray = new double[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
               resultArray[j][array.length - i - 1] = array[i][j];
            }
        }
        return resultArray;
    }
    
    /**
     * @param Energy_AB_array массив энергии фильтра
     * @param AverageEnergy_3d_array массив энергии на 72 часа
     * 
     * Сравниваем значения ускорений соответсвующих друг-другу по шкале "времени", и если значение отношения более опредленного, 
     * запоминаем эти точки, по ним отмечаем маркерами на графике такие скачков активности.
     * 
     * @return double[ ][ ] Массив точек соответ. условиям выше.
     **/
    public double[][] huntingSearch(double[] Energy_AB_array, double[] AverageEnergy_3d_array){
    	/**
    	 * Находка - точки, крайние значения которых это рамки маркера скачка активности.
    	 **/
    	ArrayList<Double> new_3d_AverageEnergy_list = new ArrayList<Double>();
    	for(double d : AverageEnergy_3d_array) new_3d_AverageEnergy_list.add(d);
    	
    	ArrayList<Double> new_Energy_AB_array_list = new ArrayList<Double>();
    	for(double d : Energy_AB_array) new_Energy_AB_array_list.add(d);

    	ArrayList<Double> Marker_X = new ArrayList<Double>();
    	ArrayList<Double> Marker_Y = new ArrayList<Double>();
    	
    	int sizeUP = new_Energy_AB_array_list.size() - new_3d_AverageEnergy_list.size();
    	
    	int i = 0;
		for(i=0; i<= sizeUP - 1;i++) {
			
			new_3d_AverageEnergy_list.add(0, (double) 0);
			} 
		    
			int c = 0;
			for(int a = 0; a <= new_3d_AverageEnergy_list.size() - 1; a++) {
				if(((new_3d_AverageEnergy_list.get(a) != 0) && (new_3d_AverageEnergy_list.get(a) / new_Energy_AB_array_list.get(a) >= SETTINGS.Hunting_search_trashold))) {
					c++;
					if(c == 4) {
						
						/**
						 * 
						 **/
						
						double index = new_3d_AverageEnergy_list.indexOf((double)a);
						Marker_X.add(index);
						Marker_Y.add( (double) 50000);
						c = 0;
						
						/**
						 * TODO Надо поставить метки при выполнении всех условий по индексу (тип как по шкале времени)
						 **/
				}
			}
		}
		
		double[] Marker_X_double = (Marker_X).stream().mapToDouble(d->d).toArray();
		double[] Marker_Y_double = (Marker_Y).stream().mapToDouble(d->d).toArray();
		
		double[][] YX_Box_Marker = new double[2][1];
		YX_Box_Marker[0] = Marker_X_double;
		YX_Box_Marker[1] = Marker_Y_double;

		return YX_Box_Marker;
    }

	/**
	 * Строим графики энергии фитра, средних значений за N часов, дней.
	 * @param ints Векторы
	 * @param time Выборка (часы)
	 * @param aX_days Дни
	 * @throws IOException
	 */	
	public void getEnergyAllPlot(int[] ints, int time, int[] aX_days) throws IOException {
		double[][] AverageEnergy_3d = turnToRight(getAverageEnergyArrayForLinePlot(ints,time));
		double[] Energy_AB = getEnergyArrayForLinePlot_AB(ints);
						
		Plot2DPanel plot = new Plot2DPanel();
		plot.plotToolBar.setBackground(Color.WHITE);
		plot.setBackground(Color.WHITE);
		plot.addLegend("SOUTH");
		plot.addLinePlot("Energy_AB", new Color(255, 0 , 0), Energy_AB);
		plot.addLinePlot("Average Energy 72h (3d)", new Color(0, 43, 255), AverageEnergy_3d);
		plot.addLinePlot("DAYS",  new Color(0, 0, 0), getDaysOfEnergy(aX_days));
		
		// TODO добавить в путь для сохранения локальный путь
				
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

