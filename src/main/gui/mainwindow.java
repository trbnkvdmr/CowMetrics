package main.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.calculations.Energy;
import main.calculations.GetDays;
import main.calculations.GetVector;
import main.calculations.FFT_third_party.FFT;
import main.csv.CsvParser;
import main.settings.Settings;

/**
 * Рисуем интерфейс приложения
 * @author Дмитрий Трубников
 */
public class mainwindow extends JFrame{
	Settings SETTINGS = new Settings();	
	private static final long serialVersionUID = 1L;
	protected static String Folder = null;

	public void createmainwindow() throws IOException {

		JFrame window = new JFrame();
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(SETTINGS.MAINWINDOW_ICO));
		createUI(window);
		window.setIconImage(icon.getImage());
		window.setSize(500, 380);
		window.setTitle(SETTINGS.APPNAME);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
	}

	public void createUI(JFrame window) throws IOException {
		JLabel label = new JLabel(SETTINGS.LABLEFORTEXTFIELDOFNMAINWINDOW);
		JTextField textField = new JTextField(SETTINGS.TEXTFIEDLINMAINWINDOW, 24);
		JButton button1 = new JButton(SETTINGS.BUTTON1);
		JButton button2 = new JButton(SETTINGS.BUTTON2);
		JButton button3 = new JButton(SETTINGS.BUTTON3);
		JButton button4 = new JButton(SETTINGS.BUTTON4);
		
		button1.setBackground(new Color(83, 130, 52));
		button1.setForeground(Color.WHITE);
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.setContentAreaFilled(true);
		
		button2.setBackground(new Color(83, 130, 52));
		button2.setForeground(Color.WHITE);
		button2.setBorderPainted(false);
		button2.setFocusPainted(false);
		button2.setContentAreaFilled(true);
		
		button3.setBackground(new Color(83, 130, 52));
		button3.setForeground(Color.WHITE);
		button3.setBorderPainted(false);
		button3.setFocusPainted(false);
		button3.setContentAreaFilled(true);
		
		button4.setBackground(new Color(83, 130, 52));
		button4.setForeground(Color.WHITE);
		button4.setBorderPainted(false);
		button4.setFocusPainted(false);
		button4.setContentAreaFilled(true);
		
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(SETTINGS.MAINWINDOW_IMAGE));
		Image image = imageIcon.getImage();
		image = image.getScaledInstance(480, 270, Image.SCALE_SMOOTH);
		
		JLabel picLabel = new JLabel(new ImageIcon(image));
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);

		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);

		///////////////////////////////////////////////////////////

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(picLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label)
										.addComponent(textField)
										.addComponent(button1)
										.addComponent(button2)
										.addComponent(button3)
								        .addComponent(button4))))));
		
		///////////////////////////////////////////////////////////

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(window);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					textField.setText(file.getAbsolutePath());
					Folder = file.getAbsolutePath();
					SETTINGS.setFolder(Folder);
					SETTINGS.setOUTPUTCSV(Folder);
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CsvParser csvparse = new CsvParser();	
				try {
					csvparse.MergeCsv(SETTINGS.Folder_Path, SETTINGS.Outputcsv_Path);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CsvParser csvparse = new CsvParser();
				GetVector GetVector = new GetVector();
				GetDays GetDays = new GetDays();
																
				try {
					SETTINGS.setVector_Array(GetVector.getVector(csvparse.ReadCSV(SETTINGS.Outputcsv_Path)));
					SETTINGS.setDay_Array(GetDays.getDays((csvparse.ReadCSV(SETTINGS.Outputcsv_Path))));
			
					int[] ints = (SETTINGS.Vector_array).stream().mapToInt(i->i).toArray();
					double[] doubles = Arrays.stream(ints).asDoubleStream().toArray();
					
					FFT fft = new FFT();
					fft.get_FFT(doubles);					
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CsvParser csvparse = new CsvParser();
				GetVector GetVector = new GetVector();
				GetDays GetDays = new GetDays();
				Energy Energy = new Energy();	
								
				try {
					SETTINGS.setVector_Array(GetVector.getVector(csvparse.ReadCSV(SETTINGS.Outputcsv_Path)));
					SETTINGS.setDay_Array(GetDays.getDays((csvparse.ReadCSV(SETTINGS.Outputcsv_Path))));
										
					int[] ints = (SETTINGS.Vector_array).stream().mapToInt(i->i).toArray();
									
					ArrayList<String> aX_days = SETTINGS.Day_array;
					ArrayList<String> aX_days_new_for_energy = new ArrayList<String>();
					
			        for (int i = 0; i < aX_days.size(); i+=3600) {
			        	aX_days_new_for_energy.add((aX_days.get(i)+"000"));
			        }
			        
			        String[] aX_days_new_for_energy_array = aX_days_new_for_energy.toArray(new String[0]);
					int[] aX_days_int = Arrays.stream(aX_days_new_for_energy_array).mapToInt(Integer::parseInt).toArray();							

					Energy.getEnergyAllPlot(ints,36,aX_days_int);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		pack();
		window.getContentPane().add(panel);
	}
}
