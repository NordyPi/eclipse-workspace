package edu.du.cs.loklinnord.quad;
import java.awt.Color;
import java.awt.Rectangle;

import edu.du.dudraw.Draw;

public class SpaceShip {
	// class variables
	private Color color;
	private int radius;
	private int xPos;
	private int yPos;
	private int xView;
	private int yView;
	// constructor to set ship location and viewing distance
	public SpaceShip(int x, int y, int xView, int yView) {
		this.color = Color.MAGENTA;
		this.radius = 10;
		this.xPos = x;
		this.yPos = y;
		this.xView = xView;
		this.yView = yView;
	}
	// draws green box that represents ships viewing area
	public void drawViewingArea(Draw canvas) {
		canvas.setPenColor(Color.GREEN);
		canvas.rectangle(xPos, yPos, xView/2, yView/2);
	}
	// returns the area of what the ship can see
	public Rectangle getViewingArea() {
		return new Rectangle(xPos - xView/2, yPos - yView/2, xView, yView);
	}
	// draws itself on the canvas
	public void draw(Draw canvas) {
		canvas.setPenColor(color);
		canvas.filledCircle(xPos, yPos, radius);
	}
	// moves based on passed in x and y values
	public void move(int x, int y) {
		xPos += x;
		yPos += y;
	}
	// checks for collisions with specified planet
	public boolean collisionCheck(Planet p) {
		boolean collide = p.colition(xPos, yPos, radius);
		return collide; 
	}
	// changes color if collision
	public void collide(boolean c) {
		if(c) {
			color = Color.RED;
		}
		else {
			color = Color.BLUE;
		}
	}
}
