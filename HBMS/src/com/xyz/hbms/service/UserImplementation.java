package com.xyz.hbms.service;

import java.sql.SQLException;

import com.xyz.hbms.dao.UserDao;
import com.xyz.hbms.dao.UserDaoImpl;
import com.xyz.hbms.model.User;

public class UserImplementation implements UserInterface{

	UserDao userDao = new UserDaoImpl();
	@Override
	public boolean  registerUser(User user) {
		boolean rs = userDao.addUser(user);
		return rs;
		
	}
	
	
	@Override
	public String checkRole(String username, String password) throws SQLException {
		return userDao.getRole(username, password);
	}


	@Override
	public String getUserId(String username, String password) throws SQLException {
		return userDao.getUserId(username, password);
	}

}
