package com.ExamPortal;

import java.awt.Color;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;
import java.util.regex.Pattern;

import javax.swing.*;

import com.util.data.*;

public class admin_ins extends JFrame implements ActionListener, KeyListener {
	
	JLabel ladmin_id,ladmin_password,ladmin_name,ladmin_dob,ladmin_gender,ladmin_phone,ladmin_email,ladmin_add;
	JTextField tadmin_id,tadmin_password,tadmin_name,tadmin_dob,tadmin_add,tadmin_phone,tadmin_email;
	JButton insert,clear;
	JComboBox tadmin_gender;
	JPanel panel ;
	JLabel correct;
	
	
	//JDBC
	Connection con;PreparedStatement st;

	admin_ins()
	{
		setSize(2000,1000);
		setTitle("ADMIN");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("welcome_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(2000, 1000, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 2000, 1000);
	    add(image);
	    
	    
	    
		ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("TEPlogo.png"));
	    Image logo1= logo.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT);
	    ImageIcon logo2 = new ImageIcon(logo1);
	    JLabel logo3 = new JLabel("THE EXAM PORTAL",logo2,SwingConstants.CENTER);
	    logo3.setVerticalTextPosition(JLabel.BOTTOM);
	    logo3.setHorizontalTextPosition(JLabel.CENTER);
	    logo3.setBounds(1600,700, 400, 200);
	    logo3.setForeground(new Color(0,76,153));
	    logo3.setFont(new Font("Arial",Font.BOLD,20));
	    add(logo3);
	    image.add(logo3);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("admin_login.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("ADMIN",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(20,20, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_insert.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("INSERT",insert2,SwingConstants.CENTER);
	    
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(900,0, 200, 200);
	    add(insert3);
	    image.add(insert3);
	    
	    panel= new JPanel(); 
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(600,100,800, 600);
	    panel.setLayout(null);
	    add(panel);
	    image.add(panel);
	    
	    
		ladmin_id=new JLabel("ADMIN ID:");
		ladmin_id.setBounds(100,100,200,30);
		ladmin_id.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_id.setForeground(new Color(0,102,204));
		panel.add(ladmin_id);
		tadmin_id=new JTextField();
		tadmin_id.setText(getID());
		tadmin_id.setBounds(250,100,350,30);
		tadmin_id.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_id);
		correct();
		
		
		ladmin_name=new JLabel("NAME:");
		ladmin_name.setBounds(100,150,200,30);
		ladmin_name.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_name.setForeground(new Color(0,102,204));
		panel.add(ladmin_name);
		tadmin_name=new JTextField();
		tadmin_name.setBounds(250,150,350,30);
		tadmin_name.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_name);
		tadmin_name.addKeyListener(this);
		
		
		ladmin_dob=new JLabel("DATE OF BIRTH:");
		ladmin_dob.setBounds(100,200,200,30);
		ladmin_dob.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_dob.setForeground(new Color(0,102,204));
		panel.add(ladmin_dob);
		tadmin_dob=new JTextField();
		tadmin_dob.setBounds(250,200,350,30);
		tadmin_dob.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_dob);
		tadmin_dob.addKeyListener(this);
		
		ladmin_gender=new JLabel("GENDER:");
		ladmin_gender.setBounds(100,250,200,30);
		ladmin_gender.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_gender.setForeground(new Color(0,102,204));
		panel.add(ladmin_gender);
		tadmin_gender=new JComboBox();
		tadmin_gender.insertItemAt("", 0);
		tadmin_gender.insertItemAt("MALE", 1);
		tadmin_gender.insertItemAt("FEMALE", 2);
		tadmin_gender.insertItemAt("OTHERS", 3);
		tadmin_gender.setBounds(250,250,350,30);
		tadmin_gender.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_gender);
		
		ladmin_phone=new JLabel("PHONE:");
		ladmin_phone.setBounds(100,300,200,30);
		ladmin_phone.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_phone.setForeground(new Color(0,102,204));
		panel.add(ladmin_phone);
		tadmin_phone=new JTextField();
		tadmin_phone.setBounds(250,300,350,30);
		tadmin_phone.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_phone);
		tadmin_phone.addKeyListener(this);
		
		ladmin_email=new JLabel("EMAIL:");
		ladmin_email.setBounds(100,350,200,30);
		ladmin_email.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_email.setForeground(new Color(0,102,204));
		panel.add(ladmin_email);
		tadmin_email=new JTextField();
		tadmin_email.setBounds(250,350,350,30);
		tadmin_email.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_email);
		tadmin_email.addKeyListener(this);
		
		ladmin_add=new JLabel("ADDRESS:");
		ladmin_add.setBounds(100,400,200,30);
		ladmin_add.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_add.setForeground(new Color(0,102,204));
		panel.add(ladmin_add);
		tadmin_add=new JTextField();
		tadmin_add.setBounds(250,400,350,30);
		tadmin_add.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_add);
		
		
		ladmin_password=new JLabel("PASSWORD:");
		ladmin_password.setBounds(100,450,200,30);
		ladmin_password.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_password.setForeground(new Color(0,102,204));
		panel.add(ladmin_password);
		tadmin_password=new JTextField();
		tadmin_password.setBounds(250,450,350,30);
		tadmin_password.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tadmin_password);
		tadmin_password.addKeyListener(this);
		
		
		
		
		insert=new JButton("INSERT");
	    insert.setBounds(500,550,200,30);
	    insert.setFont(new Font("Arial",Font.BOLD,16));
	    insert.setBackground(new Color(0,76,153));
		insert.setForeground(Color.WHITE);
		panel.add(insert);
		insert.addActionListener(this);
		
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new admin_ins();

	}
	//METHODS 
	
	void correct()
	{
		correct=new JLabel();
		panel.add(correct);
	   
	}
	
	
	
	String getID()
	{
		String aid;
		String y;
		LocalDateTime myDateObj = LocalDateTime.now();
	    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy");

	    y= myDateObj.format(myFormatObj);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select admin_id  from tep_admin";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			
			if (rs.next())
			{
				
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(10,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					aid="ADMIN"+y+"X"+"000"+(x+1);
				else if(x>=10 && x<99)
					aid="ADMIN"+y+"X"+"00"+(x+1);
				else if(x>=100 && x<999)
					aid="ADMIN"+y+"X"+"0"+(x+1);
				else
					aid="ADMIN"+y+"X"+(x+1);
			}
			else {
				
				aid="ADMIN"+y+"X"+"0001";
			}
			return aid;
			
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==insert)
		{
			if (tadmin_id.getText().length()==0 ||tadmin_name.getText().length()==0 ||tadmin_dob.getText().length()==0 ||tadmin_phone.getText().length()==0 ||tadmin_email.getText().length()==0||tadmin_add.getText().length()==0||tadmin_password.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String id=tadmin_id.getText();
				String name=tadmin_name.getText();
				String dob=tadmin_dob.getText();
				String gender=tadmin_gender.getSelectedItem().toString();
				String phone=tadmin_phone.getText();
				String email=tadmin_email.getText();
				String add=tadmin_add.getText();
				String pass=tadmin_password.getText();
				
				admin_util_data obj=new admin_util_data();
				String msg=obj.insertData(id,name,dob,gender,phone,email,add,pass);				
				JOptionPane.showMessageDialog(this, msg);
				//a_idf,a_namef,a_phnf,a_emlf,a_addf;
				tadmin_id.setText(getID());
				tadmin_name.setText("");
				tadmin_dob.setText("");
				tadmin_gender.insertItemAt("",0);
				tadmin_phone.setText("");
				tadmin_email.setText("");
				tadmin_add.setText("");
				tadmin_password.setText("");
				correct.setText("");
			}
	
		
	}

}

	@Override
	public void keyPressed(KeyEvent ae) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
		String regex;
		if(arg0.getSource()==tadmin_name)
		{
			regex = "^[\\p{L} .'-]+$";
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regex);
		      //Creating a Matcher object
		      Matcher matcher = pattern.matcher(tadmin_name.getText());
		      //Verifying whether given phone number is valid
		      if(matcher.matches()) {
		    	  int ascii = 10003;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(0,153,0));
		    	  correct.setBounds(630, 150, 100, 30);
		    	  panel.add(correct);
		  	    
		  	    
		         
		      } else {
		    	  int ascii = 10060;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(249,14,14));
		    	  correct.setBounds(630, 150, 100, 30);
		    	  panel.add(correct);
		         
		      }
			
		}
		
		if(arg0.getSource()==tadmin_dob)
		{
			regex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regex);
		      //Creating a Matcher object
		      Matcher matcher = pattern.matcher(tadmin_dob.getText());
		      //Verifying whether given phone number is valid
		      if(matcher.matches()) {
		    	  int ascii = 10003;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(0,153,0));
		    	  correct.setBounds(630, 200, 100, 30);
		    	  panel.add(correct);
		  	    
		  	    
		         
		      } else {
		    	  int ascii = 10060;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign+" Must be pattern dd-mm-yyyy");
		    	  correct.setForeground(new Color(249,14,14));
		    	  correct.setBounds(610, 200, 300, 30);
		    	  panel.add(correct);
		         
		      }
			
		}
		
		if(arg0.getSource()==tadmin_phone)
		{
			regex = "^\\d{10}$";
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regex);
		      //Creating a Matcher object
		      Matcher matcher = pattern.matcher(tadmin_phone.getText());
		      //Verifying whether given phone number is valid
		      if(matcher.matches()) {
		    	  int ascii = 10003;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(0,153,0));
		    	  correct.setBounds(630, 300, 100, 30);
		    	  panel.add(correct);
		  	    
		  	    
		         
		      } else {
		    	  int ascii = 10060;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(249,14,14));
		    	  correct.setBounds(630, 300, 100, 30);
		    	  panel.add(correct);
		         
		      }
			
		}
		
		if(arg0.getSource()==tadmin_email)
		{
			regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regex);
		      //Creating a Matcher object
		      Matcher matcher = pattern.matcher(tadmin_email.getText());
		      //Verifying whether given phone number is valid
		      if(matcher.matches()) {
		    	  int ascii = 10003;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(0,153,0));
		    	  correct.setBounds(630, 350, 100, 30);
		    	  panel.add(correct);
		  	    
		  	    
		         
		      } else {
		    	  int ascii = 10060;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign+" eg. xyz123@fgd.com");
		    	  correct.setForeground(new Color(249,14,14));
		    	  correct.setBounds(610, 350, 300, 30);
		    	  panel.add(correct);
		         
		      }
			
		}
		
		if(arg0.getSource()==tadmin_password)
		{
			regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regex);
		      //Creating a Matcher object
		      Matcher matcher = pattern.matcher(tadmin_password.getText());
		      //Verifying whether given phone number is valid
		      if(matcher.matches()) {
		    	  int ascii = 10003;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(0,153,0));
		    	  correct.setBounds(630, 450, 100, 30);
		    	  panel.add(correct);
		  	    
		  	    
		         
		      } else {
		    	  int ascii = 10060;      
		    	  String sign = Character.toString((char)ascii);
		    	  correct.setText(sign);
		    	  correct.setForeground(new Color(249,14,14));
		    	  correct.setBounds(630, 450, 100, 30);
		    	  panel.add(correct);
		         
		      }
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}}
