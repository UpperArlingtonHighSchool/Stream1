/* Riddhi Gupta (unless otherwise specified) */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStatistics {

	private static ArrayList<String> locationList = new ArrayList<String>();
	private static ArrayList<Double> yearSince2013List = new ArrayList<Double>();
	private static ArrayList<Double> nitriteList = new ArrayList<Double>();
	private static ArrayList<Double> nitrateList = new ArrayList<Double>();
	public static final int nitriteIndex = 2;
	public static final int nitrateIndex = 3;

	// scrapes data from the file
	public static void getData() throws FileNotFoundException {
		String location = "";
		double year = 0;
		double nitrite = 0;
		double nitrate = 0;
		Scanner file = new Scanner(new File(Main.STREAM_PATH));
		while (file.hasNextLine()) {
			location = file.next();
			year = file.nextDouble();
			year = year - 2013;
			for(int i = 0; i < 11; i++)
			{
			file.nextDouble();
			}
			nitrite = file.nextDouble();
			nitrate = file.nextDouble();
			file.nextLine();
			locationList.add(location);
			nitriteList.add(nitrite);
			nitrateList.add(nitrate);
			yearSince2013List.add(year);
		}
	}

	// x1 + x2 + x3 + ...
	private static double sum(ArrayList<Double> x) {
		double sum = 0;
		for (Double d : x) {
			sum += d;
		}
		return sum;
	}

	// x1^2 + x2^2 + x3^3 + ...
	private static double sumXSquared(ArrayList<Double> x) {
		double sum = 0;
		double square = 0;
		for (Double d : x) {
			square = Math.pow(d, 2);
			sum += square;
		}
		return sum;
	}

	// x1*y1 + x2*y2 + x3*y3 + ..
	private static double sumXY(ArrayList<Double> x, ArrayList<Double> y) {
		double sum = 0;
		double product = 0;

		for (int i = 0; i < x.size(); i++) {
			product = x.get(i) * y.get(i);
			sum += product;
		}
		return sum;
	}

	// slope (dy/dx) of the line of regression
	public static double CalculateSlopeXY(ArrayList<Double> x, ArrayList<Double> y) {
		double slope;
		slope = (x.size() * sumXY(x, y) - sum(x) * sum(y)) / (x.size() * sumXSquared(x) - sum(x) * sum(x));
		return slope;
	}

	// y-intercept of line of regression (when x=0)
	public static double CalculateYIntercept(ArrayList<Double> x, ArrayList<Double> y) {
		double yInt;
		yInt = (sum(y) - CalculateSlopeXY(x, y) * sum(x)) / x.size();
		return yInt;
	}

	// arithmetic-mean(x1, x2, x3, ...)
	public static double mean(ArrayList<Double> x) {
		double mean = sum(x) / x.size();
		return mean;
	}

	// standard-deviation(x1, x2, x3, ...)
	public static double stdDev(ArrayList<Double> x) {
		double stdDeviation = 0;
		double sum = 0;
		double avg = mean(x);
		for (double d : x) {
			sum = sum + Math.pow(d - avg, 2);
		}
		stdDeviation = Math.sqrt(avg / x.size());
		return stdDeviation;
	}
	
	// the estimated y-value of the line of regression for a given `numX'
	private static double yHat(double numX, ArrayList<Double> x, ArrayList<Double> y)
	{
		double slope = CalculateSlopeXY(x,y);
		double yInt = CalculateYIntercept(x,y);
		double output = numX*slope+yInt;
		return output;
	}
	
	// calculates the error of a line of regression
	// sum of squares of distances from true y-values and estimated y-values (y-hat)
	private static double sumSquaredRegression(ArrayList<Double> x, ArrayList<Double> y)
	{
		double yPredicted;
		double yActual;
		double difference;
		double sum = 0;
		for(int i = 0; i < x.size(); i++)
		{
			yPredicted = yHat(x.get(i), x, y);
			yActual = y.get(i);
			difference = yActual - yPredicted;
			sum = sum + Math.pow(difference, 2);
		}
		return sum;
	}
	
	// sumSquaredRegression where the line of regression is y=mean(y1, y2, y3,...)
	private static double totalSumOfSquares(ArrayList<Double> x, ArrayList<Double> y)
	{
		double yBar = mean(y);
		double yActual;
		double difference;
		double sum = 0;
		for(int i = 0; i < x.size(); i++)
		{
			yActual = y.get(i);
			difference = yActual - yBar;
			sum = sum + Math.pow(difference, 2);
		}
		return sum;
	}

	// calculates the R^2 value from 0.0 to 1.0
	// 0 = no correlation
	// 1 = perfect correlation
	public static double rSquared(ArrayList<Double> x, ArrayList<Double> y)
	{
		double r2 = sumSquaredRegression(x,y)/totalSumOfSquares(x,y);
		r2 = 1- r2;
		return r2;
	}
	
	// minimal value of set
	public static double minimum(ArrayList<Double> x)
	{
		double min = x.get(0);
		for(double d : x)
		{
			if (d < min)
			{
				min = d;
			}
		}

		return min;
	}

	// maximal value of set
	public static double maximum(ArrayList<Double> x)
	{
		double max = x.get(0);
		for(double d : x)
		{
			if (d > max)
			{
				max = d;
			}
		}

		return max;
	}
	
	/* Alex */
	// turns data into different form for Analysist.java
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Object>> getDataArrayList() {
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		data.add((ArrayList<Object>)(ArrayList<?>) locationList);
		data.add((ArrayList<Object>)(ArrayList<?>) yearSince2013List);
		data.add((ArrayList<Object>)(ArrayList<?>) nitriteList);
		data.add((ArrayList<Object>)(ArrayList<?>) nitrateList);
		return data;
	}
}
