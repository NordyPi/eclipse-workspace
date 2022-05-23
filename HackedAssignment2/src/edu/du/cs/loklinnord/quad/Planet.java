package edu.du.cs.loklinnord.quad;
import java.awt.Color;
import java.awt.Rectangle;

import edu.du.dudraw.Draw;

public class Planet {
	// class variables
	private int xPos;
	private int yPos;
	private int radius;
	private Color color;
	// constructor. sets to specified position with a random radius
	public Planet(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius= (int)(Math.random()*5) + 5;
		color = Color.BLUE;
	}
	// draws planet to specified canvas
	public void draw(Draw canvas) {
		canvas.setPenColor(color);
		canvas.filledCircle(xPos, yPos, radius);
	}
	// detirmines if planet is within colition and returns boolean
	public boolean colition(int xPos, int yPos, int radius) {
		// using distance formula for calculations
		double distance = Math.sqrt(Math.pow(this.xPos - xPos, 2) + Math.pow(this.yPos - yPos, 2));
		double radiusDistance = this.radius + radius;
		if(distance < radiusDistance) {
			return true;
		}
		return false;
	}
	// changes color to orange if touching ship
	public void colitionColor(boolean collide) {
		if(collide) {
			color = Color.ORANGE;
		} else {
			color = new Color(128,0,128);
		}
	}
	// compares its coords to specified rectangle to detiermine if inside bounds
	public boolean insideArea(Rectangle r) {
		return r.contains(xPos, yPos);
	}
}
