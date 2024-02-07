package com.ExamPortal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class about extends JFrame {
	
	JLabel abt,about_tep,about_software,app,app_value,tech,tech_value,lang,lang_value,database,tdatabase;
	
	about(){
		setSize(2000,1000);
		setLayout(null);
		setBackground(Color.WHITE);
		setTitle("Welcome");
		setLocationRelativeTo(null); 
		setResizable(false);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("welcome_bg.jpg"));
	    Image i2 = i1.getImage().getScaledInstance(2000, 1000, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image = new JLabel(i3);
	    image.setBounds(0,0, 2000, 1000);
	    add(image);
	    
	    ImageIcon about = new ImageIcon(ClassLoader.getSystemResource("about.png"));
	    Image about1 = about.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon about2 = new ImageIcon(about1);
	  
	   
	    
	    abt=new JLabel(about2);
	    abt.setBounds(800,20, 400, 100);
	    abt.setText("ABOUT");
	    abt.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,40));
	    abt.setForeground(new Color(0,76,153));
		add(abt);
		image.add(abt);
		
		about_tep=new JLabel();
		about_tep.setBounds(50,200, 2000, 100);
		about_tep.setText("Exams Portal is a software solution enabling"
				+ " institutions to easily and safely publish and distribute"
				+ " exams and related reports. ");
		about_tep.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		about_tep.setForeground(new Color(0,76,153));
		add(about_tep);
		image.add(about_tep);
		
		ImageIcon software = new ImageIcon(ClassLoader.getSystemResource("software_details.png"));
		Image software1 = software.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		ImageIcon software2 = new ImageIcon(software1);
		about_software=new JLabel(software2);
		about_software.setBounds(900,300, 300, 100);
		about_software.setText("SOFTWARE DETAILS ");
		about_software.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		about_software.setForeground(new Color(0,76,153));
		add(about_software);
		image.add(about_software);
		
		
		app=new JLabel("APPLICATION TYPE      : ");
		app.setBounds(600,400, 300, 100);
		app.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		app.setForeground(new Color(0,76,153));
		add(app);
		image.add(app);
		
		app_value=new JLabel(" DESKTOP APPLICATION");
		app_value.setBounds(900,400, 400, 100);
		app_value.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		app_value.setForeground(new Color(0,76,153));
		add(app_value);
		image.add(app_value);
		
		lang=new JLabel("LANGUAGE              : ");
		lang.setBounds(600,450, 300, 100);
		lang.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		lang.setForeground(new Color(0,76,153));
		add(lang);
		image.add(lang);
		
		lang_value=new JLabel(" JAVA");
		lang_value.setBounds(900,450, 300, 100);
		lang_value.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		lang_value.setForeground(new Color(0,76,153));
		add(lang_value);
		image.add(lang_value);
		
		tech=new JLabel("TECHNOLOGY            : ");
		tech.setBounds(600,500, 300, 100);
		tech.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		tech.setForeground(new Color(0,76,153));
		add(tech);
		image.add(tech);
		
		tech_value=new JLabel(" AWT, SPRING");
		tech_value.setBounds(900,500, 300, 100);
		tech_value.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		tech_value.setForeground(new Color(0,76,153));
		add(tech_value);
		image.add(tech_value);
		
		database=new JLabel("DATABASE            : ");
		database.setBounds(600,550, 300, 100);
		database.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		database.setForeground(new Color(0,76,153));
		add(database);
		image.add(database);
		
		tdatabase=new JLabel(" ORACLE");
		tdatabase.setBounds(900,550, 300, 100);
		tdatabase.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		tdatabase.setForeground(new Color(0,76,153));
		add(tdatabase);
		image.add(tdatabase);
		
		
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new about();

	}

}
