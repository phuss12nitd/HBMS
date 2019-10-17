package com.xyz.hbms.service;

import java.sql.SQLException;

import com.xyz.hbms.model.User;

public interface UserInterface {
	
	
	public boolean registerUser( User user);
	public String checkRole(String username, String password) throws SQLException;
	public String getUserId(String username, String password) throws SQLException;

}
