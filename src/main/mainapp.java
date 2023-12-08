package main;

import java.io.IOException;

import main.gui.mainwindow;

/**
 * @see url <a href="https://github.com/trbnkvdmr/CowMetrics">CowMetrics - github</a>
 * @author Dmitry_Sergeevich
 * @version 0.1
 */
public class mainapp {
	public static void main(String[] args) throws IOException {
		mainwindow mainwindow = new mainwindow();
		mainwindow.createmainwindow();
	}
}