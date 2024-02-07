package com.ExamPortal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.util.data.admin_util_data;
import com.util.data.result_util_data; 




public class result extends JFrame implements ActionListener, ItemListener {
	
	JLabel lstudent_id,lstudent_name,lemail,lphone,lgender,ldob,laddress;
	JLabel id,name,email,phone,gender,dob,address,exam_date;
	JLabel lexampaper_id,lsubject,ltotal_mark,lscore,lcorrect_ans,lincorrect_ans,lgrade,lstatus;
	JLabel tsubject,ttotal_mark,tscore,tcorrect_ans,tincorrect_ans,tgrade,tstatus,dum;
	JPanel panel2,panel3,panel4;
	ChartPanel panel;
	JButton exam,result,manage,logout,show;
	JComboBox subject,texampaper_id;
	
	String a,b;
	//JDBC variables
		Connection con;PreparedStatement st;ResultSet rs;
		
		JTable table;int i=0;int j=0;
		
		DefaultTableModel model = new DefaultTableModel();	
	
		result()
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
	    
	    
	    
	    
	    
	    //EXAM LOGO 
	    ImageIcon insert_logo = new ImageIcon(ClassLoader.getSystemResource("examicon.png"));
	    Image insert1 = insert_logo.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon insert2 = new ImageIcon(insert1);
	    
	  //EXAM LOGO 
	    ImageIcon insert_logo1 = new ImageIcon(ClassLoader.getSystemResource("resulticon.png"));
	    Image insert3 = insert_logo1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon insert4 = new ImageIcon(insert3);
	    
	    
	    // WHITE PANEL
	    JPanel panel1 = new JPanel(); 
	    panel1.setBackground(Color.WHITE);
	    panel1.setBounds(100,50,800, 400);
	    panel1.setLayout(null);
	    add(panel1);
	    image.add(panel1);
	    
