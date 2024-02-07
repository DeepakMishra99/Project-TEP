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

import com.util.data.exampaper_util_data;

public class exampaper_question_delete extends JFrame implements ActionListener,ItemListener{

	JLabel lquestion_id,lduration,lhr,lmarks,ltotalmark,loption1_id,loption2_id,loption3_id,loption4_id,lcorrect_id,lexampaper_id,lquestions,lquesid;
	JLabel quesid,optionid1,optionid2,optionid3,optionid4;
	JTextField tquestion,toption1,tmarks,ttotalmark,toption2,toption3,toption4;
	JButton delete,next;
	JComboBox questions, tquesid,duration,tcorrect_id,texampaper_id;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
	
		exampaper_question_delete()
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
	    JLabel insert3 = new JLabel("DELETE QUESTION",insert2,SwingConstants.CENTER);
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(750,0, 400, 100);
	    add(insert3);
	    image.add(insert3);
	    
	    // WHITE PANEL
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(300,50,1300, 850);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	    
	    //EXAM-PAPER ID
	    lexampaper_id=new JLabel("EXAM-PAPER ID:");
	    lexampaper_id.setBounds(100, 50, 200, 30);
	    lexampaper_id.setFont(new Font("Arial",Font.BOLD,18));
	    lexampaper_id.setForeground(new Color(0,102,204));
		add(lexampaper_id);
		con.add(lexampaper_id);
		texampaper_id=new JComboBox();
		texampaper_id.insertItemAt("", 0);
		texampaper_id.setBounds(270, 50, 300, 30);
		texampaper_id.setFont(new Font("Arial",Font.BOLD,18));
		texampaper_id.setForeground(new Color(255,102,102));
		fillExampaperID();
		add(texampaper_id);
		con.add(texampaper_id);
		texampaper_id.addItemListener(this);
		
		
		
		// SUBJECT
	    lquesid=new JLabel("QUESTION ID:");
	    lquesid.setBounds(100, 100, 200, 30);
	    lquesid.setFont(new Font("Arial",Font.BOLD,18));
	    lquesid.setForeground(new Color(0,102,204));
		add(lquesid);
		con.add(lquesid);
		tquesid=new JComboBox();
		tquesid.insertItemAt("", 0);
		tquesid.setBounds(270, 100, 200, 30);
		tquesid.setFont(new Font("Arial",Font.BOLD,18));
		tquesid.setForeground(new Color(255,102,102));
		add(tquesid);
		con.add(tquesid);
		tquesid.addItemListener(this);
		
		
		
		//QUESTION
	    lquestion_id=new JLabel("QUESTION ID:");
		lquestion_id.setBounds(100, 200, 150, 30);
		lquestion_id.setFont(new Font("Arial",Font.BOLD,18));
		lquestion_id.setForeground(new Color(0,102,204));
		add(lquestion_id);
		con.add(lquestion_id);
		
		quesid=new JLabel();
		quesid.setBounds(260, 200, 200, 30);
		quesid.setFont(new Font("Arial",Font.BOLD,18));
		quesid.setForeground(new Color(255,102,102));
		add(quesid);
		con.add(quesid);
			
		tquestion=new JTextField();
		tquestion.setBounds(100,240,1000,30);
		tquestion.setFont(new Font("Arial",Font.BOLD,16));
		add(tquestion);
		con.add(tquestion);
		
		//OPTION1
		loption1_id=new JLabel("OPTION1 ID:");
		loption1_id.setBounds(100, 290, 150, 30);
		loption1_id.setFont(new Font("Arial",Font.BOLD,18));
		loption1_id.setForeground(new Color(0,102,204));
		add(loption1_id);
		con.add(loption1_id);
		
		optionid1=new JLabel();
		optionid1.setBounds(260, 290, 200, 30);
		optionid1.setFont(new Font("Arial",Font.BOLD,18));
		optionid1.setForeground(new Color(255,102,102));
		add(optionid1);
		con.add(optionid1);
		toption1=new JTextField();
		toption1.setBounds(100,330,1000,30);
		toption1.setFont(new Font("Arial",Font.BOLD,16));
		add(toption1);
		con.add(toption1);
		
		//OPTION2
		loption2_id=new JLabel("OPTION2 ID:");
		loption2_id.setBounds(100, 390, 400, 30);
		loption2_id.setFont(new Font("Arial",Font.BOLD,18));
		loption2_id.setForeground(new Color(0,102,204));
		add(loption2_id);
		con.add(loption2_id);
		
		optionid2=new JLabel();
		optionid2.setBounds(260, 390, 200, 30);
		optionid2.setFont(new Font("Arial",Font.BOLD,18));
		optionid2.setForeground(new Color(255,102,102));
		add(optionid2);
		con.add(optionid2);
		
