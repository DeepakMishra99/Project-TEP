package com.ExamPortal;

import java.awt.*;
import javax.swing.*;

public class welcome_splash extends JFrame{
static JLabel l1,l2;
	
	static JProgressBar jp;
	int val=0;
	
	welcome_splash()
	{
		setSize(2000,1000);
		setLayout(null);
		
		setLocationRelativeTo(null); 
		setResizable(false);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("tep_logo.jpg"));
	    Image i2 = i1.getImage().getScaledInstance(2000, 1000, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image = new JLabel(i3);
	    image.setBounds(0,0, 2000, 1000);
	    add(image);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setForeground(new Color(0,102,204));
		
		
		jp.setBounds(100,800,1650,30);
		add(jp);
		image.add(jp);
		
		l1=new JLabel();
		l1.setBounds(1000, 750, 100, 50);
		l1.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,20));
		add(l1);
		image.add(l1);
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		int i;
		welcome_splash d=new welcome_splash();
		
		
	try
	{

	
	
	for(i=0;i<=100;i++)
	{
		Thread.sleep(35);	
		d.jp.setValue(i);
		d.l1.setText(String.valueOf(i)+"%");
		if(i==100)
		{
			jp.setVisible(false);
			
		    
		}
				
	}
	
	
	d.dispose();
	new welcome();
	
	}
	catch(InterruptedException ae){
		
	}
		
	}		
		
}