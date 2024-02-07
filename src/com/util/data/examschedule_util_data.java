package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class examschedule_util_data {
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String scheduleid,String exampaperid,String date,String time)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_examschedule values(?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, scheduleid);
			st.setString(2, exampaperid);
			st.setString(3,date);
			st.setString(4,time);
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
			String sql="delete from tep_examschedule where schedule_id=?";
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
	
	public String updateData(String exampaperid,String date,String time,String scheduleid)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update tep_examschedule set exampaper_id=?,schedule_date=?,schedule_time=? where schedule_id=?";
			st=con.prepareStatement(sql);
			st.setString(1,exampaperid);
			st.setString(2,date);
			st.setString(3,time);
			st.setString(4,scheduleid);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}


}
	
	
	public String[] findAllData(String a)
	{
		String x[]=new String[4];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  * from tep_examschedule";
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
		String x[]=new String[4];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  * from tep_examschedule where schedule_id=?";
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

}
