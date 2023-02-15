package com.yesdosoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	public static Connection getCon() {
		Connection con=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/yesdosoft22","root","Yog@1984");
			return con;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}

public static ResultSet getUser() {
		
		Connection con=getCon();
		try {
			PreparedStatement ps=con.prepareStatement("select * from tbluser");
			ResultSet rs=ps.executeQuery();
			
			return rs;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
}
}