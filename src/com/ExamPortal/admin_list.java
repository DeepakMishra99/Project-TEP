package com.ExamPortal;
import java.sql.DriverManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
public class admin_list extends JFrame {
	
		JTable jt;int i=0;int j=0;
		String cols[]= {"ADMIN ID","ADMIN NAME","ADMIN DOB","ADMIN GENDER","ADMIN PHONE","ADMIN EMAIL","ADMIN ADDRESS","STATUS"};
		Connection con;Statement st;ResultSet rs;
		DefaultTableModel tb=new DefaultTableModel();
		
		public admin_list() {
		// TODO Auto-generated constructor stub
			setSize(1600,800);
			setTitle("ADMIN DATA TABLE");
			setVisible(true);
			setLocationRelativeTo(null); 
			
			tb.setColumnIdentifiers(cols);
			
			jt=new JTable();
			
			jt.setModel(tb);
			jt.setFont(new Font("Arial",Font.BOLD,14));
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jt.setBackground(new Color(204,229,255));
			jt.setForeground(new Color(0,76,153));

			fillData();
			JTableHeader header = jt.getTableHeader();
			header.setBackground(new Color(0,76,153));
			header.setForeground(new Color(255,255,255));
			header.setFont(new Font("Arial",Font.BOLD,16));
			JScrollPane jp=new JScrollPane(jt);
			add(jp);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		void fillData()
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select * from tep_admin";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next())
				{
					
					tb.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(9)});
					
				}
				
				con.close();
			}
			catch(Exception ex) {}
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new admin_list();
		}

	

}
