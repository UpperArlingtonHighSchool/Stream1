/* Riddhi Gupta */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStatistics {

private static ArrayList<String> locationList = new ArrayList<String>();
private static ArrayList<Double> yearSince2013List = new ArrayList<Double>();
private static ArrayList<Double> nitriteList = new ArrayList<Double>();
private static ArrayList<Double> nitrateList = new ArrayList<Double>();

public static void getData() throws FileNotFoundException
{
String location = "";
double year = 0;
double nitrite = 0;
double nitrate = 0;
Scanner file = new Scanner(new File("src/StreamData.txt"));
while(file.hasNextLine())
{
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

private static double sum(ArrayList<Double> x)
{
double sum = 0;
for(Double d : x)
{
sum+=d;
}
return sum;
}

private static double sumXSquared(ArrayList<Double> x)
{
double sum = 0;
double square = 0;
for(Double d : x)
{
square = Math.pow(d, 2);
sum+=square;
}
return sum;
}

private static double sumXY(ArrayList<Double> x, ArrayList<Double> y)
{
double sum = 0;
double product = 0;

for(int i = 0; i < x.size(); i++)
{
product = x.get(i)*y.get(i);
sum+=product;
}
return sum;
}

public static double CalculateSlopeXY(ArrayList<Double> x, ArrayList<Double> y)
{
double slope;
slope = (x.size()*sumXY(x,y)-sum(x)*sum(y))/(x.size()*sumXSquared(x)-sum(x)*sum(x));
return slope;
}

public static double CalculateYIntercept(ArrayList<Double> x, ArrayList<Double> y)
{
double yInt;
yInt = (sum(y) - CalculateSlopeXY(x,y)*sum(x))/x.size();
return yInt;
}

public static double mean(ArrayList<Double> x)
{
double mean = sum(x)/x.size();
return mean;
}

public static double stdDev(ArrayList<Double> x)
{
double stdDeviation = 0;
double sum = 0;
double avg = mean(x);
for(double d : x)
{
sum = sum + Math.pow(d-avg, 2);
}
stdDeviation = Math.sqrt(avg/x.size());
return stdDeviation;
}
}
