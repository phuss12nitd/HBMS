package com.xyz.hbms.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.xyz.hbms.model.Hotel;
import com.xyz.hbms.model.RoomDetails;
import com.xyz.hbms.model.User;
import com.xyz.hbms.service.HotelImplementation;
import com.xyz.hbms.service.RoomImplementation;
import com.xyz.hbms.service.UserImplementation;

public class MainUi {

	public static int manipulativeId = 103;
	public static void main(String[] args) throws SQLException {

		UserImplementation userImpl = new UserImplementation();
		HotelImplementation hotelImpl = new HotelImplementation();
		List<Hotel> hotelList = new ArrayList<Hotel>();
		Scanner sc = new Scanner(System.in);
		System.out.println("**********************Welcome to Hotel Booking Management System**********************");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
		int choice = 0;
		int flag = 1;
		while (flag != 0) {
			System.out.println("1. Login Page");
			System.out.println("2. Register");
			choice = sc.nextInt();
			if (choice == 1 || choice == 2) {
				flag = 0;

			} else {
				System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
			}
		}
		switch (choice) {
		case 1:
			flag = 1;
			int selection = 0;
			String username = null, password = null;
			while (flag != 0) {
				System.out.println("1. Admin");
				System.out.println("2. Customer ");
				System.out.println("3. Hotel Employee ");
				selection = sc.nextInt();
				if (selection >= 1 || selection <= 3) {
					int check = 1;
					while (check != 0) {
						System.out.println("Enter username");
						username = sc.next().toUpperCase();
						System.out.println("Enter password");
						password = sc.next().toUpperCase();
						flag = 0;

						String userRole = userImpl.checkRole(username, password);
						if (userRole != null) {
							System.out.println("Welcome " + userRole.toLowerCase());
							check = 0;
						} else
							System.out.println(
									"Wrong Username and Password.\nPlease check with your credentials\nLet's try it again\n\n");
					}
				} else {
					System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
				}

			}

			if (selection == 1) {
				/*
				 * 
				 * admin functionalities
				 * 
				 * 
				 */
				int admin_choice = 1;
				while (admin_choice > 0 && admin_choice <= 3) {
					System.out.println("1. Hotel Management");
					System.out.println("2. Room Management");
					System.out.println("3. Generate Reports");
					admin_choice = sc.nextInt();

					switch (admin_choice) {
					case 1:
						/*
						 * 
						 * hotel management functionalities
						 * 
						 */
						System.out.println("1. Register hotel");
						System.out.println("2. Remove hotel");
						System.out.println("3. Modify hotel");
						
						int ch = sc.nextInt();
						switch(ch)
						{
						case 1:
							Hotel newHotel = new Hotel();
							String hotelId = "H" + manipulativeId;
							manipulativeId++;
							newHotel.setHotelId(hotelId);
							System.out.println("Enter City:");
							newHotel.setCity(sc.next());
							System.out.println("Enter Hotel Name:");
							newHotel.setHotelName(sc.next());
							System.out.println("Enter Address:");
							newHotel.setAddress(sc.next());
							System.out.println("Enter Description:");
							newHotel.setDescription(sc.next());
							System.out.println("Enter Avg Rate per night:");
							newHotel.setAvgRatePerNight(sc.nextDouble());
							System.out.println("Enter Phone No. 1");
							newHotel.setPhoneNo1(sc.next());
							System.out.println("Enter Phone No. 2");
							newHotel.setPhoneNo2(sc.next());
							System.out.println("Enter Rating:");
							newHotel.setRating(sc.next());
							System.out.println("Enter email:");
							newHotel.setEmail(sc.next());
							System.out.println("Enter fax:");
							newHotel.setFax(sc.next());
							
							boolean response = hotelImpl.addHotel(newHotel);
							if(response)
								System.out.println("Hotel Registered Succesfully : " + hotelId);
							else
								System.out.println("Problem with the registration.");
							break;
						case 2:
							
							System.out.println("Enter Hotel Id to remove it:");
							hotelId = sc.next();
							response = hotelImpl.removeHotel(hotelId);
							if(response)
								System.out.println("Hotel Removed Succesfully : " + hotelId);
							else
								System.out.println("Problem with the removal operation.");
					
							break;
						case 3:
							System.out.println("Enter the Hotel Id of the hotel you want to update the description of: ");
							hotelId= sc.next();
							System.out.println("Enter the updated description");
							String hotelDescription= sc.next();
							boolean result = hotelImpl.changeHotelDescription(hotelId, hotelDescription);
							if(result)
							{
								System.out.println("Hotel description updated successfully with ID: "+hotelId);
							}
							else
							{
								System.out.println("Problem with the updation operation");
							}
							break;
						}
						
						break;
					case 2:
						/*
						 * 
						 * 
						 * 
						 * room management functionalities
						 * 
						 * 
						 */
						
						System.out.println("1. Register room");
						System.out.println("2. Remove room");
						System.out.println("3. Modify room rate per night");
						RoomImplementation roomImplementation= new RoomImplementation();
						int room_choice= sc.nextInt();
						switch(room_choice)
						{
						case 1:
							{
								
								RoomDetails roomDetails= new RoomDetails();
								System.out.println("Enter the hotel Id: ");
								roomDetails.setHotelId(sc.next());
								System.out.println("Enter the room Id: ");
								roomDetails.setRoomId(sc.next());
								System.out.println("Enter the room number");
								roomDetails.setRoomNo(sc.next());
								System.out.println("Enter the room type");
								roomDetails.setRoomType(sc.next());
								System.out.println("Enter the room per night: ");
								roomDetails.setPerNight(sc.nextDouble());
								System.out.println("Enter the availablity: ");
								roomDetails.setAvailability(sc.nextBoolean());
								boolean result = roomImplementation.insertRoomDetails(roomDetails);
								if(result)
								{
									System.out.println("Room added successfully with room Id: "+roomDetails.getRoomId());
								}
								else
								{
									System.out.println("There was a problem with the registration");
								}
								break;
							}
						case 2:
							{
								System.out.println("Enter the room Id to remove: ");
								String roomId= sc.next();
								boolean result = roomImplementation.removeRoomDetails(roomId);
								if(result)
								{
									System.out.println("The room was deleted with Id :"+roomId);
								}
								else
								{
									System.out.println("There was a problem with the delete operation");
								}
								
								break;
							}
						case 3:
							{
								System.out.println("Enter the room Id for which you want to update the per night rate: ");
								String roomId= sc.next();
								System.out.println("Enter the discount percentage");
								double roomDiscountPercentage= sc.nextDouble();
								boolean result = roomImplementation.updateRoomPerNight(roomId, roomDiscountPercentage);
								if(result)
								{
									System.out.println("The per night rate was updated with room id: "+roomId);
								}
								else
								{
									System.out.println("There was a problem with the updation process");
								}
								break;
							}
						default:
							{
							System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
							}
						}
						break;
					case 3:
						
							System.out.println("1. View List of Hotels");
							System.out.println("2. View Bookings of specific hotel");
							System.out.println("3. View guest list of specific hotel");
							System.out.println("4. View bookings for specified date");
							int report_choice = sc.nextInt();
							
							switch(report_choice)
							{
							case 1:
								 hotelList = hotelImpl.listAllHotels();
								 for(Hotel hotel : hotelList)
								 {		 System.out.println("Hotel Id: " + hotel.getHotelId() + " || Hotel Name: "
												+ hotel.getHotelName() + " || City: " + hotel.getCity() + " || Price: "
												+ hotel.getAvgRatePerNight() + "\n");
								 }
								 break;
							case 2:
								/*
								 * 
								 * bookings of specific hotel(by hotelId)
								 * 
								 */
								 break;
							case 3:
								/*
								 * 
								 * view guest list of specific hotel. (by hotelId)
								 * 
								 * 
								 */
								 break;
							case 4:
								/*
								 * 
								 * bookings for specified date.
								 * 
								 * 
								 */
								 break;
								
							
							}

						/*
						 * 
						 * 
						 * reports functionalities
						 * 
						 * 
						 * 
						 */
						break;
					default:
						System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
					}
				}

			}

			else if (selection == 2 || selection == 3) {
				int user_choice = 0;

				while (user_choice == 0) {
					System.out.println("1. Search for Hotel Rooms");
					System.out.println("2. Book Hotel Rooms");
					System.out.println("3. View Booking Status");
					user_choice = sc.nextInt();

					switch (user_choice) {
					case 1:
						System.out.println("Enter the Location to search for Hotels:");
						String hotel_location = sc.next();
						hotelList = hotelImpl.searchByLocation(hotel_location);
						for (Hotel hotel : hotelList) {
							System.out.println("Hotel Id: " + hotel.getHotelId() + " || Hotel Name: "
									+ hotel.getHotelName() + " || City: " + hotel.getCity() + " || Price: "
									+ hotel.getAvgRatePerNight() + "\n");

						}
						System.out.println();
						System.out.println("1. Search by Price Range");
						System.out.println("2. Search by Hotel Name");
						System.out.println("3. Search by Location");
						System.out.println("4. Sort by Rating");
						System.out.println("5. Sort by Average Price per night");

						int ch = sc.nextInt();
						List<Hotel> filteredHotelList = new ArrayList<Hotel>();
						switch (ch) {
						case 1:
							System.out.println("Set your lower price Range:");
							int min = sc.nextInt();
							System.out.println("Set your higher price Range:");
							int max = sc.nextInt();
							for (Hotel hotel : hotelList) {
								if (hotel.getAvgRatePerNight() >= min && hotel.getAvgRatePerNight() <= max)
									filteredHotelList.add(hotel);
							}
							break;

						case 2:
							System.out.println("Enter the Hotel Name:");
							String hotelName = sc.next();
							for (Hotel hotel : hotelList) {
								if (hotel.getHotelName().toLowerCase().contains(hotelName.toLowerCase()))
									filteredHotelList.add(hotel);
							}
							break;
						case 3:
							System.out.println("Enter location:");
							String hotelLocation = sc.next();
							for (Hotel hotel : hotelList) {
								if (hotel.getAddress().toLowerCase().contains(hotelLocation.toLowerCase()))
									filteredHotelList.add(hotel);
							}
							break;
						case 4:
							System.out.println("The hotels sorted by Rating:");
							filteredHotelList = hotelList;
							Collections.sort(filteredHotelList, Hotel.hotelRatingComparator);

							break;
						case 5:
							System.out.println("The hotels sorted by Average rate per night:");
							filteredHotelList = hotelList;
							Collections.sort(filteredHotelList, Hotel.hotelPriceComparator);
							break;
						}
						for (Hotel hotel : filteredHotelList)
							System.out.println("Hotel Id: " + hotel.getHotelId() + " || Hotel Name: "
									+ hotel.getHotelName() + " || City: " + hotel.getCity() + " || Price: "
									+ hotel.getAvgRatePerNight() + "\n");

					case 2:

						System.out.println("Enter Hotel Id:");
						int hotelId = sc.nextInt();
						System.out.println("Showing all the rooms:");
						/*
						 * 
						 * 
						 * show the rooms of the particular hotel ID and let the user choose the roomId
						 * for booking
						 * 
						 * 
						 */

						break;
					case 3:
						/*
						 * 
						 * 
						 * 
						 * view booking status of all the bookings that he has made.
						 * 
						 * 
						 * 
						 * 
						 * 
						 */
					default:
						System.out.println("OOPS!!!!\nWrong Input. Please try again.");
						user_choice = 0;
					}
				}

			}

			break;
		case 2:
			User user = new User();
			System.out.println("Enter User Id:");
			// check if user_id is unique or not
			user.setUserId(sc.next());
			System.out.println("Enter Password");
			user.setUserPassword(sc.next());
			System.out.println("Enter User Role:");
			System.out.println("1. Customer");
			System.out.println("2. Employee");
			int role = sc.nextInt();
			if (role == 1)
				user.setUserRole("CUSTOMER");
			else if (role == 2)
				user.setUserRole("EMPLOYEE");
			System.out.println("Enter UserName");
			// check if username is unique or not
			user.setUserName(sc.next());
			System.out.println("Enter Mobile Numnber");
			user.setMobileNo(sc.next());
			System.out.println("Enter Phone");
			user.setPhone(sc.next());
			System.out.println("Enter Address");
			user.setAddress(sc.next());
			System.out.println("Enter Email");
			user.setEmail(sc.next());
			boolean result = userImpl.registerUser(user);
			if (result)
				System.out.println(user.getUserRole().toLowerCase() + " added.");

		}

	}

}