	  //TEP LOGO 
	  		ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("TEPlogo.png"));
	  	    Image logo1= logo.getImage().getScaledInstance(130, 100, Image.SCALE_DEFAULT);
	  	    ImageIcon logo2 = new ImageIcon(logo1);
	  	    JLabel logo3 = new JLabel("THE EXAM PORTAL",logo2,SwingConstants.CENTER);
	  	    logo3.setVerticalTextPosition(JLabel.BOTTOM);
	  	    logo3.setHorizontalTextPosition(JLabel.CENTER);
	  	    logo3.setBounds(-10,-30, 200, 200);
	  	    logo3.setForeground(new Color(0,76,153));
	  	    logo3.setFont(new Font("Arial",Font.BOLD,12));
	  	    add(logo3);
	  	    panel1.add(logo3);
	    
	    panel2 = new JPanel(); 
	    panel2.setBackground(new Color(255,255,255));
	    panel2.setBounds(1000,50,800, 400);
	    panel2.setLayout(new BorderLayout());
	    add(panel2);
	    image.add(panel2);
	    
	  //EXAMPAPER LOGO 
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("result.png"));
	    Image i2 = i1.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("RESULT",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setForeground(new Color(0,76,153));
	    image1.setFont(new Font("Arial",Font.BOLD,16));
	    image1.setBounds(5,10, 100, 100);
	    add(image1);
	    panel2.add(image1);
	    
	    
	    panel3 = new JPanel(); 
	    panel3.setBackground(new Color(6,85,147));
	    panel3.setBounds(100,500,800, 400);
	    panel3.setLayout(new BorderLayout());
	    add(panel3);
	    image.add(panel3);
	    
	    panel4 = new JPanel(); 
	    panel4.setBackground(new Color(6,85,147));
	    panel4.setBounds(1000,500,800, 400);
	    panel4.setLayout(new BorderLayout());
	    add(panel4);
	    image.add(panel4);
	    
	    
	    
	  
	    
	   
	   
	    
	    //EXAM-PAPER ID
	    lstudent_id=new JLabel("STUDENT ID:");
	    lstudent_id.setBounds(200, 50, 200, 30);
	    lstudent_id.setFont(new Font("Arial",Font.BOLD,16));
	    lstudent_id.setForeground(new Color(0,102,204));
		add(lstudent_id);
		panel1.add(lstudent_id);
		
		id=new JLabel();
		id.setBounds(500,50,200,30);
		id.setForeground(new Color(0,0,0));
		id.setFont(new Font("Arial",Font.BOLD,16));
		add(id);
		panel1.add(id);
		
		lstudent_name=new JLabel("STUDENT NAME:");
	    lstudent_name.setBounds(200, 100, 200, 30);
	    lstudent_name.setFont(new Font("Arial",Font.BOLD,16));
	    lstudent_name.setForeground(new Color(0,102,204));
		add(lstudent_name);
		panel1.add(lstudent_name);
		name=new JLabel();
		name.setBounds(500,100,200,30);
		name.setForeground(new Color(0,0,0));
		name.setFont(new Font("Arial",Font.BOLD,16));
		add(name);
		panel1.add(name);
		
		lemail=new JLabel("EMAIL:");
	    lemail.setBounds(200, 150, 200, 30);
	    lemail.setFont(new Font("Arial",Font.BOLD,16));
	    lemail.setForeground(new Color(0,102,204));
		add(lemail);
		panel1.add(lemail);
		email=new JLabel();
		email.setBounds(500,150,400,30);
		email.setForeground(new Color(0,0,0));
		email.setFont(new Font("Arial",Font.BOLD,16));
		add(email);
		panel1.add(email);
		
		lphone=new JLabel("PHONE:");
	    lphone.setBounds(200, 200, 200, 30);
	    lphone.setFont(new Font("Arial",Font.BOLD,16));
	    lphone.setForeground(new Color(0,102,204));
		add(lphone);
		panel1.add(lphone);
		phone=new JLabel();
		phone.setBounds(500,200,300,30);
		phone.setForeground(new Color(0,0,0));
		phone.setFont(new Font("Arial",Font.BOLD,16));
		add(phone);
		panel1.add(phone);
		
		
		lgender=new JLabel("GENDER:");
	    lgender.setBounds(200, 300, 200, 30);
	    lgender.setFont(new Font("Arial",Font.BOLD,16));
	    lgender.setForeground(new Color(0,102,204));
		add(lgender);
		panel1.add(lgender);
		gender=new JLabel();
		gender.setBounds(500,300,200,30);
		gender.setForeground(new Color(0,0,0));
		gender.setFont(new Font("Arial",Font.BOLD,16));
		add(gender);
		panel1.add(gender);
		
		ldob=new JLabel("DOB:");
	    ldob.setBounds(200, 250, 200, 30);
	    ldob.setFont(new Font("Arial",Font.BOLD,16));
	    ldob.setForeground(new Color(0,102,204));
		add(ldob);
		panel1.add(ldob);
		
		dob=new JLabel();
		dob.setBounds(500,250,200,30);
		dob.setForeground(new Color(0,0,0));
		dob.setFont(new Font("Arial",Font.BOLD,16));
		add(dob);
		panel1.add(dob);
		
		laddress=new JLabel("ADDRESS:");
		laddress.setBounds(200, 350, 200, 30);
		laddress.setFont(new Font("Arial",Font.BOLD,16));
		laddress.setForeground(new Color(0,102,204));
		add(laddress);
		panel1.add(laddress);
		
		address=new JLabel();
		address.setBounds(500,350,200,30);
		address.setForeground(new Color(0,0,0));
		address.setFont(new Font("Arial",Font.BOLD,16));
		add(address);
		panel1.add(address);
		
		
		//PANEL2
		lexampaper_id=new JLabel("EXAMPAPER ID:");
		lexampaper_id.setBounds(200, 50, 200, 30);
		lexampaper_id.setFont(new Font("Arial",Font.BOLD,16));
		lexampaper_id.setForeground(new Color(0,102,204));
		add(lexampaper_id);
		panel2.add(lexampaper_id);
		
		texampaper_id=new JComboBox();
		texampaper_id.setBounds(400,50,200,30);
		texampaper_id.insertItemAt("", 0);
		texampaper_id.setForeground(new Color(0,0,0));
		texampaper_id.setFont(new Font("Arial",Font.BOLD,16));
		add(texampaper_id);
		fillExampaperId();
		panel2.add(texampaper_id);
		texampaper_id.addItemListener(this);
		
		exam_date=new JLabel();
		exam_date.setBounds(610, 50, 200, 30);
		exam_date.setFont(new Font("Arial",Font.BOLD,16));
		exam_date.setForeground(new Color(0,102,204));
		add(exam_date);
		panel2.add(exam_date);
		
		lsubject=new JLabel("SUBJECT");
		lsubject.setBounds(200, 100, 200, 30);
		lsubject.setFont(new Font("Arial",Font.BOLD,16));
		lsubject.setForeground(new Color(0,102,204));
		add(lsubject);
		panel2.add(lsubject);
		
		tsubject=new JLabel();
		tsubject.setBounds(400, 100, 200, 30);
		tsubject.setFont(new Font("Arial",Font.BOLD,16));
		tsubject.setForeground(new Color(0,102,204));
		add(tsubject);
		panel2.add(tsubject);
		
		ltotal_mark=new JLabel("TOTAL MARKS: ");
		ltotal_mark.setBounds(200, 150, 200, 30);
		ltotal_mark.setFont(new Font("Arial",Font.BOLD,16));
		ltotal_mark.setForeground(new Color(0,102,204));
		add(ltotal_mark);
		panel2.add(ltotal_mark);
		
		ttotal_mark=new JLabel();
		ttotal_mark.setBounds(400, 150, 200, 30);
		ttotal_mark.setFont(new Font("Arial",Font.BOLD,16));
		ttotal_mark.setForeground(new Color(0,102,204));
		add(ttotal_mark);
		panel2.add(ttotal_mark);
		
		lscore=new JLabel("SCORE:");
		lscore.setBounds(200, 200, 200, 30);
		lscore.setFont(new Font("Arial",Font.BOLD,16));
		lscore.setForeground(new Color(0,102,204));
		add(lscore);
		panel2.add(lscore);
		
		tscore=new JLabel();
		tscore.setBounds(400, 200, 200, 30);
		tscore.setFont(new Font("Arial",Font.BOLD,16));
		tscore.setForeground(new Color(0,102,204));
		add(tscore);
		panel2.add(tscore);
		
		lcorrect_ans=new JLabel("CORRECT ANSWER:");
		lcorrect_ans.setBounds(200, 250, 200, 30);
		lcorrect_ans.setFont(new Font("Arial",Font.BOLD,16));
		lcorrect_ans.setForeground(new Color(0,102,204));
		add(lcorrect_ans);
		panel2.add(lcorrect_ans);
		
		tcorrect_ans=new JLabel();
		tcorrect_ans.setBounds(400, 250, 200, 30);
		tcorrect_ans.setFont(new Font("Arial",Font.BOLD,16));
		tcorrect_ans.setForeground(new Color(0,102,204));
		add(tcorrect_ans);
		panel2.add(tcorrect_ans);
		
		lincorrect_ans=new JLabel("INCORRECT ANSWER:");
		lincorrect_ans.setBounds(200, 300, 200, 30);
		lincorrect_ans.setFont(new Font("Arial",Font.BOLD,16));
		lincorrect_ans.setForeground(new Color(0,102,204));
		add(lincorrect_ans);
		panel2.add(lincorrect_ans);
		
		tincorrect_ans=new JLabel();
		tincorrect_ans.setBounds(400, 300, 200, 30);
		tincorrect_ans.setFont(new Font("Arial",Font.BOLD,16));
		tincorrect_ans.setForeground(new Color(0,102,204));
		add(tincorrect_ans);
		panel2.add(tincorrect_ans);
		
		lgrade=new JLabel("GRADE");
		lgrade.setBounds(200, 350, 200, 30);
		lgrade.setFont(new Font("Arial",Font.BOLD,16));
		lgrade.setForeground(new Color(0,102,204));
		add(lgrade);
		panel2.add(lgrade);
		
		tgrade=new JLabel();
		tgrade.setBounds(400, 350, 200, 30);
		tgrade.setFont(new Font("Arial",Font.BOLD,16));
		tgrade.setForeground(new Color(0,102,204));
		add(tgrade);
		panel2.add(tgrade);
		
		
		dum=new JLabel();
		dum.setBounds(200, 400, 200, 30);
		dum.setFont(new Font("Arial",Font.BOLD,16));
		dum.setForeground(new Color(0,102,204));
		add(dum);
		panel2.add(dum);
		
		
		
		
		
		
		
		
		
		
		
	
		
		//panel 2
		
	
		//panel3
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
			model.addColumn("SELECTED ID");
			model.addColumn("CORRECT ID");
	       
	        table.setModel(model);
	       
	        // Add the table to the panel
	       panel3.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		
	       
	       
	       
	       
	       tstatus=new JLabel();
			tstatus.setBounds(0, 0, 0, 0);
			tstatus.setFont(new Font("Arial",Font.BOLD,16));
			tstatus.setForeground(new Color(0,102,204));
			add(tstatus);
			
			//panel4
		
			     
				    
			
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new result();

	}
	
	   
	
	
	void fillExampaperId()
	{
		String sid=id.getText();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select exampaper_id from tep_result where user_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, sid);
			rs=st.executeQuery();
			while(rs.next())
			{
				texampaper_id.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	
	void fillGrade()
	{
		String id=texampaper_id.getSelectedItem().toString();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select grade from tep_result where exampaper_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, id);
			rs=st.executeQuery();
			if(rs.next())
			{
				tgrade.setText(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}

	void resultTable()
	{
		String a =texampaper_id.getSelectedItem().toString() ;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			String sql="select question_id,option_id,correct_id from tep_exam where exampaper_id=?";
			st=con.prepareStatement(sql);
			
			st.setString(1, a);
			rs=st.executeQuery();
			
			while(rs.next())
			{
				
				model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3)});
				
			}
			
			con.close();
		}
		catch(Exception ex) {}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==texampaper_id)
		{
			resultTable();
			String k=texampaper_id.getSelectedItem().toString();
			result_util_data obj=new result_util_data();
			String s[]=obj.findData(k);
			//a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf
			if (s!=null) {
				tsubject.setText(s[0]);
				ttotal_mark.setText(s[1]);
				tscore.setText(s[2]);
				tcorrect_ans.setText(s[3]);
				tincorrect_ans.setText(s[4]);
				tgrade.setText(s[5]);
				tstatus.setText(s[6]);
				exam_date.setText(s[7]);
				a=s[3];
				b=s[4];
				res_chart();
				
			}
				else
				{
					JOptionPane.showMessageDialog(this, "Data not found..");
				}
			
			
			
		
			
		
			}
		 
		
		
		}
	void res_chart() {
		int correct_ans= Integer.parseInt(a);
		int incorrect_ans= Integer.parseInt(b);
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Correct Answer", correct_ans);
        dataset.setValue("Incorrect Answer", incorrect_ans);
        
        
        

        JFreeChart chart = ChartFactory.createPieChart3D("Exam Result Chart  ["+texampaper_id.getSelectedItem().toString()+"]", dataset, false, false, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setForegroundAlpha(0.6f);
        plot.setCircular(true);
        plot.setLabelFont(new Font("Arial",Font.BOLD,16));
        plot.setLabelBackgroundPaint(new Color(135,206,250));
       

        panel4.add(new ChartPanel(chart));
        panel4.setPreferredSize(new Dimension(800, 600));
		
	}
		
	

}
