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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.util.data.subject_util_data;
import com.util.data.user_util_data;

public class subject_update extends JFrame implements ItemListener, ActionListener {
	JLabel lsub_id,lsub_name;
	JTextField tsub_name;
	JButton update,clear;
	JComboBox tsub_id;
	JPanel panel;
	
	//JDBC variables
			Connection con;PreparedStatement st;ResultSet rs;
	
	subject_update()
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
	    
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_upd.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("UPDATE",insert2,SwingConstants.CENTER);
	    
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
	   	
		lsub_id=new JLabel("SUBJECT ID:");
		lsub_id.setBounds(100,150,200,30);
		lsub_id.setFont(new Font("Arial",Font.BOLD,18));
		lsub_id.setForeground(new Color(0,102,204));
		panel.add(lsub_id);
		tsub_id=new JComboBox();
		tsub_id.insertItemAt("", 0);
		tsub_id.setBounds(100,200,350,30);
		tsub_id.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tsub_id);
		fillSubjectId();
		tsub_id.addItemListener(this);
		
		lsub_name=new JLabel("SUBJECT NAME:");
		lsub_name.setBounds(100,300,200,30);
		lsub_name.setFont(new Font("Arial",Font.BOLD,18));
		lsub_name.setForeground(new Color(0,102,204));
		panel.add(lsub_name);
		tsub_name=new JTextField();
		tsub_name.setBounds(100,350,350,30);
		tsub_name.setFont(new Font("Arial",Font.BOLD,16));
		panel.add(tsub_name);
		
		
		
		
		
		
		update=new JButton("UPDATE");
	    update.setBounds(500,540,200,30);
	    update.setFont(new Font("Arial",Font.BOLD,16));
	    update.setBackground(new Color(0,76,153));
		update.setForeground(Color.WHITE);
		panel.add(update);
		update.addActionListener(this);
		
		
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new subject_update();

	}


	// METHODS
	
				void fillSubjectId()
				{
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
						String sql="select subject_id from tep_subject";
						st=con.prepareStatement(sql);
						rs=st.executeQuery();
						while(rs.next())
						{
							tsub_id.addItem(rs.getString(1));
						}
						
					}
					catch(Exception ex) {}
				}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==tsub_id)
		{
			String k=tsub_id.getSelectedItem().toString();
			subject_util_data obj=new subject_util_data();
					String s[]=obj.findData(k);
					
					if (s!=null) {
						tsub_name.setText(s[0]);
						
					}
						else
						{
							JOptionPane.showMessageDialog(this, "Data not found..");
						}		
					}
			
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==update)
		{
			if (tsub_id.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
					String id=tsub_id.getSelectedItem().toString();
					String name=tsub_name.getText();
					
					
				
				subject_util_data obj=new subject_util_data();
				String msg=obj.updateData(name,id);				
				JOptionPane.showMessageDialog(this, msg);
				
				
				}
			}
		}
		
	}

		
	
}