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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.data.admin_util_data;
import com.util.data.exampaper_util_data;


public class exampaper_del extends JFrame implements ActionListener,ItemListener {
	
	JLabel lexampaper_id,lsubject;
	JPanel panel2;
	JButton show,delete;
	JComboBox subject,texampaper_id;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
		
		JTable table;int i=0;int j=0;
				
		DefaultTableModel model = new DefaultTableModel();
	
	exampaper_del()
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
	    JLabel insert3 = new JLabel("DELETE EXAM-PAPER",insert2,SwingConstants.CENTER);
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(900,0, 200, 100);
	    add(insert3);
	    image.add(insert3);
	    
	    // WHITE PANEL
	    JPanel panel1 = new JPanel(); 
	    panel1.setBackground(Color.WHITE);
	    panel1.setBounds(300,50,1300, 200);
	    panel1.setLayout(null);
	    add(panel1);
	    image.add(panel1);
	    
	   panel2 = new JPanel(); 
	    panel2.setBackground(Color.WHITE);
	    panel2.setBounds(300,300,1300, 500);
	    panel2.setLayout(new BorderLayout());
	    add(panel2);
	    image.add(panel2);
	    
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
	    lsubject=new JLabel("SUBJECT:");
	    lsubject.setBounds(100, 50, 200, 30);
	    lsubject.setFont(new Font("Arial",Font.BOLD,18));
	    lsubject.setForeground(new Color(0,102,204));
		add(lsubject);
		panel1.add(lsubject);
		subject=new JComboBox();
		subject.insertItemAt("", 0);
		subject.setBounds(270, 50, 300, 30);
		subject.setFont(new Font("Arial",Font.BOLD,18));
		subject.setForeground(new Color(255,102,102));
		add(subject);
		fillSubjectName();
		panel1.add(subject);
		
		
		//panel 2
		
		
		 JTable table = new JTable();
		 table.setFont(new Font("Arial",Font.BOLD,14));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setBackground(new Color(153, 204, 255));
			table.setForeground(new Color(0,76,153));
			
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(0,76,153));
			header.setForeground(new Color(255,255,255));
			header.setFont(new Font("Arial",Font.BOLD,16));
			
	        model.addColumn("QUESTION ID");
	        model.addColumn("QUESTION");
	        model.addColumn("OPTION1 ID");
	        model.addColumn("OPTION1");
	        model.addColumn("OPTION2 ID");
	        model.addColumn("OPTION2");
	        model.addColumn("OPTION3 ID");
	        model.addColumn("OPTION3");
	        model.addColumn("OPTION4 ID");
	        model.addColumn("OPTION4");
	        model.addColumn("CORRECT ID");
	       
	        table.setModel(model);
	       

	       dataTable();

	        // Add the table to the panel
	        panel2.add(new JScrollPane(table), BorderLayout.CENTER);

	        
	        show=new JButton("SEARCH");
			show.setBounds(600, 100, 200, 30);
			show.setFont(new Font("Arial",Font.BOLD,16));
		    show.setBackground(new Color(0,76,153));
			show.setForeground(Color.WHITE);
			add(show);
			panel1.add(show);
			show.addActionListener(this);
			
			delete=new JButton("DELETE");
			delete.setBounds(1400, 850, 200, 30);
			delete.setFont(new Font("Arial",Font.BOLD,16));
			delete.setBackground(new Color(0,76,153));
			delete.setForeground(Color.WHITE);
			add(delete);
			image.add(delete);
			delete.addActionListener(this);
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new exampaper_del();

	}
	
	void fillSubjectName()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select subject_name from tep_subject";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				subject.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
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
		
		if(arg0.getSource()==delete)
		{
			if (texampaper_id.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all Data first");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String id=texampaper_id.getSelectedItem().toString();
				
				
				exampaper_util_data obj=new exampaper_util_data();
				String msg=obj.deleteData(id);				
				JOptionPane.showMessageDialog(this, msg);
				
				this.dispose();
				}
			}
		}
		
		
		if(arg0.getSource()==show)
		{
			String a =texampaper_id.getSelectedItem().toString() ;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				
				String sql="select question_id,question,option1_id,option1,option2_id,option2,option3_id,option3,option4_id,option4,correct_id from tep_exampaper where exampaper_id=?";
				st=con.prepareStatement(sql);
				
				st.setString(1, a);
				rs=st.executeQuery();
				
				while(rs.next())
				{
					
					model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)});
					
				}
				
				con.close();
			}
			catch(Exception ex) {}
		}
		
		
	}
}