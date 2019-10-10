package com.xyz.hbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xyz.hbms.db.ConnectionFactory;

import com.xyz.hbms.model.RoomDetails;

public class RoomDaoImpl implements RoomDao {

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public List<RoomDetails> showAll() throws SQLException {
		List<RoomDetails> roomList = new ArrayList<RoomDetails>();
		ResultSet resultSet = null;
		String SQL = "SELECT * FROM ROOMDETAILS";
		Connection connection = getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement(SQL);
			resultSet = stat.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		while (resultSet.next()) {
			RoomDetails roomDetails = new RoomDetails();
			roomDetails.setHotelId(resultSet.getString("HOTEL_ID"));
			roomDetails.setRoomId(resultSet.getString("ROOM_ID"));
			roomDetails.setRoomNo(resultSet.getString("ROOM_NO"));
			roomDetails.setRoomType(resultSet.getString("ROOM_TYPE"));
			roomDetails.setPerNight(resultSet.getInt("PER_NIGHT_RATE"));
			roomDetails.setAvailability(resultSet.getBoolean("AVAILABILITIES"));
			roomList.add(roomDetails);
		}

		return roomList;
	}

	@Override
	public boolean addRoomDetails(RoomDetails roomDetails) {
		String SQL = "INSERT INTO ROOMDETAILS VALUES ( ?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();
		int result = 0;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SQL);
			preparedStatement.setString(1, roomDetails.getHotelId());
			preparedStatement.setString(2, roomDetails.getRoomId());
			preparedStatement.setString(3, roomDetails.getRoomNo());
			preparedStatement.setString(4, roomDetails.getRoomType());
			preparedStatement.setDouble(5, roomDetails.getPerNight());
			preparedStatement.setBoolean(6, roomDetails.getAvailability());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 1) {
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	public boolean deleteRoomDetails(String roomId) {
		String SQL = "DELETE FROM ROOMDETAILS WHERE ROOM_ID= ?";
		Connection connection = getConnection();
		int result = 0;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL);
			prepareStatement.setString(1, roomId);
			result = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean updateRoomPerNight(String roomId, double roomDiscountPercentage) {
		String SQL = "UPDATE ROOMDETAILS SET PER_NIGHT_RATE = ? where ROOM_ID = '" + roomId + "'";
		Connection connection = getConnection();
		int result = 0;
		double updatePerNight = 0;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL);
			double perNight = getPerNight(roomId);
			updatePerNight = perNight * (1 - (roomDiscountPercentage / 100));
			prepareStatement.setDouble(1, updatePerNight);
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result == 0.0) {
			return false;
		} else {
			return true;
		}
	}

	public double getPerNight(String roomId) {
		String SQL = "SELECT PER_NIGHT_RATE FROM ROOMDETAILS WHERE ROOM_ID = '" + roomId + "'";
		Connection connection = getConnection();
		double result = 0.0;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL);
			while (resultSet.next()) {
				result = resultSet.getDouble("PER_NIGHT_RATE");
				break;
			}
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
