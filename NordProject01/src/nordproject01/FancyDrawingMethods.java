package nordproject01;

import java.awt.Color;
import edu.du.dudraw.DUDraw;

public class FancyDrawingMethods {
	//Define final color variables
	public static final Color WACK_GREEN = new Color(65, 156, 96);
	public final static Color WACK_RED = new Color(67, 48, 43);
	public final static Color WACK_PINK = new Color(255, 65, 205);
	
	//Private static tracker variables
	private static int starCalled = 0;
	private static int ngonCalled = 0;
	
	//Method for drawing filled star with 4 points
	public static void starWithFourPoints(double centerX, double centerY, double radius) {
		//Define arrays for storing point values
		double[] xPoints = new double[8];
		double[] yPoints = new double[8];
		//constant for our inner point displacement
		double radPercent = radius / 4;
		
		//define all x points
		xPoints[0] = centerX;
		xPoints[1] = centerX + radPercent;
		xPoints[2] = centerX + radius;
		xPoints[3] = centerX + radPercent;
		xPoints[4] = centerX;
		xPoints[5] = centerX - radPercent;
		xPoints[6] = centerX - radius;
		xPoints[7] = centerX - radPercent;
		//define all y points
		yPoints[0] = centerY + radius;
		yPoints[1] = centerY + radPercent;
		yPoints[2] = centerY;
		yPoints[3] = centerY - radPercent;
		yPoints[4] = centerY - radius;
		yPoints[5] = centerY - radPercent;
		yPoints[6] = centerY;
		yPoints[7] = centerY + radPercent;
		
		//draw the shape and increase counter
		DUDraw.filledPolygon(xPoints, yPoints);
		starCalled += 1;
	}
	
	//Method for drawing Regular Ngon
	public static void filledRegularNgon(double centerX, double centerY, double radius, int n) {
		//Define arrays for storing point values
		double[] xPoints = new double[n];
		double[] yPoints = new double[n];
		
		//variables for calculating coordinates
		double angle = 0;
		double angleBetween = 360 / (double) n;
		
		//Calculate x and y points using trig by going around unit circle in increments of 360/n for the degrees
		for (int i = 0; i < n; i ++) {
			xPoints[i] = (centerX + (radius) * Math.cos(Math.toRadians(angle)));
			angle += angleBetween;
		}
		angle = 0.0;
		for (int i = 0; i < n; i ++) {
			yPoints[i] = (centerY + (radius) * Math.sin(Math.toRadians(angle)));
			angle += angleBetween;
		}
		//Draw the shape and increase counter
		DUDraw.filledPolygon(xPoints, yPoints);
		ngonCalled += 1;
	}
	
	//Getters for call trackers
	public static int getFilledRegularNgonCalls() {
		return ngonCalled;
	}
	public static int getFourPointedStarCalls() {
		return starCalled;
	}

}
