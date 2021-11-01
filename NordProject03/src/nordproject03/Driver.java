package nordproject03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import edu.du.dudraw.DUDraw;

public class Driver {

	public static void main(String[] args) throws IOException {
		//Sets up scanner to get the filename
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your filename please: ");
		String fileName = keyboard.nextLine();
		//Opens RAF pointing to specified filename
		Scanner file = new Scanner(new File(fileName));
		//Sets up canvas dimensions based on file data
		int rows = file.nextInt();
		int cols = file.nextInt();
		System.out.println(rows+ " " + cols);
		int width = 100 * cols;
		int height = 100 * rows;
		DUDraw.setCanvasSize(width, height);
		DUDraw.setXscale(0 , width);
		DUDraw.setYscale(0 , height);
		//Defines and configures the array of emojis
		Emoji[][] emojis = new Emoji[rows][cols];
		int xPos;
		int yPos = -50;
		for (int r = 0; r < rows; r ++) {
			xPos = -50;
			yPos += 100;
			for (int c = 0; c < cols; c++) {
				xPos += 100;
				String type = file.next();
				if (type.equals("smile")) {
					emojis[r][c] = new SmileyFaceEmoji(xPos, yPos, 50);
					System.out.println("smiley");
				}
				if (type.equals("surprise")) {
					emojis[r][c] = new SuprisedFaceEmoji(xPos, yPos, 50);
					System.out.println("suprised");
				}
				if (type.contains("clock")) {
					int time = file.nextInt();
					emojis[r][c] = new ClockEmoji(xPos, yPos, 50, time);
					System.out.println("clock");
				}
			}
		}
		
		//Draws all of the objects
		for (int r = 0; r < rows; r ++) {;
			for (int c = 0; c < cols; c++) {
				emojis[r][c].draw();
			}
		}
	}

}
