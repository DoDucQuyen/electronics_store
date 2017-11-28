package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ultils.DatabaseProperties;



public class DbManager {
	private String url;
	private String username;
	private String password;
	public Connection connection;
	
	public DbManager(){
		try {
			this.url = DatabaseProperties.getProperty("url");
			this.username = DatabaseProperties.getProperty("username");
			this.password = DatabaseProperties.getProperty("password");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// open Connection
	public Connection openConnection(){
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + url,
				    username, password);
		} catch (SQLException  e) {
			connection = null;
		}
		return connection;
	}
	
	
	//close Connection
	public boolean closeConnection(){
		try {
			if(connection != null && !connection.isClosed()){
				connection.close();
				return true;
			} 
			else return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
}
