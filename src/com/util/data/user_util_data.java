package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class user_util_data {

	
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String id,String name,String dob,String gender,String phone,String email,String address,String password)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into tep_user values(?,?,?,?,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, name);
			st.setString(3,dob);
			st.setString(4,gender);
			st.setString(5,phone);
			st.setString(6,email);
			st.setString(7,address);
			st.setString(8,password);
			st.setInt(9,1);
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
			String sql="delete from tep_user where user_id=?";
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
	
	public String updateData(String name,String dob,String gender,String phone,String email,String address,String password,String id)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update tep_user set user_name=?,user_dob=?,user_gender=?,user_phone=?,user_email=?,user_address=?,user_password=?,status=? where user_id=?";
			st=con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, dob);
			st.setString(3,phone);
			st.setString(4,gender);
			st.setString(5,email);
			st.setString(6,address);
			st.setString(7,password);
			st.setInt(8,1);
			st.setString(9,id);
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
				String sql="select  user_name,user_dob,user_gender,user_phone,user_email,user_address,user_password from tep_user where user_id=?";
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
