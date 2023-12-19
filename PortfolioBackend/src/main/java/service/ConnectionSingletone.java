package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingletone  {
	 private static Connection con = null;
	private ConnectionSingletone() {
		
	}
	
	public static Connection getConnection() {
		
	 String DB_URL = "jdbc:mysql://localhost:3306/portfolio?user=nasrinjafari&password=Dela9090!";
	  
	    
		  try {
			if(con == null || con.isClosed()) {
			
			        	Class.forName("com.mysql.jdbc.Driver");
			            con = DriverManager.getConnection(DB_URL);
			  }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		  return con;
	}
	
	public static void closeConnection() {
			  try {
				if(con != null && !con.isClosed()) {
				
			         con.close();
		
				  }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
