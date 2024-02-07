package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class exam_util_data {

	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String exampaperid,String userid,String question_id,String option_id,String correct_id)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_exam values(?,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1,exampaperid );
			st.setString(2, userid);
			st.setString(3,question_id);
			st.setString(4,option_id);
			st.setString(5,correct_id);
			st.setString(6,"1");
			
			
			int exe=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}
	

public String updateData(String exampaperid,String userid,String question_id,String option_id,String correct_id)
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		String sql="update tep_exam set exampaper_id=?,user_id=?,option_id=?,correct_id=?,status=? where question_id=?";
		st=con.prepareStatement(sql);
		st.setString(1,exampaperid );
		st.setString(2, userid);
		st.setString(3,option_id);
		st.setString(4,correct_id);
		st.setString(5,"1");
		st.setString(6,question_id);
		int as=st.executeUpdate();
		con.close();
		return "Record Updated";
	}
	catch(Exception ex) {
		return ex.toString();
	}


}
}
