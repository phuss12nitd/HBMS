package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.model.BookingDetails;
import com.xyz.hbms.model.User;

public interface BookingInterface {

	public int addBooking(BookingDetails bookingDetails) throws SQLException;

	public List<BookingDetails> showBookingByHotelId(String hotelId);

	public List<User> showGuestList(String hotelId);

	public List<BookingDetails> showMyBookings(String userId);

	public List<BookingDetails> showAllBookings(String date);
}
