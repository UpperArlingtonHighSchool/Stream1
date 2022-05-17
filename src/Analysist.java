/* Ty Fredrick */

import java.util.ArrayList;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import org.jfree.data.general.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.renderer.RendererState;
import org.jfree.chart.plot.*;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.*;
import javax.swing.*;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jfree.chart.axis.*;

public class Analysist {
	private ArrayList<ArrayList<Object>> masterList = new ArrayList<ArrayList<Object>>();
	public Image last;
	private ScatterRenderer plot;
	private CategoryPlot cPlot;
	DefaultMultiValueCategoryDataset current = new DefaultMultiValueCategoryDataset();
	private JFreeChart JFC = new JFreeChart(cPlot);
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
		return panel;
		//File picture = new File("");
		/*cPlot.setChart(JFC);
		for(int i = 0; i < masterList.get(index1).size(); i++)
		{
			plot.drawItem(null, new CategoryItemRendererState(new PlotRenderingInfo(new ChartRenderingInfo())), new Rectangle(0, 0, 2, 2), cPlot, null, null, current, 0, 0, 0);
		}*/
	}
	public static void main(String[] args)
	{
		
	}
}
