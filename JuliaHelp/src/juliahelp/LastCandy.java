package juliahelp;

import java.util.Scanner;

/* Julia Herberg
 * October 2021
 * Last Candy
 */
public class LastCandy {

	public static void main(String[] args) {
		// step 1
		System.out.println(
				"In this game, you and your partner will take turns taking candy out of any of the boxes, until there is none left. The person to take the last piece wins!");
		int[] candiesBox;
		candiesBox = new int[6];
		for (int i = 0; i < 6; i++) {
			candiesBox[i] = 7;
		}
		//Bool to store keep track of if the game is over. Set to false to start
		boolean isGameOver = false;
		// step 2
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Player 1, what's your name?: ");
		String player1 = keyboard.nextLine();
		System.out.print("Player 2, what's your name?: ");
		String player2 = keyboard.nextLine();
		
		
		// s3
		//Main game loop here. while the game isn't over, keep running this. Start on turn 0
		int turn = 0;
		while(!isGameOver) {
			//increase the turn count.
			turn ++;
			displayGame(candiesBox);
			System.out.println(" ");
			int box = 0;
			int candy = 0;
			//Current player String, this will reduce repetitive code
			String currentPlayer = "";
			
			//Determines which player's turn it is
			if (turn % 2 != 0) {
				currentPlayer = player1;
			} else if (turn % 2 == 0) {
				currentPlayer = player2;
			}
			
			//This next part will ask the current player for input and check for valid input
			boolean isValidInput = false;
			while(!isValidInput) {
				//First prompt for input:
				System.out.print(currentPlayer + ", which box would you like to take candy from? Please input a number:     ");
				box =  keyboard.nextInt();
				//Checks to see if it is outside of the range. ALSO, if the selected box was empty, doesn't allow
				if (box > 6 || box < 0 || candiesBox[box-1] == 0) {
					System.out.print("Sorry, box " + box + " wasn't an option");
					System.out.println(" ");
				} else {
					//If the input is valid, set the bool to true, which will end the input validation while loop
					isValidInput = true;
					System.out.println(" ");
				}
			}
			//of the input for the box is valid, it will then go to asking for the amount of candy to take. sets the bool to false again
			isValidInput = false;
			while(!isValidInput) {
				System.out.print("How many candies would you like to take from " + box + "?  Please input a number:     ");
				candy = keyboard.nextInt();
				
				//Can't take more candies than what are in the box currently.
				if (candy > candiesBox[box-1] || candy < 1) {
					System.out.print("Sorry, " + candy + " candies wasn't an option");
					System.out.println(" ");
				} else {
					isValidInput = true;
					System.out.println(" ");
				}
			}
			
			//Now if both box # and candy # are valid, we will remove specified candy from the specified box
			candiesBox[box - 1] = candiesBox[box - 1] - candy;
			//check to see if the game is over, and update the isGameOver bool
			isGameOver = gameOver(candiesBox);
			// if this returns true, the while loop will end and proceed to below code.
		}
		
		
		// step 4
		System.out.println(" ");

		if (turn % 2 != 0) {
			System.out.println(player1 + " won!");
		}
		if (turn % 2 == 0) {
			System.out.println(player2 + " won!");
		}
		keyboard.close();
	}

	// step 3
	public static void displayGame(int[] candiesBox) {
		//loop through the passed in int[] object
		int i;
		for (i = 0; i < 6; i++) {
			System.out.print(candiesBox[i] + " ");
		}
		return;

	}

// step 9 - I changed this to return a boolean. if the game isn't over, return false, if it is return true.
	//We are going to pass in candiesBox here as well
	public static boolean gameOver(int[] candiesBox) {
		//I would check to see here if each box only has 0 and return true, rather than not zero and return false.
		if (candiesBox[0] == 0 && candiesBox[1] == 0 && candiesBox[2] == 0 && candiesBox[3] == 0 && candiesBox[4] == 0
				&& candiesBox[5] == 0) {
			System.out.print("Game Over");
			return true;
		} else {
			return false;
		}
	}
}