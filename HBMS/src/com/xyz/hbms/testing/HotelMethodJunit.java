package com.xyz.hbms.testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

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
		Hotel hotel = new Hotel("", "Jamshedpur", "IBIS", "Karunchi", "Beautiful", 499, "7843979", "74398233", "4.1",
				"phuss@gmail.com", "5433544");
		boolean actualResult = testHotel.addHotel(hotel);
		boolean expectedResult = true;
		assertEquals(actualResult, expectedResult);

	}

	@Test
	public void addHotelTestNegative() throws SQLException {
		Hotel hotel = new Hotel("", "Jamshedpur", "IBIS", "Karunchi", "Beautiful", 499, "7843979", "74398233", "4.1",
				"phuss@gmail.com", "5433544");
		boolean actualResult = testHotel.addHotel(hotel);
		boolean expectedResult = false;
		assertEquals(actualResult, expectedResult);

	}

	@Test
	public void updateHotelDescTestPositive() {
		boolean actualResult = testHotel.updateHotelDescription("H100", "Magnificent View");
		boolean expectedResult = true;
		assertEquals(actualResult, expectedResult);

	}

	@Test
	public void updateHotelDescTestNegative() {
		boolean actualResult = testHotel.updateHotelDescription("H100", "Magnificent View");
		boolean expectedResult = false;
		assertEquals(actualResult, expectedResult);

	}

	@Test
	public void searchByLocationTestPositive() {
		List<Hotel> hotelList = testHotel.searchByLocation("PUNE");
		int actualSize = hotelList.size();
		int expectedSize = 3;
		assertEquals(actualSize, expectedSize);

	}

	@Test
	public void searchByLocationTestNegative() {
		List<Hotel> hotelList = testHotel.searchByLocation("BANGALORE");
		int actualSize = hotelList.size();
		int expectedSize = 3;
		assertEquals(actualSize, expectedSize);

	}

	@Test
	public void searchByLocationPositive() {
		List<Hotel> hotelList = testHotel.searchByPrice(1000, 3000);
		int actualSize = hotelList.size();
		int expectedSize = 2;
		assertEquals(actualSize, expectedSize);

	}

	@Test
	public void searchByPriceTestNegative() {
		List<Hotel> hotelList = testHotel.searchByPrice(1000, 2000);
		int actualSize = hotelList.size();
		int expectedSize = 3;
		assertEquals(actualSize, expectedSize);

	}

	@AfterClass
	public static void afterClass() {
		System.out.println("HotelDaoTest->end");
		testHotel = null;
	}

}
