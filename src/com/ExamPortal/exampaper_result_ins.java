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
import com.util.data.result_util_data;

public class exampaper_result_ins extends JFrame implements ActionListener, ItemListener {
	
	JLabel lstudent_id,lstudent_name,lemail,lphone,lgender,ldob,lsubject,lgrade,lstatus,lexam_date;
	JLabel lexampaper_id,ltotal_ques,leach_mark,ltotal_mark,lcorrect_ans,lincorrect_ans,lscore;
	JButton exam,result,insert,logout,show;
	JTextField tstudent_name,temail,phone,tgender,tdob,tsubject,tgrade,tstatus,texam_date;
	JTextField ttotal_ques,teach_mark,ttotal_mark,tcorrect_ans,tincorrect_ans,tscore;
	JComboBox texampaper_id,tstudent_id;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
		
		JTable table;int i=0;int j=0;
		
		DefaultTableModel model = new DefaultTableModel();	
	
		exampaper_result_ins()
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
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("resulticon.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("RESULT",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,20));
	    image1.setBounds(20,20, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_insert.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("INSERT",insert2,SwingConstants.CENTER);
	    insert3.setForeground(new Color(0,76,153));
	    insert3.setFont(new Font("Arial",Font.BOLD,26));
	    insert3.setBounds(900,-50, 200, 200);
	    add(insert3);
	    image.add(insert3); 
	    
	
	    
	    
	    // WHITE PANEL
	    JPanel panel1 = new JPanel(); 
	    panel1.setBackground(Color.WHITE);
	    panel1.setBounds(450,50,1000, 900);
	    panel1.setLayout(null);
	    add(panel1);
	    image.add(panel1);
	    
	    
	   
	    
	    //EXAM-PAPER ID
	    
	    lexampaper_id=new JLabel("EXAMPAPER ID:");
	    lexampaper_id.setBounds(100, 50, 200, 30);
	    lexampaper_id.setFont(new Font("Arial",Font.BOLD,18));
	    lexampaper_id.setForeground(new Color(0,102,204));
		add(lexampaper_id);
		panel1.add(lexampaper_id);
		
		texampaper_id=new JComboBox();
		texampaper_id.setBounds(350,50,400,30);
		texampaper_id.insertItemAt("", 0);
		texampaper_id.setForeground(new Color(0,0,0));
		texampaper_id.setFont(new Font("Arial",Font.BOLD,18));
		add(texampaper_id);
		fillExampaperId();
		panel1.add(texampaper_id);
		texampaper_id.addItemListener(this);
		
		
	    lstudent_id=new JLabel("STUDENT ID:");
	    lstudent_id.setBounds(100, 100, 200, 30);
	    lstudent_id.setFont(new Font("Arial",Font.BOLD,18));
	    lstudent_id.setForeground(new Color(0,102,204));
		add(lstudent_id);
		panel1.add(lstudent_id);
		
		tstudent_id=new JComboBox();
		tstudent_id.setBounds(350,100,400,30);
		tstudent_id.insertItemAt("", 0);
		tstudent_id.setForeground(new Color(0,0,0));
		tstudent_id.setFont(new Font("Arial",Font.BOLD,18));
		add(tstudent_id);
		panel1.add(tstudent_id);
		tstudent_id.addItemListener(this);
		
		lstudent_name=new JLabel("STUDENT NAME:");
	    lstudent_name.setBounds(100, 150, 200, 30);
	    lstudent_name.setFont(new Font("Arial",Font.BOLD,18));
	    lstudent_name.setForeground(new Color(0,102,204));
		add(lstudent_name);
		panel1.add(lstudent_name);
		tstudent_name=new JTextField();
		tstudent_name.setBounds(350,150,400,30);
		tstudent_name.setForeground(new Color(0,0,0));
		tstudent_name.setFont(new Font("Arial",Font.BOLD,18));
		add(tstudent_name);
		panel1.add(tstudent_name);
		
		lemail=new JLabel("STUDENT EMAIL:");
	    lemail.setBounds(100, 200, 200, 30);
	    lemail.setFont(new Font("Arial",Font.BOLD,18));
	    lemail.setForeground(new Color(0,102,204));
		add(lemail);
		panel1.add(lemail);
		temail=new JTextField();
		temail.setBounds(350,200,400,30);
		temail.setForeground(new Color(0,0,0));
		temail.setFont(new Font("Arial",Font.BOLD,18));
		add(temail);
		panel1.add(temail);
		
		lgender=new JLabel("STUDENT GENDER:");
	    lgender.setBounds(100, 250, 200, 30);
	    lgender.setFont(new Font("Arial",Font.BOLD,18));
	    lgender.setForeground(new Color(0,102,204));
		add(lgender);
		panel1.add(lgender);
		
		tgender=new JTextField();
		tgender.setBounds(350,250,400,30);
		tgender.setForeground(new Color(0,0,0));
		tgender.setFont(new Font("Arial",Font.BOLD,18));
		add(tgender);
		panel1.add(tgender);
		
		ldob=new JLabel("STUDENT DOB:");
	    ldob.setBounds(100, 300, 200, 30);
	    ldob.setFont(new Font("Arial",Font.BOLD,18));
	    ldob.setForeground(new Color(0,102,204));
		add(ldob);
		panel1.add(ldob);
		
		tdob=new JTextField();
		tdob.setBounds(350,300,400,30);
		tdob.setForeground(new Color(0,0,0));
		tdob.setFont(new Font("Arial",Font.BOLD,18));
		add(tdob);
		panel1.add(tdob);
		
		
		lsubject=new JLabel("SUBJECT:");
		lsubject.setBounds(100, 350, 200, 30);
		lsubject.setFont(new Font("Arial",Font.BOLD,18));
		lsubject.setForeground(new Color(0,102,204));
		add(lsubject);
		panel1.add(lsubject);
		
		tsubject=new JTextField();
		tsubject.setBounds(350,350,400,30);
		tsubject.setForeground(new Color(0,0,0));
		tsubject.setFont(new Font("Arial",Font.BOLD,18));
		add(tsubject);
		panel1.add(tsubject);
		
		ltotal_ques=new JLabel("TOTAL QUESTION:");
		ltotal_ques.setBounds(100, 400, 200, 30);
		ltotal_ques.setFont(new Font("Arial",Font.BOLD,18));
		ltotal_ques.setForeground(new Color(0,102,204));
		add(ltotal_ques);
		panel1.add(ltotal_ques);
		
		ttotal_ques=new JTextField();
		ttotal_ques.setBounds(350,400,400,30);
		ttotal_ques.setForeground(new Color(0,0,0));
		ttotal_ques.setFont(new Font("Arial",Font.BOLD,18));
		add(ttotal_ques);
		panel1.add(ttotal_ques);
		
		
		leach_mark=new JLabel("EACH MARK:");
		leach_mark.setBounds(100, 450, 200, 30);
		leach_mark.setFont(new Font("Arial",Font.BOLD,18));
		leach_mark.setForeground(new Color(0,102,204));
		add(leach_mark);
		panel1.add(leach_mark);
		
		teach_mark=new JTextField();
		teach_mark.setBounds(350,450,400,30);
		teach_mark.setForeground(new Color(0,0,0));
		teach_mark.setFont(new Font("Arial",Font.BOLD,18));
		add(teach_mark);
		panel1.add(teach_mark);
		
		ltotal_mark=new JLabel("TOTAL MARKS:");
		ltotal_mark.setBounds(100, 500, 200, 30);
		ltotal_mark.setFont(new Font("Arial",Font.BOLD,18));
		ltotal_mark.setForeground(new Color(0,102,204));
		add(ltotal_mark);
		panel1.add(ltotal_mark);
		
		ttotal_mark=new JTextField();
		ttotal_mark.setBounds(350,500,400,30);
		ttotal_mark.setForeground(new Color(0,0,0));
		ttotal_mark.setFont(new Font("Arial",Font.BOLD,18));
		add(ttotal_mark);
		panel1.add(ttotal_mark);
		

		lcorrect_ans=new JLabel("CORRECT ANSWER:");
		lcorrect_ans.setBounds(100, 550, 200, 30);
		lcorrect_ans.setFont(new Font("Arial",Font.BOLD,18));
		lcorrect_ans.setForeground(new Color(0,102,204));
		add(lcorrect_ans);
		panel1.add(lcorrect_ans);
		
		tcorrect_ans=new JTextField();
		tcorrect_ans.setBounds(350,550,400,30);
		tcorrect_ans.setForeground(new Color(0,0,0));
		tcorrect_ans.setFont(new Font("Arial",Font.BOLD,18));
		add(tcorrect_ans);
		panel1.add(tcorrect_ans);
		
		lincorrect_ans=new JLabel("INCORRECT ANSWER:");
		lincorrect_ans.setBounds(100, 600, 200, 30);
		lincorrect_ans.setFont(new Font("Arial",Font.BOLD,18));
		lincorrect_ans.setForeground(new Color(0,102,204));
		add(lincorrect_ans);
		panel1.add(lincorrect_ans);
		
		tincorrect_ans=new JTextField();
		tincorrect_ans.setBounds(350,600,400,30);
		tincorrect_ans.setForeground(new Color(0,0,0));
		tincorrect_ans.setFont(new Font("Arial",Font.BOLD,18));
		add(tincorrect_ans);
		panel1.add(tincorrect_ans);
		
		lscore=new JLabel("SCORE:");
		lscore.setBounds(100, 650, 200, 30);
		lscore.setFont(new Font("Arial",Font.BOLD,18));
		lscore.setForeground(new Color(0,102,204));
		add(lscore);
		panel1.add(lscore);
		
		tscore=new JTextField();
		tscore.setBounds(350,650,400,30);
		tscore.setForeground(new Color(0,0,0));
		tscore.setFont(new Font("Arial",Font.BOLD,18));
		add(tscore);
		panel1.add(tscore);
		
		lgrade=new JLabel("GRADE:");
		lgrade.setBounds(100, 700, 200, 30);
		lgrade.setFont(new Font("Arial",Font.BOLD,18));
		lgrade.setForeground(new Color(0,102,204));
		add(lgrade);
		panel1.add(lgrade);
		
		tgrade=new JTextField();
		tgrade.setBounds(200,700,100,30);
		tgrade.setForeground(new Color(0,0,0));
		tgrade.setFont(new Font("Arial",Font.BOLD,18));
		add(tgrade);
		panel1.add(tgrade);
		
		lstatus=new JLabel("STATUS:");
		lstatus.setBounds(500, 700, 200, 30);
		lstatus.setFont(new Font("Arial",Font.BOLD,18));
		lstatus.setForeground(new Color(0,102,204));
		add(lstatus);
		panel1.add(lstatus);
		
		tstatus=new JTextField();
		tstatus.setBounds(600,700,100,30);
		tstatus.setForeground(new Color(0,0,0));
		tstatus.setFont(new Font("Arial",Font.BOLD,18));
		add(tstatus);
		panel1.add(tstatus);
		
		lexam_date=new JLabel("EXAM DATE:");
		lexam_date.setBounds(100, 750, 200, 30);
		lexam_date.setFont(new Font("Arial",Font.BOLD,18));
		lexam_date.setForeground(new Color(0,102,204));
		add(lexam_date);
		panel1.add(lexam_date);
		
		texam_date=new JTextField();
		texam_date.setBounds(350,750,400,30);
		texam_date.setForeground(new Color(0,0,0));
		texam_date.setFont(new Font("Arial",Font.BOLD,18));
		add(texam_date);
		panel1.add(texam_date);
		// SUBJECT
	   
		insert=new JButton("UPLOAD");
		insert.setBounds(700, 800, 200, 30);
		insert.setFont(new Font("Arial",Font.BOLD,16));
		insert.setBackground(new Color(255,102,102));
		insert.setForeground(Color.WHITE);
		add(insert);
		panel1.add(insert);
		insert.addActionListener(this);
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new exampaper_result_ins();

	}
	
	// METHODS
	 void fillExampaperId()
	 {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select exampaper_id from tep_exam group by exampaper_id ";
				st=con.prepareStatement(sql);
				rs=st.executeQuery();
				while(rs.next())
				{
					texampaper_id.addItem(rs.getString(1));
				}
				
			}
			catch(Exception ex) {}
	 }
	 
	 
	 void fillStudentId()
	 {
		 String sid=texampaper_id.getSelectedItem().toString();
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select user_id from tep_exam where exampaper_id=? group by user_id ";
				st=con.prepareStatement(sql);
				st.setString(1, sid);
				rs=st.executeQuery();
				while(rs.next())
				{
					tstudent_id.addItem(rs.getString(1));
				}
				
			}
			catch(Exception ex) {}
	 }
	 
	 void fillExampaperData()
	 {

		 String examid=texampaper_id.getSelectedItem().toString();
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select count(question_id),exampaper_subject,each from tep_exampaper where exampaper_id=? group by exampaper_subject,each";
				st=con.prepareStatement(sql);
				st.setString(1, examid);
				rs=st.executeQuery();
				while(rs.next())
				{
					ttotal_ques.setText(rs.getString(1));
					tsubject.setText(rs.getString(2));
					teach_mark.setText(rs.getString(3));
				}
				
			}
			catch(Exception ex) {
				
			} 
	 }
	 
	 void fillStudentData() {
		 String sid=tstudent_id.getSelectedItem().toString();
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select user_name,user_email,user_dob,user_gender from tep_user where user_id=?";
				st=con.prepareStatement(sql);
				st.setString(1, sid);
				rs=st.executeQuery();
				while(rs.next())
				{
					tstudent_name.setText(rs.getString(1));
					temail.setText(rs.getString(2));
					tdob.setText(rs.getString(3));
					tgender.setText(rs.getString(4));
				}
				
			}
			catch(Exception ex) {
				
			} 
	 }
	 
	 void fillCorrectAns() {
		 String sid=tstudent_id.getSelectedItem().toString();
		 String examid=texampaper_id.getSelectedItem().toString();
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select count(*) from tep_exam where exampaper_id=? and user_id=? and option_id=correct_id";
				st=con.prepareStatement(sql);
				st.setString(1, examid);
				st.setString(2, sid);
				rs=st.executeQuery();
				while(rs.next())
				{
					tcorrect_ans.setText(rs.getString(1));
					
				}
				
			}
			catch(Exception ex) {
				
			} 
		 
	 }
	 
	 
	 void fillIncorrectAns() {
		 String sid=tstudent_id.getSelectedItem().toString();
		 String examid=texampaper_id.getSelectedItem().toString();
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select count(*) from tep_exam where exampaper_id=? and user_id=? and option_id!=correct_id";
				st=con.prepareStatement(sql);
				st.setString(1, examid);
				st.setString(2, sid);
				rs=st.executeQuery();
				while(rs.next())
				{
					tincorrect_ans.setText(rs.getString(1));
					
				}
				
			}
			catch(Exception ex) {
				
			} 
		 
	 }

	 public void filldate() {
			Connection conn;PreparedStatement stt;ResultSet rss;
			try {
				String epid=texampaper_id.getSelectedItem().toString();
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
				texam_date.setText(rss.getString(1)+" "+"["+rss.getString(2)+"]");
				
				}
			
					
				
				 
			}
			catch(Exception ex) {
				System.out.println(ex.toString());
			}
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==insert)
		{
			if (texampaper_id.getSelectedItem().toString().length()==0 ||tstudent_id.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String examid=texampaper_id.getSelectedItem().toString();
				String sid=tstudent_id.getSelectedItem().toString();
				String name=tstudent_name.getText();
				String email=temail.getText();
				String gender=tgender.getText();
				String dob=tdob.getText();
				String sub=tsubject.getText();
				String tq=ttotal_ques.getText();
				String te=teach_mark.getText();
				String tm=ttotal_mark.getText();
				String ca=tcorrect_ans.getText();
				String ina=tincorrect_ans.getText();
				String score=tscore.getText();
				String grade=tgrade.getText();
				String st=tstatus.getText();
				String examdate=texam_date.getText();
				
				result_util_data obj=new result_util_data();
				String msg=obj.insertData(examid,sid,name,email,gender,dob,sub,tq,te,tm,ca,ina,score,grade,examdate,st);				
				JOptionPane.showMessageDialog(this, msg);
			}
		}
	
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==texampaper_id)
		{
			fillStudentId();
			fillExampaperData();
			String ques=ttotal_ques.getText();
			String each_mark=teach_mark.getText();
			int t=Integer.parseInt(ques)*Integer.parseInt(each_mark);
			String total=String.valueOf(t);
			ttotal_mark.setText(total);
			filldate();
		}
		
		if(arg0.getSource()==tstudent_id)
		{
			
			fillStudentData();
			fillCorrectAns();
			fillIncorrectAns();
			
			String correct=tcorrect_ans.getText();
			String ques=ttotal_ques.getText();
			String each_mark=teach_mark.getText();
			int s=Integer.parseInt(correct)*Integer.parseInt(each_mark);
			String score_mark=String.valueOf(s);
			tscore.setText(score_mark);
			int t=Integer.parseInt(ques)*Integer.parseInt(each_mark);
			int percentage=(s*100)/t;
			
			if(percentage>=90)
			{
				tgrade.setText("A+");
				tstatus.setText("PASS");
				
			}
			else if(percentage>=80 && s<90)
			{
				tgrade.setText("A");
				tstatus.setText("PASS");
				
			}
			else if(percentage>=70 && s<80)
			{
				tgrade.setText("B+");
				tstatus.setText("PASS");
				
			}
			else if(percentage>=60 && s<70)
			{
				tgrade.setText("B");
				tstatus.setText("PASS");
				
			}
			else if(percentage>=50 && s<60)
			{
				tgrade.setText("C");
				tstatus.setText("PASS");
				
			}
			else if(percentage>=40 && s<50)
			{
				tgrade.setText("D");
				tstatus.setText("PASS");
				
			}
			
			else
			{
				tgrade.setText("E");
				tstatus.setText("FAIL");
				
			}
			
		}
		
		
	}

}
