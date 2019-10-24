package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.exception.HotelNotFoundException;
import com.xyz.hbms.exception.RoomNotFoundException;
import com.xyz.hbms.exception.UserNotFoundException;
import com.xyz.hbms.model.BookingDetails;
import com.xyz.hbms.model.User;

public interface BookingInterface {

	public int addBooking(BookingDetails bookingDetails) throws SQLException, RoomNotFoundException;

	public List<BookingDetails> showBookingByHotelId(String hotelId) throws HotelNotFoundException;

	public List<User> showGuestList(String hotelId) throws HotelNotFoundException;

	public List<BookingDetails> showMyBookings(String userId) throws UserNotFoundException;

	public List<BookingDetails> showAllBookings(String date);

	public double getFinalAmount(BookingDetails bookingDetails);
}
