package com.xyz.hbms.testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xyz.hbms.dao.RoomDaoImpl;
import com.xyz.hbms.model.RoomDetails;

public class RoomMethodJunit {
	
	static RoomDaoImpl testRoom;
	
	 @BeforeClass
	 public static void beforeClass() {
	 System.out.println("RoomDaoTest->begin");
	 testRoom = new RoomDaoImpl();
	 }



	 @Test
	 public void addRoomTestPositive() throws SQLException {
		 RoomDetails room = new RoomDetails("H103","100","Deluxe",1980.92,1);
		 boolean actualResult = testRoom.addRoomDetails(room);
		 boolean expectedResult = true;
		 assertEquals(actualResult, expectedResult);
		 
	 }
	 
	 
	 
	 @Test
	 public void addRoomTestNegative() throws SQLException {
		 RoomDetails room = new RoomDetails("H103","100","Deluxe",1980.92,1);
		 boolean actualResult = testRoom.addRoomDetails(room);
		 boolean expectedResult = false;
		 assertEquals(actualResult, expectedResult);
		 
	 }
	
	@Test
	 public void getRatePerNightTestPositive() {
		 double actualRoomRate = testRoom.getPerNight("R109");
		 double expectedRoomRate = 1980.92;
		 assertEquals(actualRoomRate, expectedRoomRate, 0.01);
		 
	 }
	
	@Test
	 public void getRatePerNightTestNegative() {
		 double actualRoomRate = testRoom.getPerNight("R109");
		 double expectedRoomRate = 1900.92;
		 assertEquals(actualRoomRate, expectedRoomRate, 0.01);
		 
	 }
	 
	 @AfterClass
	 public static void afterClass() {
	 System.out.println("RoomDaoTest->end");
	 testRoom=null;
	 }

}
