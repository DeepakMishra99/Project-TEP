package com.ExamPortal;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class welcome extends JFrame implements ActionListener {
	JLabel lbl,lbl1,lblvisid,lblNewLabel1;
	JButton Admin,User,abt,About;
	ButtonGroup bg;
	
	Connection con;
	PreparedStatement st;
	
	
	public welcome() {
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
	    Image about1 = about.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	    ImageIcon about2 = new ImageIcon(about1);
	  
	   
	    
	    abt=new JButton(about2);
	    abt.setBounds(1650,800, 200, 50);
	    abt.setText("ABOUT");
	    abt.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
	    abt.setContentAreaFilled(false);
	    abt.setFocusPainted(false);
	    abt.setBorderPainted(false);
	    abt.setForeground(new Color(0,76,153));
		add(abt);
		image.add(abt);
		abt.addActionListener(this);
	    

		ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("TEPlogo.png"));
	    Image logo1 = logo.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
	    ImageIcon logo2 = new ImageIcon(logo1);
		lbl=new JLabel(logo2);
		lbl.setText("WELCOME TO THE EXAM PORTAL");
		lbl.setBounds(500,100,1000,200);
		lbl.setFont(new Font("Georgian",Font.BOLD,40));
		lbl.setForeground(new Color(0,102,204));
		add(lbl);
		image.add(lbl);
		
		ImageIcon user_icon1 = new ImageIcon(ClassLoader.getSystemResource("student_login.png"));
	    Image user_icon2 = user_icon1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon user_icon3 = new ImageIcon(user_icon2);
	  
		User=new JButton(user_icon3);
		User.setBounds(1100,400,400,150);
		User.setText("STUDENT LOGIN");
		User.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		User.setContentAreaFilled(false);
		User.setFocusPainted(false);
		User.setBorderPainted(false);
		User.setForeground(new Color(0,76,153));
		add(User);
		image.add(User);
		
		
		ImageIcon admin_icon1 = new ImageIcon(ClassLoader.getSystemResource("admin_login.png"));
	    Image admin_icon2 = admin_icon1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon admin_icon3 = new ImageIcon(admin_icon2);
	    
		Admin=new JButton(admin_icon3);
		Admin.setBounds(400,400,400,150);
		Admin.setText("ADMIN LOGIN");
		Admin.setFont(new Font("Arial Rounded MT Bold ",Font.BOLD,20));
		Admin.setContentAreaFilled(false);
		Admin.setFocusPainted(false);
		Admin.setBorderPainted(false);
		Admin.setForeground(new Color(0,76,153));
		add(Admin);
		image.add(Admin);
		
		
		
		Admin.addActionListener(this);
		User.addActionListener(this);
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
 }
		
		
		
		
		
		
	

public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new welcome();
	}

@Override
public void actionPerformed(ActionEvent ae) {
	// TODO Auto-generated method stub
	if(ae.getSource()==abt)
	{
		new about();
	}
	if(ae.getSource()==Admin)
	{
		new admin_login();
	}
	if(ae.getSource()==User)
	{
		new user_login();
	}
	
}
}


