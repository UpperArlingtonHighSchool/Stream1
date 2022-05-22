/* Ty Fredrick */

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
	private ArrayList<ArrayList<Object>> masterList = new ArrayList<ArrayList<Object>>();
	public Image last;
	private ScatterRenderer plot;
	private CategoryPlot cPlot;
	DefaultMultiValueCategoryDataset current = new DefaultMultiValueCategoryDataset();
	private JFreeChart JFC;
	public Analysist(ArrayList<ArrayList<Object>> lists)
	{
		masterList = lists;
	}
	public void addList(ArrayList<Object> insert)
	{
		masterList.add(insert);
	}
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
	public void constructLineGraph(int index1, int index2)
	{
		current.add(masterList.get(index1), 1, 0);
		current.add(masterList.get(index2), 2, 0);
		
	}
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
	public JPanel createDemoPanel() {
		JFreeChart jfreechart = ChartFactory.createScatterPlot(
	            "Scatter Plot Demo", "X", "Y", numeralizeXY(),
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
	public XYDataset numeralizeXY() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = new XYSeries("Nitrite");
		
		for (int i = 0; i < masterList.size(); i++) {
			series.add(numeralize(masterList.get(DataStatistics.nitriteIndex))[i], numeralize(masterList.get(DataStatistics.nitrateIndex))[i]);
		}
		
		dataset.addSeries(series);

		return dataset;
	}
}
