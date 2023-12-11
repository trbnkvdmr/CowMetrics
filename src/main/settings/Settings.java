package main.settings;

import java.util.ArrayList;

/**
 * Настройки и константы
 * 
 * @author Dmitry_Sergeevich
 */
public class Settings {
	public String FolderPath = "";
	public String OutputCsvPath = "";
	public final String CSVOUTFILENAME = "/allinone.csv";
	public final String APPNAME = "Cow metrics analyzer";
	public final String MAINWINDOW_ICO =   "logo.png";
	public final String MAINWINDOW_IMAGE = "logo1+.jpg";
	public final String LABLEFORTEXTFIELDOFNMAINWINDOW = "Folder :";
	public final String TEXTFIEDLINMAINWINDOW = "";
	public final String BUTTON1 = "Open";
	public final String BUTTON2 = "Go ahead";
	public final String BUTTON3 = "Vector";
	public final String BUTTON4 = "Energy";
	
	public ArrayList<Integer> VectorArray;
	public ArrayList<String> DayArray;
	
	public int SampleOfEnergy = 3600; 						// @default 3600
	public int EnergyFiltrationThresholdValue = 100;			// @default 100
	
	public float HuntingSearchTrashold = (float) 1.2;

	public void setFolder(String FolderPath){
		this.FolderPath = FolderPath;
	}
	public void setOutputCSV(String OutputCsvPath){
		this.OutputCsvPath = OutputCsvPath;
	}
	public void setVectorArray (ArrayList<Integer> array) {
		this.VectorArray = array;
	}
	public void setDayArray(ArrayList<String> array) {
		this.DayArray = array;
	}
}
