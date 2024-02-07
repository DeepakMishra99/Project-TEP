package com.ExamPortal;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.data.exampaper_util_data;


public class exampaper_status extends JFrame implements ActionListener,ItemListener {
	
	JLabel lexampaper_id,lstatus,title;
	JPanel panel2;
	JButton show,update;
	JComboBox tstatus,texampaper_id;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
		
		JTable table;int i=0;int j=0;
				
		DefaultTableModel model = new DefaultTableModel();
	
	exampaper_status()
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
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("qa.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("EXAM PAPER",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(20,20, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    //INSERT LOGO 
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_del.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("EXAM-PAPER STATUS",insert2,SwingConstants.CENTER);
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(900,0, 400, 100);
	    add(insert3);
	    image.add(insert3);
	    
	    // WHITE PANEL
	    JPanel panel1 = new JPanel(); 
	    panel1.setBackground(Color.WHITE);
	    panel1.setBounds(700,200,700, 500);
	    panel1.setLayout(null);
	    add(panel1);
	    image.add(panel1);
	    
	    
	    
	    
	    //EXAM-PAPER ID
	    lexampaper_id=new JLabel("EXAM-PAPER ID:");
	    lexampaper_id.setBounds(100, 100, 200, 30);
	    lexampaper_id.setFont(new Font("Arial",Font.BOLD,18));
	    lexampaper_id.setForeground(new Color(0,102,204));
		add(lexampaper_id);
		panel1.add(lexampaper_id);
		texampaper_id=new JComboBox();
		texampaper_id.setBounds(270, 100, 300, 30);
		texampaper_id.insertItemAt("", 0);
		texampaper_id.setFont(new Font("Arial",Font.BOLD,18));
		texampaper_id.setForeground(new Color(255,102,102));
		add(texampaper_id);
		fillExampaperID();
		panel1.add(texampaper_id);
		texampaper_id.addItemListener(this);
		
		
		// SUBJECT
	    lstatus=new JLabel("STATUS:");
	    lstatus.setBounds(100, 200, 200, 30);
	    lstatus.setFont(new Font("Arial",Font.BOLD,18));
	    lstatus.setForeground(new Color(0,102,204));
		add(lstatus);
		panel1.add(lstatus);
		tstatus=new JComboBox();
		tstatus.insertItemAt("", 0);
		tstatus.insertItemAt("ACTIVE",1);
		tstatus.insertItemAt("INACTIVE",2);
		tstatus.setBounds(270, 200, 300, 30);
		tstatus.setFont(new Font("Arial",Font.BOLD,18));
		tstatus.setForeground(new Color(255,102,102));
		add(tstatus);
		panel1.add(tstatus);
		
		
			update=new JButton("UPDATE");
			update.setBounds(250, 300, 200, 30);
			update.setFont(new Font("Arial",Font.BOLD,16));
			update.setBackground(new Color(0,76,153));
			update.setForeground(Color.WHITE);
			add(update);
			panel1.add(update);
			update.addActionListener(this);
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new exampaper_status();

	}
	
	
	
	void fillExampaperID()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql=" select exampaper_id from tep_exampaper group by exampaper_id ";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				texampaper_id.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	
	void dataTable()
	{
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==update)
		{
			String s = null;
			
			if (texampaper_id.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
					String id=texampaper_id.getSelectedItem().toString();
					if(tstatus.getSelectedItem().toString()=="ACTIVE")
					{
						
						s="1";
					}
					
					else if(tstatus.getSelectedItem().toString()=="INACTIVE")
					{
						
						s="0";
					}
					
					else
					{
						JOptionPane.showMessageDialog(this, "Please select ACTIVE or INACTIVE");
					}
					
					
				
				exampaper_util_data obj=new exampaper_util_data();
				String msg=obj.updateExampaper(s,id);
				JOptionPane.showMessageDialog(this, msg);
				
				}
			}
		}
		
		
		
	}
}