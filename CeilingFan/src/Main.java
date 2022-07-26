/**
 * Program Name: Main.java
 * Purpose: The main class for a ceiling fan simulation
 * Coder: Cameron Hughes
 * Date: July 26, 2022
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		CeilingFan fan = new CeilingFan();
		
		System.out.println("===Ceiling Fan Simulation===\n");
		
		boolean quit = false;
		while (!quit) {
			System.out.println("Commands:\nType '1' to pull the first chord and speed up the fan's rotation\n" + 
					"Type '2' to pull the second chord and change the fan's spin direction\n" +
					"Type 'x' to exit the program" );
			
			// Get user input
			System.out.print("\n> ");
			String userInput = input.nextLine();
			
			switch (userInput) {
			case "x":
				quit = true;
				break;
			case "1":
				fan.SpeedUp();
				break;
			case "2":
				fan.ChangeDirection();
				break;
			case "":
				break;
			default:
				System.out.println("Invalid input. Please re-enter.\n");
				break;
			}// end switch
		}// end while
		
		System.out.println("\n\n===Program Terminated===");
		input.close();
	}//end main
}
