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

import javax.swing.*;

import com.ExamPortal.*;


public class admin_login extends JFrame implements ActionListener, KeyListener{
	JLabel ladmin_id,ladmin_password,login,login1,sign,a,b;
	JComboBox tadmin_id;
	JPasswordField tadmin_password;
	JButton loginb,newsign,forgot,back;
	
	//JDBC
	Connection con;PreparedStatement st;ResultSet rs;

	admin_login(){
		setSize(2000,1000);
		setTitle("ADMIN LOGIN");
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
	    logo3.setBounds(0,20, 400, 200);
	    logo3.setForeground(new Color(0,76,153));
	    logo3.setFont(new Font("Arial",Font.BOLD,20));
	    add(logo3);
	    image.add(logo3);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("admin_login.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("ADMIN LOGIN",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(900,150, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(600,200,800, 600);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		ladmin_id=new JLabel("Admin Id:");
		ladmin_id.setBounds(100,200,100,30);
		ladmin_id.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_id.setForeground(new Color(0,76,153));
		con.add(ladmin_id);
		tadmin_id=new JComboBox();
		tadmin_id.setBounds(100,240,350,30);
		tadmin_id.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_id);
		
		
		
		
		ladmin_password=new JLabel("Password:");
		ladmin_password.setBounds(100,300,100,30);
		ladmin_password.setFont(new Font("Arial",Font.BOLD,18));
		ladmin_password.setForeground(new Color(0,76,153));
		con.add(ladmin_password);
		tadmin_password=new JPasswordField();
		tadmin_password.setBounds(100,340,350,30);
		tadmin_password.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tadmin_password);
		filldata();
		
		
		forgot=new JButton("Forgot Password?");
		forgot.setBounds(60,400,200,30);
		forgot.setFont(new Font("Arial",Font.BOLD,14));
		forgot.setContentAreaFilled(false);
	    forgot.setFocusPainted(false);
	    forgot.setBorderPainted(false);
	    forgot.setForeground(Color.BLUE);
		con.add(forgot);
		forgot.addActionListener(this);
		
		loginb=new JButton("Login");
	    loginb.setBounds(200,450,200,30);
	    loginb.setFont(new Font("Arial",Font.BOLD,16));
	    loginb.setBackground(new Color(0,76,153));
		loginb.setForeground(Color.WHITE);
		con.add(loginb);
		loginb.addActionListener(this);
		loginb.addKeyListener(this);
		
		
		newsign=new JButton("Create new account!");
	    newsign.setBounds(50,500,250,30);
	    newsign.setFont(new Font("Arial",Font.BOLD,16));
	    newsign.setContentAreaFilled(false);
	    newsign.setFocusPainted(false);
	    newsign.setBorderPainted(false);
		newsign.setForeground(Color.RED);
		con.add(newsign);
		newsign.addActionListener(this);
		
		
		ImageIcon back1 = new ImageIcon(ClassLoader.getSystemResource("back.png"));
	    Image back2= back1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	    ImageIcon back3 = new ImageIcon(back2);
	    back=new JButton(back3);
	    back.setText("BACK");
		back.setBounds(500,500,300,50);
		back.setFont(new Font("Arial",Font.BOLD,16));
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setForeground(new Color(4,118,208));
		con.add(back);
		back.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new admin_login();

	}
	
	//METHODS
	void filldata()
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
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==loginb)
		{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

				String aid=tadmin_id.getSelectedItem().toString();
				String pas=tadmin_password.getText();
				Statement st=con.createStatement();
				
				String sql="select * from tep_admin where admin_id='"+aid+"'and admin_password='"+pas+"'";
				ResultSet rs=st.executeQuery(sql);
				
				if(tadmin_id.getSelectedItem().toString().length()==0||tadmin_password.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Fill all the entries First");
				}
				if (rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login successfully");
				    
				    admin_dashboard d=new admin_dashboard();
				    String z=rs.getString("admin_name");
				    char y=z.charAt(0);
				    d.id.setText(tadmin_id.getSelectedItem().toString());
				    d.label.setText(Character.toString(y));
				    d.name.setText(rs.getString("admin_name"));
				    d.email.setText(rs.getString("admin_email"));
				    d.phone.setText("+91 "+rs.getString("admin_phone"));
				    d.gender.setText(rs.getString("admin_gender"));
				    
				    if(rs.getString("status").equals("1"))
				    {
				    	d.status.setText("Active");
				    }
				    else
				    {
				    	d.status.setText("Inactive");
				    }
				    d.setVisible(true);
				    
					
				}
				else
					JOptionPane.showMessageDialog(null, "Login Failed");
				con.close();
			}
			catch(Exception ex) {
			}
			
			
		}
		
		if(ae.getSource()==(newsign))
		{
			new admin_ins();
		}
		if(ae.getSource()==(forgot))
		{
			new admin_find();
		}
		if(ae.getSource()==(back))
		{
			this.dispose();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tadmin_id)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				tadmin_password.requestFocus();
			}
		}
		
		if(e.getSource()==tadmin_password)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

					String aid=tadmin_id.getSelectedItem().toString();
					String pas=tadmin_password.getText();
					Statement st=con.createStatement();
					
					String sql="select * from tep_admin where admin_id='"+aid+"'and admin_password='"+pas+"'";
					ResultSet rs=st.executeQuery(sql);
					
					if(tadmin_id.getSelectedItem().toString().length()==0||tadmin_password.getText().length()==0)
					{
						JOptionPane.showMessageDialog(this, "Fill all the entries First");
					}
					if (rs.next())
					{
						JOptionPane.showMessageDialog(null, "Login successfully");
					    
					    admin_dashboard d=new admin_dashboard();
					    String z=rs.getString("admin_name");
					    char y=z.charAt(0);
					    d.id.setText(tadmin_id.getSelectedItem().toString());
					    d.label.setText(Character.toString(y));
					    d.name.setText(rs.getString("admin_name"));
					    d.email.setText(rs.getString("admin_email"));
					    d.phone.setText("+91 "+rs.getString("admin_phone"));
					    d.gender.setText(rs.getString("admin_gender"));
					    
					    if(rs.getString("status").equals("1"))
					    {
					    	d.status.setText("Active");
					    }
					    else
					    {
					    	d.status.setText("Inactive");
					    }
					    d.setVisible(true);
					    
						
					}
					else
						JOptionPane.showMessageDialog(null, "Login Failed");
					con.close();
				}
				catch(Exception ex) {
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
