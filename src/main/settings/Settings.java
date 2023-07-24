package main.settings;

import java.util.ArrayList;

public class Settings {
	public String Folder_Path = "";
	public String Outputcsv_Path = "";
	public final String CSVOUTFILENAME = "/allinone.csv";
	
	public final String APPNAME = "Cow metrics analyzer";
	public final String MAINWINDOW_ICO =   "src/main/resources/logo.png";
	public final String MAINWINDOW_IMAGE = "src/main/resources/logo1+.jpg";
	public final String LABLEFORTEXTFIELDOFNMAINWINDOW = "Folder :";
	public final String TEXTFIEDLINMAINWINDOW = "";
	public final String BUTTON1 = "Open";
	public final String BUTTON2 = "Go ahead";
	public final String BUTTON3 = "Vector";
	public final String BUTTON4 = "Energy";
	
	public ArrayList<Integer> Vector_array;
	public ArrayList<Integer> Energy_array;
	public ArrayList<Integer> Average_Energy_Array;
	public ArrayList<Integer> Average_Energy_X;
	public ArrayList<Integer> Average_Energy_X2;
	
	public int Sample_of_Energy = 3600; 						// @default 3600
	public int Energy_filtration_threshold_value = 100;			// @default 100
	//public int Sample_time_for_average_Energy = 24;			// в часах @default 24
	
	///////////////////////////////////////////////////////
	public void setFolder(String FOLDER_PATH){
		this.Folder_Path = FOLDER_PATH;
	}
	public void setOUTPUTCSV(String OUTPUTCSV_PATH){
		this.Outputcsv_Path = OUTPUTCSV_PATH;
	}
	public void setVector_Array (ArrayList<Integer> array) {
		this.Vector_array = array;
	}
	public void setEnergy_Array (ArrayList<Integer> array) {
		this.Energy_array = array;
	}
	public void setAverageEnergyArray(ArrayList<Integer> array) {
		this.Average_Energy_Array = array;
	}
	public void setAverage_Energy_X(ArrayList<Integer> array) {
		this.Average_Energy_X = array;
	}
	public ArrayList<Integer> getAverage_Energy_X(){
		return this.Average_Energy_X;
	}
	public void setAverage_Energy_X2(ArrayList<Integer> array) {
		this.Average_Energy_X2 = array;
	}
	public ArrayList<Integer> getAverage_Energy_X2(){
		return this.Average_Energy_X2;
	}
	
}
