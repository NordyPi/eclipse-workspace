package edu.du.nord.project;

import java.awt.event.KeyEvent;
import java.util.Random;

import edu.du.dudraw.DUDraw;

public class Simulator {
	//Instance variables
	public enum Mode {
		SAND, FLOOR
	}
	private enum Element {
		SAND, FLOOR, EMPTY
	}
	
	private int width;
	private int height;
	private Element[][] elements;
	private Mode drawMode;
	
	//Constructor
	public Simulator(int w, int h) {
		//Sets up variables
		this.width = w;
		this.height = h;
		this.elements = new Element[h][w];
		DUDraw.setXscale(0, h);
		DUDraw.setYscale(0, w);
		this.drawMode = Mode.SAND;
		//Sets all elements to EMPTY
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				this.elements[r][c] = Element.EMPTY;
			}
		}
	}
	
	//Method to switch drawing mode when called
	public void switchMode(int k) {
		if (k == KeyEvent.VK_F) {
			drawMode = Mode.FLOOR;
		}
		if (k == KeyEvent.VK_S) {
			drawMode = Mode.SAND;
		}
	}
	//Mode getter
	public Mode getMode() {
		return this.drawMode;
	}
	//Method to draw sand and floor
	public void placeElement(int row, int col) {
		System.out.println(row);
		System.out.println(col);
		if (this.drawMode == Mode.SAND) {
			DUDraw.setPenColor(DUDraw.RED);
			Random ran = new Random();
			//Loops through a square centered around the mouse click coords
			for (int r = row - 2; r < row + 2; r += 2) {
				for (int c = col -2; c < col + 2; c += 2) {
					//Will only draw with 50/50 chance for random, and catches errors and skips out of bounds
					if (ran.nextDouble() >= 0.5 && this.elements[r][c] == Element.EMPTY) {
						try {
							this.elements[r][c] = Element.SAND;
							DUDraw.filledSquare(r, c, 1);
						} catch(ArrayIndexOutOfBoundsException exception) {
							System.out.println(exception);
						}
					}
				}
					
			}
		}
		else {
			DUDraw.setPenColor(DUDraw.BLACK);
			for (int r = row - 3; r < row + 3; r++) {
				for (int c = col -3; c < col + 3; c++) {
					DUDraw.filledSquare(r, c, 1);
					this.elements[r][c] = Element.FLOOR;
				}
			}
		}
	}
	
	//Updates the location of all the sand, makes it fall
	public void update() {
		//Loops through entire array, not bottom row cuz it won't move
		for (int r = 0; r < height; r++) {
			for (int c = 1; c < width; c++) {
				//Checks to see if the sand can fall. If so, moves it down and replaces with empty
				if(this.elements[r][c] == Element.SAND && this.elements[r][c-1] == Element.EMPTY) {
					this.elements[r][c] = Element.EMPTY;
					this.elements[r][c-1] = Element.SAND;
				}
			}
		}
	}
	//Method to draw the grid
	public void draw() {
		//loops through entire array, checks the contents, and draws sand or floor
		for (int r = 0; r < height; r++) {
			for (int c = 1; c < width; c++) {
				if(this.elements[r][c] == Element.SAND) {
					DUDraw.setPenColor(DUDraw.RED);
					DUDraw.filledSquare(r, c, 1);
				}
				if(this.elements[r][c] == Element.FLOOR) {
					DUDraw.setPenColor(DUDraw.BLACK);
					DUDraw.filledSquare(r, c, 1);
				}	
			
			}
		}
	}
}
