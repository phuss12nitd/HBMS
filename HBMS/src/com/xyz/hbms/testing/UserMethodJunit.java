package com.xyz.hbms.testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xyz.hbms.dao.RoomDaoImpl;
import com.xyz.hbms.dao.UserDao;
import com.xyz.hbms.dao.UserDaoImpl;

public class UserMethodJunit {

	
	UserDao userDao = new UserDaoImpl();
	static RoomDaoImpl testRoom;
	
	 @BeforeClass
	 public static void beforeClass() {
	 System.out.println("UserDaoTest->begin");
	 testRoom = new RoomDaoImpl();
	 }



	 @Test
	 public void getRolePositive() throws SQLException {
		 String user1 = "ADMIN";
		 String userRole = userDao.getRole("ADMIN","ADMIN"); 
		 assertEquals(userRole, user1);
	 }
	 
	 
	 @Test
	 public void getRoleNgative() throws SQLException {
		 String user1 = "Employee";
		 String userRole = userDao.getRole("ADMIN","ADMIN"); 
		 assertEquals(userRole, user1);
	 }
	 
	 
	 @Test
	 public void getUserIdPositive() throws SQLException {
		 String userId = userDao.getUserId("ANANYA", "CUST104");
		 String userIdExpected = "C104";
		 assertEquals(userId, userIdExpected);
	 }
	 
	 
	 @Test
	 public void getUserIdNegative() throws SQLException {
		 String userId = userDao.getUserId("ANANYA", "CUST104");
		 String userIdExpected = "C105";
		 assertEquals(userId, userIdExpected);
	 }
	 
	 
	 @AfterClass
	 public static void afterClass() {
	 System.out.println("UserDaoTest->end");
	 testRoom=null;
	 }

}
