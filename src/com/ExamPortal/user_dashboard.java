package com.ExamPortal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.data.user_util_data;

public class user_dashboard extends JFrame implements ActionListener{
	JLabel lstudent_id,lstudent_name,lemail,lphone,lgender,ldob,lstatus;
	JLabel id,name,email,phone,gender,dob,status;
	JPanel panel2,panel3,panel4;
	JButton exam,result,manage,logout,show;
	JComboBox subject,texampaper_id;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
		
		JTable table;int i=0;int j=0;
		
		DefaultTableModel model = new DefaultTableModel();	
	
		user_dashboard()
	{
		setSize(2000,1000);
		setTitle("ADMIN");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		//BACKGROUND 
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("welcome_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(2000, 1000, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 2000, 1000);
	    add(image);
	    
	    //TEP LOGO 
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
	    
	    //EXAMPAPER LOGO 
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
	    
	    //EXAM LOGO 
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("examicon.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    
	  //EXAM LOGO 
	    ImageIcon insert_logo1 = new ImageIcon(ClassLoader.getSystemResource("resulticon.png"));
	    Image insert3 = insert_logo1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon insert4 = new ImageIcon(insert3);
	    
	    
	    // WHITE PANEL
	    JPanel panel1 = new JPanel(); 
	    panel1.setBackground(Color.WHITE);
	    panel1.setBounds(300,50,1300, 400);
	    panel1.setLayout(null);
	    add(panel1);
	    image.add(panel1);
	    
	    panel2 = new JPanel(); 
	    panel2.setBackground(new Color(6,85,147));
	    panel2.setBounds(400,500,300, 300);
	    panel2.setLayout(new BorderLayout());
	    add(panel2);
	    image.add(panel2);
	    
	    panel3 = new JPanel(); 
	    panel3.setBackground(new Color(6,85,147));
	    panel3.setBounds(1000,500,300, 300);
	    panel3.setLayout(new BorderLayout());
	    add(panel3);
	    image.add(panel3);
	    
	    panel4 = new JPanel(); 
	    panel4.setBackground(new Color(6,85,147));
	    panel4.setBounds(1600,50,200, 200);
	    panel4.setLayout(new BorderLayout());
	    add(panel4);
	    image.add(panel4);
	   
	    
	    //EXAM-PAPER ID
	    lstudent_id=new JLabel("STUDENT ID:");
	    lstudent_id.setBounds(50, 50, 200, 30);
	    lstudent_id.setFont(new Font("Arial",Font.BOLD,18));
	    lstudent_id.setForeground(new Color(0,102,204));
		add(lstudent_id);
		panel1.add(lstudent_id);
		
		id=new JLabel();
		id.setBounds(250,50,200,30);
		id.setForeground(new Color(0,0,0));
		id.setFont(new Font("Arial",Font.BOLD,18));
		add(id);
		panel1.add(id);
		
		lstudent_name=new JLabel("STUDENT NAME:");
	    lstudent_name.setBounds(50, 100, 200, 30);
	    lstudent_name.setFont(new Font("Arial",Font.BOLD,18));
	    lstudent_name.setForeground(new Color(0,102,204));
		add(lstudent_name);
		panel1.add(lstudent_name);
		name=new JLabel();
		name.setBounds(250,100,200,30);
		name.setForeground(new Color(0,0,0));
		name.setFont(new Font("Arial",Font.BOLD,18));
		add(name);
		panel1.add(name);
		
		lemail=new JLabel("EMAIL:");
	    lemail.setBounds(50, 150, 200, 30);
	    lemail.setFont(new Font("Arial",Font.BOLD,18));
	    lemail.setForeground(new Color(0,102,204));
		add(lemail);
		panel1.add(lemail);
		email=new JLabel();
		email.setBounds(250,150,400,30);
		email.setForeground(new Color(0,0,0));
		email.setFont(new Font("Arial",Font.BOLD,18));
		add(email);
		panel1.add(email);
		
		lphone=new JLabel("PHONE:");
	    lphone.setBounds(50, 200, 200, 30);
	    lphone.setFont(new Font("Arial",Font.BOLD,18));
	    lphone.setForeground(new Color(0,102,204));
		add(lphone);
		panel1.add(lphone);
		phone=new JLabel();
		phone.setBounds(250,200,300,30);
		phone.setForeground(new Color(0,0,0));
		phone.setFont(new Font("Arial",Font.BOLD,18));
		add(phone);
		panel1.add(phone);
		
		
		lgender=new JLabel("GENDER:");
	    lgender.setBounds(50, 300, 200, 30);
	    lgender.setFont(new Font("Arial",Font.BOLD,18));
	    lgender.setForeground(new Color(0,102,204));
		add(lgender);
		panel1.add(lgender);
		gender=new JLabel();
		gender.setBounds(250,300,200,30);
		gender.setForeground(new Color(0,0,0));
		gender.setFont(new Font("Arial",Font.BOLD,18));
		add(gender);
		panel1.add(gender);
		
		ldob=new JLabel("DOB:");
	    ldob.setBounds(50, 250, 200, 30);
	    ldob.setFont(new Font("Arial",Font.BOLD,18));
	    ldob.setForeground(new Color(0,102,204));
		add(ldob);
		panel1.add(ldob);
		
		dob=new JLabel();
		dob.setBounds(250,250,200,30);
		dob.setForeground(new Color(0,0,0));
		dob.setFont(new Font("Arial",Font.BOLD,18));
		add(dob);
		panel1.add(dob);
		
		
		lstatus=new JLabel("STATUS:");
		lstatus.setBounds(50, 350, 200, 30);
		lstatus.setFont(new Font("Arial",Font.BOLD,18));
		lstatus.setForeground(new Color(0,102,204));
		add(lstatus);
		panel1.add(lstatus);
		
		status=new JLabel();
		status.setBounds(250,350,200,30);
		status.setForeground(new Color(0,0,0));
		status.setFont(new Font("Arial",Font.BOLD,18));
		add(status);
		panel1.add(status);
		
		
		
		// SUBJECT
	   
		manage=new JButton("MANAGE PROFILE");
		manage.setBounds(700, 350, 200, 30);
		manage.setFont(new Font("Arial",Font.BOLD,16));
		manage.setBackground(new Color(255,102,102));
		manage.setForeground(Color.WHITE);
		add(manage);
		panel1.add(manage);
		manage.addActionListener(this);
		
		logout=new JButton("LOGOUT");
		logout.setBounds(1000, 350, 200, 30);
		logout.setFont(new Font("Arial",Font.BOLD,16));
		logout.setBackground(new Color(255,102,102));
		logout.setForeground(Color.WHITE);
		add(logout);
		panel1.add(logout);
		logout.addActionListener(this);
		
		show=new JButton("SHOW SUBJECT");
		show.setBounds(1000, 10, 200, 30);
		show.setFont(new Font("Arial",Font.BOLD,16));
		show.setBackground(new Color(255,102,102));
		show.setForeground(Color.WHITE);
		add(show);
		panel1.add(show);
		show.addActionListener(this);
		
		//panel 2
		
		
		exam=new JButton("EXAMINATION",insert2);
		exam.setBounds(100, 10, 30, 30);
		exam.setFont(new Font("Arial",Font.BOLD,20));
	    exam.setBackground(new Color(6,85,147));
	    exam.setVerticalTextPosition(JButton.BOTTOM);
	    exam.setHorizontalTextPosition(JButton.CENTER);
		exam.setForeground(Color.WHITE);
		add(exam);
		panel2.add(exam);
		exam.addActionListener(this);
		
		result=new JButton("RESULT",insert4);
		result.setBounds(300, 50, 30, 30);
		result.setFont(new Font("Arial",Font.BOLD,20));
	    result.setBackground(new Color(6,85,147));
	    result.setVerticalTextPosition(JButton.BOTTOM);
	    result.setHorizontalTextPosition(JButton.CENTER);
		result.setForeground(Color.WHITE);
		add(result);
		panel3.add(result);
		result.addActionListener(this);
	
		//panel4
		JTable table = new JTable();
		 table.setFont(new Font("Arial",Font.BOLD,14));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setBackground(new Color(153, 204, 255));
			table.setForeground(new Color(0,76,153));
			
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(0,76,153));
			header.setForeground(new Color(255,255,255));
			header.setFont(new Font("Arial",Font.BOLD,16));
			
	        
			
			model.addColumn("SUBJECT");
	       
	        table.setModel(model);
	       
	        // Add the table to the panel
	       panel4.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new user_dashboard();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==manage)
		{
			new user_update();
		}
		
		if(e.getSource()==logout)
		{
			JOptionPane.showMessageDialog(null, "Logout successfully");
			this.dispose();
		}
		
		if(e.getSource()==exam)
		{
			 instruction d=new instruction();
			
				   
				    d.ldummy1.setText(id.getText());
				    d.ldummy2.setText(name.getText());
					
			
		}
		
		if(e.getSource()==result)
		{
			result d=new result();
			
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

					
					String userid=id.getText();
					Statement st=con.createStatement();
					
					String sql="select * from tep_user where user_id='"+userid+"'";
					ResultSet rs=st.executeQuery(sql);
					
					
					if (rs.next())
					{
											    
					   d.id.setText(rs.getString("user_id"));
					   d.name.setText(rs.getString("user_name"));
					   d.email.setText(rs.getString("user_email"));
					   d.phone.setText(rs.getString("user_phone"));
					   d.dob.setText(rs.getString("user_dob"));
					   d.gender.setText(rs.getString("user_gender"));
					   d.address.setText(rs.getString("user_address"));
					   
					   d.setVisible(true);
					    
						
					}
					else
						JOptionPane.showMessageDialog(null, "Something Error.");
					
					
					con.close();
				}
				catch(Exception ex) {
				}
				
				try {
					String sid=id.getText();
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					String sql="select exampaper_id from tep_result where user_id=?";
					st=con.prepareStatement(sql);
					st.setString(1, sid);
					rs=st.executeQuery();
					while(rs.next())
					{
						d.texampaper_id.addItem(rs.getString(1));
					}
					
				}
				catch(Exception ex) {}
		}
		
		if(e.getSource()==show)
		{
			String a =id.getText() ;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				
				String sql="select subject_id from tep_studentsubject where user_id=? and status=1";
				st=con.prepareStatement(sql);
				
				st.setString(1, a);
				rs=st.executeQuery();
				
				while(rs.next())
				{
					
					model.addRow(new Object[] {rs.getString(1)});
					
				}
				
				con.close();
			}
			catch(Exception ex) {}
		}
		
	}
	

}
