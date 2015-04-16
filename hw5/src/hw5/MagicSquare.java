/***************************************************************************************
 *                         hw5/MagicSquare.java - Copyright rciampa
 *                         
 * Title: Homework #5 Magic Square
 * 
 * Abstract: A magic square has the same number of rows and columns, and "n" stands for
 * 			 the number of rows and columns it has. So, a magic square always contains
 * 		     n^2 numbers. A magic square that contains the integers from 1 to n^2 is 
 *           a magic square.
 *
 * @author rciampa
 * ID: 7470
 * Date: Wed Feb 22 2015 : 20:13:03
 * File: hw5/MagicSquare.java
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

package hw5;

import java.util.Scanner;

public class MagicSquare {

	public static void main(String[] args) {

		int[][] magicSquare;
		int num = requestMagicSquareSize();

		// Dynamically size the matrix
		magicSquare = new int[num][num];
		// Fill the Magic Square
		magicSquare = buildMagicSquare(magicSquare, num);
		// Print the magic square
		printMagicSquare(magicSquare, num);
	}
	
	

	/**
	 * builds the Magic Square from the user input
	 * 
	 * @param ms Magic square array
	 * @param size The odd integer the user entered
	 * @return The populated magic square
	 */
	private static int[][] buildMagicSquare(int[][] ms, int size) {

		/*
		 * Here we find the position for the number
		 * 1 as it is the key position
		 */
		int r = size - 1; //Adjusted for the array index
		int c = size / 2; //Finds the middle of the array column
		//Insert the number one (1) first at our location
		ms[r][c] = 1;

		
		/*
		 * We set i at 2 because i hold magic square numbers and we already
		 * started with one (1) in the place we needed it
		 */
		for (int i = 2; i <= size * size; i++) {
			//Use modulus for the circular row and column
			/*
			 * So if we start with a 5x5 magic square, we are at size=5, r=4 and c=2 from
			 * the finding of position one (1) above. Since we need to move down a row
			 * and can't, we go the the next row by (r + 1) mod 5 = 0. Next we need to 
			 * find the column for two (2) which in this case would be one more than where
			 * we placed one (1) at, so (c + 1) mod 5 = 3.
			 */
			
			//Check to see that we have a zero, if so set this location
			//as row and column
			if (ms[(r + 1) % size][(c + 1) % size] == 0) {
				r = (r + 1) % size; //Set the tested row
				c = (c + 1) % size; //Set the tested column
			} else {
				/*
				 * Here we are going to rotate through the index of rows to
				 * find the next position in the same column
				 */
				r = (r - 1 + size) % size;
			}
			//Set the value for this location
			ms[r][c] = i;
		}
		return ms;
	}

	/**
	 * Prints the magic square with formating
	 * 
	 * @param ms The magic square array
	 */
	private static void printMagicSquare(int[][] ms, int size) {
		
		//Print banner
		System.out.println("\n***** " + size + " x " + size + " Magic Square *****\n");
		//Loop to print the magic square
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++) {
				System.out.print("|");
				// We need to add spaces for digit place holders
				if (ms[i][j] < 10){System.out.print(" ");}
				if (ms[i][j] < 100){System.out.print(" ");}
				// There is always a space after the current number
				System.out.print(ms[i][j] + " ");
			}
			// Add the separator
			System.out.println("|");
		}
	}

	/**
	 * Gets an odd integer from the user
	 * 
	 * @return An odd integer
	 */
	private static int requestMagicSquareSize() {
		Scanner keyboard = new Scanner(System.in);
		int value = 0;
		do {
			System.out.print("Enter input number: ");
			value = keyboard.nextInt();
		} while ((value % 2 == 0));
		//
		keyboard.close();
		// Return the odd value to the calling code
		return value;
	}

}
