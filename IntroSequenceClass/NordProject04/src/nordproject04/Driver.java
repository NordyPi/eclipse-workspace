package nordproject04;

import java.awt.Color;
import java.io.*;
import java.util.*;
import edu.du.dudraw.DUDraw;

public class Driver {
	public static void main(String[] args) {
		//Sets up scanner for input and variables
		Scanner input = new Scanner(System.in);
		boolean keepLooping = false;
		int operationSelect;
		String fileName = null;
		String secretFileName = null;
		Color[][] imageArray = null;
		Color[][] secretImageArray = null;

		DUDraw.clear();
		DUDraw.enableDoubleBuffering();

		while (!keepLooping) {
			operationSelect = 0;
			// Asks the user what they want to use
			while (operationSelect == 0) {
				System.out.println("what would you like to do?: ");
				System.out.println("1 - extract image");
				System.out.println("2 - embed image");
				//Error catching if file isn't there
				try {
					operationSelect = input.nextInt();
					input.nextLine();
				}catch (InputMismatchException e){
					System.out.println("Please enter an integer. Try again:");
					input.nextLine();
				}
			}
			// Depending on input decrypts or encrypts file
			if (operationSelect == 1){
				try {
					// sets up extraction
					System.out.println("Enter fileName of image to extract from: ");
					fileName = input.nextLine();
					imageArray = BMPIO.readBMPFile(fileName);
					secretImageArray = Steganography.extractSecretImage(fileName);
				}catch (IOException e){
					System.out.println(e.getMessage());
					continue;
				}
			} else if (operationSelect == 2){
				try {
					//sets up embedding
					System.out.println("enter original image fileName: ");
					fileName = input.nextLine();
					System.out.println("enter fileName of image to embed: ");
					secretFileName = input.nextLine();
					imageArray = BMPIO.readBMPFile(fileName);
					secretImageArray = Steganography.embedSecretImage(fileName, secretFileName);
				}catch (IOException e){
					System.out.println(e.getMessage());
					continue;
				}
			} else {
				//if not one or two entered, goes back
				System.out.println("Please enter 1 or 2. Try again:");
				continue;
			}
			//sets up canvas
			setupDualCanvas(imageArray, secretImageArray);
			drawDualImage(imageArray, secretImageArray);

			// asks to loop
			System.out.println("Perform another operation? y/n");
			String ans = input.nextLine();
			if (ans == "n") {
				keepLooping = true;
			}
		}		
	}

	//configures single draw canvas
	public static void setupCanvas(Color[][] imageArray) {
		DUDraw.setCanvasSize(imageArray[0].length, imageArray.length);
		DUDraw.setXscale(0, imageArray[0].length);	
		DUDraw.setYscale(0, imageArray.length);
	}

	// sets up canvas to fit both original and altered image
	public static void setupDualCanvas(Color[][] imageArray, Color[][] imageArray2) {
		int tallerImage;
		if (imageArray.length < imageArray2.length) {
			tallerImage = imageArray2.length;
		}else {
			tallerImage = imageArray.length;
		}
		DUDraw.setCanvasSize(imageArray[0].length+imageArray2[0].length, tallerImage);
		DUDraw.setXscale(0, imageArray[0].length+imageArray2[0].length);
		DUDraw.setYscale(0, tallerImage);
	}

	// Draws image from the imageArray data
	public static void drawImage(Color[][] imageArray) {
		if (imageArray != null) {
			for (int row = 0; row < imageArray.length; ++row) {
				for (int col = 0; col < imageArray[0].length; ++col) {
					DUDraw.setPenColor(imageArray[row][col]);
					DUDraw.point(col,row);
				}
			}
		}
		DUDraw.show();
	}

	//draws the doubleImage, both origiiinal and altered from arrayData
	public static void drawDualImage(Color[][] imageArray, Color[][] imageArray2) {
		if (imageArray != null && imageArray2 != null) {
			for (int row = 0; row < imageArray.length; ++row) {
				for (int col = 0; col < imageArray[0].length; ++col) {
					DUDraw.setPenColor(imageArray[row][col]);
					DUDraw.point(col,row);
				}
			}
			for (int row = 0; row < imageArray.length; ++row) {
				for (int col = 0; col < imageArray[0].length; ++col) {
					DUDraw.setPenColor(imageArray2[row][col]);
					DUDraw.point(col+imageArray[0].length,row);
				}
			}
		}
		DUDraw.show();
	}
}
