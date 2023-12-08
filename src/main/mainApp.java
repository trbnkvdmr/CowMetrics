package main;

import java.io.IOException;

import main.gui.mainwindow;

/**
 * @see url <a href="https://github.com/trbnkvdmr/CowMetrics">CowMetrics - github</a>
 * @version 0.1
 * @author Dmitry_Sergeevich
 */
public class mainApp {
	public static void main(String[] args) throws IOException {
		mainwindow mainwindow = new mainwindow();
		mainwindow.createmainwindow();
	}
}