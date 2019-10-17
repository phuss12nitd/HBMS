package com.xyz.hbms.dao;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.model.BookingDetails;
import com.xyz.hbms.model.User;

public interface BookingDao {

	public int bookRoom(BookingDetails bookingDetails) throws SQLException;

	public List<BookingDetails> viewBookingByHotelId(String hotelId);

	public List<User> showGuestList(String hotelId);

	public List<BookingDetails> listMyBookings(String userId);

	public List<BookingDetails> getAllBookings();
}