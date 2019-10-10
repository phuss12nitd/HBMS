package com.xyz.hbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xyz.hbms.db.ConnectionFactory;
import com.xyz.hbms.model.User;

public class UserDaoImpl implements UserDao {

	
	
	
	//getting connection from ConnectionFactory
	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	
	@Override
	public boolean addUser(User user) {
		String SQL = "INSERT INTO USERS VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();
		int rs = 0;
		try {
			PreparedStatement stat = conn.prepareStatement(SQL);
			stat.setString(1, user.getUserId());
			stat.setString(2, user.getUserPassword());
			stat.setString(3, user.getUserRole());
			stat.setString(4, user.getUserName());
			stat.setString(5, user.getMobileNo());
			stat.setString(6, user.getPhone());
			stat.setString(7, user.getAddress());
			stat.setString(8, user.getEmail());
			 rs =stat.executeUpdate();
			System.out.println(rs + " user registered");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			if(rs == 1)
				return true;
			return false;
	}




	@Override
	public String getRole(String username, String password) throws SQLException {
		String SQL = "SELECT USER_ROLE FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";
		Connection conn = getConnection();
		ResultSet rs = null;
		String role = null;
		try {
			PreparedStatement stat= conn.prepareStatement(SQL);
			stat.setString(1, username);
			stat.setString(2, password);
			rs = stat.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while(rs.next())
		{
			 role = (rs.getString("USER_ROLE"));
		}
		
		return role;
	}

}
