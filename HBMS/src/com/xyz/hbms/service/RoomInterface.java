package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.exception.RoomNotFoundException;
import com.xyz.hbms.model.RoomDetails;

public interface RoomInterface {
	
	public List<RoomDetails> listAll(String hotelId) throws SQLException;
	public boolean insertRoomDetails(RoomDetails roomDetails);
	public boolean removeRoomDetails(String roomId) throws RoomNotFoundException;
	public boolean updateRoomPerNight(String roomId, double roomDiscountPercentage) throws RoomNotFoundException;
	public RoomDetails findRoomById(String roomId) throws SQLException, RoomNotFoundException;
	public int updateAvailability(String roomId, int availability) throws RoomNotFoundException;

}
