package nordproject03;

import edu.du.dudraw.DUDraw;

public abstract class FaceEmoji extends Emoji {
	public FaceEmoji(int x, int y, int r) {
		super(x, y, r);
	}
	
	public void draw() {
		//Draws the yellow filled and black outline circle
		DUDraw.setPenColor(DUDraw.YELLOW);
		DUDraw.filledCircle(xPos, yPos, rad);
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.circle(xPos, yPos, rad);
		//Draws both of the eyes
		DUDraw.filledCircle(xPos - rad/2, yPos + rad/2, rad / 8);
		DUDraw.filledCircle(xPos + rad/2, yPos + rad/2, rad / 8);
	}
}
