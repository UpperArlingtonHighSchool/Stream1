/* Greyson Fowler */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.parser.pdf.PDFParser;

import java.util.Scanner;
import java.util.Scanner.*;
public class pdfParser {
	public String[] nitrogens() throws Exception
	{
		 // Create a content handler
        BodyContentHandler contenthandler = new BodyContentHandler();
 
        // Create a file in local directory
        File f = new File("C:/Users/fowle/eclipse-workspace/Stream Study Group Project/src/2012NutrientMonitoringReport_Final.pdf");
 
        // Create a file input stream
        // on specified path with the created file
        FileInputStream fstream = new FileInputStream(f);
 
        // Create an object of type Metadata to use
        Metadata data = new Metadata();
 
        // Create a context parser for the pdf document
        ParseContext context = new ParseContext();
 
        // PDF document can be parsed using the PDFparser
        // class
        PDFParser pdfparser = new PDFParser();
 
        // Method parse invoked on PDFParser class
        pdfparser.parse(fstream, contenthandler, data,context);
 
        // Printing the contents of the pdf document
        // using toString() method in java
        //replace with the thing that instead checks for the nitrate levels in the 4 rivers checked and adds them to the graphs as straight lines 
        //or just states them I am not sure which but both work.
        // search for "Nitrite as N (mg/L)" and "Nitrate as N (mg/L)"
        // the order will go Stillwater Rvier, Englewood, Great Miami River at Huber Heights, Mad River near Dayton, and Great miami river near Fairfield 
        System.out.println("Extracting contents :"+ contenthandler.toString());
        Scanner bob=new Scanner(contenthandler.toString());
        String nitrite="";
        String nitrate="";
        String line="";
        while(bob.hasNext())
        {
        	line=bob.nextLine();
        	if(line.contains("Nitrite as N (mg/L)"))
        	{
        	nitrite=line.substring(line.lastIndexOf(')')+1);	
        	}
        	if(line.contains("Nitrate as N (mg/L)"))
        	{
        	nitrate=line.substring(line.lastIndexOf(')')+1);
        	}
        }
        String[] n= {nitrite,nitrate};
        return n;
	}
}
