package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.dao.UserDao;
import com.xyz.hbms.dao.UserDaoImpl;
import com.xyz.hbms.exception.UserNotFoundException;
import com.xyz.hbms.model.User;

public class UserImplementation implements UserInterface{

	UserDao userDao = new UserDaoImpl();
	final Logger logger = Logger.getLogger(UserImplementation.class.getName());
	
	
	
	public UserImplementation() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}


	@Override
	public boolean  registerUser(User user) {
		boolean rs = userDao.addUser(user);
		if(rs)
			logger.info("New User Registered");
		return rs;
		
	}
	
	
	@Override
	public String checkRole(String username, String password) throws SQLException, UserNotFoundException {
		String result = userDao.getRole(username, password);
		if(result!=null)
			return result;
		else
			throw new UserNotFoundException("User Not Found!");
	}


	@Override
	public String getUserId(String username, String password) throws SQLException, UserNotFoundException {
		String result =  userDao.getUserId(username, password);
		if(result!=null)
			return result;
		else
			throw new UserNotFoundException("User Not Found!");
	}

}