		toption2=new JTextField();
		toption2.setBounds(100,430,1000,30);
		toption2.setFont(new Font("Arial",Font.BOLD,16));
		add(toption2);
		con.add(toption2);
		
		//OPTION3
		loption3_id=new JLabel("OPTION3 ID:");
		loption3_id.setBounds(100, 490, 400, 30);
		loption3_id.setFont(new Font("Arial",Font.BOLD,18));
		loption3_id.setForeground(new Color(0,102,204));
		add(loption3_id);
		con.add(loption3_id);
		
		optionid3=new JLabel();
		optionid3.setBounds(260, 490, 200, 30);
		optionid3.setFont(new Font("Arial",Font.BOLD,18));
		optionid3.setForeground(new Color(255,102,102));
		add(optionid3);
		con.add(optionid3);
		
		toption3=new JTextField();
		toption3.setBounds(100,530,1000,30);
		toption3.setFont(new Font("Arial",Font.BOLD,16));
		add(toption3);
		con.add(toption3);
		
		//OPTION4
		loption4_id=new JLabel("OPTION4 ID:");
		loption4_id.setBounds(100, 590, 400, 30);
		loption4_id.setFont(new Font("Arial",Font.BOLD,18));
		loption4_id.setForeground(new Color(0,102,204));
		add(loption4_id);
		con.add(loption4_id);
		
		optionid4=new JLabel();
		optionid4.setBounds(260, 590, 200, 30);
		optionid4.setFont(new Font("Arial",Font.BOLD,18));
		optionid4.setForeground(new Color(255,102,102));
		add(optionid4);
		con.add(optionid4);
		
		toption4=new JTextField();
		toption4.setBounds(100,630,1000,30);
		toption4.setFont(new Font("Arial",Font.BOLD,16));
		add(toption4);
		con.add(toption4);
		
		//CORRECT OPTION
		lcorrect_id=new JLabel("CORRECT ANSWER ID:");
		lcorrect_id.setFont(new Font("Arial",Font.BOLD,18));
		lcorrect_id.setForeground(new Color(0,102,204));
		lcorrect_id.setBounds(100, 690, 400, 30);
		add(lcorrect_id);
		con.add(lcorrect_id);
		
		tcorrect_id=new JComboBox();
		tcorrect_id.setBounds(100,730,1000,30);
		tcorrect_id.insertItemAt("", 0);
		
		
		tcorrect_id.setFont(new Font("Arial",Font.BOLD,18));
		tcorrect_id.setForeground(new Color(255,102,102));
		add(tcorrect_id);
		con.add(tcorrect_id);
		
		//SAVE BUTTON
		delete=new JButton("DELETE");
		delete.setBounds(900, 790, 200, 30);
		delete.setFont(new Font("Arial",Font.BOLD,16));
	    delete.setBackground(new Color(0,76,153));
		delete.setForeground(Color.WHITE);
		add(delete);
		con.add(delete);
		delete.addActionListener(this);
		
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new exampaper_question_delete();
	}
	
	//METHODS 
	void fillExampaperID()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select exampaper_id from tep_exampaper group by exampaper_id ";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				texampaper_id.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	
	void fillOptionId()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select exampaper_id from tep_exampaper group by exampaper_id ";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				texampaper_id.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==delete)
		{
			if (tquesid.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all Data first");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String id=tquesid.getSelectedItem().toString();
				
				
				exampaper_util_data obj=new exampaper_util_data();
				String msg=obj.deleteQues(id);				
				JOptionPane.showMessageDialog(this, msg);
				
				this.dispose();
				}
			}
		}
		
	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==texampaper_id)
		{
			
			try {
				String a=texampaper_id.getSelectedItem().toString();
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select question_id from tep_exampaper where exampaper_id=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				rs=st.executeQuery();
				while(rs.next())
				{
					tquesid.addItem(rs.getString(1));
				}
				
			}
			catch(Exception ex) {}
			
		}
		
		if(e.getSource()==tquesid)
		{
			String k=tquesid.getSelectedItem().toString();
			exampaper_util_data obj=new exampaper_util_data();
					String s[]=obj.findData(k);
					//a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf
					
					if (s!=null) {
						quesid.setText(s[0]);
						tquestion.setText(s[1]);
						optionid1.setText(s[2]);
						toption1.setText(s[3]);
						optionid2.setText(s[4]);
						toption2.setText(s[5]);
						optionid3.setText(s[6]);
						toption3.setText(s[7]);
						optionid4.setText(s[8]);
						toption4.setText(s[9]);
						tcorrect_id.insertItemAt(s[2], 1);
						tcorrect_id.insertItemAt(s[4], 2);
						tcorrect_id.insertItemAt(s[6], 3);
						tcorrect_id.insertItemAt(s[8], 4);
						tcorrect_id.setSelectedItem(s[10]);
					}
						else
						{
							JOptionPane.showMessageDialog(this, "Data not found..");
						}		
					}
		
	}
	

}
