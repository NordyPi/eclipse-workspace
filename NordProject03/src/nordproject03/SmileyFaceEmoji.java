package nordproject03;

import edu.du.dudraw.DUDraw;

public class SmileyFaceEmoji extends FaceEmoji {
	public SmileyFaceEmoji(int x, int y, int r) {
		super(x, y, r);
	}
	
	public void draw() {
		//Calls super to draw the base
		super.draw();
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.arc(xPos, yPos, rad / 4, 180, 0);
		System.out.println("drew smiley " + "x:" + xPos + " y:" + yPos);
	}
}
