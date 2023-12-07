package main.settings;

import java.util.ArrayList;

public class Settings {
	public String Folder_Path = "";
	public String Outputcsv_Path = "";
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
	
	public ArrayList<Integer> Vector_array;
	public ArrayList<String> Day_array;
	
	public int Sample_of_Energy = 3600; 						// @default 3600
	public int Energy_filtration_threshold_value = 100;			// @default 100
	//public int Sample_time_for_average_Energy = 24;			// в часах @default 24
	
	public float Hunting_search_trashold = (float) 1.2;
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
	public void setDay_Array(ArrayList<String> array) {
		this.Day_array = array;
	}
}
