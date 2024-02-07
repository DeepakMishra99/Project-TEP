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

import com.util.data.admin_util_data;
import com.util.data.examschedule_util_data;

public class exam_schedule_del extends JFrame implements ItemListener, ActionListener {
	JLabel lschedule_id,lexam_id,lexam_date,lexam_time;
	JTextField texam_id,texam_date,texam_time;
	JButton delete,clear;
	JComboBox  tschedule_id ;
	
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
	
	exam_schedule_del()
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("schedule.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("EXAM SCHEDULE",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(0,20, 300, 200);
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
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(600,100,800, 600);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		lschedule_id=new JLabel("EXAM SCHEDULE ID:");
		lschedule_id.setBounds(100,100,200,30);
		lschedule_id.setFont(new Font("Arial",Font.BOLD,18));
		lschedule_id.setForeground(new Color(0,102,204));
		con.add(lschedule_id);
		tschedule_id=new JComboBox();
		tschedule_id.setBounds(100,150,350,30);
		tschedule_id.insertItemAt("", 0);
		tschedule_id.setFont(new Font("Arial",Font.BOLD,16));
		fillScheduleId();
		con.add(tschedule_id);
		tschedule_id.addItemListener(this);
		
		lexam_id=new JLabel("EXAM ID:");
		lexam_id.setBounds(100,200,200,30);
		lexam_id.setFont(new Font("Arial",Font.BOLD,18));
		lexam_id.setForeground(new Color(0,102,204));
		con.add(lexam_id);
		texam_id=new JTextField();
		texam_id.setBounds(100,250,350,30);
		texam_id.setFont(new Font("Arial",Font.BOLD,16));
		con.add(texam_id);
		
		lexam_date=new JLabel("EXAM DATE:");
		lexam_date.setBounds(100,300,200,30);
		lexam_date.setFont(new Font("Arial",Font.BOLD,18));
		lexam_date.setForeground(new Color(0,102,204));
		con.add(lexam_date);
		texam_date=new JTextField();
		texam_date.setBounds(100,350,350,30);
		texam_date.setFont(new Font("Arial",Font.BOLD,16));
		con.add(texam_date);
		
		lexam_time=new JLabel("EXAM TIME:");
		lexam_time.setBounds(100,400,200,30);
		lexam_time.setFont(new Font("Arial",Font.BOLD,18));
		lexam_time.setForeground(new Color(0,102,204));
		con.add(		lexam_time);
		texam_time=new JTextField();
		texam_time.setBounds(100,450,350,30);
		texam_time.setFont(new Font("Arial",Font.BOLD,16));
		con.add(texam_time);
		
		
		
		
		
		
		delete=new JButton("DELETE");
	    delete.setBounds(500,540,200,30);
	    delete.setFont(new Font("Arial",Font.BOLD,16));
	    delete.setBackground(new Color(0,76,153));
		delete.setForeground(Color.WHITE);
		con.add(delete);
		delete.addActionListener(this);
		
		
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new exam_schedule_del();

	}
// METHODS	
	void fillScheduleId()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select schedule_id  from tep_examschedule";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				tschedule_id.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	
	
@Override
	
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==tschedule_id)
		{
			String k=tschedule_id.getSelectedItem().toString();
			examschedule_util_data obj=new examschedule_util_data();
					String s[]=obj.findData(k);
					if (s!=null) {
						texam_id.setText(s[1]);
						texam_date.setText(s[2]);
						texam_time.setText(s[3]);
						
					}
						else
						{
							JOptionPane.showMessageDialog(this, "Data not found..");
						}		
					}
			
		}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	if(arg0.getSource()==delete)
	{
		if (tschedule_id.getSelectedItem().toString().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Check all Data first");
		}
		else
		{
			int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
			if(opt==0)
			{
			String id=tschedule_id.getSelectedItem().toString();
			
			
			examschedule_util_data obj=new examschedule_util_data();
			String msg=obj.deleteData(id);				
			JOptionPane.showMessageDialog(this, msg);
			
			}
		}
	}
	
}
		

}