package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.dao.RoomDao;
import com.xyz.hbms.dao.RoomDaoImpl;
import com.xyz.hbms.model.RoomDetails;

public class RoomImplementation implements RoomInterface {

	private RoomDao roomDao= new RoomDaoImpl();
	

	@Override
	public List<RoomDetails> listAll(String HotelId) throws SQLException {
		 return roomDao.showAll(HotelId);
	}

	@Override
	public boolean insertRoomDetails(RoomDetails roomDetails) {
		 return roomDao.addRoomDetails(roomDetails);
	}

	@Override
	public boolean removeRoomDetails(String roomId) {
		return roomDao.deleteRoomDetails(roomId);
		
	}

	@Override
	public boolean updateRoomPerNight(String roomId, double roomDiscountPercentage) {
		return roomDao.updateRoomPerNight(roomId, roomDiscountPercentage);
		
	}

	@Override
	public RoomDetails findRoomById(String roomId) throws SQLException {
		return roomDao.getRoomDetails(roomId);
	}

	@Override
	public int updateAvailability(String roomId, int avail) {
		return roomDao.updateAvailability(roomId, avail);
	}

}
