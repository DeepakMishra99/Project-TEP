package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class subject_util_data {
	

	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String id,String name)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_subject values(?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, name);
		
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
			String sql="delete from tep_subject where subject_id=?";
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
	
	public String updateData(String name,String id)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update tep_subject set subject_name=? where subject_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2,id);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}
	
	public String[] findData(String a)
	{
		String x[]=new String[7];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  subject_name from tep_subject where subject_id=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				rs=st.executeQuery();
				
				if (rs.next())
				{
					x[0]=rs.getString(1);
				
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
	
	public String insertStudentSub(String id,String name)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_studentsubject values(?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, name);
			st.setInt(3, 1);
		
			int exe=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}
	
	public String updateStudentSub(String stat,String id,String name)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update tep_studentsubject set status=? where user_id=? and subject_id=?";
			st=con.prepareStatement(sql);
			st.setString(1,stat);
			st.setString(2,id);
			st.setString(3,name);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
			return ex.toString();
		
		}



}

}
