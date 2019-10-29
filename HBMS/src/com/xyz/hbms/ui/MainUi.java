package com.xyz.hbms.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.xyz.hbms.exception.HotelNotFoundException;
import com.xyz.hbms.exception.RoomNotFoundException;
import com.xyz.hbms.exception.UserNotFoundException;
import com.xyz.hbms.model.BookingDetails;
import com.xyz.hbms.model.Hotel;
import com.xyz.hbms.model.RoomDetails;
import com.xyz.hbms.model.User;
import com.xyz.hbms.service.BookingImplementation;
import com.xyz.hbms.service.BookingInterface;
import com.xyz.hbms.service.HotelImplementation;
import com.xyz.hbms.service.HotelInterface;
import com.xyz.hbms.service.RoomImplementation;
import com.xyz.hbms.service.RoomInterface;
import com.xyz.hbms.service.UserImplementation;
import com.xyz.hbms.service.UserInterface;
import com.xyz.hbms.util.Validations;

/**
 * <h1>Hotel Booking Management System</h1> This project is aimed at developing
 * an online Hotel Bookings Management System (HBMS).This system can be used to
 * search for Hotel rooms and reserve them. This is an integrated system that
 * contains both the user component, Hotel-Employee component and the Admin
 * component. There are features like report generators etc in this system.
 * 
 * There are three categories of people who would access the system viz.
 * customer, hotel-employee & Admin. Each one of them would have some exclusive
 * privileges (for e.g. customer and hotel- employee can just search for hotel
 * rooms and reserve them, but only the admin has the right to add new and
 * modify hotels.)
 *
 * @Group 4
 * @author Piyush Kumar - 46001546
 * @author Ananya Panigrahi 46001100
 * @author Yashraj Shrivastav
 * @version 1.0
 * @since 2019-10-29
 */

public class MainUi {

	public static void main(String[] args) throws SQLException {

		// logger statements to keep the log genearting at every fuction call.
		final Logger logger;
		PropertyConfigurator.configure(".\\resources\\log4j.properties");
		logger = Logger.getLogger(MainUi.class.getName());

		// making an object of all the service layers to use it further for our
		// functions
		UserInterface userImpl = new UserImplementation();
		HotelInterface hotelImpl = new HotelImplementation();
		RoomInterface roomImplementation = new RoomImplementation();
		BookingInterface bookingImpl = new BookingImplementation();
		List<Hotel> hotelList = new ArrayList<Hotel>();

		/*
		 * Two scanners sc1 and sc2 are taken into consideration which we will be using
		 * through out the main.
		 */
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);

