package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.dao.RoomDao;
import com.xyz.hbms.model.RoomDetails;

public class RoomImplementation implements RoomInterface {

	private RoomDao roomDao;
	public RoomDao getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Override
	public List<RoomDetails> listAll() throws SQLException {
		 return getRoomDao().showAll();
	}

	@Override
	public boolean insertRoomDetails(RoomDetails roomDetails) {
		 return getRoomDao().addRoomDetails(roomDetails);
	}

	@Override
	public boolean removeRoomDetails(String roomId) {
		return getRoomDao().deleteRoomDetails(roomId);
		
	}

	@Override
	public boolean updateRoomPerNight(String roomId, double roomDiscountPercentage) {
		return getRoomDao().updateRoomPerNight(roomId, roomDiscountPercentage);
		
	}

}
