package com.secure.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	static Connection con;
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/secure_cipertext","root","root");
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return con;
	}

}
