package com.xyz.hbms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validations {
	private static String regex = "";

	public static boolean  checkPassword(String password) {
		regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,20})";
		return Pattern.matches(regex,password);
	}
	
	public static boolean checkMobile(String mobile) {
		regex = "([6-9][0-9]{9})";
		return Pattern.matches(regex,mobile);
	}
	
	public static boolean checkPhone(String phone) {
		regex = "([0-9]{2,4}[0-9]{4,6})";
		return Pattern.matches(regex,phone);
	}
	
	public static boolean checkEmail(String email) {
		regex = "(\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b)";
		return Pattern.matches(regex,email);
	}
	
	public static boolean checkBookedFrom(String bookedFrom, String bookedTo) {
		regex = "(^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)$)";
		boolean fromDate =  Pattern.matches(regex,bookedFrom);
		boolean toDate =  Pattern.matches(regex,bookedTo);
		Date date1=null,date2=null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(bookedFrom);
			date2=new SimpleDateFormat("yyyy-MM-dd").parse(bookedTo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(date2.compareTo(date1) >= 0 && fromDate && toDate) {
			return true;
		}
		return false;
	}
	
	public static boolean checkRating(String rating) {
		if(Double.parseDouble(rating)>0 && Double.parseDouble(rating)<=5)
			return true;
		else
			return false;
	}
	
	public static boolean checkFax(String fax) {
		regex = "([0-9]{2,4}[0-9]{4,6})";
		return Pattern.matches(regex,fax);
	}
	
	public static boolean checkRoomType(String roomType) {
		if(roomType.equalsIgnoreCase("Standard")||roomType.equalsIgnoreCase("Deluxe")||roomType.equalsIgnoreCase("Executive")||roomType.equalsIgnoreCase("Studio")||roomType.equalsIgnoreCase("Suite"))
			return true;
		return false;
	}
	
}
