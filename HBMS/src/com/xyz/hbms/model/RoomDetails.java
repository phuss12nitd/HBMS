package com.xyz.hbms.model;

public class RoomDetails {
	private String hotelId;
	private String roomId;
	private String roomNo;
	private String roomType;
	private double perNight;
	private int availability;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getPerNight() {
		return perNight;
	}

	public void setPerNight(double perNight) {
		this.perNight = perNight;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int i) {
		this.availability = i;
	}

	public RoomDetails(String hotelId, String roomId, String roomNo, String roomType, double perNight,
			int availability) {
		super();
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.perNight = perNight;
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "RoomDetails [hotelId=" + hotelId + ", roomId=" + roomId + ", roomNo=" + roomNo + ", roomType="
				+ roomType + ", perNight=" + perNight + ", availability=" + availability + "]";
	}

	public RoomDetails() {
	}
}
