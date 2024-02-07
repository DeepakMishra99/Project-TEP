package com.ExamPortal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import com.util.data.*;


public class user_ins extends JFrame implements ActionListener, KeyListener {
	
	JLabel luser_id,luser_password,luser_name,luser_dob,luser_gender,luser_phone,luser_email,luser_add;
	JTextField tuser_id,tuser_password,tuser_name,tuser_dob,tuser_add,tuser_phone,tuser_email;
	JButton insert,clear;
	JComboBox tuser_gender;
	JPanel panel ;
	JLabel correct;
	
	
	//JDBC
	Connection con;PreparedStatement st;
	
	user_ins()
	{
		setSize(2000,1000);
		setTitle("USER");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("student_login.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("STUDENT",i3,SwingConstants.CENTER);
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
	    
	    panel = new JPanel(); 
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(600,100,800, 600);
	    panel.setLayout(null);
	    add(panel);
	    image.add(panel);
	   	
	    luser_id=new JLabel("STUDENT ID:");
		luser_id.setBounds(100,100,200,30);
		luser_id.setFont(new Font("Arial",Font.BOLD,18));
		luser_id.setForeground(new Color(0,102,204));
		panel.add(luser_id);
		tuser_id=new JTextField();
		tuser_id.setText(getID());
		tuser_id.setBounds(250,100,350,30);
		tuser_id.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_id);
		correct();
		
		
		luser_name=new JLabel("NAME:");
		luser_name.setBounds(100,150,200,30);
		luser_name.setFont(new Font("Arial",Font.BOLD,18));
		luser_name.setForeground(new Color(0,102,204));
		panel.add(luser_name);
		tuser_name=new JTextField();
		tuser_name.setBounds(250,150,350,30);
		tuser_name.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_name);
		tuser_name.addKeyListener(this);
		
		
		luser_dob=new JLabel("DATE OF BIRTH:");
		luser_dob.setBounds(100,200,200,30);
		luser_dob.setFont(new Font("Arial",Font.BOLD,18));
		luser_dob.setForeground(new Color(0,102,204));
		panel.add(luser_dob);
		tuser_dob=new JTextField();
		tuser_dob.setBounds(250,200,350,30);
		tuser_dob.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_dob);
		tuser_dob.addKeyListener(this);
		
		luser_gender=new JLabel("GENDER:");
		luser_gender.setBounds(100,250,200,30);
		luser_gender.setFont(new Font("Arial",Font.BOLD,18));
		luser_gender.setForeground(new Color(0,102,204));
		panel.add(luser_gender);
		tuser_gender=new JComboBox();
		tuser_gender.insertItemAt("", 0);
		tuser_gender.insertItemAt("MALE", 1);
		tuser_gender.insertItemAt("FEMALE", 2);
		tuser_gender.insertItemAt("OTHERS", 3);
		tuser_gender.setBounds(250,250,350,30);
		tuser_gender.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_gender);
		
		luser_phone=new JLabel("PHONE:");
		luser_phone.setBounds(100,300,200,30);
		luser_phone.setFont(new Font("Arial",Font.BOLD,18));
		luser_phone.setForeground(new Color(0,102,204));
		panel.add(luser_phone);
		tuser_phone=new JTextField();
		tuser_phone.setBounds(250,300,350,30);
		tuser_phone.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_phone);
		tuser_phone.addKeyListener(this);
		
		luser_email=new JLabel("EMAIL:");
		luser_email.setBounds(100,350,200,30);
		luser_email.setFont(new Font("Arial",Font.BOLD,18));
		luser_email.setForeground(new Color(0,102,204));
		panel.add(luser_email);
		tuser_email=new JTextField();
		tuser_email.setBounds(250,350,350,30);
		tuser_email.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_email);
		tuser_email.addKeyListener(this);
		
		luser_add=new JLabel("ADDRESS:");
		luser_add.setBounds(100,400,200,30);
		luser_add.setFont(new Font("Arial",Font.BOLD,18));
		luser_add.setForeground(new Color(0,102,204));
		panel.add(luser_add);
		tuser_add=new JTextField();
		tuser_add.setBounds(250,400,350,30);
		tuser_add.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_add);
		
		
		luser_password=new JLabel("PASSWORD:");
		luser_password.setBounds(100,450,200,30);
		luser_password.setFont(new Font("Arial",Font.BOLD,18));
		luser_password.setForeground(new Color(0,102,204));
		panel.add(luser_password);
		tuser_password=new JTextField();
		tuser_password.setBounds(250,450,350,30);
		tuser_password.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_password);
		tuser_password.addKeyListener(this);
		
		
		
		
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
		new user_ins();

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
				String sql="select user_id  from tep_user";
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(sql);
				
				if (rs.next())
				{
					
					rs.last();
					String g=rs.getString(1);
					String w=g.substring(10,g.length());
					int x=Integer.parseInt(w);
					if (x<10)
						aid="STU"+y+"X"+"000"+(x+1);
					else if(x>=10 && x<99)
						aid="STU"+y+"X"+"00"+(x+1);
					else if(x>=100 && x<999)
						aid="STU"+y+"X"+"0"+(x+1);
					else
						aid="STU"+y+"X"+(x+1);
				}
				else {
					
					aid="STU"+y+"X"+"0001";
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
				if (tuser_id.getText().length()==0 ||tuser_name.getText().length()==0 ||tuser_dob.getText().length()==0 ||tuser_phone.getText().length()==0 ||tuser_email.getText().length()==0||tuser_add.getText().length()==0||tuser_password.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this,"Check all data first..");
				}
				else
				{
					String id=tuser_id.getText();
					String name=tuser_name.getText();
					String dob=tuser_dob.getText();
					String gender=tuser_gender.getSelectedItem().toString();
					String phone=tuser_phone.getText();
					String email=tuser_email.getText();
					String add=tuser_add.getText();
					String pass=tuser_password.getText();
					
					user_util_data obj=new user_util_data();
					String msg=obj.insertData(id,name,dob,gender,phone,email,add,pass);				
					JOptionPane.showMessageDialog(this, msg);
					//a_idf,a_namef,a_phnf,a_emlf,a_addf;
					tuser_id.setText(getID());
					tuser_name.setText("");
					tuser_dob.setText("");
					tuser_gender.insertItemAt("", 0);
					tuser_phone.setText("");
					tuser_email.setText("");
					tuser_add.setText("");
					tuser_password.setText("");
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
			if(arg0.getSource()==tuser_name)
			{
				regex = "^[\\p{L} .'-]+$";
			      //Creating a pattern object
			      Pattern pattern = Pattern.compile(regex);
			      //Creating a Matcher object
			      Matcher matcher = pattern.matcher(tuser_name.getText());
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
			
			if(arg0.getSource()==tuser_dob)
			{
				regex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
			      //Creating a pattern object
			      Pattern pattern = Pattern.compile(regex);
			      //Creating a Matcher object
			      Matcher matcher = pattern.matcher(tuser_dob.getText());
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
			
			if(arg0.getSource()==tuser_phone)
			{
				regex = "^\\d{10}$";
			      //Creating a pattern object
			      Pattern pattern = Pattern.compile(regex);
			      //Creating a Matcher object
			      Matcher matcher = pattern.matcher(tuser_phone.getText());
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
			
			if(arg0.getSource()==tuser_email)
			{
				regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
			      //Creating a pattern object
			      Pattern pattern = Pattern.compile(regex);
			      //Creating a Matcher object
			      Matcher matcher = pattern.matcher(tuser_email.getText());
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
			
			if(arg0.getSource()==tuser_password)
			{
				regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
			      //Creating a pattern object
			      Pattern pattern = Pattern.compile(regex);
			      //Creating a Matcher object
			      Matcher matcher = pattern.matcher(tuser_password.getText());
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
