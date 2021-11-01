package nordproject03;

import edu.du.dudraw.DUDraw;

public class ClockEmoji extends Emoji {
	int hour;
	public ClockEmoji(int x, int y, int r, int h) {
		super(x, y, r);
		this.hour = h;
	}
	
	public void draw() {
		//Draws outline
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.circle(xPos, yPos, rad);
		//Draws the hour hand using trig
		int hourAngle = (360/12) * hour + (90 - (60 * hour));
		double x = xPos + (rad * Math.cos(hourAngle * (Math.PI / 180)));
		double y = yPos + (rad * Math.sin(hourAngle * (Math.PI / 180)));
		DUDraw.line(xPos, yPos, x, y);
		System.out.println("drew clock " + "x:" + xPos + " y:" + yPos);
	}
}
