package com.xyz.hbms.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.xyz.hbms.dao.BookingDao;
import com.xyz.hbms.dao.BookingDaoImpl;
import com.xyz.hbms.model.BookingDetails;
import com.xyz.hbms.model.User;

public class BookingImplementation implements BookingInterface {
	BookingDao bookingDao = new BookingDaoImpl();

	@Override
	public int addBooking(BookingDetails bookingDetails) throws SQLException {

		return bookingDao.bookRoom(bookingDetails);
	}

	@Override
	public List<BookingDetails> showBookingByHotelId(String hotelId) {
		return bookingDao.viewBookingByHotelId(hotelId);
		
	}

	@Override
	public List<User> showGuestList(String hotelId) {
		return bookingDao.showGuestList(hotelId);
	}

	@Override
	public List<BookingDetails> showMyBookings(String userId) {
		return bookingDao.listMyBookings(userId);
	}

	@Override
	public List<BookingDetails> showAllBookings(String date) {
		List<BookingDetails> bookedList = bookingDao.getAllBookings();
		List<BookingDetails> bookingListDatewise = new ArrayList<BookingDetails>();
		
		for(BookingDetails bookingDetails : bookedList)
		{
			String checkInDate = bookingDetails.getBookedFrom();
			String checkOutDate = bookingDetails.getBookedTo();
			java.util.Date date1=null;
			java.util.Date date3=null;
			java.util.Date date2=null;
			try {
				date1 =new SimpleDateFormat("yyyy-MM-dd").parse(checkInDate);
				 date3=new SimpleDateFormat("yyyy-MM-dd").parse(checkOutDate);
				 date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
			
			if( date2.compareTo(date1) >= 0 && date2.compareTo(date3) <= 0)
				bookingListDatewise.add(bookingDetails);
		}
		return bookingListDatewise;
	}

	@Override
	public double getFinalAmount(BookingDetails bookingDetails) {
		return bookingDao.bookingAmount(bookingDetails);
	}

}
