/**
 * Program Name: CeilingFan.java
 * Purpose: An object class to simulate a ceiling fan and the various options associated with it.
 * Coder: Cameron Hughes
 * Date: July 26, 2022
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CeilingFan {
	int MAX_SPEED = 3;
	
	// Attributes
	int speed = 0; // default to 0
	String direction = "Clockwise"; // default to Clockwise
	
	// Method for speeding up the fan
	public void SpeedUp() {
		if (speed >= MAX_SPEED) {
			speed = 0;
		}
		else {
			speed++;
		}
		
		// Output new fan settings
		try {
			this.Print();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// Method for changing the direction of the fan
	public void ChangeDirection() {
		if (direction == "Clockwise") {
			direction = "Counter-Clockwise";
		}
		else if (direction == "Counter-Clockwise") {
			direction = "Clockwise";
		}
		
		// Output new fan settings
		try {
			this.Print();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// Method for printing out the fan's info with an ascii animation
	public void Print() throws InterruptedException, IOException {
		Scanner input = new Scanner(System.in);
		
		// Calculate how fast the animation plays based off of current speed settings
		int playSpeed = 500 - speed * 150;
		if (playSpeed < 10) { // maximum speed allowed
			playSpeed = 10;
		}
		int currentFrame = 0;
		
		while(System.in.available() == 0) {
			
			// Cycle through the frames in the correct order
			if (direction == "Clockwise") {
				currentFrame++;
				if (currentFrame == frames.size()) {
					currentFrame = 0;
				}
			}
			else if (direction == "Counter-Clockwise") {
				currentFrame--;
				if (currentFrame == -1) {
					currentFrame = frames.size() - 1;
				}
			}
			
			// Check if the fan is moving
			if (speed == 0) {
				System.out.println(frames.get(0));
			}
			else {
				System.out.println(frames.get(currentFrame));
			}
			System.out.println("   Speed: " + speed + "       Direction: " + direction);
			System.out.println("        Press ENTER to continue...\n\n\n\n");
			
			Thread.sleep(playSpeed);
		}
		// Clean out the input
		String userInput = input.nextLine();
		
	}// end print
	
	// Animation frames
	List<String> frames = Arrays.asList(
			"                                          \n" +
			"                    ##                    \n" +
			"                    ##                    \n" +
			"                    ##                    \n" +
			"      ######        ##        ######      \n" +
			"           ####     ##     ####           \n" +
			"               ###|0000|###               \n" +
			"                 |000000|                 \n" +
			"               ###|0000|###               \n" +
			"           ####     ##     ####           \n" +
			"      ######        ##        ######      \n" +
			"                    ##                    \n" +
			"                    ##                    \n" +
			"                    ##                    \n" +
			"                                          ",
			"                                          \n" +
			"                         ##               \n" +
			"        ###              ##               \n" +
			"          ####          ##                \n" +
			"             ####      ##                 \n" +
			"                ###   ##                  \n" +
			"                  |0000|    #########     \n" +
			"             ####|000000|####             \n" +
			"     #########    |0000|                  \n" +
			"                  ##   ###                \n" +
			"                 ##      ####             \n" +
			"                ##          ####          \n" +
			"               ##              ###        \n" +
			"               ##                         \n" +
			"                                          ",
			"                                          \n" +
			"           ##                ##           \n" +
			"            ##              ##            \n" +
			"              ##          ##              \n" +
			"               ##        ##               \n" +
			"                 ##    ##                 \n" +
			"                  |0000|                  \n" +
			"     ############|000000|############     \n" +
			"                  |0000|                  \n" +
			"                 ##    ##                 \n" +
			"               ##        ##               \n" +
			"              ##          ##              \n" +
			"            ##              ##            \n" +
			"           ##                ##           \n" +
			"                                          ",
			"                                          \n" +
			"               ##                         \n" +
			"               ##              ###        \n" +
			"                ##          ####          \n" +
			"                 ##      ####             \n" +
			"                  ##   ###                \n" +
			"     #########    |0000|                  \n" +
			"             ####|000000|####             \n" +
			"                  |0000|    #########     \n" +
			"                ###   ##                  \n" +
			"             ####      ##                 \n" +
			"          ####          ##                \n" +
			"        ###              ##               \n" +
			"                         ##               \n" +
			"                                          ");
}
