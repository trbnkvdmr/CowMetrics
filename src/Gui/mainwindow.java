package Gui;

import javax.swing.JFrame;

public class mainwindow  extends JFrame{
	JFrame frame = new JFrame("CowMetricsAnalyzer");
	
	public void Gui(){
		this.setSize(500, 500);
		this.setTitle("Cow metrics analyzer");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
