package com.xyz.hbms.service;

/*
 * Booking service implementation
 * It implements BookingInterface
 * It performs booking related operations
 * 
 */

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.dao.BookingDao;
import com.xyz.hbms.dao.BookingDaoImpl;
import com.xyz.hbms.exception.HotelNotFoundException;
import com.xyz.hbms.exception.RoomNotFoundException;
import com.xyz.hbms.exception.UserNotFoundException;
import com.xyz.hbms.model.BookingDetails;
import com.xyz.hbms.model.User;

public class BookingImplementation implements BookingInterface {
	
	//logger statements to keep the log generating at every function call.
	final Logger logger = Logger.getLogger(BookingImplementation.class.getName());
	
	// making an object of BookingDaoImpl to use it for implementing functions
	BookingDao bookingDao = new BookingDaoImpl();

	public BookingImplementation() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		
	}
	
	/*
	 * Booking Details are added to the bookingdetails table
	 * 
	 * Input:- Booking Details
	 * Output:- Boolean for success or failure
	 */
	@Override
	public int addBooking(BookingDetails bookingDetails) throws SQLException, RoomNotFoundException {
		logger.info("Booking Details Added");
		return bookingDao.bookRoom(bookingDetails);
		
	}

	/*
	 * Report of all Booking Details of a selected Hotel
	 * 
	 * Input:- Hotel Id
	 * Output:-  Booking Details
	 */

	@Override
	public List<BookingDetails> showBookingByHotelId(String hotelId) throws HotelNotFoundException {
		List<BookingDetails> bookingDetails = bookingDao.viewBookingByHotelId(hotelId);
		if(bookingDetails.size()>0)
			return bookingDetails;
		else
			throw new HotelNotFoundException("Hotel with Hotel ID:" +hotelId+" Not Found!");
		
	}

	/*
	 * Report of all Guests of a selected Hotel
	 * 
	 * Input:- Hotel Id
	 * Output:- Guest List
	 */
	@Override
	public List<User> showGuestList(String hotelId) throws HotelNotFoundException {
		List<User> userList = bookingDao.showGuestList(hotelId);
		if(userList.size()>0)
			return userList;
		else
			throw new HotelNotFoundException("Hotel with Hotel ID:" +hotelId+" Not Found!");
	}

	/*
	 * Report of all Bookings made by the user
	 * 
	 * Input:- User ID
	 * Output:- List of Bookings
	 */
	@Override
	public List<BookingDetails> showMyBookings(String userId) throws UserNotFoundException {
		List<BookingDetails> bookingDetails = bookingDao.listMyBookings(userId);
		if(bookingDetails.size()>0)
			return bookingDetails;
		else
			throw new UserNotFoundException("User Not Found!");
	}

	/*
	 * Report of all the Bookings on a particular date 
	 * 
	 * Input:- Date
	 * Output:- List of Bookings
	 */
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

	/*
	 *Gives the total amount of booking
	 *
	 *Input:- Booking Details
	 *Output:- Final Amount
	 */
	@Override
	public double getFinalAmount(BookingDetails bookingDetails) {
		return bookingDao.bookingAmount(bookingDetails);
	}

}
