package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.dao.UserDao;
import com.xyz.hbms.dao.UserDaoImpl;
import com.xyz.hbms.exception.UserNotFoundException;
import com.xyz.hbms.model.User;

/*
 * User service implementation
 * It implements UserInterface
 * It performs User related operations
 * 
 */
public class UserImplementation implements UserInterface{
	// making an object of UserDaoImpl to use it for implementing functions
	UserDao userDao = new UserDaoImpl();
	
	//logger statements to keep the log generating at every function call.
	final Logger logger = Logger.getLogger(UserImplementation.class.getName());
	
	
	
	public UserImplementation() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	/*
	 * Registration of a new user is done
	 * 
	 * Input:- User Details
	 * Output:- Boolean for success or failure
	 * 
	 */
	@Override
	public boolean  registerUser(User user) {
		boolean rs = userDao.addUser(user);
		if(rs)
			logger.info("New User Registered");
		return rs;
		
	}
	
	/*
	 * Returns the role of a user
	 * 
	 * Input:- Username, Password
	 * Output:- Customer || Employee
	 * 
	 */
	
	@Override
	public String checkRole(String username, String password) throws SQLException, UserNotFoundException {
		String result = userDao.getRole(username, password);
		if(result!=null)
			return result;
		else
			throw new UserNotFoundException("User Not Found!");
	}

	/*
	 * Returns the User ID of a particular user
	 * 
	 * Input:- Username, Password
	 * Output:- User ID
	 * 
	 */
	@Override
	public String getUserId(String username, String password) throws SQLException, UserNotFoundException {
		String result =  userDao.getUserId(username, password);
		if(result!=null)
			return result;
		else
			throw new UserNotFoundException("User Not Found!");
	}

}
