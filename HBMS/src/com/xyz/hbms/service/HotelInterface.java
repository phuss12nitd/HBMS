package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.model.Hotel;

public interface HotelInterface {
	
	public List<Hotel> listAllHotels() throws SQLException;
	public void bookRoom();
	public List<Hotel> searchByPrice(int min, int max);
	public List<Hotel> searchByLocation(String city);
	public boolean addHotel(Hotel hotel) throws SQLException;
	public boolean removeHotel(String id) throws SQLException;
	public boolean changeHotelDescription(String hotelId, String hotelDescription); 
}
