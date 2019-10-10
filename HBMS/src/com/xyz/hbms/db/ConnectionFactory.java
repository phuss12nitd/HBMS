package com.xyz.hbms.db;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory connectionFactory = null;
	
	private static Properties properties = new Properties();
	
	private ConnectionFactory() {
		try {
			
			try(InputStream inputstream = new FileInputStream(".//resources//config.properties")) {
				
				properties.load(inputstream);
				String dbDriver = properties.getProperty("db.driver");
				Class.forName(dbDriver);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
		public static ConnectionFactory getInstance() {
			if(connectionFactory == null)
				connectionFactory = new ConnectionFactory();
			return connectionFactory;
		}

		
		
		public Connection getConnection() throws SQLException{
			Connection conn = null;
			String dbUrl = properties.getProperty("db.url");
			String dbUsername = properties.getProperty("db.user");
			String dbPassword = properties.getProperty("db.password");
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			return conn;
			
		}
		
		
	

}
