package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.dao.RoomDao;
import com.xyz.hbms.dao.RoomDaoImpl;
import com.xyz.hbms.exception.RoomNotFoundException;
import com.xyz.hbms.model.RoomDetails;

/*
 * Room service implementation
 * It implements RoomInterface
 * It performs Room related operations
 * 
 */
public class RoomImplementation implements RoomInterface {

	// making an object of RoomDaoImpl to use it for implementing functions
	private RoomDao roomDao= new RoomDaoImpl();
	//logger statements to keep the log generating at every function call.
	final Logger logger = Logger.getLogger(RoomImplementation.class.getName());

	public RoomImplementation() {
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
	}

	
	/*
	 * Generates a report of all the rooms present in a particular hotel
	 * 
	 * Input:- Hotel ID
	 * Output:- List of Rooms
	 * 
	 */
	@Override
	public List<RoomDetails> listAll(String HotelId) throws SQLException {
		 return roomDao.showAll(HotelId);
	}

	/*
	 * Registers a new Room in the database
	 * 
	 * Input:- Room Details
	 * Output:- Boolean for success or failure
	 * 
	 */
	@Override
	public boolean insertRoomDetails(RoomDetails roomDetails) {
		boolean result = roomDao.addRoomDetails(roomDetails);
		if(result) {
			logger.info("New Room Added");
			return result;
		}
		return result;
	}

	/*
	 * Removes the room details from the database of a particular room
	 * 
	 * Input:- Room ID
	 * Output:- Boolean for success or failure
	 * 
	 */
	@Override
	public boolean removeRoomDetails(String roomId) throws RoomNotFoundException {
		boolean result = roomDao.deleteRoomDetails(roomId);
		if(result) {
			logger.info("Room with Room ID: "+roomId+" removed!");
			return result;
		}
		else
			throw new RoomNotFoundException("Room with Room ID: "+roomId+" Not Found!");
		
	}

	/*
	 * Updates the room tariff of a particular room 
	 * 
	 *  Input:- Room ID, Discount Percentage
	 *  Output:- Boolean for success or failure
	 *  
	 */
	@Override
	public boolean updateRoomPerNight(String roomId, double roomDiscountPercentage) throws RoomNotFoundException {
		boolean result = roomDao.updateRoomPerNight(roomId, roomDiscountPercentage);
		if(result) {
			logger.info("Room Rate with Room ID: "+roomId+" updated!");
			return result;
		}
		else
			throw new RoomNotFoundException("Room with Room ID: "+roomId+" Not Found!");
		
		
	}

	/*
	 * Returns the room details of a particular room
	 * 
	 * Input:- Room ID
	 * Output:- Room Details
	 */
	@Override
	public RoomDetails findRoomById(String roomId) throws SQLException, RoomNotFoundException {
		RoomDetails roomDetails = roomDao.getRoomDetails(roomId);
		if(roomDetails!=null)
			return roomDetails;
		else
			throw new RoomNotFoundException("Room with Room ID: "+roomId+" Not Found!");
	}

	/*
	 * Updates the availability of a particular room
	 * 
	 * Input:- Room ID, updated availability
	 * Output:- Boolean for success or failure
	 * 
	 */
	@Override
	public int updateAvailability(String roomId, int avail) throws RoomNotFoundException {
		int result = roomDao.updateAvailability(roomId, avail);
		if(result>0)
			return result;
		else
			throw new RoomNotFoundException("Room with Room ID: "+roomId+" Not Found!");
	}

}
