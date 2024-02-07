package com.ExamPortal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

import com.util.data.exam_util_data;

public class exam extends JFrame implements ActionListener, ItemListener{

	JLabel lquestion_id,lduration,lhr,lmarks,ltotalmark,lcorrect_id,lexampaper_id,lquestions,lsubject;
	JLabel lquesno,tquesno,correctid,quesid,optionid1,optionid2,optionid3,optionid4,lstudent_name,lstudent_id,ldate;
	JRadioButton loption1_id,loption2_id,loption3_id,loption4_id;
	JTextField subject,tquestion,toption1,tmarks,ttotalmark,toption2,toption3,toption4, texampaper_id;
	JButton save,next,previous,submit,update;
	JTextField questions,duration,tcorrect_id,tstudent_name,tstudent_id,tdate;
	JPanel panel;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;Statement st1;
		String option;
		String epid;
		
	exam()
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
	    JLabel image1 = new JLabel("EXAM",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(20,20, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    //INSERT LOGO 
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_insert.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("EXAMINATION",insert2,SwingConstants.CENTER);
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(800,0, 400, 100);
	    add(insert3);
	    image.add(insert3);
	    
	    //final sumit button
	    ImageIcon final_submit = new ImageIcon(ClassLoader.getSystemResource("final_submit.png"));
	    Image final_submit1 = final_submit.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon final_submit2 = new ImageIcon(final_submit1);
	    
	    // WHITE PANEL
	    panel = new JPanel(); 
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(300,50,1300, 850);
	    panel.setLayout(null);
	    add(panel);
	    image.add(panel);
	    
	    //EXAM-PAPER ID
	    lexampaper_id=new JLabel("EXAM-PAPER ID:");
	    lexampaper_id.setBounds(100, 50, 200, 30);
	    lexampaper_id.setFont(new Font("Arial",Font.BOLD,18));
	    lexampaper_id.setForeground(new Color(0,102,204));
		add(lexampaper_id);
		panel.add(lexampaper_id);
		texampaper_id=new JTextField();
		texampaper_id.setBounds(270, 50, 300, 30);
		texampaper_id.setEditable(false);
		texampaper_id.setFont(new Font("Arial",Font.BOLD,18));
		texampaper_id.setForeground(new Color(255,102,102));
		add(texampaper_id);
		panel.add(texampaper_id);
		
		//STUDENT NAME
		lstudent_name=new JLabel("STUDENT NAME:");
		lstudent_name.setBounds(100, 100, 200, 30);
		lstudent_name.setFont(new Font("Arial",Font.BOLD,18));
		lstudent_name.setForeground(new Color(0,102,204));
		add(lstudent_name);
		panel.add(lstudent_name);
		tstudent_name=new JTextField();
		tstudent_name.setBounds(270, 100, 300, 30);
		tstudent_name.setEditable(false);
		tstudent_name.setFont(new Font("Arial",Font.BOLD,18));
		tstudent_name.setForeground(new Color(255,102,102));
		add(tstudent_name);
		panel.add(tstudent_name);
		
		// STUDENT ID
			
	    lstudent_id=new JLabel("ROLL NO.:");
	    lstudent_id.setBounds(100, 150, 200, 30);
	    lstudent_id.setFont(new Font("Arial",Font.BOLD,18));
	    lstudent_id.setForeground(new Color(0,102,204));
		add(lstudent_id);
		panel.add(lstudent_id);
		tstudent_id=new JTextField();
		tstudent_id.setBounds(270, 150, 300, 30);
		tstudent_id.setEditable(false);
		tstudent_id.setFont(new Font("Arial",Font.BOLD,18));
		tstudent_id.setForeground(new Color(255,102,102));
		add(tstudent_id);
		panel.add(tstudent_id);
				
			//DATE
				
		ldate=new JLabel("DATE OF EXAM:");
		ldate.setBounds(100, 200, 200, 30);
		ldate.setFont(new Font("Arial",Font.BOLD,18));
		ldate.setForeground(new Color(0,102,204));
		add(ldate);
		panel.add(ldate);
		tdate=new JTextField();
		tdate.setBounds(270, 200, 300, 30);
		tdate.setEditable(false);
		tdate.setFont(new Font("Arial",Font.BOLD,18));
		tdate.setForeground(new Color(255,102,102));
		add(tdate);
		
		
		panel.add(tdate);
		
		//NO. OF QUESTIONS
		lquestions=new JLabel("TOTAL QUESTIONS:");
		lquestions.setBounds(700, 150, 200, 30);
		lquestions.setFont(new Font("Arial",Font.BOLD,18));
		lquestions.setForeground(new Color(0,102,204));
		add(lquestions);
		panel.add(lquestions);
		questions=new JTextField();
		questions.setBounds(900, 150, 100, 30);
		questions.setEditable(false);
		questions.setFont(new Font("Arial",Font.BOLD,18));
		questions.setForeground(new Color(255,102,102));
		add(questions);
		panel.add(questions);
		
		// SUBJECT
	    lsubject=new JLabel("SUBJECT:");
	    lsubject.setBounds(700, 50, 100, 30);
	    lsubject.setFont(new Font("Arial",Font.BOLD,18));
	    lsubject.setForeground(new Color(0,102,204));
		add(lsubject);
		panel.add(lsubject);
		subject=new JTextField();
		subject.setBounds(850, 50, 200, 30);
		subject.setEditable(false);
		subject.setFont(new Font("Arial",Font.BOLD,18));
		subject.setForeground(new Color(255,102,102));
		add(subject);
		panel.add(subject);
		
		//DURATION
		lduration=new JLabel("DURATION:");
		lduration.setBounds(700, 100, 200, 30);
		lduration.setFont(new Font("Arial",Font.BOLD,18));
		lduration.setForeground(new Color(0,102,204));
		add(lduration);
		panel.add(lduration);
		duration=new JTextField();
		duration.setBounds(850, 100, 100, 30);
		duration.setEditable(false);
		duration.setFont(new Font("Arial",Font.BOLD,18));
		duration.setForeground(new Color(255,102,102));
		add(duration);
		panel.add(duration);
		lhr=new JLabel("HRS");
		lhr.setBounds(950, 100, 100, 30);
		lhr.setFont(new Font("Arial",Font.BOLD,18));
		lhr.setForeground(new Color(255,102,102));
		add(lhr);
		panel.add(lhr);
		
		//OPTION3
		lmarks=new JLabel("MARKS:");
		lmarks.setBounds(700, 200, 150, 30);
		lmarks.setFont(new Font("Arial",Font.BOLD,18));
		lmarks.setForeground(new Color(0,102,204));
		add(lmarks);
		panel.add(lmarks);
		tmarks=new JTextField();
		tmarks.setBounds(800,200,100,30);
		tmarks.setEditable(false);
		tmarks.setFont(new Font("Arial",Font.BOLD,18));
		tmarks.setForeground(new Color(255,102,102));
		add(tmarks);
		panel.add(tmarks);
		
		
		//QUESTION
	    lquestion_id=new JLabel("QUESTION ID:");
		lquestion_id.setBounds(100, 300, 150, 30);
		lquestion_id.setFont(new Font("Arial",Font.BOLD,18));
		lquestion_id.setForeground(new Color(0,102,204));
		add(lquestion_id);
		panel.add(lquestion_id);
		
		quesid=new JLabel();
		quesid.setBounds(260, 300, 200, 30);
		quesid.setFont(new Font("Arial",Font.BOLD,18));
		quesid.setForeground(new Color(255,102,102));
		add(quesid);
		panel.add(quesid);
		
		lquesno=new JLabel("QUES NO. : ");
		lquesno.setBounds(900, 300, 200, 30);
		lquesno.setFont(new Font("Arial",Font.BOLD,18));
		lquesno.setForeground(new Color(0,102,204));
		add(lquesno);
		panel.add(lquesno);
		
		tquesno=new JLabel();
		tquesno.setBounds(1010, 300, 200, 30);
		tquesno.setFont(new Font("Arial",Font.BOLD,18));
		tquesno.setForeground(new Color(255,102,102));
		add(tquesno);
		panel.add(tquesno);
			
		tquestion=new JTextField();
		tquestion.setBounds(100,340,1000,30);
		tquestion.setFont(new Font("Arial",Font.BOLD,16));
		add(tquestion);
		panel.add(tquestion);
		
		//OPTION1
		loption1_id=new JRadioButton("OPTION1 ID:");
		loption1_id.setBounds(100, 390, 150, 30);
		loption1_id.setFont(new Font("Arial",Font.BOLD,18));
		loption1_id.setForeground(new Color(0,102,204));
		add(loption1_id);
		panel.add(loption1_id);
		
		optionid1=new JLabel();
		optionid1.setBounds(260, 390, 200, 30);
		optionid1.setFont(new Font("Arial",Font.BOLD,18));
		optionid1.setForeground(new Color(255,102,102));
		add(optionid1);
		panel.add(optionid1);
		toption1=new JTextField();
		toption1.setBounds(100,430,1000,30);
		toption1.setFont(new Font("Arial",Font.BOLD,16));
		add(toption1);
		panel.add(toption1);
		
		//OPTION2
		loption2_id=new JRadioButton("OPTION2 ID:");
		loption2_id.setBounds(100, 490, 150, 30);
		loption2_id.setFont(new Font("Arial",Font.BOLD,18));
		loption2_id.setForeground(new Color(0,102,204));
		add(loption2_id);
		panel.add(loption2_id);
		
		optionid2=new JLabel();
		optionid2.setBounds(260, 490, 200, 30);
		optionid2.setFont(new Font("Arial",Font.BOLD,18));
		optionid2.setForeground(new Color(255,102,102));
		add(optionid2);
		panel.add(optionid2);
		
		toption2=new JTextField();
		toption2.setBounds(100,530,1000,30);
		toption2.setFont(new Font("Arial",Font.BOLD,16));
		add(toption2);
		panel.add(toption2);
		
		//OPTION3
		loption3_id=new JRadioButton("OPTION3 ID:");
		loption3_id.setBounds(100, 590, 150, 30);
		loption3_id.setFont(new Font("Arial",Font.BOLD,18));
		loption3_id.setForeground(new Color(0,102,204));
		add(loption3_id);
		panel.add(loption3_id);
		
		optionid3=new JLabel();
		optionid3.setBounds(260, 590, 200, 30);
		optionid3.setFont(new Font("Arial",Font.BOLD,18));
		optionid3.setForeground(new Color(255,102,102));
		add(optionid3);
		panel.add(optionid3);
		
		toption3=new JTextField();
		toption3.setBounds(100,630,1000,30);
		toption3.setFont(new Font("Arial",Font.BOLD,16));
		add(toption3);
		panel.add(toption3);
		
		//OPTION4
		loption4_id=new JRadioButton("OPTION4 ID:");
		loption4_id.setBounds(100, 690, 150, 30);
		loption4_id.setFont(new Font("Arial",Font.BOLD,18));
		loption4_id.setForeground(new Color(0,102,204));
		add(loption4_id);
		panel.add(loption4_id);
		
		optionid4=new JLabel();
		optionid4.setBounds(260, 690, 200, 30);
		optionid4.setFont(new Font("Arial",Font.BOLD,18));
		optionid4.setForeground(new Color(255,102,102));
		add(optionid4);
		panel.add(optionid4);
		
		toption4=new JTextField();
		toption4.setBounds(100,730,1000,30);
		toption4.setFont(new Font("Arial",Font.BOLD,16));
		add(toption4);
		panel.add(toption4);
		
	
		ButtonGroup bg=new ButtonGroup();
		bg.add(loption1_id);
		bg.add(loption2_id);
		bg.add(loption3_id);
		bg.add(loption4_id);
		
		
		correctid=new JLabel();
		correctid.setBounds(0,0,0,0);
		correctid.setFont(new Font("Arial",Font.BOLD,18));
		correctid.setForeground(new Color(255,102,102));
		add(correctid);
		
		filldata();
		filldate();
		//SAVE BUTTON
		save=new JButton("SAVE");
		save.setBounds(200, 790, 200, 30);
		save.setFont(new Font("Arial",Font.BOLD,16));
	    save.setBackground(new Color(0,76,153));
		save.setForeground(Color.WHITE);
		add(save);
		panel.add(save);
		save.addActionListener(this);
		
		update=new JButton("UPDATE");
		update.setBounds(200, 790, 200, 30);
		update.setFont(new Font("Arial",Font.BOLD,16));
		update.setBackground(new Color(0,76,153));
		update.setForeground(Color.WHITE);
		add(update);
		panel.add(update);
		update.addActionListener(this);
		update.setVisible(false);
		
		//NEXT BUTTON
		next=new JButton("NEXT");
		next.setBounds(500, 790, 200, 30);
		next.setFont(new Font("Arial",Font.BOLD,16));
	    next.setBackground(new Color(0,76,153));
		next.setForeground(Color.WHITE);
		add(next);
		panel.add(next);
		next.addActionListener(this);
		
		//PREVIOUS BUTTON
		previous=new JButton("PREVIOUS");
		previous.setBounds(800, 790, 200, 30);
		previous.setFont(new Font("Arial",Font.BOLD,16));
		previous.setBackground(new Color(0,76,153));
		previous.setForeground(Color.WHITE);
		add(previous);
		panel.add(previous);
		previous.addActionListener(this);
		
		submit=new JButton(final_submit2);
		submit.setText("EXAMPAPER SUBMIT");
		submit.setBounds(1620, 400, 250, 120);
		submit.setFont(new Font("Arial",Font.BOLD,18));
		submit.setBackground(new Color(0,76,153));
		submit.setVerticalTextPosition(SwingConstants.BOTTOM);;
		submit.setHorizontalTextPosition(SwingConstants.CENTER);; 
		submit.setForeground(Color.WHITE);
		add(submit);
		image.add(submit);
		submit.setVisible(true);
		submit.addActionListener(this);
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new exam();
		
	}
	public void filldate() {
		Connection conn;PreparedStatement stt;ResultSet rss;
		try {
			String epid=texampaper_id.getText();
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql1="select schedule_date,schedule_time from tep_examschedule where exampaper_id=?";
			stt=conn.prepareStatement(sql1);
			stt.setString(1, epid);
			rss=stt.executeQuery();
			if(rss.next())
			{
			tdate.setText(rss.getString(1)+" "+"["+rss.getString(2)+"]");
			
			}
		
				
			
			 
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	public void filldata() {
	try {
		//Step-1 Load the driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Step-2 Connection create
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//Step-3 Statement create
		String sql="select * from tep_exampaper where status='"+1+"'";
		st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=st1.executeQuery(sql);
		rs.first();
		 texampaper_id.setText(rs.getString("exampaper_id"));
		 subject.setText(rs.getString("exampaper_subject"));
		 duration.setText(rs.getString("duration"));
		 tquesno.setText(rs.getString("exampaper_ques"));
		 quesid.setText(rs.getString("question_id"));
		 tquestion.setText(rs.getString("question"));
		 optionid1.setText(rs.getString("option1_id"));
		 toption1.setText(rs.getString("option1"));
		 optionid2.setText(rs.getString("option2_id"));
		 toption2.setText(rs.getString("option2"));
		 optionid3.setText(rs.getString("option3_id"));
		 toption3.setText(rs.getString("option3"));
		 optionid4.setText(rs.getString("option4_id"));
		 toption4.setText(rs.getString("option4"));
		 correctid.setText(rs.getString("correct_id"));
		 
		 
	
		
	}
	catch(Exception ex) {
		System.out.println(ex.toString());
	}
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

		
		String stat="1";
		Statement st=con.createStatement();
		
		String sql="select count(*),sum(each) from tep_exampaper where status='"+stat+"'";
		ResultSet rs=st.executeQuery(sql);
		if (rs.next())
		{
		    
		 
		   
			 questions.setText(rs.getString(1));
			 tmarks.setText(rs.getString(2));
		   
		    
		   
		    setVisible(true);
		    
			
		}
		else
			JOptionPane.showMessageDialog(null, "Login Failed");
		
		
		con.close();
		
	}
	catch(Exception ex) {
	}
	
	

	}
	




	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	
		if(e.getSource()==save) {
			
			
			if (texampaper_id.getText().length()==0 ||texampaper_id.getText().length()==0 ||quesid.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String id=texampaper_id.getText();
				String name=tstudent_id.getText();
				String ques_id=quesid.getText();
				String correct=correctid.getText();
			
			
				if(loption1_id.isSelected()) {
					option=optionid1.getText();
					
				}
				else if (loption2_id.isSelected())
				{
					option=optionid2.getText();
				}
				else if(loption3_id.isSelected()) {
					option=optionid3.getText();
				}
				else if(loption4_id.isSelected())
				{
					option=optionid4.getText();
				}
				
				
				exam_util_data obj=new exam_util_data();
				String msg=obj.insertData(id,name,ques_id,option,correct);				
				JOptionPane.showMessageDialog(this, msg);
				save.setVisible(false);
				update.setVisible(true);
				
			}
			
		}
		
		if(e.getSource()==update)
		{
			if (texampaper_id.getText().length()==0 ||texampaper_id.getText().length()==0 ||quesid.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{

					String id=texampaper_id.getText();
					String name=tstudent_id.getText();
					String ques_id=quesid.getText();
					String correct=correctid.getText();
				
					if(loption1_id.isSelected()) {
						option=optionid1.getText();
						
					}
					else if (loption2_id.isSelected())
					{
						option=optionid2.getText();
					}
					else if(loption3_id.isSelected()) {
						option=optionid3.getText();
					}
					else if(loption4_id.isSelected())
					{
						option=optionid4.getText();
					}
					
					
					exam_util_data obj=new exam_util_data();
					String msg=obj.updateData(id,name,ques_id,option,correct);				
					JOptionPane.showMessageDialog(this, msg);
				}
			}		
	}
		
		
		if(e.getSource()==next) {
			
			try {
				
				
					if(rs.next()) {
						
						 save.setVisible(true);
						 update.setVisible(false);
						 tquesno.setText(rs.getString("exampaper_ques"));
						 quesid.setText(rs.getString("question_id"));
						 tquestion.setText(rs.getString("question"));
						 optionid1.setText(rs.getString("option1_id"));
						 toption1.setText(rs.getString("option1"));
						 optionid2.setText(rs.getString("option2_id"));
						 toption2.setText(rs.getString("option2"));
						 optionid3.setText(rs.getString("option3_id"));
						 toption3.setText(rs.getString("option3"));
						 optionid4.setText(rs.getString("option4_id"));
						 toption4.setText(rs.getString("option4"));
						 correctid.setText(rs.getString("correct_id"));
					 
					}
					else {

						

						JOptionPane.showMessageDialog(this,"This is the Last Question.");
					}
						
					
			}
			catch(Exception ec) {
				
				
				System.out.println(ec.toString());
			}

			
		}

		if(e.getSource()==previous) {
			try {
				rs.previous();
				 
				 tquesno.setText(rs.getString("exampaper_ques"));
				 quesid.setText(rs.getString("question_id"));
				 tquestion.setText(rs.getString("question"));
				 optionid1.setText(rs.getString("option1_id"));
				 toption1.setText(rs.getString("option1"));
				 optionid2.setText(rs.getString("option2_id"));
				 toption2.setText(rs.getString("option2"));
				 optionid3.setText(rs.getString("option3_id"));
				 toption3.setText(rs.getString("option3"));
				 optionid4.setText(rs.getString("option4_id"));
				 toption4.setText(rs.getString("option4"));
				 correctid.setText(rs.getString("correct_id"));
			}
			catch(Exception ec) {
				JOptionPane.showMessageDialog(this,"This is the First Question.");
			}
	
		}
		
		if(e.getSource()==submit) {
			JOptionPane.showMessageDialog(this,"This is Final Submission");
			this.dispose();
	
		}
		
		
	}

}
