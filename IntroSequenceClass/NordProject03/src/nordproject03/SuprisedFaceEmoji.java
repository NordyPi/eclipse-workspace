package nordproject03;

import edu.du.dudraw.DUDraw;

public class SuprisedFaceEmoji extends FaceEmoji{
	public SuprisedFaceEmoji(int x, int y, int r) {
		super(x, y, r);
	}
	
	public void draw() {
		super.draw();
		DUDraw.setPenColor(DUDraw.WHITE);
		DUDraw.filledCircle(xPos, yPos - rad/2, rad/8);
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.circle(xPos, yPos - rad/2, rad/8);
		System.out.println("drew surprised " + "x:" + xPos + " y:" + yPos);
	}
}
