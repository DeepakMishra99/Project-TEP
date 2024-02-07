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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import com.util.data.exampaper_util_data;
import com.util.data.exampaper_util_data.*;

public class exampaper_new_ques extends JFrame implements ActionListener, ItemListener  {
	

	JLabel lquestion_id,lduration,lhr,lmarks,ltotalmark,loption1_id,loption2_id,loption3_id,loption4_id,lcorrect_id,lexampaper_id,lquestions,lsubject;
	JLabel quesid,optionid1,optionid2,optionid3,optionid4;
	JTextField duration,questions,tquestion,toption1,tmarks,ttotalmark,toption2,toption3,toption4,subject;
	JButton save,next;
	JComboBox tcorrect_id,texampaper_id;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
	
		exampaper_new_ques()
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
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("tep_insert.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    JLabel insert3 = new JLabel("ADD NEW QUESTION",insert2,SwingConstants.CENTER);
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
		
		//NO. OF QUESTIONS
		lquestions=new JLabel("NO. OF QUESTIONS:");
		lquestions.setBounds(100, 100, 200, 30);
		lquestions.setFont(new Font("Arial",Font.BOLD,18));
		lquestions.setForeground(new Color(0,102,204));
		add(lquestions);
		con.add(lquestions);
		questions=new JTextField();
		questions.setBounds(300, 100, 100, 30);
		questions.setFont(new Font("Arial",Font.BOLD,18));
		questions.setForeground(new Color(255,102,102));
		add(questions);
		con.add(questions);
		
		// SUBJECT
	    lsubject=new JLabel("SUBJECT:");
	    lsubject.setBounds(700, 100, 100, 30);
	    lsubject.setFont(new Font("Arial",Font.BOLD,18));
	    lsubject.setForeground(new Color(0,102,204));
		add(lsubject);
		con.add(lsubject);
		subject=new JTextField();
		subject.setBounds(800, 100, 200, 30);
		subject.setFont(new Font("Arial",Font.BOLD,18));
		subject.setForeground(new Color(255,102,102));
		add(subject);
		con.add(subject);
		
		//DURATION
		lduration=new JLabel("DURATION:");
		lduration.setBounds(100, 150, 200, 30);
		lduration.setFont(new Font("Arial",Font.BOLD,18));
		lduration.setForeground(new Color(0,102,204));
		add(lduration);
		con.add(lduration);
		duration=new JTextField();
		duration.setBounds(250, 150, 100, 30);
		duration.setFont(new Font("Arial",Font.BOLD,18));
		duration.setForeground(new Color(255,102,102));
		add(duration);
		con.add(duration);
		lhr=new JLabel("in HRS");
		lhr.setBounds(360, 150, 100, 30);
		lhr.setFont(new Font("Arial",Font.BOLD,18));
		lhr.setForeground(new Color(255,102,102));
		add(lhr);
		con.add(lhr);
		
		//OPTION3
		lmarks=new JLabel("Each Marks:");
		lmarks.setBounds(700, 150, 150, 30);
		lmarks.setFont(new Font("Arial",Font.BOLD,18));
		lmarks.setForeground(new Color(0,102,204));
		add(lmarks);
		con.add(lmarks);
		tmarks=new JTextField();
		tmarks.setBounds(850,150,100,30);
		tmarks.setFont(new Font("Arial",Font.BOLD,16));
		tmarks.setForeground(new Color(255,102,102));
		add(tmarks);
		con.add(tmarks);
		
		
		//QUESTION
	    lquestion_id=new JLabel("QUESTION ID:");
		lquestion_id.setBounds(100, 200, 150, 30);
		lquestion_id.setFont(new Font("Arial",Font.BOLD,18));
		lquestion_id.setForeground(new Color(0,102,204));
		add(lquestion_id);
		con.add(lquestion_id);
		
		quesid=new JLabel(quesID());
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
		
		optionid1=new JLabel(opt1ID());
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
		
		optionid2=new JLabel(opt2ID());
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
		
		optionid3=new JLabel(opt3ID());
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
		
		optionid4=new JLabel(opt4ID());
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
		tcorrect_id.insertItemAt(opt1ID(), 1);
		tcorrect_id.insertItemAt(opt2ID(), 2);
		tcorrect_id.insertItemAt(opt3ID(), 3);
		tcorrect_id.insertItemAt(opt4ID(), 4);
		tcorrect_id.setFont(new Font("Arial",Font.BOLD,18));
		tcorrect_id.setForeground(new Color(255,102,102));
		add(tcorrect_id);
		con.add(tcorrect_id);
		
		//SAVE BUTTON
		save=new JButton("SAVE");
		save.setBounds(400, 790, 200, 30);
		save.setFont(new Font("Arial",Font.BOLD,16));
	    save.setBackground(new Color(0,76,153));
		save.setForeground(Color.WHITE);
		add(save);
		con.add(save);
		save.addActionListener(this);
		
		//NEXT BUTTON
		next=new JButton("NEXT");
		next.setBounds(700, 790, 200, 30);
		next.setFont(new Font("Arial",Font.BOLD,16));
	    next.setBackground(new Color(0,76,153));
		next.setForeground(Color.WHITE);
		add(next);
		con.add(next);
		next.addActionListener(this);
		
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new exampaper_new_ques();

	}

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
	
	
	
	
	String totalMarks()
	{
		try{
	   String ques=questions.getText();
	   String each=tmarks.getText();
	   Integer total;
	   total=Integer.parseInt(ques)*Integer.parseInt(each);
	   String totalMarks=String.valueOf(total);
		System.out.println(totalMarks);
	   return(totalMarks);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			return ex.toString();	
			
		}
		
	   
	}
	