		/*
		 * 
		 * START
		 * 
		 */
		System.out.println("Welcome to Hotel Booking Management System");
		System.out.println("------------------------------------------");
		int choice = 0;
		int flag = 1;
		int flagAdmin = 0;
		while (flagAdmin == 0) {
			while (flag != 0) {
				/*
				 * asking the user whether he wants to login or register
				 * 
				 */
				System.out.println("1. Login Page");
				System.out.println("2. Register");
				choice = sc.nextInt();
				if (choice == 1 || choice == 2) {
					flag = 0;

				} else {
					/*
					 * wrong user input case
					 */
					System.out.println("\nOOPS!!!!\nWrong Input. Please try again.");
				}
			}
			switch (choice) {
			case 1:
				flag = 1;
				int selection = 0;
				String username = null, password = null;
				String userId = null;
				while (flag != 0) {
					/*
					 * 
					 * A single integrated user login interface from where all customers, employees
					 * and admin can login.
					 * 
					 */
					System.out.println("Please Select your Role:");
					System.out.println("------------------------");
					System.out.println("1. Admin");
					System.out.println("2. Customer ");
					System.out.println("3. Hotel Employee ");
					selection = sc.nextInt();
					if (selection >= 1 && selection <= 3) {
						int check = 1;
						while (check != 0) {
							/*
							 * asking for username and password
							 * 
							 */
							System.out.println("Login Portal");
							System.out.println("------------");
							System.out.print("Enter username :  ");
							username = sc.next().toUpperCase();
							System.out.print("Enter password :  ");
							password = sc.next().toUpperCase();

						/*	String fmt = "%2$5s %3$10s%n";
							Console console = System.console();
							if(console!=null)
							{char[] passwordArray = console.readPassword(fmt,"Enter","Password: ");
							password = new String(passwordArray);
							}
							else System.out.println("Console is null");*/
							flag = 0;

							String userRole;
							try {
								userRole = userImpl.checkRole(username, password);
								userId = userImpl.getUserId(username, password);
								if (userRole != null) {
									/*
									 * Getting the user into his role portal. If the user is employee, he will be
									 * able to see only the employee functionalities and will be deprived of other
									 * role privileges.
									 * 
									 */
									System.out.println(
											"\nWelcome to the " + userRole.toLowerCase() + " portal -> " + username);
									System.out.println("---------------------------------------");
									check = 0;
								} else
									System.out.println(
											"Wrong Username and Password.\nPlease check with your credentials\nLet's try it again\n\n");

							} catch (UserNotFoundException e) {
								logger.info("User Login: " + e.getMessage());
							}
						}
					} else {
						System.out.println("\nOOPS!!!!\nWrong Input. Please try again.");
					}

				}

				if (selection == 1) {
					/*
					 * 
					 * admin functionalities:- 1. He can manage hotel i.e Add, modify and delete
					 * hotels 2. He can manage Room i.e Add, modify and delete rooms 3. He can see
					 * various types of reports, based on criteria
					 * 
					 */
					int admin_choice = 1;
					while (admin_choice > 0 && admin_choice <= 3) {
						System.out.println("1. Hotel Management");
						System.out.println("2. Room Management");
						System.out.println("3. Generate Reports");
						System.out.println("4. Logout");
						admin_choice = sc.nextInt();

						switch (admin_choice) {
						case 1:
							/*
							 * 
							 * hotel management functionalities:- The admin can register a new hotel, remove
							 * a new hotel and modify a new hotel i.e either description or any offers.
							 *
							 * 
							 */
							System.out.println("Please select your Hotel Operation:");
							System.out.println("-----------------------------------");
							System.out.println("1. Register hotel");
							System.out.println("2. Remove hotel");
							System.out.println("3. Modify hotel");

							int ch = sc.nextInt();
							switch (ch) {
							case 1:
								/*
								 * 
								 * Hotel Registration
								 * 
								 */
								Hotel newHotel = new Hotel();
								String hotelId;
								System.out.println("Hotel Registration");
								System.out.println("-------------------");
								System.out.print("Enter City :  ");
								newHotel.setCity(sc.next());
								System.out.print("Enter Hotel Name :  ");
								newHotel.setHotelName(sc1.nextLine());
								System.out.print("Enter Address :  ");
								Scanner sc2 = new Scanner(System.in);
								newHotel.setAddress(sc2.nextLine());
								System.out.print("Enter Description :  ");
								newHotel.setDescription(sc1.nextLine());
								System.out.print("Enter Avg Rate per night :  ");
								newHotel.setAvgRatePerNight(sc.nextDouble());
								System.out.print("Enter Phone No. 1 :  ");
								int flag1 = 0;
								while (flag1 == 0) {
									String phone1 = sc.next();
									if (Validations.checkPhone(phone1)) {
										newHotel.setPhoneNo1(phone1);
										flag1++;
									} else
										System.out.println("Invalid Phone Number!");
								}
								while (flag1 == 1) {
									System.out.print("Enter Phone No. 2 :  ");
									String phone2 = sc.next();
									if (Validations.checkPhone(phone2)) {
										newHotel.setPhoneNo2(phone2);
										flag1++;
									} else
										System.out.println("Invalid Phone Number!");
								}
								while (flag1 == 2) {
									System.out.print("Enter Rating :  ");
									String rating = sc.next();

									if (Validations.checkRating(rating)) {
										newHotel.setRating(rating);
										flag1++;
									} else
										System.out.println("Rating value must be between 1 and 5(inclusive)");
								}
								while (flag1 == 3) {
									System.out.print("Enter email :  ");
									String email = sc.next();
									if (Validations.checkEmail(email)) {
										newHotel.setEmail(email);
										flag1++;
									} else
										System.out.println("Invalid email!");

								}
								while (flag1 == 4) {
									System.out.print("Enter fax :  ");
									String fax = sc.next();
									if (Validations.checkFax(fax)) {
										newHotel.setFax(fax);
										flag1++;
									} else
										System.out.println("Invalid Fax number!");
								}
								boolean response = hotelImpl.addHotel(newHotel);
								if (response)
									System.out.println("Hotel Registered Succesfully! ");
								else
									System.out.println("Problem with the registration!");
								break;
							case 2:
								/*
								 * 
								 * Remove Hotel. Input:- hotel Id Output:- confirmation
								 * 
								 */
								System.out.println("Hotel Removal");
								System.out.println("--------------");
								System.out.print("Enter Hotel Id to remove it :  ");
								hotelId = sc.next();

								try {
									response = hotelImpl.removeHotel(hotelId);
									if (response)
										System.out.println("Hotel Removed Succesfully!");
									else
										System.out.println(
												"Problem with the removal operation.Either Hotel doesn't exist.");
								} catch (HotelNotFoundException e) {
									logger.info("Hotel Management:" + e.getMessage());
								}

								break;
							case 3:

								/*
								 * 
								 * Hotel update 1. Hotel description can be updated. 2. Special offers can be
								 * included
								 * 
								 * 
								 */
								System.out.println("Hotel Updation");
								System.out.println("--------------");
								System.out.println("1. Update Hotel description");
								System.out.println("2. Include special offers");
								int choice2 = sc.nextInt();
								switch (choice2) {
								case 1:
									// Update Hotel description
									System.out.println("Description Update");
									System.out.println("------------------");
									System.out.println(
											"Enter the Hotel Id of the hotel you want to update the description of: ");
									hotelId = sc.next();
									System.out.println("Enter the updated description");
									String hotelDescription = sc1.nextLine();
									boolean result;
									try {
										result = hotelImpl.changeHotelDescription(hotelId, hotelDescription);
										if (result) {
											System.out.println(
													"Hotel description updated successfully with ID: " + hotelId);
										} else {
											System.out.println("Problem with the updation operation");
										}
									} catch (HotelNotFoundException e) {
										logger.info("Hotel Management" + e.getMessage());
									}
									break;
								case 2:
									// Include special offers
									System.out.println("Special Offers");
									System.out.println("---------------");
									System.out.println(
											"Enter the Hotel Id of the hotel you want to include the special offers: ");
									hotelId = sc.next();
									System.out.println("Enter the percentage discount: ");
									double percentageDiscount = sc.nextDouble();
									if (percentageDiscount < 0)
										percentageDiscount = 0;
									else if (percentageDiscount > 100)
										percentageDiscount = 100;
									boolean truth;
									try {
										truth = hotelImpl.includeSpecialOffers(hotelId, percentageDiscount);
										if (truth) {
											System.err.println("special offers included with hotel Id: " + hotelId);
										} else {
											System.out.println("There was a problem with the operation");
										}
									} catch (HotelNotFoundException e) {
										logger.info("Hotel Management: " + e.getMessage());
									}
									break;
								default:
									System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
								}

							}

							break;
						case 2:
							/*
							 * 
							 * 
							 * Room management functionalities:- Here, the admin can add rooms to a specific
							 * hotel. Rooms can be removed as well. The room rates can be modified. The room
							 * availability can be updated.
							 * 
							 * 
							 */
							System.out.println("Please select your Room Operation:");
							System.out.println("---------------------------------");
							System.out.println("1. Register room");
							System.out.println("2. Remove room");
							System.out.println("3. Modify room rate per night");
							System.out.println("4. Update Room Availability");
							int room_choice = sc.nextInt();
							switch (room_choice) {
							case 1: {
								// Register room
								System.out.println("Room Registration");
								System.out.println("----------------");
								RoomDetails roomDetails = new RoomDetails();
								System.out.print("Enter the hotel Id :  ");
								roomDetails.setHotelId(sc.next());
								/*
								 * System.out.println("Enter the room Id: "); roomDetails.setRoomId(sc.next());
								 */
								System.out.print("Enter the room number :  ");
								roomDetails.setRoomNo(sc.next());
								int flag1 = 0;
								while (flag1 == 0) {
									System.out.print("Enter the room type :  ");
									String roomType = sc1.next();
									if (Validations.checkRoomType(roomType)) {
										roomDetails.setRoomType(roomType);
										flag1++;
									} else {
										System.out.println("\n\nInvalid Room Type. Please enter from the pool :-");
										System.out.println("Standard || Deluxe || Executive || Studio || Suite\n\n");
									}
								}
								System.out.print("Enter the room rate per night :  ");
								roomDetails.setPerNight(sc.nextDouble());
								while (flag1 == 1) {
									System.out.println("Enter the availablity(1/0) :  ");
									int avail = sc.nextInt();
									if (avail == 1 || avail == 0) {
										roomDetails.setAvailability(avail);
										flag1++;
									} else
										System.out.println("Please choose 1 for unoccupied and 0 for occupied!");
								}
								boolean result = roomImplementation.insertRoomDetails(roomDetails);
								if (result) {
									System.out.println("Room registered successfully.\n");
								} else {
									System.out.println("There was a problem with the room registration");
								}
								break;
							}
							case 2: {
								// Remove room
								System.out.println("Room removal");
								System.out.println("------------");
								System.out.print("Enter the room Id to remove :  ");
								String roomId = sc.next();
								boolean result;
								try {
									result = roomImplementation.removeRoomDetails(roomId);
									if (result) {
										System.out.println("The room was deleted with Id :" + roomId);
									} else {
										System.out.println(
												"There was a problem with the delete operation. Maybe room doesn't exist.");
									}
								} catch (RoomNotFoundException e) {
									logger.info("Room Management: " + e.getMessage());
								}

								break;
							}
							case 3: {
								// Modify room rate per night
								System.out.println("Room modification");
								System.out.println("-----------------");
								System.out
										.print("Enter the room Id for which you want to update the per night rate :  ");
								String roomId = sc.next();
								System.out.print("Enter the discount percentage :  ");
								double roomDiscountPercentage = sc.nextDouble();
								if (roomDiscountPercentage < 0)
									roomDiscountPercentage = 0;
								else if (roomDiscountPercentage > 100)
									roomDiscountPercentage = 100;
								boolean result;
								try {
									result = roomImplementation.updateRoomPerNight(roomId, roomDiscountPercentage);
									if (result) {
										System.out.println("The per night rate was updated with room id: " + roomId);
									} else {
										System.out.println("There was a problem with the updation process");
									}
								} catch (RoomNotFoundException e) {
									logger.info("Room Management: " + e.getMessage());
								}
								break;
							}
							case 4: {
								// Admin can change the availability of a room
								System.out.println("Update Availability");
								System.out.println("-------------------");
								int result = 0;
								String final_avail = null;
								System.out.print("Enter the room Id to make it available :  ");
								String roomId = sc.next();
								RoomDetails roomdetails;
								try {
									roomdetails = roomImplementation.findRoomById(roomId);
									if (roomdetails.getAvailability() == 1)
										System.out.println("The room is already available");
									else
										result = roomImplementation.updateAvailability(roomId,
												roomdetails.getAvailability());
									if (roomdetails.getAvailability() == 1)
										final_avail = "Not available";
									else
										final_avail = "Available";
									if (result == 1)
										System.out.println(
												"Room availability of Room Id:" + roomId + " : " + final_avail);
								} catch (RoomNotFoundException e) {
									logger.info("Room Management: " + e.getMessage());
								}

							}
								break;
							default: {
								System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
							}
							}
							break;
						case 3:
							/*
							 * 
							 * The admin can generate different types of reports:- - can generate list of
							 * all hotels - can see the bookings of a specified hotels - can see the guest
							 * list of a specific hotel - can see booking of a speciified date
							 * 
							 * 
							 */
							System.out.println("Report Generation Portal");
							System.out.println("------------------------");
							System.out.println("1. View List of Hotels");
							System.out.println("2. View Bookings of specific hotel");
							System.out.println("3. View guest list of specific hotel");
							System.out.println("4. View bookings for specified date");
							int report_choice = sc.nextInt();

							switch (report_choice) {
							case 1:
								/*
								 * list of all hotels registered with the system
								 * 
								 */
								System.out.println("List of All Hotels");
								System.out.println("------------------");
								hotelList = hotelImpl.listAllHotels();
								for (Hotel hotel : hotelList) {
									System.out.println("Hotel Id: " + hotel.getHotelId() + " || Hotel Name: "
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
								System.out.println("Booking of specific Hotel");
								System.out.println("-------------------------");
								System.out.print("Enter the Hotel ID :  ");
								String hotelId = sc.next();
								List<BookingDetails> bookedList;
								try {
									bookedList = bookingImpl.showBookingByHotelId(hotelId);
									if (!bookedList.isEmpty()) {
										System.out.println("Booking Details of Hotel of Hotel Id:" + hotelId);
										System.out.println("-----------------------------------------");

										for (BookingDetails details : bookedList)
											System.out.println("Booking Id: " + details.getBookingId() + " || User Id: "
													+ details.getUserId() + " || Check-in Date: "
													+ details.getBookedFrom() + " || Check-out Date: "
													+ details.getBookedTo() + " || Total Amount: "
													+ details.getAmount());
										System.out.println();
									} else
										System.out.println("No bookings under this hotel!");
								} catch (HotelNotFoundException e) {
									logger.info("Booking Reports : " + e.getMessage());
								}
								break;
							case 3:
								/*
								 * 
								 * view guest list of specific hotel. (by hotelId)
								 * 
								 * 
								 */
								System.out.println("Hotelwise Guest List");
								System.out.println("--------------------");
								System.out.print("Enter the Hotel ID :  ");
								hotelId = sc.next();
								List<User> guestList;
								try {
									guestList = bookingImpl.showGuestList(hotelId);
									if (!guestList.isEmpty()) {
										System.out.println("Guest List of Hotel of Hotel Id:" + hotelId);
										System.out.println("-----------------------------------");

										for (User user : guestList)
											System.out.println("User Id: " + user.getUserId() + " || User Name: "
													+ user.getUserName() + " || Mobile: " + user.getMobileNo()
													+ " || Email: " + user.getEmail());
										System.out.println();
									} else
										System.out.println("No guest list for this hotel! ");
								} catch (HotelNotFoundException e) {
									logger.info("Booking Reports : " + e.getMessage());
								}
								break;
							case 4:
								/*
								 * 
								 * bookings for specified date.
								 * 
								 * 
								 */
								System.out.println("Booking of specific Date");
								System.out.println("------------------------");
								System.out.print("Enter Specific Date(yyyy-MM-dd) :  ");
								String date = sc.next();
								bookedList = bookingImpl.showAllBookings(date);
								if (!bookedList.isEmpty()) {
									System.out.println("Bookings under date -> " + date + " :");
									System.out.println("-----------------------------------");
									for (BookingDetails details : bookedList)
										System.out.println("Booking Id: " + details.getBookingId() + " || User Id: "
												+ details.getUserId() + " || Check-in Date: " + details.getBookedFrom()
												+ " || Check-out Date: " + details.getBookedTo() + " || Total Amount: "
												+ details.getAmount());
								} else
									System.out.println("\nNo bookings on this date!\n");
								break;
							}

							break;
						case 4:
							System.out.println("\nLogging off!\n");
							flag++;
							break;
						default:
							System.out.println("\"OOPS!!!!\\nWrong Input. Please try again.");
						}
					}

				}

				else if (selection == 2 || selection == 3) {
					int user_choice = 0;
					int flg = 0;
					/*
					 * 
					 * Employee || Customer login He can search hotels based on different filters:
					 * 1. Price Range 2. Hotel Name 3. Location 4. Rating 5. Average Price per night
					 * He can book a room in a hotel He can see his own list of bookings
					 * 
					 * 
					 */
					while (user_choice == 0 && flg == 0) {
						System.out.println("1. Search for Hotel Rooms");
						System.out.println("2. Book Hotel Rooms");
						System.out.println("3. View Booking Status");
						System.out.println("4. Logout");
						user_choice = sc.nextInt();

						switch (user_choice) {
						case 1:
							System.out.println("Hotel Search");
							System.out.println("------------");
							int sizeList = 0;

							while (sizeList == 0) {
								System.out.print("Enter the City to search for Hotels :  ");
								String hotel_location = sc.next();
								hotelList = hotelImpl.searchByLocation(hotel_location);
								sizeList = hotelList.size();
								System.out.println("\nList of all the hotels in " + hotel_location + " : ");
								System.out.println("----------------------------------------");
								if (sizeList == 0)
									System.out.println("No hotels registered with us in " + hotel_location);
								System.out.println();
							}

							for (Hotel hotel : hotelList) {
								System.out.println("Hotel Id: " + hotel.getHotelId() + " || Hotel Name: "
										+ hotel.getHotelName() + " || City: " + hotel.getCity() + " || Price: "
										+ hotel.getAvgRatePerNight() + "\n");

							}
							System.out.println();
							System.out.println("Filter and Sort Menu");
							System.out.println("---------------------");
							System.out.println("1. Search by Price Range");
							System.out.println("2. Search by Hotel Name");
							System.out.println("3. Search by Location");
							System.out.println("4. Sort by Rating");
							System.out.println("5. Sort by Average Price per night");
							System.out.println("6. Book directly");

							int ch = sc.nextInt();
							List<Hotel> filteredHotelList = new ArrayList<Hotel>();
							switch (ch) {
							case 1:
								// Search By Price Range
								System.out.println("Filter by Price");
								System.out.println("---------------");
								System.out.print("Set your lower price Range :  ");
								int min = sc.nextInt();
								System.out.print("Set your higher price Range :  ");
								int max = sc.nextInt();
								System.out.println("\nList of hotels as per requirement");
								System.out.println("---------------------- -----------");
								for (Hotel hotel : hotelList) {
									if (hotel.getAvgRatePerNight() >= min && hotel.getAvgRatePerNight() <= max)
										filteredHotelList.add(hotel);
								}
								break;

							case 2:
								// Search by Hotel Name
								System.out.println("Filter by Hotel Name");
								System.out.println("---------------------");
								System.out.println("Enter the Hotel Name:");
								String hotelName = sc.next();
								for (Hotel hotel : hotelList) {
									if (hotel.getHotelName().toLowerCase().contains(hotelName.toLowerCase()))
										filteredHotelList.add(hotel);
								}
								break;
							case 3:
								// Search by Location
								System.out.println("Filter by Location");
								System.out.println("-----------------");
								System.out.print("Enter location :  ");
								String hotelLocation = sc.next();
								for (Hotel hotel : hotelList) {
									if (hotel.getAddress().toLowerCase().contains(hotelLocation.toLowerCase()))
										filteredHotelList.add(hotel);
								}
								break;
							case 4:
								// Sort by Rating
								System.out.println("Sort by User Rating");
								System.out.println("--------------------");
								filteredHotelList = hotelList;
								Collections.sort(filteredHotelList, Hotel.hotelRatingComparator);

								break;
							case 5:
								// Sort by Average Price per night
								System.out.println("Sort by Average rate per night");
								System.out.println("-----------------------------");
								filteredHotelList = hotelList;
								Collections.sort(filteredHotelList, Hotel.hotelPriceComparator);
								break;
							}
							for (Hotel hotel : filteredHotelList)
								System.out.println("Hotel Id: " + hotel.getHotelId() + " || Hotel Name: "
										+ hotel.getHotelName() + " || City: " + hotel.getCity() + " || Price: "
										+ hotel.getAvgRatePerNight() + "\n");

						case 2:

							int flag3=1;
							System.out.println("Hotel Booking");
							System.out.println("-------------");
							while(flag3!=0) {
							int flagFinal = 0;
							while (flagFinal == 0) {
								System.out.print("Enter Hotel Id :  ");
								String hotelId = sc.next();
								System.out.println("\nShowing all the rooms:");
								System.out.println("------------------------");
								/*
								 * 
								 * 
								 * show the rooms of the particular hotel ID and let the user choose the roomId
								 * for booking
								 * 
								 * 
								 */
								List<RoomDetails> roomList = roomImplementation.listAll(hotelId);
								String flagger = "N";
								int roomListSize =0;
								for (RoomDetails room : roomList) {
									if (room.getAvailability() == 1) {
										flagger = "Y";
										System.out.println("Room Id: " + room.getRoomId() /*
																							 * + " || Room No.: " +
																							 * room.getRoomNo()
																							 */
												+ " || Room Type: " + room.getRoomType() + " || Room Price: "
												+ room.getPerNight() + " || Availability: " + flagger);
										flag3=0;
									}
									else roomListSize++;
										
								}
								if (roomList.size() == roomListSize)
									{System.out.println("No rooms available in Hotel : " + hotelId);
									}
							}
								System.out.println();

								System.out.print("Enter Room Id :  ");
								String roomId = sc.next();
								BookingDetails bookingDetails = new BookingDetails();
								int flag1 = 0;
								while (flag1 == 0) {
									System.out.print("Enter the check-in Date(yyyy-MM-dd) :  ");
									String checkinDate = sc.next();
									System.out.print("Enter the check-out Date(yyyy-MM-dd) :  ");
									String checkoutDate = sc.next();
									if (Validations.checkBookedFrom(checkinDate, checkoutDate)) {
										// check if same room id is not booked on same date from booking details table
										bookingDetails.setBookedFrom(checkinDate);
										bookingDetails.setBookedTo(checkoutDate);
										flag1++;
									} else
										System.out.println(
												"Either Invalid Date format or Checkout Date earlier than Checkin Date!");
								}
								while (flag1 == 1) {
									System.out.print("Enter number of adults :  ");
									int adultCount = sc.nextInt();
									if (adultCount > 2)
										System.out.println("You cannot have more than two adults in a room!");
									else {
										flag1++;
										bookingDetails.setNumberOfAdults(adultCount);
									}
								}
								while (flag1 == 2) {
									System.out.print("Enter number of children :  ");
									int childCount = sc.nextInt();
									if (childCount > 2)
										System.out.println("You cannot have more than two children in a room!");
									else {
										flag1++;
										bookingDetails.setNumberOfChildren(childCount);
									}
								}
								bookingDetails.setUserId(userId);

								bookingDetails.setRoomId(roomId);
								// display amount and ask for confirmation from user
								double bookingAmount = bookingImpl.getFinalAmount(bookingDetails);
								System.out.println("\nYour total amount of booking : " + bookingAmount);
								System.out.println("\nPlease confirm to Proceed:");
								System.out.println("------------------------");
								System.out.println("1. Confirm");
								System.out.println("2. Plan again");
								int plan = sc.nextInt();
								if (plan == 1) {
									int bookingId = 0;
									try {
										bookingId = bookingImpl.addBooking(bookingDetails);
										if (bookingId != 0) {

											System.out.println("Hola! Booking Confirmed. Booking Id: " + bookingId);
											flagFinal++;

										} else
											System.out.println("Error! Booking not confirmed yet!");
									} catch (RoomNotFoundException e) {
										logger.info("Room Management: " + e.getMessage());
									}

								} else
									continue;
							}
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

							List<BookingDetails> myBookings;
							try {
								System.out.println("My Bookings");
								System.out.println("------------");
								myBookings = bookingImpl.showMyBookings(userId);
								if (!myBookings.isEmpty()) {
									for (BookingDetails myBooking : myBookings)
										System.out.println("Booking Id: " + myBooking.getBookingId() + " || User Id: "
												+ myBooking.getUserId() + " || Check-in Date: "
												+ myBooking.getBookedFrom() + " || Check-out Date: "
												+ myBooking.getBookedTo() + " || Total Amount: "
												+ myBooking.getAmount());
									System.out.println("\n");
								} else
									System.out.println("No bookings made by you yet!");
							} catch (UserNotFoundException e) {
								logger.info("My Bookings: " + e.getMessage());
							}
							break;
						case 4:
							/*
							 * Logout
							 */
							System.out.println("\n\nThank you.");
							System.out.println("Logging Off!\n\n");
							flg++;
							break;
						default:
							System.out.println("OOPS!!!!\nWrong Input. Please try again.");
						}
						user_choice = 0;
					}

				}

				break;
			case 2:
				int flag1 = 0;

				/*
				 * New User Registration Data is asked from new user and validated accordingly
				 * 
				 */
				System.out.println("User Registration Portal");
				System.out.println("------------------------");
				User user = new User();
				
				while (flag1 == 0) {
					System.out.println("Please choose User Role:");
					System.out.println("1. Customer");
					System.out.println("2. Employee");
					int role = sc.nextInt();
					if (role == 1)
						user.setUserRole("CUSTOMER");
					else if (role == 2)
						user.setUserRole("EMPLOYEE");
					System.out.println("Please fill the form");
					System.out.println("-------------------");
					System.out.print("Enter UserName :  ");
					user.setUserName(sc.next());
					System.out.print("Enter Password :  ");
					String pass = sc.next();
					if (Validations.checkPassword(pass)) {
						user.setUserPassword(pass);
						flag1++;
					} else
						System.out.println(
								"Ensure that password is 8 to 20 characters long and contains a mix of upper and lower case characters, one numeric and one special character!");
				}
				while (flag1 == 1) {
					System.out.print("Enter Mobile Number :  ");
					String mobile = sc.next();
					if (Validations.checkMobile(mobile)) {
						user.setMobileNo(mobile);
						flag1++;
					} else
						System.out.println("Invalid Mobile No. Please enter again!");
				}
				while (flag1 == 2) {
					System.out.print("Enter Phone :  ");
					String phone = sc.next();
					if (Validations.checkPhone(phone)) {
						user.setPhone(phone);
						flag1++;
					} else
						System.out.println("Invalid Phone No. Please enter again!");
				}

				System.out.print("Enter Address :  ");
				user.setAddress(sc1.nextLine());

				while (flag1 == 3) {
					System.out.print("Enter Email :  ");
					String email = sc.next();
					if (Validations.checkEmail(email)) {
						user.setEmail(email);
						flag1++;
					} else
						System.out.println("Invalid email. Please enter again!");
				}
				/*
				 * User is regsitered
				 * 
				 */
				boolean result = userImpl.registerUser(user);
				if (result) {
					System.out.println(user.getUserRole().toLowerCase() + " added.");
					choice = 3;
				} else
					System.out.println("User registration unsuccesful! Please Try again\n");
				break;
			case 3:
				System.out.println("\n\n");
				System.exit(0);
				break;
			}

		}
		sc.close();
		sc1.close();
	}
}
