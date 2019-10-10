package com.xyz.hbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xyz.hbms.db.ConnectionFactory;
import com.xyz.hbms.model.Hotel;

public class HotelDaoImpl implements HotelDao{


	//getting connection from ConnectionFactory
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
	public List<Hotel> showAll() throws SQLException {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		ResultSet rs=null;
		String SQL = "SELECT * FROM HOTEL";
		Connection conn = getConnection();
		try {
			PreparedStatement stat = conn.prepareStatement(SQL);
			rs = stat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while(rs.next())
		{
			Hotel hotel = new Hotel();
			hotel.setAddress(rs.getString("ADDRESS"));
			hotel.setAvgRatePerNight(rs.getDouble("AVG_RATE_PER_NIGHT"));
			hotel.setCity(rs.getString("CITY"));
			hotel.setDescription(rs.getString("DESCRIPTION"));
			hotel.setEmail(rs.getString("EMAIL"));
			hotel.setFax(rs.getString("FAX"));
			hotel.setHotelId(rs.getString("HOTEL_ID"));
			hotel.setHotelName(rs.getString("HOTEL_NAME"));
			hotel.setPhoneNo1(rs.getString("PHONE_NO1"));
			hotel.setPhoneNo2(rs.getString("PHONE_NO2"));
			hotel.setRating(rs.getString("RATING"));
			hotelList.add(hotel);
		
		}
		return hotelList;
	}
	
	public List<Hotel> searchByPrice(int min, int max) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		ResultSet rs=null;
		Connection conn = getConnection();
		String query = "SELECT * FROM HOTEL WHERE AVG_RATE_PER_NIGHT BETWEEN "+min+" AND "+max;
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			rs = stat.executeQuery();
			while(rs.next())
			{
				Hotel hotel = new Hotel();
				hotel.setAddress(rs.getString("ADDRESS"));
				hotel.setAvgRatePerNight(rs.getDouble("AVG_RATE_PER_NIGHT"));
				hotel.setCity(rs.getString("CITY"));
				hotel.setDescription(rs.getString("DESCRIPTION"));
				hotel.setEmail(rs.getString("EMAIL"));
				hotel.setFax(rs.getString("FAX"));
				hotel.setHotelId(rs.getString("HOTEL_ID"));
				hotel.setHotelName(rs.getString("HOTEL_NAME"));
				hotel.setPhoneNo1(rs.getString("PHONE_NO1"));
				hotel.setPhoneNo2(rs.getString("PHONE_NO2"));
				hotel.setRating(rs.getString("RATING"));
				hotelList.add(hotel);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return hotelList;
	}
	public List<Hotel> searchByLocation(String city) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		ResultSet rs=null;
		Connection conn = getConnection();
		String query = "SELECT * FROM HOTEL WHERE CITY = '"+city + "'";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			rs = stat.executeQuery();
			while(rs.next())
			{
				Hotel hotel = new Hotel();
				hotel.setAddress(rs.getString("ADDRESS"));
				hotel.setAvgRatePerNight(rs.getDouble("AVG_RATE_PER_NIGHT"));
				hotel.setCity(rs.getString("CITY"));
				hotel.setDescription(rs.getString("DESCRIPTION"));
				hotel.setEmail(rs.getString("EMAIL"));
				hotel.setFax(rs.getString("FAX"));
				hotel.setHotelId(rs.getString("HOTEL_ID"));
				hotel.setHotelName(rs.getString("HOTEL_NAME"));
				hotel.setPhoneNo1(rs.getString("PHONE_NO1"));
				hotel.setPhoneNo2(rs.getString("PHONE_NO2"));
				hotel.setRating(rs.getString("RATING"));
				hotelList.add(hotel);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return hotelList;
	}

	@Override
	public boolean updateHotelDescription(String hotelId, String hotelDescription) {
		String SQL = "UPDATE HOTEL SET DESCRIPTION = '" + hotelDescription + "' where hotel_id ='" + hotelId
				+ "'";
		Connection connection = getConnection();
		int result = 0;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL);
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


	@Override
	public boolean addHotel(Hotel hotel) throws SQLException {
		Connection conn = getConnection();
		String query = "Insert into Hotel values (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, hotel.getHotelId());
		stat.setString(2, hotel.getCity());
		stat.setString(3, hotel.getHotelName());
		stat.setString(4, hotel.getAddress());
		stat.setString(5, hotel.getDescription());
		stat.setDouble(6, hotel.getAvgRatePerNight());
		stat.setString(7, hotel.getPhoneNo1());
		stat.setString(8, hotel.getPhoneNo2());
		stat.setString(9, hotel.getRating());
		stat.setString(10, hotel.getEmail());
		stat.setString(11, hotel.getFax());

	int rs = stat.executeUpdate();	
	if(rs==1)	
		return true;
	return false;
	}


	@Override
	public boolean deleteHotel(String id) throws SQLException {
		Connection conn = getConnection();
		String query = "Delete from Hotel where hotel_id = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, id);
		
		int rs = stat.executeUpdate();	
		if(rs==1)	
			return true;
		return false;
	}

}