	String getID()
	{
		String id;
		LocalDateTime myDateObj = LocalDateTime.now();
	    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddyyyyHHmm");

	    id = "EP#"+myDateObj.format(myFormatObj);
	   
				
	return id;
	}



String getQuesNo()
{
	String aid;
	String y;
	String a=texampaper_id.getSelectedItem().toString();
	LocalDateTime myDateObj = LocalDateTime.now();
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy");

    y= myDateObj.format(myFormatObj);
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		String sql="select exampaper_ques  from tep_exampaper where exampaper_id='"+a+"'";
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery(sql);
		
		if (rs.next())
		{
			
			rs.last();
			String g=rs.getString(1);
			String w=g.substring(3,g.length());
			int x=Integer.parseInt(w);
			System.out.println(x);
			if (x<10)
				aid="Q"+"00"+(x+1);
			else if(x>=10 && x<99)
				aid="Q"+"0"+(x+1);
			
			else
				aid="Q"+(x+1);
		}
		else {
			
			aid="Q"+"001";
		}
		return aid;
		
	}
	catch (Exception ex) {
		return ex.toString();
	}
}





String quesID()
{
	String id;
	LocalDateTime myDateObj = LocalDateTime.now();
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddyyHH"+0+"mmss");

    id = "QUES#"+myDateObj.format(myFormatObj);
   
			
return id;
}

String opt1ID()
{
	String id;
	LocalDateTime myDateObj = LocalDateTime.now();
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddyyHH"+0+"mmss");

    id = "OPT1A#"+myDateObj.format(myFormatObj);
   
			
return id;
}

String opt2ID()
{
	String id;
	LocalDateTime myDateObj = LocalDateTime.now();
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddyyHH"+0+"mmss");

    id = "OPT2B#"+myDateObj.format(myFormatObj);
   
			
return id;
}

String opt3ID()
{
	String id;
	LocalDateTime myDateObj = LocalDateTime.now();
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddyyHH"+0+"mmss");

    id = "OPT3C#"+myDateObj.format(myFormatObj);
   
			
return id;
}

String opt4ID()
{
	String id;
	LocalDateTime myDateObj = LocalDateTime.now();
    
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddyyHH"+0+"mmss");

    id = "OPT4D#"+myDateObj.format(myFormatObj);
   
			
return id;
}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==texampaper_id)
		{
			
			String k=texampaper_id.getSelectedItem().toString();
			questions.setText(getQuesNo());
			   exampaper_util_data obj=new exampaper_util_data();
					String s[]=obj.findExtra(k);
					//a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf
					if (s!=null) {
						subject.setText(s[0]);
						//questions.setText(s[1]);
						
						duration.setText(s[2]);
						tmarks.setText(s[3]);
						
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
		if(e.getSource()==save)
		{

			
			if (texampaper_id.getSelectedItem().toString().length()==0 )
				{
					JOptionPane.showMessageDialog(this,"Check all data first..");
				}
				else
				{
					
					String exampaperid=texampaper_id.getSelectedItem().toString();
					String sub=subject.getText();
					String noq=questions.getText();
					String dur=duration.getText();
					String perques=tmarks.getText();
					String qid=quesid.getText();
					String ques=tquestion.getText();
					String op1id=optionid1.getText();
					String op1=toption1.getText();
					String op2id=optionid2.getText();
					String op2=toption2.getText();
					String op3id=optionid3.getText();
					String op3=toption3.getText();
					String op4id=optionid4.getText();
					String op4=toption4.getText();
					String copid=tcorrect_id.getSelectedItem().toString();
					
					
					
					 exampaper_util_data obj=new exampaper_util_data();
					String msg=obj.insertData(exampaperid,sub,noq,dur,perques,qid,ques,op1id,op1,op2id,op2,op3id,op3,op4id,op4,copid);				
					JOptionPane.showMessageDialog(this, msg);
					
				}
		
			
		
		}
		
		if(e.getSource()==next)
		{
			
			
			quesid.setText(quesID());
			tquestion.setText("");
			optionid1.setText(opt1ID());
			toption1.setText("");
			optionid2.setText(opt2ID());
			toption2.setText("");
			optionid3.setText(opt3ID());
			toption3.setText("");
			optionid4.setText(opt4ID());
			toption4.setText("");
			tcorrect_id.setSelectedItem("");
				}
		
			
		}
		
		
	

}
