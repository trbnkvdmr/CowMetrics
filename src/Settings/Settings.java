package Settings;

import java.util.ArrayList;

public class Settings {
	public String Folder_Path = "";
	public String Outputcsv_Path = "";
	public final String CSVOUTFILENAME = "/allinone.csv";
	
	
	public final String APPNAME = "Cow metrics analyzer";
	public final String MAINWINDOW_ICO = "./src/Source/logo.png";
	public final String MAINWINDOW_IMAGE = "./src/Source/logo1+.jpg";
	public final String LABLEFORTEXTFIELDOFNMAINWINDOW = "Folder :";
	public final String TEXTFIEDLINMAINWINDOW = "";
	public final String BUTTON1 = "Open";
	public final String BUTTON2 = "Go ahead";
	public final String BUTTON3 = "Vector";
	public final String BUTTON4 = "Energy";
	
	public ArrayList<Integer> Vector_array;
	public ArrayList<Integer> Energy_array;
	
	public int Sample_of_Energy = 3600; 						// @default 3600
	public int Energy_filtration_threshold_value = 100;			// @default 100
	
	
	///////////////////////////////////////////////////////
	public void setFolder(String FOLDER_PATH){
		this.Folder_Path = FOLDER_PATH;
	}
	public void setOUTPUTCSV(String OUTPUTCSV_PATH){
		this.Outputcsv_Path = OUTPUTCSV_PATH;
	}
	
	public void setVector_Array (ArrayList<Integer> array) {
		Vector_array = array;
	}
	
	public void setEnergy_Array (ArrayList<Integer> array) {
		Energy_array = array;
	}
}
