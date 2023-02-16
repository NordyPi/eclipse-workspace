package nordlab09;

import java.awt.Color;
import java.util.Random;
import edu.du.dudraw.DUDraw;

public abstract class MovingCircle {
	//Class variables
	protected double xPos;
	protected double yPos;
	protected double xVel;
	protected double yVel;
	protected double radius;
	private Color color;
	Random ran = new Random();
	
	//Radius constructor
	public MovingCircle(double r) {
		//Sets up radius and radom x and y coords
		this.radius = r;
		this.xPos = ran.nextDouble();
		this.yPos = ran.nextDouble();
		
		//Randomizes velocity
		double temp = ran.nextDouble();
		if (temp <= 0.25) {
			this.xVel = ran.nextDouble();
			this.yVel = ran.nextDouble();
		} else if (temp <= 0.50) {
			this.xVel = ran.nextDouble() * -1.0;
			this.yVel = ran.nextDouble();
		} else if (temp <= 0.75) {
			this.xVel = ran.nextDouble() * -1.0;
			this.yVel = ran.nextDouble() * -1.0;
		}
		else {
			this.xVel = ran.nextDouble();
			this.yVel = ran.nextDouble() * -1.0;
		}
		this.xVel = this.xVel / 1000.0;
		this.yVel = this.yVel / 1000.0;
	}
	
	//Method to move. Does nothing, as implemented by child classes
	public abstract void move();
	
	//Method to draw. changes pen color back to black afterwards.
	public void draw() {
		DUDraw.setPenColor(this.color);
		DUDraw.filledCircle(xPos, yPos, radius);
		DUDraw.setPenColor(DUDraw.BLACK);
	}
	
	//Move method. adds the velocity to current coords.
	public void forward() {
		this.xPos += this.xVel;
		this.yPos += this.yVel;
	}
	
	//Bouunce method. flips the velocity if the circle is out of bounds of the respective axis.
	public void bounce() {
		if ((this.xPos >= 1.0 && this.xVel > 0) || (this.xPos <= 0.0 && this.xVel < 0)) {
			this.xVel = this.xVel * -1.0;
		}
		if ((this.yPos >= 1.0 && this.yVel > 0) || (this.yPos <= 0.0 && this.yVel < 0)) {
			this.yVel = this.yVel * -1.0;
		}
	}
	
	//Sets color!
	public void setColor(Color c) {
		this.color = c;
	}

}
