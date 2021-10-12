package nordlab09;

import java.util.Random;

import edu.du.dudraw.DUDraw;

public class WobbyCircle extends MovingCircle{

	public WobbyCircle(double r) {
		super(r);
		super.setColor(DUDraw.RED);
	}
	
	public void move() { 
		Random ran = new Random();
		forward();
		bounce();
		draw();
		
		double temp = ran.nextDouble();
		if (temp <= 0.25) {
			this.xPos += ran.nextDouble() / 1000;
			this.yPos += ran.nextDouble() / 1000;
		} else if (temp <= 0.50) {
			this.xPos -= ran.nextDouble() / 1000;
			this.yPos += ran.nextDouble() / 1000;
		} else if (temp <= 0.75) {
			this.xPos -= ran.nextDouble() / 1000;
			this.yPos -= ran.nextDouble() / 1000;
		}
		else {	
			this.xPos += ran.nextDouble() / 1000;
			this.yPos -= ran.nextDouble() / 1000;
		}
	}
}
