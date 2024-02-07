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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.ExamPortal.exam;;

public class instruction extends JFrame implements ActionListener {
	JLabel l1,l2,l3,l4,l5,ldummy1,ldummy2;
	
	JButton ok;
	//JDBC
		static Connection con;static PreparedStatement st;static ResultSet rs;

		instruction(){
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("instruction.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("INSTRUCTIONS",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(900,100, 200, 200);
	    add(image1);
	    image.add(image1);
	    
	    JPanel panel = new JPanel(); 
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(600,200,800, 600);
	    panel.setLayout(null);
	    add(panel);
	    image.add(panel);
	   	
	    l1=new JLabel("1) Read all Question carefully.");
		l1.setBounds(100,150,800,30);
		l1.setFont(new Font("Arial",Font.BOLD,18));
		l1.setForeground(new Color(0,102,204));
		panel.add(l1);
		
		l2=new JLabel("2) Complete the exam before given time.");
		l2.setBounds(100,200,800,30);
		l2.setFont(new Font("Arial",Font.BOLD,18));
		l2.setForeground(new Color(0,102,204));
		panel.add(l2);
		
		l3=new JLabel("3) If you save the question then there is no correction.");
		l3.setBounds(100,250,800,30);
		l3.setFont(new Font("Arial",Font.BOLD,18));
		l3.setForeground(new Color(0,102,204));
		panel.add(l3);
		
		
		l4=new JLabel("4) Use next and previous button to go next and previous question.");
		l4.setBounds(100,300,800,30);
		l4.setFont(new Font("Arial",Font.BOLD,18));
		l4.setForeground(new Color(0,102,204));
		panel.add(l4);
		
		l5=new JLabel("5) If you submit the exam then there will no change.");
		l5.setBounds(100,350,800,30);
		l5.setFont(new Font("Arial",Font.BOLD,18));
		l5.setForeground(new Color(0,102,204));
		panel.add(l5);
		
		ldummy1=new JLabel();
		ldummy1.setBounds(0,0,0,0);
		ldummy1.setFont(new Font("Arial",Font.BOLD,18));
		ldummy1.setForeground(new Color(0,102,204));
		add(ldummy1);
		ldummy2=new JLabel();
		ldummy2.setBounds(0,0,0,0);
		ldummy2.setFont(new Font("Arial",Font.BOLD,18));
		ldummy2.setForeground(new Color(0,102,204));
		add(ldummy2);
		
		ok=new JButton("OK");
	    ok.setBounds(320,500,100,30);
	    ok.setFont(new Font("Arial",Font.BOLD,16));
	    ok.setBackground(new Color(255,153,153));
		ok.setForeground(new Color(204,0,0));
		panel.add(ok);
		ok.addActionListener(this);
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new instruction();
			
			

		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==ok)
			{	
				
				exam d=new exam();
					try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
						String stat="1";
						Statement st=con.createStatement();
						String sql="select * from tep_exampaper where status='"+stat+"'";
						ResultSet rs=st.executeQuery(sql);
						
						
						if (rs.next())
						{
												    
						   
						    d.tstudent_id.setText(ldummy1.getText());
						    d.tstudent_name.setText(ldummy2.getText());
						   
						    
						    
						  
						    
						   
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
