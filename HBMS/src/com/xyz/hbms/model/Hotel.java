package com.xyz.hbms.model;

import java.util.Comparator;

public class Hotel {
	
	private String hotelId;
	private String city;
	private String hotelName;
	private String address;
	private String description;
	private double avgRatePerNight;
	private String phoneNo1;
	private String phoneNo2;
	private String rating;
	private String email;
	private String fax;
	
	
	public Hotel()
	{
		super();
	}


	public String getHotelId() {
		return hotelId;
	}


	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getAvgRatePerNight() {
		return avgRatePerNight;
	}


	public void setAvgRatePerNight(double avgRatePerNight) {
		this.avgRatePerNight = avgRatePerNight;
	}


	public String getPhoneNo1() {
		return phoneNo1;
	}


	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}


	public String getPhoneNo2() {
		return phoneNo2;
	}


	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	@Override
	public String toString() {
		return "Hotel Details \n[Hotel Id=" + hotelId + ", City=" + city + ", Hotel Name=" + hotelName + ", Address=" + address
				+ "\nDescription=" + description + "\nAvg Rate Per Night=" + avgRatePerNight + "PhoneNo1=" + phoneNo1
				+ ", PhoneNo2=" + phoneNo2 + "\nRating=" + rating + ", Email=" + email + ", Fax=" + fax + "]\n\n";
	}
	
	
	public static Comparator<Hotel> hotelRatingComparator = new Comparator<Hotel>() {

		
		@Override
		public int compare(Hotel h1, Hotel h2) {
			String ratingH1 = h1.getRating();
			String ratingH2 = h2.getRating();
			
			return ratingH1.compareTo(ratingH2);
		}};
	
		
		public static Comparator<Hotel> hotelPriceComparator = new Comparator<Hotel>() {

			
			@Override
			public int compare(Hotel h1, Hotel h2) {
				double priceH1 = h1.getAvgRatePerNight();
				double priceH2 = h2.getAvgRatePerNight();
				
				if(priceH1 > priceH2)
					return 1;
				return 0;
			}};
		

}
