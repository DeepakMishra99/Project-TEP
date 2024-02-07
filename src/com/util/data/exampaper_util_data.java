package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class exampaper_util_data {

	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String exampaperid,String sub,String noq,String dur,String perques,String qid,String ques,String op1id,String op1,String op2id,String op2,String op3id,String op3,String op4id,String op4,String copid)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_exampaper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1,exampaperid );
			st.setString(2, sub);
			st.setString(3,noq);
			st.setString(4,dur);
			st.setString(5,perques);
			st.setString(6,qid);
			st.setString(7,ques);
			st.setString(8,op1id);
			st.setString(9, op1);
			st.setString(10, op2id);
			st.setString(11,op2);
			st.setString(12,op3id);
			st.setString(13,op3);
			st.setString(14,op4id);
			st.setString(15,op4);
			st.setString(16,copid);
			st.setString(17,"1");
			
			int exe=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}
	
	
	
	public String deleteData(String id)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="delete from tep_exampaper where exampaper_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, id);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Delete";
		}
		catch(Exception ex) {
			return ex.toString();
		}


	}
	
	
	public String deleteQues(String id)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="delete from tep_exampaper where question_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, id);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Delete";
		}
		catch(Exception ex) {
			return ex.toString();
		}


	}
	
	public String updateQues(String quesid,String ques,String opt1,String opt2,String opt3,String opt4,String correctid)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update tep_exampaper set question=?,option1=?,option2=?,option3=?,option4=?,correct_id=? where question_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, ques);
			st.setString(2, opt1);
			st.setString(3,opt2);
			st.setString(4,opt3);
			st.setString(5,opt4);
			st.setString(6,correctid);
			st.setString(7,quesid);
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}
	
	public String updateExampaper(String stat,String id)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update tep_exampaper set status=? where exampaper_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, stat);
			st.setString(2, id);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}

	}
	
	public String[] findExtra(String a)
	{
		String x[]=new String[4];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select exampaper_subject,exampaper_ques,duration,each from tep_exampaper where exampaper_id=? group by exampaper_subject,exampaper_ques,duration,each";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				rs=st.executeQuery();
				
				if (rs.next())
				{
					x[0]=rs.getString(1);
					x[1]=rs.getString(2);
					x[2]=rs.getString(3);
					x[3]=rs.getString(4);
					
				
				}
				else
				{
					System.out.println("Wrong");
					x=null;
				}
				return x;
			}
		catch(Exception ex) {
			return null;
		}


}
	
	public String[] findData(String a)
	{
		String x[]=new String[11];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select question_id,question,option1_id,option1,option2_id,option2,option3_id,option3,option4_id,option4,correct_id from tep_exampaper where question_id=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				rs=st.executeQuery();
				
				if (rs.next())
				{
					x[0]=rs.getString(1);
					x[1]=rs.getString(2);
					x[2]=rs.getString(3);
					x[3]=rs.getString(4);
					x[4]=rs.getString(5);
					x[5]=rs.getString(6);
					x[6]=rs.getString(7);
					x[7]=rs.getString(8);
					x[8]=rs.getString(9);
					x[9]=rs.getString(10);
					x[10]=rs.getString(11);
				
				}
				else
				{
					System.out.println("Wrong");
					x=null;
				}
				return x;
			}
		catch(Exception ex) {
			return null;
		}


}
	



public String[] findAllData()
{
	String x[]=new String[16];
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select exampaper_id,exampaper_subject,examapaper_ques,duration,each,question_id,question,option1_id,option1,option2_id,option2,option3_id,option3,option4_id,option4,correct_id from tep_exampaper where status=?";
			st=con.prepareStatement(sql);
			st.setString(1, "1");
			rs=st.executeQuery();
			
			if (rs.next())
			{
				x[0]=rs.getString(1);
				x[1]=rs.getString(2);
				x[2]=rs.getString(3);
				x[3]=rs.getString(4);
				x[4]=rs.getString(5);
				x[5]=rs.getString(6);
				x[6]=rs.getString(7);
				x[7]=rs.getString(8);
				x[8]=rs.getString(9);
				x[9]=rs.getString(10);
				x[10]=rs.getString(11);
				x[11]=rs.getString(12);
				x[12]=rs.getString(13);
				x[13]=rs.getString(14);
				x[14]=rs.getString(15);
				x[15]=rs.getString(16);
			
			}
			else
			{
				System.out.println("Wrong");
				x=null;
			}
			return x;
		}
	catch(Exception ex) {
		return null;
	}


}

}