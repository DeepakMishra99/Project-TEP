package com.ExamPortal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.util.data.admin_util_data;
import com.util.data.user_util_data;

public class user_del extends JFrame implements ActionListener, ItemListener {
	
	JLabel luser_id,luser_password,luser_name,luser_gender,luser_dob,luser_phone,luser_email,luser_add;
	JTextField tuser_password,tuser_name,tuser_gender,tuser_dob,tuser_add,tuser_phone,tuser_email;
	JButton delete,clear;
	JComboBox tuser_id;
	JPanel panel;
	//JDBC variables
			Connection con;PreparedStatement st;ResultSet rs;
	
	user_del()
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
	    
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_del.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("DELETE",insert2,SwingConstants.CENTER);
	    
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
		tuser_id=new JComboBox();
		tuser_id.setBounds(250,100,350,30);
		tuser_id.insertItemAt("", 0);
		tuser_id.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_id);
		fillUserId();
		tuser_id.addItemListener(this);
		
		
		luser_name=new JLabel("NAME:");
		luser_name.setBounds(100,150,200,30);
		luser_name.setFont(new Font("Arial",Font.BOLD,18));
		luser_name.setForeground(new Color(0,102,204));
		panel.add(luser_name);
		tuser_name=new JTextField();
		tuser_name.setBounds(250,150,350,30);
		tuser_name.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_name);
		
		
		luser_dob=new JLabel("DATE OF BIRTH:");
		luser_dob.setBounds(100,200,200,30);
		luser_dob.setFont(new Font("Arial",Font.BOLD,18));
		luser_dob.setForeground(new Color(0,102,204));
		panel.add(luser_dob);
		tuser_dob=new JTextField();
		tuser_dob.setBounds(250,200,350,30);
		tuser_dob.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_dob);
		
		luser_gender=new JLabel("GENDER:");
		luser_gender.setBounds(100,250,200,30);
		luser_gender.setFont(new Font("Arial",Font.BOLD,18));
		luser_gender.setForeground(new Color(0,102,204));
		panel.add(luser_gender);
		tuser_gender=new JTextField();
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
		
		luser_email=new JLabel("EMAIL:");
		luser_email.setBounds(100,350,200,30);
		luser_email.setFont(new Font("Arial",Font.BOLD,18));
		luser_email.setForeground(new Color(0,102,204));
		panel.add(luser_email);
		tuser_email=new JTextField();
		tuser_email.setBounds(250,350,350,30);
		tuser_email.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_email);
		
		luser_add=new JLabel("ADDRESS:");
		luser_add.setBounds(100,400,200,30);
		luser_add.setFont(new Font("Arial",Font.BOLD,18));
		luser_add.setForeground(new Color(0,102,204));
		panel.add(luser_add);
		tuser_add=new JTextField();
		tuser_add.setBounds(250,400,350,30);
		tuser_add.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tuser_add);

		
		
		
		
		delete=new JButton("DELETE");
	    delete.setBounds(500,550,200,30);
	    delete.setFont(new Font("Arial",Font.BOLD,16));
	    delete.setBackground(new Color(0,76,153));
		delete.setForeground(Color.WHITE);
		panel.add(delete);
		delete.addActionListener(this);
		
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new user_del();
		

	}
	
	
	// METHODS
	
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==delete)
		{
			if (tuser_id.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all Data first");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String id=tuser_id.getSelectedItem().toString();
				
				
				user_util_data obj=new user_util_data();
				String msg=obj.deleteData(id);				
				JOptionPane.showMessageDialog(this, msg);
				tuser_id.getSelectedItem().toString();
				tuser_name.setText("");
				tuser_dob.setText("");
				tuser_gender.setText("");
				tuser_phone.setText("");
				tuser_email.setText("");
				tuser_add.setText("");
				}
			}
		}
		
	}
		
	


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tuser_id)
		{
			String k=tuser_id.getSelectedItem().toString();
			user_util_data obj=new user_util_data();
					String s[]=obj.findData(k);
					//a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf
					if (s!=null) {
						tuser_name.setText(s[0]);
						tuser_dob.setText(s[1]);
						tuser_gender.setText(s[2]);
						tuser_phone.setText(s[3]);
						tuser_email.setText(s[4]);
						tuser_add.setText(s[5]);
					}
						else
						{
							JOptionPane.showMessageDialog(this, "Data not found..");
						}		
					}
		
	}


}
