package com.xyz.hbms.testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xyz.hbms.dao.HotelDaoImpl;
import com.xyz.hbms.model.Hotel;

public class HotelMethodJunit {
	
	static HotelDaoImpl testHotel;
	
	 @BeforeClass
	 public static void beforeClass() {
	 System.out.println("HotelDaoTest->begin");
	 testHotel = new HotelDaoImpl();
	 }



	 @Test
	 public void addHotelTestPositive() throws SQLException {
		 Hotel hotel = new Hotel("","Jamshedpur","IBIS","Karunchi","Beautiful",499,"7843979","74398233","4.1","phuss@gmail.com","5433544");
		 boolean actualResult = testHotel.addHotel(hotel);
		 boolean expectedResult = true;
		 assertEquals(actualResult, expectedResult);
		 
	 }
	 
	 
	 @Test
	 public void addHotelTestNegative() throws SQLException {
		 Hotel hotel = new Hotel("","Jamshedpur","IBIS","Karunchi","Beautiful",499,"7843979","74398233","4.1","phuss@gmail.com","5433544");
		 boolean actualResult = testHotel.addHotel(hotel);
		 boolean expectedResult = false;
		 assertEquals(actualResult, expectedResult);
		 
	 }
	


	 @AfterClass
	 public static void afterClass() {
	 System.out.println("HotelDaoTest->end");
	 testHotel=null;
	 }

}
