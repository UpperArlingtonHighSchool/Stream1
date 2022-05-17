/* Greyson Fowler */

import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.util.Scanner;
import java.lang.Math.*;
import static java.lang.System.*;
import java.text.*;
import java.util.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.*;
//this is the heart of the GUI 
public class StreamFrame extends JFrame implements ActionListener{
Container tuberware=getContentPane();
JLabel title=new JLabel("Nitrate and Nitrite Stream Contents");
JButton chart1=new JButton("Placeholder");
JButton chart2=new JButton("Placeholder");
JButton chart3=new JButton("Placeholder");
JButton chart4=new JButton("Placeholder");
JLabel nitrateLevels;//=new JLabel("<html>First line<br>Second line</html>");
JLabel nitriteLevels;//=new JLabel("<html>First line<br>Second line</html>");
ImageIcon logo=new ImageIcon("src/logo.png");
JLabel image=new JLabel(logo);
	//initilization
	public StreamFrame()
	{
		String[] levels=new String[2];
		pdfParser bob=new pdfParser();
		try {
			levels=bob.nitrogens();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String thing="Nitrites:";
		String[] locations= {"Stillwater River At Englewood","Great Miami River At Huberheights","Mad River near Dayton","Great Miami River Near Fairfield"};
		String[] nitrite=levels[0].split(" ");
		String thing3="";
		for(int x=0;x<4;x++)
		{
			if(x<=1)
			thing+=(" "+locations[x]+" "+nitrite[x+1]+",");
			if(x>1)
			{
				thing3+=" "+locations[x]+" "+nitrite[x+1]+",";
			}
		}
		nitriteLevels=new JLabel("<html>"+thing+"<br>"+thing3+"</html>");
		//nitriteLevels.setText(thing);
		String thing2="Nitrates:";
	//	out.println(levels[0]+" "+levels[1]);
		String[] nitrate=levels[1].split(" ");
		String thing4="";
		for(int y=0;y<4;y++)
		{
			//out.println(nitrate[x]);
			if(y<=1)
			thing2+=(" "+locations[y]+": "+nitrate[y+1]+",");
			if(y>1)
			{
				thing4+=" "+locations[y]+": "+nitrate[y+1]+",";
			}
		}
		nitrateLevels=new JLabel("<html>"+thing2+"<br>"+thing4+"</html>");
		//nitriteLevels.setText(thing);
		//nitrateLevels.setText(thing2);
		setLayoutManager();
		setLocationAndSize();
		intoTuberware();
		addActionListener();
		
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='a')
				{
				//	move.horizontal(-2);
					//int align=title.getHorizontalAlignment();
					//title.setHorizontalAlignment(align-2);
					
					//title.setBounds((title.getHorizontalAlignment()-1),title.getVerticalAlignment(),title.getHeight(),title.getWidth());
				//	intoTuberware();
				}
				if(e.getKeyChar()=='d')
				{
				//	move.horizontal(2);
				}
				if(e.getKeyChar()=='s')
				{
				//	move.vertical(2);
				}
				if(e.getKeyChar()=='w')
				{
				//	move.vertical(-2);
				}
				if(e.getKeyChar()==' ')
				{
				//	move.fire();
				    
				}
          //  System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
			
		}
			@Override
			public void keyReleased(KeyEvent e)
			{
				
			}
		});
		
		
	}
	public void setLayoutManager()
	{
		//desc setting layout manager
		tuberware.setLayout(null);
	}
	//used to set location and size for all stored variables. 
	public void setLocationAndSize()
	{
	title.setBounds(400,200,250,300);	
	Color bl=new Color(10,10,255);
	title.setForeground(bl);
	chart1.setBounds(175,400,100,50);
	chart2.setBounds(350,400,100,50);
	chart3.setBounds(525,400,100,50);
	chart4.setBounds(700,400,100,50);
	nitrateLevels.setBounds(250,600,600,100);
	nitriteLevels.setBounds(250,800,600,100);
	image.setBounds(350,0,300,300);
	//image.setResizable(true);
	}
	public void intoTuberware()
	{
		tuberware.add(title);
		tuberware.add(chart1);
		tuberware.add(chart2);
		tuberware.add(chart3);
		tuberware.add(chart4);
		tuberware.add(nitriteLevels);
		tuberware.add(nitrateLevels);
		tuberware.add(image);
	}
	public void addActionListener()
	{
		chart1.addActionListener(this);
		chart2.addActionListener(this);
		chart3.addActionListener(this);
		chart4.addActionListener(this);
	}
	@Override//this is used to detect actions and respond accordingly
	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource()==chart1)
	{

	//Set this to the appropriate graph instead of a blank jpanel.	
	JPanel placeholder=new JPanel();
	tuberware.add(placeholder);
	Color bl=new Color(0,100,200);
	placeholder.setForeground(bl);
	JFrame host=new JFrame();
	host.getContentPane().add(placeholder);
	host.setVisible(true);
	host.setBounds(10,10,250,250);
	host.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	host.setResizable(false);

	}
	if(e.getSource()==chart2)
	{

	//Set this to the appropriate graph instead of a blank jpanel.	
	JPanel placeholder=new JPanel();
	tuberware.add(placeholder);
	Color bl=new Color(0,100,200);
	placeholder.setForeground(bl);
	JFrame host=new JFrame();
	host.getContentPane().add(placeholder);
	host.setVisible(true);
	host.setBounds(10,10,250,250);
	host.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	host.setResizable(false);

	}
	if(e.getSource()==chart3)
	{

	//Set this to the appropriate graph instead of a blank jpanel.	
	JPanel placeholder=new JPanel();
	tuberware.add(placeholder);
	Color bl=new Color(0,100,200);
	placeholder.setForeground(bl);
	JFrame host=new JFrame();
	host.getContentPane().add(placeholder);
	host.setVisible(true);
	host.setBounds(10,10,250,250);
	host.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	host.setResizable(false);

	}
	if(e.getSource()==chart4)
	{

	//Set this to the appropriate graph instead of a blank jpanel.	
	JPanel placeholder=new JPanel();
	tuberware.add(placeholder);
	Color bl=new Color(175,200,250);
	//placeholder.setForeground(bl);
	placeholder.setBackground(bl);
	JFrame host=new JFrame();
	host.getContentPane().add(placeholder);
	host.setVisible(true);
	host.setBounds(10,10,250,250);
	host.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	host.setResizable(false);

	}
	}
}
