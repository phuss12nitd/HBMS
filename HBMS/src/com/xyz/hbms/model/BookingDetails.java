package com.xyz.hbms.model;



public class BookingDetails {

	private String bookingId;
	private String roomId;
	private String userId;
	private String bookedFrom;
	private String bookedTo;
	private int numberOfAdults;
	private int numberOfChildren;
	private double amount;
	
	public BookingDetails() {
	}

	public BookingDetails(String bookingId, String roomId, String userId, String bookedFrom, String bookedTo,
			int numberOfAdults, int numberOfChildren, double amount) {
		super();
		this.bookingId = bookingId;
		this.roomId = roomId;
		this.userId = userId;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.amount = amount;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(String bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public String getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(String bookedTo) {
		this.bookedTo = bookedTo;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", roomId=" + roomId + ", userId=" + userId + ", bookedFrom="
				+ bookedFrom + ", bookedTo=" + bookedTo + ", numberOfAdults=" + numberOfAdults + ", numberOfChildren="
				+ numberOfChildren + ", amount=" + amount + "]";
	}
	
	
}
