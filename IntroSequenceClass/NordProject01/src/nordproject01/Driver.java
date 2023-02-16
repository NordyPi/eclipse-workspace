package nordproject01;

import edu.du.dudraw.DUDraw;

public class Driver {

	public static void main(String[] args) {
		//Copied and pasted all of this code. Replaced with my color variables
		
		DUDraw.setCanvasSize(1000, 1000);
		DUDraw.setPenRadius(2);
		DUDraw.clear(DUDraw.WHITE);
		// yellow "circle" in center of canvas
		DUDraw.setPenColor(DUDraw.YELLOW);
		FancyDrawingMethods.filledRegularNgon(0.5, 0.5, 0.5, 20);
		// orange-ish triangular shape for nose:
		DUDraw.setPenColor(FancyDrawingMethods.WACK_PINK);
		FancyDrawingMethods.filledRegularNgon(0.5, 0.5, 0.1, 3);
		// green-ish star for eye on the left:
		DUDraw.setPenColor(FancyDrawingMethods.WACK_RED);
		FancyDrawingMethods.starWithFourPoints(0.3, 0.7, 0.1);
		// red-ish star for eye on the right:
		DUDraw.setPenColor(FancyDrawingMethods.WACK_GREEN);
		FancyDrawingMethods.starWithFourPoints(0.7, 0.7, 0.2);
		// "mouth" drawn with two ngons
		DUDraw.setPenColor(255, 0, 0);// red
		FancyDrawingMethods.filledRegularNgon(0.45, 0.2, 0.15, 9);
		DUDraw.setPenColor(255, 255, 255); // white
		FancyDrawingMethods.filledRegularNgon(0.45, 0.2, 0.13, 9);
		
		//Display tracker variables
		System.out.println("Number of calls to starWithFourPoints: " + FancyDrawingMethods.getFourPointedStarCalls());
		System.out.println("Number of calls to regularNgon: " + FancyDrawingMethods.getFilledRegularNgonCalls());
	}

}
