package com.ExamPortal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;


import com.util.data.*;



public class admin_update extends JFrame implements ActionListener, ItemListener{
	
	JLabel ladmin_id,ladmin_password,ladmin_name,ladmin_dob,ladmin_gender,ladmin_phone,ladmin_email,ladmin_add;
	JTextField tadmin_password,tadmin_name,tadmin_dob,tadmin_add,tadmin_phone,tadmin_email;
	JButton update,clear;
	JComboBox tadmin_id,tadmin_gender;
	
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
	
	admin_update()
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
	    
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_upd.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("UPDATE",insert2,SwingConstants.CENTER);
	    
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(900,0, 200, 200);
	    add(insert3);
	    image.add(insert3);
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(600,100,800, 800);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
	    ladmin_id=new JLabel("ADMIN ID:");
		ladmin_id.setBounds(100,100,200,30);
		ladmin_id.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_id.setForeground(new Color(0,102,204));
		con.add(ladmin_id);
		tadmin_id=new JComboBox();
		tadmin_id.setBounds(250,100,350,30);
		tadmin_id.insertItemAt("", 0);
		tadmin_id.setFont(new Font("Arial",Font.BOLD,16));
		fillAdminId();
		con.add(tadmin_id);
		tadmin_id.addItemListener(this);
		
		ladmin_name=new JLabel("NAME:");
		ladmin_name.setBounds(100,150,200,30);
		ladmin_name.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_name.setForeground(new Color(0,102,204));
		con.add(ladmin_name);
		tadmin_name=new JTextField();
		tadmin_name.setBounds(250,150,350,30);
		tadmin_name.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_name);
		
		ladmin_dob=new JLabel("DATE OF BIRTH:");
		ladmin_dob.setBounds(100,200,200,30);
		ladmin_dob.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_dob.setForeground(new Color(0,102,204));
		con.add(ladmin_dob);
		tadmin_dob=new JTextField();
		tadmin_dob.setBounds(250,200,350,30);
		tadmin_dob.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_dob);
		
		ladmin_gender=new JLabel("GENDER:");
		ladmin_gender.setBounds(100,250,200,30);
		ladmin_gender.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_gender.setForeground(new Color(0,102,204));
		con.add(ladmin_gender);
		tadmin_gender=new JComboBox();		
		tadmin_gender.setBounds(250,250,350,30);
		tadmin_gender.insertItemAt("", 0);
		tadmin_gender.insertItemAt("MALE", 1);
		tadmin_gender.insertItemAt("FEMALE", 2);
		tadmin_gender.insertItemAt("OTHERS", 3);
		tadmin_gender.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_gender);
		
		ladmin_phone=new JLabel("PHONE:");
		ladmin_phone.setBounds(100,300,200,30);
		ladmin_phone.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_phone.setForeground(new Color(0,102,204));
		con.add(ladmin_phone);
		tadmin_phone=new JTextField();
		tadmin_phone.setBounds(250,300,350,30);
		tadmin_phone.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_phone);
		
		ladmin_email=new JLabel("EMAIL:");
		ladmin_email.setBounds(100,350,200,30);
		ladmin_email.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_email.setForeground(new Color(0,102,204));
		con.add(ladmin_email);
		tadmin_email=new JTextField();
		tadmin_email.setBounds(250,350,350,30);
		tadmin_email.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_email);
		
		ladmin_add=new JLabel("ADDRESS:");
		ladmin_add.setBounds(100,400,200,30);
		ladmin_add.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_add.setForeground(new Color(0,102,204));
		con.add(ladmin_add);
		tadmin_add=new JTextField();
		tadmin_add.setBounds(250,400,350,30);
		tadmin_add.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_add);
		
		
		ladmin_password=new JLabel("PASSWORD:");
		ladmin_password.setBounds(100,450,200,30);
		ladmin_password.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_password.setForeground(new Color(0,102,204));
		con.add(ladmin_password);
		tadmin_password=new JTextField();
		tadmin_password.setBounds(250,450,350,30);
		tadmin_password.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_password);
		
		
		
		
		update=new JButton("UPDATE");
	    update.setBounds(500,550,200,30);
	    update.setFont(new Font("Arial",Font.BOLD,16));
	    update.setBackground(new Color(0,76,153));
		update.setForeground(Color.WHITE);
		con.add(update);
		update.addActionListener(this);
	
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new admin_update();

	}
	
	// METHODS
	
			void fillAdminId()
			{
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					String sql="select admin_id from tep_admin";
					st=con.prepareStatement(sql);
					rs=st.executeQuery();
					while(rs.next())
					{
						tadmin_id.addItem(rs.getString(1));
					}
					
				}
				catch(Exception ex) {}
			}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==update)
		{
			if (tadmin_id.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
					String id=tadmin_id.getSelectedItem().toString();
					String name=tadmin_name.getText();
					String dob=tadmin_dob.getText();
					String gender=tadmin_gender.getSelectedItem().toString();
					String phone=tadmin_phone.getText();
					String email=tadmin_email.getText();
					String add=tadmin_add.getText();
					String pass=tadmin_password.getText();
					
				
				admin_util_data obj=new admin_util_data();
				String msg=obj.updateData(name,dob,gender,phone,email,add,pass,id);				
				JOptionPane.showMessageDialog(this, msg);
				tadmin_id.setSelectedItem("");
				tadmin_name.setText("");
				tadmin_dob.setText("");
				tadmin_gender.setSelectedItem("");
				tadmin_phone.setText("");
				tadmin_email.setText("");
				tadmin_add.setText("");
				tadmin_password.setText("");
				}
			}
		}
		
	}


	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==tadmin_id)
		{
			String k=tadmin_id.getSelectedItem().toString();
			admin_util_data obj=new admin_util_data();
					String s[]=obj.findData(k);
					if (s!=null) {
						tadmin_name.setText(s[0]);
						tadmin_dob.setText(s[1]);
						tadmin_gender.setSelectedItem(s[2]);
						tadmin_phone.setText(s[3]);
						tadmin_email.setText(s[4]);
						tadmin_add.setText(s[5]);
						tadmin_password.setText(s[6]);
					}
						else
						{
							JOptionPane.showMessageDialog(this, "Data not found..");
						}		
					}
			
		}
		

}