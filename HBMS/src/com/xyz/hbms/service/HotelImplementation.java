package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.dao.HotelDao;
import com.xyz.hbms.dao.HotelDaoImpl;
import com.xyz.hbms.exception.HotelNotFoundException;
import com.xyz.hbms.model.Hotel;

public class HotelImplementation implements HotelInterface {

	HotelDao hotelImpl = new HotelDaoImpl();
	
	final Logger logger = Logger.getLogger(HotelImplementation.class.getName());
	public HotelImplementation() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	@Override
	public List<Hotel> listAllHotels() throws SQLException {
		
		return hotelImpl.showAll();
		
	}

	public List<Hotel> searchByPrice(int min, int max) {
		List<Hotel> list = hotelImpl.searchByPrice(min, max);
		return list;
	}
	public List<Hotel> searchByLocation(String city) {
		List<Hotel> list = hotelImpl.searchByLocation(city);
		return list;
	}

	@Override
	public boolean addHotel(Hotel hotel) throws SQLException {
		boolean result = hotelImpl.addHotel(hotel);
		if(result) {
			logger.info("New Hotel Added");
			return result;
		}
		return result;
	}

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
