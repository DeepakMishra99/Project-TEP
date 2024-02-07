package com.ExamPortal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;


public class admin_dashboard extends JFrame implements ActionListener {
	
	JMenuBar mb;
	JLabel ldate,ltitle,label,lname,lid,lemail,lphone,lstatus,lgender,name,id,email,phone,status,gender;
	String nn;
	JPanel jp;
	JButton logout,manage;
	JMenu admin_master,user_master,subject,exam_schedule,exam,result;
	JMenuItem admin1,admin2,admin3,admin4,admin5,user1,user2,user3,user4,user5,sub1,sub2,sub3,sub4,sub5,sub6,sub7;
	JMenuItem es1,es2,es3,es4,es5,exam1,exam2,exam3,exam4,exam5,exam6,exam7,exam8,exam9,res1,res2,res3,res4,res5;
	
	admin_dashboard()
	{
	setSize(2000,1000);
	setTitle("ADMIN");
	setLayout(null);
	setLocationRelativeTo(null); 
	setResizable(false);
	
	mb=new JMenuBar();
	
	ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("welcome_bg.jpg"));
    Image bg2= bg1.getImage().getScaledInstance(2000, 1080, Image.SCALE_DEFAULT);
    ImageIcon bg3 = new ImageIcon(bg2);
    JLabel image = new JLabel(bg3);
    image.setBounds(0,0, 2000, 1000);
    add(image);
    
    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("TEPlogo.png"));
    Image logo1= logo.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
    ImageIcon logo2 = new ImageIcon(logo1);
    JLabel logo3 = new JLabel("THE  EXAM  PORTAL",logo2,SwingConstants.CENTER);
    logo3.setVerticalTextPosition(JLabel.BOTTOM);
    logo3.setHorizontalTextPosition(JLabel.CENTER);
    logo3.setBounds(1000,300, 400, 400);
    logo3.setForeground(new Color(0,76,153));
    logo3.setFont(new Font("Arial",Font.BOLD,20));
    add(logo3);
    image.add(logo3);
    
    jp= new JPanel();
	jp.setBounds(0, 0, 400, 1000);
	jp.setBackground(new Color(204,229,255));
	image.add(jp);
	jp.setLayout(null);
	
	admin_master=new JMenu("Admin Master");
	admin_master.setForeground(new Color(0,76,153));
	admin1=new JMenuItem("Insert");
	admin2=new JMenuItem("Delete");
	admin3=new JMenuItem("Update");
	admin4=new JMenuItem("Find");
	admin5=new JMenuItem("Show Data");
	admin_master.add(admin1);
	admin_master.addSeparator();
	admin_master.add(admin2);
	admin_master.addSeparator();
	admin_master.add(admin3);
	admin_master.addSeparator();
	admin_master.add(admin4);
	admin_master.addSeparator();
	admin_master.add(admin5);
   	
	user_master=new JMenu("User Master");
	user_master.setForeground(new Color(0,76,153));
	user1=new JMenuItem("Insert Student");
	user2=new JMenuItem("Delete Student");
	user3=new JMenuItem("Update Student");
	user4=new JMenuItem("Find Student Detail");
	user5=new JMenuItem("Show Student List");
	user_master.add(user1);
	user_master.addSeparator();
	user_master.add(user2);
	user_master.addSeparator();
	user_master.add(user3);
	user_master.addSeparator();
	user_master.add(user4);
	user_master.addSeparator();
	user_master.add(user5);
	
	
	
	subject=new JMenu("Subject");
	subject.setForeground(new Color(0,76,153));
	sub1=new JMenuItem("Insert");
	sub2=new JMenuItem("Delete");
	sub3=new JMenuItem("Update");
	sub4=new JMenuItem("Find");
	sub5=new JMenuItem("Show Data");
	sub6=new JMenuItem("Student Subject");
	sub7=new JMenuItem("Update Student Subject");
	subject.add(sub1);
	subject.addSeparator();
	subject.add(sub2);
	subject.addSeparator();
	subject.add(sub3);
	subject.addSeparator();
	subject.add(sub4);
	subject.addSeparator();
	subject.add(sub5);
	subject.addSeparator();
	subject.add(sub6);
	subject.addSeparator();
	subject.add(sub7);
	
	exam_schedule=new JMenu("Exam Schedule Master");
	exam_schedule.setForeground(new Color(0,76,153));
	es1=new JMenuItem("Upload");
	es2=new JMenuItem("Delete");
	es3=new JMenuItem("Update");
	es4=new JMenuItem("Find");
	es5=new JMenuItem("Show Data");
	exam_schedule.add(es1);
	exam_schedule.addSeparator();
	exam_schedule.add(es2);
	exam_schedule.addSeparator();
	exam_schedule.add(es3);
	exam_schedule.addSeparator();
	exam_schedule.add(es4);
	exam_schedule.addSeparator();
	exam_schedule.add(es5);
	
	exam=new JMenu("Exam Master");
	exam.setForeground(new Color(0,76,153));
	exam1=new JMenuItem("Upload Exampaper");
	exam2=new JMenuItem("Delete Exampaper");
	
	exam4=new JMenuItem("Find Exampaper");
	exam5=new JMenuItem("Exampaper Status");
	exam6=new JMenuItem("Add Question");
	exam7=new JMenuItem("Delete Question");
	exam8=new JMenuItem("Update Question");
	exam9=new JMenuItem("Show Exampaper List");
	exam.add(exam1);
	exam.addSeparator();
	exam.add(exam2);
	exam.addSeparator();
	
	exam.add(exam4);
	exam.addSeparator();
	exam.add(exam5);
	exam.addSeparator();
	exam.add(exam6);
	exam.addSeparator();
	exam.add(exam7);
	exam.addSeparator();
	exam.add(exam8);
	exam.addSeparator();
	exam.add(exam9);
	
	result=new JMenu("Result");
	result.setForeground(new Color(0,76,153));
	res1=new JMenuItem("Upload Result");
	
	
	result.add(res1);
	

	
	
	
	
	
	mb.add(admin_master);
	mb.add(user_master);
	mb.add(subject);
	mb.add(exam_schedule);
	mb.add(exam);
	mb.add(result);
	
	
	
	user1.addActionListener(this);
	user2.addActionListener(this);
	user3.addActionListener(this);
	user4.addActionListener(this);
	user5.addActionListener(this);
	
	sub1.addActionListener(this);
	sub2.addActionListener(this);
	sub3.addActionListener(this);
	sub4.addActionListener(this);
	sub5.addActionListener(this);
	sub6.addActionListener(this);
	sub7.addActionListener(this);
	
	es1.addActionListener(this);
	es2.addActionListener(this);
	es3.addActionListener(this);
	es4.addActionListener(this);
	es5.addActionListener(this);
	
	exam1.addActionListener(this);
	exam2.addActionListener(this);
	exam4.addActionListener(this);
	exam5.addActionListener(this);
	exam6.addActionListener(this);
	exam7.addActionListener(this);
	exam8.addActionListener(this);
	exam9.addActionListener(this);
	
	res1.addActionListener(this);
	
	
	admin1.addActionListener(this);
	admin2.addActionListener(this);
	admin3.addActionListener(this);
	admin4.addActionListener(this);
	admin5.addActionListener(this);
	
	
	
	ltitle=new JLabel("      ADMIN ");
	ltitle.setBounds(0,0,400,50);
	ltitle.setOpaque(true);
	ltitle.setForeground(new Color(255,255,255));
	ltitle.setBackground(new Color(0,76,153));
	ltitle.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,50));
	jp.add(ltitle);
	
	label=new JLabel(" A");
	label.setBounds(150,100,100,100);
	label.setOpaque(true);
	label.setForeground(new Color(255,255,255));
	label.setBackground(new Color(0,76,153));
	label.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,80));
	jp.add(label);
	
	lid=new JLabel("ADMIN ID:");
	lid.setBounds(20,300,100,50);
	lid.setForeground(new Color(0,0,0));
	lid.setFont(new Font("Arial",Font.BOLD,16));
	jp.add(lid);
	id=new JLabel();
	id.setBounds(120,300,300,50);
	id.setForeground(new Color(24, 123, 205));
	id.setFont(new Font("Arial",Font.BOLD,16));
	add(id);
	jp.add(id);
	
	lname=new JLabel("NAME:");
	lname.setBounds(20,360,200,50);
	lname.setForeground(new Color(0,0,0));
	lname.setFont(new Font("Arial",Font.BOLD,16));
	add(lname);
	jp.add(lname);
	name=new JLabel();
	name.setBounds(120,360,300,50);
	name.setForeground(new Color(24, 123, 205));
	name.setFont(new Font("Arial",Font.BOLD,16));
	add(name);
	jp.add(name);

	lemail=new JLabel("EMAIL:");
	lemail.setBounds(20,420,100,50);
	lemail.setForeground(new Color(0,0,0));
	lemail.setFont(new Font("Arial",Font.BOLD,16));
	add(lemail);
	jp.add(lemail);
	email=new JLabel();
	email.setBounds(120,420,300,50);
	email.setForeground(new Color(24, 123, 205));
	email.setFont(new Font("Arial",Font.BOLD,16));
	add(email);
	jp.add(email);
	
	
	lphone=new JLabel("PHONE:");
	lphone.setBounds(20,480,100,50);
	lphone.setForeground(new Color(0,0,0));
	lphone.setFont(new Font("Arial",Font.BOLD,16));
	add(lphone);
	jp.add(lphone);
	phone=new JLabel();
	phone.setBounds(120,480,300,50);
	phone.setForeground(new Color(24, 123, 205));
	phone.setFont(new Font("Arial",Font.BOLD,16));
	add(phone);
	jp.add(phone);
	
	
	lgender=new JLabel("GENDER:");
	lgender.setBounds(20,540,100,50);
	lgender.setForeground(new Color(0,0,0));
	lgender.setFont(new Font("Arial",Font.BOLD,16));
	add(lgender);
	jp.add(lgender);
	gender=new JLabel();
	gender.setBounds(120,540,200,50);
	gender.setForeground(new Color(24, 123, 205));
	gender.setFont(new Font("Arial",Font.BOLD,16));
	add(gender);
	jp.add(gender);
	
	lstatus=new JLabel("STATUS:");
	lstatus.setBounds(20,600,100,50);
	lstatus.setForeground(new Color(0,0,0));
	lstatus.setFont(new Font("Arial",Font.BOLD,16));
	add(lstatus);
	jp.add(lstatus);
	status=new JLabel();
	status.setBounds(120,600,200,50);
	status.setForeground(new Color(24, 123, 205));
	status.setFont(new Font("Arial",Font.BOLD,16));
	add(status);
	jp.add(status);
	
	manage=new JButton("MANAGE PROFILE");
	manage.setBounds(0,680,400,50);
	manage.setForeground(new Color(255,255,255));
	manage.setBackground(new Color(0,76,153));
	manage.setFont(new Font("Arial",Font.BOLD,16));
	add(manage);
	jp.add(manage);
	manage.addActionListener(this);
	
	logout=new JButton("LOGOUT");
	logout.setBounds(0,730,400,50);
	logout.setForeground(new Color(255,255,255));
	logout.setBackground(new Color(0,76,153));
	logout.setFont(new Font("Arial",Font.BOLD,16));
	add(logout);
	jp.add(logout);
	logout.addActionListener(this);
	
	DateFormat formatter = new SimpleDateFormat("E, dd-MMM-yyyy");
    Calendar obj = Calendar.getInstance();
    String str = formatter.format(obj.getTime());
     
	
	ldate=new JLabel("DATE: "+str);
	ldate.setBounds(1600,30,300,50);
	ldate.setForeground(new Color(41, 71, 90));
	ldate.setFont(new Font("Arial",Font.BOLD,25));
	add(ldate);
	image.add(ldate);
	
	
	setJMenuBar(mb);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new admin_dashboard();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// ADMIN MASTER
		if(e.getSource()==admin1)
		{
			new admin_ins();
		}
		if(e.getSource()==admin2)
		{
			new admin_del();
		}
		if(e.getSource()==admin3)
		{
			new admin_update();
		}
		if(e.getSource()==admin4)
		{
			new admin_find();
		}
		if(e.getSource()==admin5)
		{
			new admin_list();
		}
		
		// USER MASTER
		if(e.getSource()==user1)
		{
			new user_ins();
		}
		if(e.getSource()==user2)
		{
			new user_del();
		}
		if(e.getSource()==user3)
		{
			new user_update();
		}
		if(e.getSource()==user4)
		{
			new user_find();
		}
		if(e.getSource()==user5)
		{
			new user_list();
		}

		// SUBJECT
		if(e.getSource()==sub1)
		{
			new subject_ins();
		}
		if(e.getSource()==sub2)
		{
			new subject_del();
		}
		if(e.getSource()==sub3)
		{
			new subject_update();
		}
		if(e.getSource()==sub4)
		{
			new subject_find();
		}
		if(e.getSource()==sub5)
		{
			new subject_list();
		}
		if(e.getSource()==sub6)
		{
			new student_subject();
		}
		if(e.getSource()==sub7)
		{
			new student_subject_upd();
		}
		
		// EXAM MASTER
		if(e.getSource()==es1)
		{
			new exam_schedule_ins();
		}
		if(e.getSource()==es2)
		{
			new exam_schedule_del();
		}
		if(e.getSource()==es3)
		{
			new exam_schedule_update();
		}
		if(e.getSource()==es4)
		{
			new exam_schedule_find();
		}
		if(e.getSource()==es5)
		{
			new exam_schedule_list();
		}
		
		// EXAM MASTER
		if(e.getSource()==exam1)
		{
			new exampaper_ins();
		}
		if(e.getSource()==exam2)
		{
			new exampaper_del();
		}
		
		if(e.getSource()==exam4)
		{
			new exampaper_find();
		}
		if(e.getSource()==exam5)
		{
			new exampaper_status();
		}
		if(e.getSource()==exam6)
		{
			new exampaper_new_ques();
		}
		
		if(e.getSource()==exam7)
		{
			new exampaper_question_delete();
		}
		if(e.getSource()==exam8)
		{
			new exampaper_question_update();
		}
		if(e.getSource()==exam9)
		{
			new exampaper_list();
		}
		
		// RESULT
		if(e.getSource()==res1)
		{
			new exampaper_result_ins();
		}
		
		//logout
		if(e.getSource()==logout)
		{
			JOptionPane.showMessageDialog(null, "Logout successfully");
			this.dispose();
		}
		
		//manage
		
		if(e.getSource()==manage)
		{
			new admin_update();
		}
		

		
	
	}

}
