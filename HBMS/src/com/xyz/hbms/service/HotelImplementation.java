package com.xyz.hbms.service;

import java.sql.SQLException;


import java.util.List;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.dao.HotelDao;
import com.xyz.hbms.dao.HotelDaoImpl;
import com.xyz.hbms.exception.HotelNotFoundException;
import com.xyz.hbms.model.Hotel;


/*
 * Hotel service implementation
 * It implements HotelInterface
 * It performs Hotel related operations
 * 
 */
public class HotelImplementation implements HotelInterface {

	// making an object of HotelDaoImpl to use it for implementing functions
	HotelDao hotelImpl = new HotelDaoImpl();
	
	//logger statements to keep the log generating at every function call.
	final Logger logger = Logger.getLogger(HotelImplementation.class.getName());
	public HotelImplementation() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	/*
	 * Generates a report of all the hotels present in the database
	 */
	@Override
	public List<Hotel> listAllHotels() throws SQLException {
		
		return hotelImpl.showAll();
		
	}

	/*
	 * Gives a list of hotels in a particular price range
	 * 
	 * Input:- Minimum and Maximum Price
	 * Output:- List of Hotels
	 * 
	 */
	public List<Hotel> searchByPrice(int min, int max) {
		List<Hotel> list = hotelImpl.searchByPrice(min, max);
		return list;
	}
	/*
	 *Gives a list of hotels in a particular location
	 *
	 *Input:- City name
	 *Output:- List of Hotels
	 *
	 */
	public List<Hotel> searchByLocation(String city) {
		List<Hotel> list = hotelImpl.searchByLocation(city);
		return list;
	}

	/*
	 *Registration of a new hotel is done
	 *
	 *Input:- Hotel details
	 *Output:- Boolean for success or failure
	 *
	 */
	@Override
	public boolean addHotel(Hotel hotel) throws SQLException {
		boolean result = hotelImpl.addHotel(hotel);
		if(result) {
			logger.info("New Hotel Added");
			return result;
		}
		return result;
	}

	/*
	 * Removes a hotel from the list with the hotel id entered
	 * 
	 * Input:- Hotel ID
	 * Output:- Boolean for success or failure
	 */
	@Override
	public boolean removeHotel(String id) throws SQLException, HotelNotFoundException {
		boolean result = hotelImpl.deleteHotel(id);
		if(result) {
			logger.info("Hotel with Hotel ID: "+id+" removed");
			return result;
		}
		else
			throw new HotelNotFoundException("Hotel with Hotel ID:" +id+" Not Found!");
	}

	/*
	 * Function to change the hotel's description
	 * 
	 * Input:- Hotel ID and the description string
	 * Output:- Boolean for success or failure
	 * 
	 */
	@Override
	public boolean changeHotelDescription(String hotelId, String hotelDescription) throws HotelNotFoundException {
		boolean result =  hotelImpl.updateHotelDescription(hotelId, hotelDescription);
		if(result) {
			logger.info("Hotel Description with Hotel ID: "+hotelId+" updated");
			return result;
		}
		else
			throw new HotelNotFoundException("Hotel with Hotel ID:" +hotelId+" Not Found!");
	}

	/*
	 * Function to include special offers for a hotel
	 * 
	 * Input:- Hotel ID and discount percentage
	 * Output:- Boolean for success or failure
	 * 
	 */
	@Override
	public boolean includeSpecialOffers(String hotelId, double percentageDiscount) throws HotelNotFoundException {
		boolean result =  hotelImpl.updateSpecialOffers(hotelId, percentageDiscount);
		if(result) {
			logger.info("Hotel Offers with Hotel ID: "+hotelId+" updated");
			return result;
		}
		else
			throw new HotelNotFoundException("Hotel with Hotel ID:" +hotelId+" Not Found!");
	}
	
	

}
