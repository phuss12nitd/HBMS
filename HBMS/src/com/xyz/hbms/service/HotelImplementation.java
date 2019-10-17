package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.dao.HotelDao;
import com.xyz.hbms.dao.HotelDaoImpl;
import com.xyz.hbms.model.Hotel;

public class HotelImplementation implements HotelInterface {

	HotelDao hotelImpl = new HotelDaoImpl();
	@Override
	public List<Hotel> listAllHotels() throws SQLException {
		
		return hotelImpl.showAll();
		
	}

	@Override
	public void bookRoom() {
		// TODO Auto-generated method stub
		
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
		return hotelImpl.addHotel(hotel);
	}

	@Override
	public boolean removeHotel(String id) throws SQLException {
		return hotelImpl.deleteHotel(id);
	}

	@Override
	public boolean changeHotelDescription(String hotelId, String hotelDescription) {
		return hotelImpl.updateHotelDescription(hotelId, hotelDescription);
	}

	@Override
	public boolean includeSpecialOffers(String hotelId, double percentageDiscount) {
		return hotelImpl.updateSpecialOffers(hotelId, percentageDiscount);
		
	}
	
	

}
