package Gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Settings.Settings;
import csvParser.CsvParser;

public class mainwindow  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static String Folder = null;

	public void createmainwindow() throws IOException{
		JFrame window = new JFrame("CowMetricsAnalyzer");
		ImageIcon icon = new ImageIcon("E:\\Work\\CowMetrics\\src\\Source\\logo.png");
		createUI(window);
		window.setIconImage(icon.getImage());	
		window.setSize(500,350);
		window.setTitle("Cow metrics analyzer");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
	}
	
	public void createUI(JFrame window) throws IOException {
		JLabel label = new JLabel ("Folder :");
		JTextField textField = new JTextField("",20);
		JButton button1 = new JButton("Open");
		JButton button2 = new JButton("Go ahead");
		button1.setBackground(new Color(83,130,52));
		button1.setForeground(Color.WHITE);
		button2.setBackground(new Color(83,130,52));
		button2.setForeground(Color.WHITE);
		
		BufferedImage myPicture = ImageIO.read(new File("E:\\Work\\CowMetrics\\src\\Source\\logo1+.jpg"));
		Image image = myPicture.getScaledInstance(480, 270, Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(image));
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true); 
        layout.setAutoCreateContainerGaps(true); 
        
        ///////////////////////////////////////////////////////////
		
        layout.setVerticalGroup(layout.createSequentialGroup()
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    		.addComponent(picLabel))
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	            .addGroup(layout.createSequentialGroup()
        	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	                    		.addComponent(label)
        	                    		.addComponent(textField)
        	                    		.addComponent(button1)
        	                    		.addComponent(button2)))));
        
        ///////////////////////////////////////////////////////////
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JFileChooser fileChooser = new JFileChooser();
               fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               int option = fileChooser.showOpenDialog(window);
               if(option == JFileChooser.APPROVE_OPTION){
                  File file = fileChooser.getSelectedFile();
                  textField.setText(file.getAbsolutePath());
                  Folder = file.getAbsolutePath();
               }
            }
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Settings settings = new Settings();
            	CsvParser csvparse = new CsvParser();
            	settings.setFolder(Folder);          	            	
            	try {
					csvparse.MergeCsv(settings.FOLDER_PATH,settings.OUTPUTCSV_PATH);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
        
        pack();
        window.getContentPane().add(panel);
	}
}
