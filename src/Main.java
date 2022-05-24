import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* Alex Gardner */

public class Main {
	// instances of objects
	public static Analysist analyst;
	private static StreamFrame sf;

	// relative locations of data
	public static final String STREAM_PATH = "data/AllTheDataCorrected.txt";
	public static final String PDF_PATH = "data/2012NutrientMonitoringReport_Final.pdf";
	public static final String LOGO_PATH = "data/logo.png";
	
	public static void main(String[] args) {
		// extract data
		try {
			DataStatistics.getData();
		} catch (FileNotFoundException e) {
			System.out.println("Data not found.");
			e.printStackTrace();
		}
		
		// instantiate analyst from data
		analyst = new Analysist(DataStatistics.getDataArrayList());
		
		sf = new StreamFrame();
		
		// create the StreamFrame
		sf.setTitle("Stream Nitrite and Nitrate Data");
		sf.setVisible(true);
		sf.setBounds(0, 0, 1000, 1000);
		sf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		sf.setResizable(false);
		
		// create the line graph that can be opened later
		analyst.constructLineGraph(DataStatistics.nitriteIndex, DataStatistics.nitrateIndex);
		analyst.formImage(DataStatistics.nitriteIndex, DataStatistics.nitrateIndex, 300, 200, (Graphics2D) sf.getGraphics());
	}
}
