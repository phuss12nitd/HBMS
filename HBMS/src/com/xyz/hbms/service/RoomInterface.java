package com.xyz.hbms.service;

import java.sql.SQLException;
import java.util.List;

import com.xyz.hbms.model.RoomDetails;

public interface RoomInterface {
	
	public List<RoomDetails> listAll(String hotelId) throws SQLException;
	public boolean insertRoomDetails(RoomDetails roomDetails);
	public boolean removeRoomDetails(String roomId);
	public boolean updateRoomPerNight(String roomId, double roomDiscountPercentage);
	public RoomDetails findRoomById(String roomId) throws SQLException;
	public int updateAvailability(String roomId, int availability);

}
