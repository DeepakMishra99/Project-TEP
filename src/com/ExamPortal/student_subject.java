package com.ExamPortal;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;


import com.util.data.subject_util_data;
import com.util.data.user_util_data;

public class student_subject extends JFrame implements ActionListener {
	JLabel luser_id,lsub_name;
	JComboBox tuser_id,tsub_name;
	JButton insert;
	
	//JDBC
		Connection con;PreparedStatement st;ResultSet rs;
	
		student_subject()
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
	    
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("subject.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("STUDENT SUBJECT",insert2,SwingConstants.CENTER);
	    
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(800,0, 400, 200);
	    add(insert3);
	    image.add(insert3);
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(600,100,800, 600);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		luser_id=new JLabel("STUDENT ID:");
		luser_id.setBounds(100,150,200,30);
		luser_id.setFont(new Font("Arial",Font.BOLD,18));
		luser_id.setForeground(new Color(0,102,204));
		con.add(luser_id);
		tuser_id=new JComboBox();
		tuser_id.setBounds(100,200,350,30);
		tuser_id.setFont(new Font("Arial",Font.BOLD,16));
		fillUserId();
		con.add(tuser_id);
		
		lsub_name=new JLabel("SUBJECT NAME:");
		lsub_name.setBounds(100,300,200,30);
		lsub_name.setFont(new Font("Arial",Font.BOLD,18));
		lsub_name.setForeground(new Color(0,102,204));
		con.add(lsub_name);
		tsub_name=new JComboBox();
		tsub_name.setBounds(100,350,350,30);
		tsub_name.setFont(new Font("Arial",Font.BOLD,16));
		fillSubjectName();
		con.add(tsub_name);
		
		
		
		
		
		
		insert=new JButton("INSERT");
	    insert.setBounds(500,540,200,30);
	    insert.setFont(new Font("Arial",Font.BOLD,16));
	    insert.setBackground(new Color(0,76,153));
		insert.setForeground(Color.WHITE);
		con.add(insert);
		insert.addActionListener(this);
		
		
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new student_subject();

	}

	// methods
	
	void fillUserId()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select user_id from tep_user";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				tuser_id.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	
	void fillSubjectName()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select subject_name from tep_subject";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				tsub_name.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==insert)
		{
			if (tuser_id.getSelectedItem().toString().length()==0 ||tsub_name.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String id=tuser_id.getSelectedItem().toString();
				String name=tsub_name.getSelectedItem().toString();
				
				
				subject_util_data obj=new subject_util_data();
				String msg=obj.insertStudentSub(id,name);				
				JOptionPane.showMessageDialog(this, msg);
				//a_idf,a_namef,a_phnf,a_emlf,a_addf;
				
			
			}
	
		
	}
		
	}
}
