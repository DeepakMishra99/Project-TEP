
package com.ExamPortal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import com.util.data.user_util_data;

public class user_login extends JFrame implements ActionListener, KeyListener {
	JLabel user_id,user_password,login,login1,sign,a,b;
	JComboBox tuser_id;
	JPasswordField tuser_password;
	JButton loginb,newsign,forgot,back;
	//JDBC
		Connection con;PreparedStatement st;ResultSet rs;

	user_login(){
		setSize(2000,1000);
		setTitle("STUDENT LOGIN");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("student_login.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("STUDENT LOGIN",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(900,150, 200, 200);
	    add(image1);
	    image.add(image1);
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(600,200,800, 600);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		user_id=new JLabel("Student Id:");
		user_id.setBounds(100,200,100,30);
		user_id.setFont(new Font("Arial",Font.BOLD,18));
		user_id.setForeground(new Color(0,76,153));
		con.add(user_id);
		tuser_id=new JComboBox();
		tuser_id.setBounds(100,240,350,30);
		tuser_id.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tuser_id);
		filldata();
		tuser_id.addKeyListener(this);
		
		
		
		
		user_password=new JLabel("Password:");
		user_password.setBounds(100,300,100,30);
		user_password.setFont(new Font("Arial",Font.BOLD,18));
		user_password.setForeground(new Color(0,76,153));
		con.add(user_password);
		tuser_password=new JPasswordField();
		tuser_password.setBounds(100,340,350,30);
		tuser_password.setFont(new Font("Arial",Font.BOLD,16));
		con.add(tuser_password);
		tuser_id.addKeyListener(this);
		
		
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
		new user_login();

	}

	//METHODS
		void filldata()
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

		@Override
		public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub
			if(ae.getSource()==loginb)
			{
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

					String aid=tuser_id.getSelectedItem().toString();
					String pas=tuser_password.getText();
					Statement st=con.createStatement();
					
					String sql="select * from tep_user where user_id='"+aid+"'and user_password='"+pas+"'";
					ResultSet rs=st.executeQuery(sql);
					
					if(tuser_id.getSelectedItem().toString().length()==0||tuser_password.getText().length()==0)
					{
						JOptionPane.showMessageDialog(this, "Fill all the entries First");
					}
					if (rs.next())
					{
						JOptionPane.showMessageDialog(null, "Login successfully");
					    
					    user_dashboard d=new user_dashboard();
					    
					    d.id.setText(tuser_id.getSelectedItem().toString());
					   
					    d.name.setText(rs.getString("user_name"));
					    d.email.setText(rs.getString("user_email"));
					    d.phone.setText("+91 "+rs.getString("user_phone"));
					    d.gender.setText(rs.getString("user_gender"));
					    d.dob.setText(rs.getString("user_dob"));
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
				new user_ins();
			}
			if(ae.getSource()==(forgot))
			{
				new user_find();
			}
			if(ae.getSource()==(back))
			{
				this.dispose();
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==tuser_id)
			{
				if(e.getKeyCode()==e.VK_ENTER)
				{
					tuser_password.requestFocus();
				}
			}
			
			if(e.getSource()==tuser_password)
			{
				if(e.getKeyCode()==e.VK_ENTER)
				{
					try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

						String aid=tuser_id.getSelectedItem().toString();
						String pas=tuser_password.getText();
						Statement st=con.createStatement();
						
						String sql="select * from tep_user where user_id='"+aid+"'and user_password='"+pas+"'";
						ResultSet rs=st.executeQuery(sql);
						
						if(tuser_id.getSelectedItem().toString().length()==0||tuser_password.getText().length()==0)
						{
							JOptionPane.showMessageDialog(this, "Fill all the entries First");
						}
						if (rs.next())
						{
							JOptionPane.showMessageDialog(null, "Login successfully");
						    
						    user_dashboard d=new user_dashboard();
						    
						    
						    d.id.setText(tuser_id.getSelectedItem().toString());
						    
						    d.name.setText(rs.getString("user_name"));
						    d.email.setText(rs.getString("user_email"));
						    d.phone.setText("+91 "+rs.getString("user_phone"));
						    d.gender.setText(rs.getString("user_gender"));
						    d.dob.setText(rs.getString("user_dob"));
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
