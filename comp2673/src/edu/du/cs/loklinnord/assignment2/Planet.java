package edu.du.cs.loklinnord.assignment2;

import edu.du.dudraw.DUDraw;
import edu.du.dudraw.Draw;

public class Planet {
	// instance variables
	private int xPos;
	private int yPos;
	private int radius;
	
	public Planet(int x, int y, int r) {
		this.xPos = x;
		this.yPos = y;
		this.radius = r;
	}
	
	public void draw(Draw panel) {
		panel.setPenColor(DUDraw.BOOK_BLUE);
		panel.filledCircle(xPos, yPos, radius);
	}
	
	public boolean between(int[] coords) {
		if ((xPos > coords[0] && xPos < coords[1]) && (yPos > coords[2] && yPos < coords[3])) {
			return true;
		} else {
			return false;
		}
	}
}
