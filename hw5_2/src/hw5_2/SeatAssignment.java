/***************************************************************************************
 *                         hw5_2/SeatAssignment.java - Copyright rciampa
 *                         
 * Title: Homework #5.2 Otter Airways
 * Abstract: The system allows a user to select the seat from a row number and a letter,
 * 		     if the seat is available it is reserved otherwise the user is allowed to
 * 			 select another seat.
 * 			 
 *   		 If the user inputs a seat location [row and seat letter] that is not valid
 * 			 the user is allowed to input other selection.
 *
 *			 The current status of the seating is printed to the console at each user
 *			 seat selection. Seats that are currently reserved are shown with an 'X' in
 *			 the place of 'A', 'B', 'C' or 'D'.
 *
 *			 When the user is finished with the seat reservation system the user can
 *			 enter a 'Q' at the row selection prompt to exit the application.
 *
 * @author rciampa
 * ID: 7470
 * Date: Wed Feb 22 2015 : 20:13:03
 * File: hw5_2/SeatAssignment.java
 * Requires: n/a
 *
 * This application created for the CST338 course at CSU Monterey Bay SP:2015 Semester.
 * This software is not licensed or warranted for any other purpose than the CSUMB
 * scholastic 2015 semester with Dr. Byun.
 *
 * Heading.java
 * /home/rciampa/Documents/Umbrello/headings/heading.java
 *
 ***************************************************************************************/

package hw5_2;

import java.util.Scanner;

public class SeatAssignment {

	private final int MAX_ROWS = 7;
	private char[][] seatingMatrix = new char[MAX_ROWS][4];

	/**
	 * Default constructor
	 */
	public SeatAssignment() {
		// Fill the array with the defaults
		fillArray();
	}

	/**
	 * Checks to see if the user wants to exit the system
	 * 
	 * @param val
	 *            The character to check
	 * @return True if char is 'Q', false otherwise
	 */
	private boolean checkForUserExit(char val) {
		return (val == 'Q');
	}
	
	/**
	 * Checks to see if the seat has previously been reserved
	 * 
	 * @param r The row of seating to check
	 * @param s The seat within the row to check
	 * @return True if seat is reserved, false otherwise
	 */
	private boolean checkForExistingReservation(int r, int s, char seat){
		boolean isReserved = false;
		isReserved = (seatingMatrix[r - 1][s] == 'X');
		if(isReserved){
			System.out.println("Seat " + r + seat +" is already taken.\n");
		}
		return isReserved;
	}

	/**
	 * Fill the array with the row designations A-B-C-D
	 */
	private void fillArray() {
		// Loop and fill the array
		for (int i = 0; i < seatingMatrix.length; i++) {
			seatingMatrix[i][0] = 'A';
			seatingMatrix[i][1] = 'B';
			seatingMatrix[i][2] = 'C';
			seatingMatrix[i][3] = 'D';
		}
	}

	/**
	 * Builds the current seating matrix, used as a string building worker for
	 * the class method toString()
	 * 
	 * @return A formated string of the seating matrix
	 */
	private String printCurrentSeatingAssignemt() {
		String tmp = "Welcome to Otter Airways Reservation System\n";
		for (int i = 0; i < seatingMatrix.length; i++) {
			tmp += (i + 1) + "  ";
			int charCounter=0;
			for (char s : seatingMatrix[i]) {
				if (++charCounter == 3) {
					tmp += " ";
				}
				tmp += s;
			}
			tmp += "\n";
		}
		return tmp;
	}

	/**
	 * The default toString() method for the class
	 */
	public String toString() {
		return printCurrentSeatingAssignemt();
	}

	public void requestSeating() {
		Scanner keyboard = new Scanner(System.in);

		do {
			// Request the preferred row from user
			char rowChar = requestRow(keyboard);
			if (checkForUserExit(rowChar)) {
				System.out.println("Thanks for using our system.");
				System.exit(0);
			} else {
				int r = -1;
				try {
					r = Integer.parseInt(String.valueOf(rowChar));
				} catch (NumberFormatException ex) {
					r = -1;
				} finally {
					// Request the preferred seat from user
					char s = requestSeat(keyboard);
					if (validateRequestedSeat(r, s) && 
							!checkForExistingReservation(r, convertSeatLetterToInt(s), s)) {
						makeSeatReservation(r, convertSeatLetterToInt(s), s);
					}
				}
			}
			System.out.println(printCurrentSeatingAssignemt());
		} while (true);
	}

	/**
	 * Request from the user an integer that corresponds to a row in the plane,
	 * the user may enter the letter 'Q' to quit the seating selection.
	 * 
	 * @param kb
	 *            Scanner object configured as a System.in
	 * @return A character
	 */
	private char requestRow(Scanner kb) {
		System.out.println("Enter a row number: (Q to quit)");
		char row;
		row = kb.next().charAt(0);
		return row;
	}

	/**
	 * Prompts the user to enter a seat selection by entering a letter
	 * 
	 * @param kb
	 *            Scanner object configured as a System.in
	 * @return A character that corresponds to a seat on the plane
	 */
	private char requestSeat(Scanner kb) {
		System.out.println("Enter a column number:");
		return kb.next().charAt(0);
	}

	/**
	 * Validates that the user input is within the seating rage of row numbers
	 * and seat letters
	 * 
	 * @param row
	 *            The seating row in the plane
	 * @param seat
	 *            The letter of the seat
	 * @return True if values are within seating matrix, false otherwise
	 */
	private boolean validateRequestedSeat(int row, char seat) {
		boolean isValid = false;

		isValid = ((row < MAX_ROWS && row > 0) && seat == 'A' || seat == 'B'
				|| seat == 'C' || seat == 'D');
		if (!isValid) {
			System.out.println("Invalid seat number.\n");
		}

		return isValid;
	}

	/**
	 * Converts the letter seat selection to a integer for use in the array
	 * 
	 * @param seat
	 *            The seat letter selected
	 * @return An integer that corresponds to the index in the array
	 */
	private int convertSeatLetterToInt(char seat) {

		int seatNum = 0;
		switch (seat) {
		case 'A':
			seatNum = 0;
			break;
		case 'B':
			seatNum = 1;
			break;			
		case 'C':
			seatNum = 2;
			break;
		case 'D':
			seatNum = 3;
			break;
		}
		return seatNum;
	}

	/**
	 * Assigns the seat to the user as requested
	 * 
	 * @param r
	 *            The row in the plane the seat is in
	 * @param s
	 *            The seat converted to an integer
	 * @param l
	 *            The seat letter as a char
	 */
	private void makeSeatReservation(int r, int s, char l) {
		seatingMatrix[r - 1][s] = 'X';
		System.out.println("Seat " + r + l + " is assigned.\n");
	}

}
