package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class result_util_data {
	
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String examid,String userid,String name,String email,String dob,String gender,String subject,String tques,
			String eachmark,String tmark,String cans,String incans,String score, String grade,String exam_date,String status)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_result values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, examid);
			st.setString(2, userid);
			st.setString(3,name);
			st.setString(4,email);
			st.setString(5,dob);
			st.setString(6,gender);
			st.setString(7,subject);
			st.setString(8,tques);
			st.setString(9, eachmark);
			st.setString(10, tmark);
			st.setString(11,cans);
			st.setString(12,incans);
			st.setString(13,score);
			st.setString(14,grade);
			st.setString(15,exam_date);
			st.setString(16,status);
			int exe=st.executeUpdate();
			con.close();
			return "Result Upload";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}

	
	public String[] findData(String a)
	{
		String x[]=new String[8];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  exampaper_subject,tmarks,score,correct_ans,incorrect_ans,grade,status,exam_date from tep_result where exampaper_id=?";
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
