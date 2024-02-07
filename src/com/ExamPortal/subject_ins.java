package com.ExamPortal;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.util.data.subject_util_data;
import com.util.data.user_util_data;

public class subject_ins extends JFrame implements ActionListener {
	JLabel lsub_id,ladmin_password,lsub_name,ladmin_dob,ladmin_phone,ladmin_email,ladmin_add;
	JTextField tsub_id,tadmin_password,tsub_name,tadmin_dob,tadmin_add,tadmin_phone,tadmin_email;
	JButton insert;
	
	//JDBC
		Connection con;PreparedStatement st;
	
	subject_ins()
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("subject.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("SUBJECT",i3,SwingConstants.CENTER);
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
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(600,100,800, 600);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		lsub_id=new JLabel("SUBJECT ID:");
		lsub_id.setBounds(100,150,200,30);
		lsub_id.setFont(new Font("Arial",Font.BOLD,18));
		lsub_id.setForeground(new Color(0,102,204));
		con.add(lsub_id);
		tsub_id=new JTextField();
		tsub_id.setText(getID());
		tsub_id.setBounds(100,200,350,30);
		tsub_id.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tsub_id);
		
		lsub_name=new JLabel("SUBJECT NAME:");
		lsub_name.setBounds(100,300,200,30);
		lsub_name.setFont(new Font("Arial",Font.BOLD,18));
		lsub_name.setForeground(new Color(0,102,204));
		con.add(lsub_name);
		tsub_name=new JTextField();
		tsub_name.setBounds(100,350,350,30);
		tsub_name.setFont(new Font("Arial",Font.BOLD,16));
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
		new subject_ins();

	}
	
	// METHODS
	
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
			String sql="select subject_id  from tep_subject";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			
			if (rs.next())
			{
				
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(10,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					aid="SUB"+y+"X"+"000"+(x+1);
				else if(x>=10 && x<99)
					aid="SUB"+y+"X"+"00"+(x+1);
				else if(x>=100 && x<999)
					aid="SUB"+y+"X"+"0"+(x+1);
				else
					aid="SUB"+y+"X"+(x+1);
			}
			else {
				
				aid="SUB"+y+"X"+"0001";
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
			if (tsub_id.getText().length()==0 ||tsub_name.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String id=tsub_id.getText();
				String name=tsub_name.getText();
				
				
				subject_util_data obj=new subject_util_data();
				String msg=obj.insertData(id,name);				
				JOptionPane.showMessageDialog(this, msg);
				//a_idf,a_namef,a_phnf,a_emlf,a_addf;
				tsub_id.setText(getID());
				tsub_name.setText("");
			
			}
	
		
	}
		
	}
}