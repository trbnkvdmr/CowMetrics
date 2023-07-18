package Settings;

public class Settings {
	public String FOLDER_PATH = "";
	public String OUTPUTCSV_PATH = "";
	public final String CSVOUTFILENAME = "/allinone.csv";
	
	
	public final String APPNAME = "Cow metrics analyzer";
	public final String MAINWINDOW_ICO = "./src/Source/logo.png";
	public final String MAINWINDOW_IMAGE = "./src/Source/logo1+.jpg";
	public final String LABLEFORTEXTFIELDOFNMAINWINDOW = "Folder :";
	public final String TEXTFIEDLINMAINWINDOW = "";
	public final String BUTTON1 = "Open";
	public final String BUTTON2 = "Go ahead";
	public final String BUTTON3 = "File";
	
	public void setFolder(String FOLDER_PATH){
		this.FOLDER_PATH = FOLDER_PATH;
	}
	public void setOUTPUTCSV(String OUTPUTCSV_PATH){
		this.OUTPUTCSV_PATH = OUTPUTCSV_PATH;
	}
}
