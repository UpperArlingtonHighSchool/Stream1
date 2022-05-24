/* Ty Fredrick (unless otherwise specified) */

import java.util.ArrayList;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import org.jfree.data.general.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.RendererState;
import org.jfree.chart.plot.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jfree.chart.axis.*;

/* Did you mean Analyst? ~Alex */
public class Analysist {
	private ArrayList<ArrayList<Object>> masterList = new ArrayList<ArrayList<Object>>(); // the data {list of locations, list of first values, list of second values, ...}
	private ScatterRenderer plot; // the dot plot
	private CategoryPlot cPlot; // the exterior chart for the dot plot
	DefaultMultiValueCategoryDataset current = new DefaultMultiValueCategoryDataset(); // the axes (Nitrite/Nitrate)
	private JFreeChart JFC; // the chart surrounding `cPlot'
	
	public Analysist(ArrayList<ArrayList<Object>> lists)
	{
		masterList = lists;
	}
	
	// adds data point to data
	public void addList(ArrayList<Object> insert)
	{
		masterList.add(insert);
	}
	
	// turns data into a double value
	public double[] numeralize(ArrayList<Object> list)
	{
		double[] converso = new double[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			try {
				converso[i] = (double)list.get(i);
			}catch(ClassCastException ex)
			{
				return null;
			}
		}
		return converso;
	}
	
	// adds axes
	public void constructLineGraph(int index1, int index2)
	{
		current.add(masterList.get(index1), 1, 0);
		current.add(masterList.get(index2), 2, 0);
		
	}
	
	// forms the chart and places it on the window
	public JPanel formImage(int index1, int index2, int w, int h, Graphics2D window)
	{
		cPlot = new CategoryPlot(current, new CategoryAxis(), new NumberAxis(), new ScatterRenderer());
		cPlot.draw(window, new Rectangle(2, 2, 2, 2), new Point(0, 0), new PlotState(), new PlotRenderingInfo(new ChartRenderingInfo()));
		JPanel panel = new JPanel();
		panel.paint(window);
		
		
		//System.out.println("A66");
		//return panel;
		
		/* Alex */
		JFC = new JFreeChart(cPlot);
		//File picture = new File("");
		cPlot.setChart(JFC);
		plot = new ScatterRenderer();
		for(int i = 0; i < masterList.get(index1).size(); i++)
		{
			plot.drawItem(window, new CategoryItemRendererState(new PlotRenderingInfo(new ChartRenderingInfo())), new Rectangle(0, 0, 2, 2), cPlot, new CategoryAxis(), new NumberAxis(), current, 0, 0, 0);
		}
		
		return createDemoPanel();
		//panel.addRenderer(new String[], new CategoryItemRenderer[] {}, Map, Map);
	}
	
	/* Alex */
	// https://stackoverflow.com/a/6669529
	// creates the panel
	public JPanel createDemoPanel() {
		JFreeChart jfreechart = ChartFactory.createScatterPlot(
	            "Scatter Plot Demo", "Nitrite", "Nitrate", numeralizeXY(),
	            PlotOrientation.VERTICAL, true, true, false);
	        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
	        xyPlot.setDomainCrosshairVisible(true);
	        xyPlot.setRangeCrosshairVisible(true);
	        XYItemRenderer renderer = xyPlot.getRenderer();
	        renderer.setSeriesPaint(0, Color.red);
	        return new ChartPanel(jfreechart);
	}
	
	/* Alex */
	// https://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-xy-line-chart-with-xydataset
	// places data into the chart
	public XYDataset numeralizeXY() {
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		for (int i = 0; i < masterList.get(0).size(); i++) {
			x.add(numeralize(masterList.get(DataStatistics.nitriteIndex))[i]);
			y.add(numeralize(masterList.get(DataStatistics.nitrateIndex))[i]);
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = new XYSeries(
				"Slope: " + DataStatistics.CalculateSlopeXY(x, y) +
				"\nY Intercept: " + DataStatistics.CalculateYIntercept(x, y) +
				"\nNitrite Range: " + (DataStatistics.maximum(x)-DataStatistics.minimum(x)) + " (" + DataStatistics.minimum(x) + ", " + DataStatistics.maximum(x) + ")" +
				"\nNitrate Range: " + (DataStatistics.maximum(y)-DataStatistics.minimum(y)) + " (" + DataStatistics.minimum(y) + ", " + DataStatistics.maximum(y) + ")" +
				"\nNitrite Mean Value: " + DataStatistics.mean(x) +
				"\nNitrate Mean Value: " + DataStatistics.mean(y) +
				"\nStandard Deviation of Nitrite: " + DataStatistics.stdDev(x) + 
				"\nStandard Deviation of Nitrate: " + DataStatistics.stdDev(y) +
				"\nR Squared: " + DataStatistics.rSquared(x, y));
		
		for (int i = 0; i < masterList.get(0).size(); i++) {
			series.add(x.get(i), y.get(i));
		}
		
		dataset.addSeries(series);

		return dataset;
	}
}
