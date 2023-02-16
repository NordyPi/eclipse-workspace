package nordlab19;
// Worked with Luke Clements

import edu.du.dudraw.DUDraw;

public class Driver {

	public static void main(String[] args) {
		DUDraw.enableDoubleBuffering();
		drawCircles(0.5, 0.5, 0.25, 5);
		//drawTreeRecursive(10, 0.5, 0, 0.5, Math.PI / 2);
		DUDraw.show();

	}
	
	public static void drawCircles(double x, double y, double radius, int n) {
		double factor = radius / 2;
		if (n <= 0) {
			return;
		}
		DUDraw.circle(x, y, radius);
		drawCircles(x + radius, y, factor, n-1);
		drawCircles(x - radius, y, factor, n-1);
		drawCircles(x, y + radius, factor, n-1);
		drawCircles(x, y - radius, factor, n-1);
	}
	
	public static void drawTreeRecursive(int n, double x, double y, double length, double angle) {
		if (n <= 0) {
			return;
		}
		double endX = x + length * Math.cos(angle);
		double endY = y + length * Math.sin(angle);
		DUDraw.line(x, y, endX, endY);
		drawTreeRecursive(n-1, endX, endY, length / 2, angle + Math.PI/4);
		drawTreeRecursive(n-1, endX, endY, length /2, angle - Math.PI/4);
	}
	
	public static void recursiveSquare(int depth, double x, double y, double size) {
		double offset = size / 2;
		if (depth <= 0) {
			return;
		}
		drawSquare(x, y, size);
		recursiveSquare(depth-1, x + offset, y + offset, offset);
		recursiveSquare(depth-1, x - offset, y + offset, offset);
		recursiveSquare(depth-1, x + offset, y - offset, offset);
		recursiveSquare(depth-1, x - offset, y - offset, offset);
	}
	
	public static void drawSquare(double x, double y, double size) {
		DUDraw.setPenColor(DUDraw.LIGHT_GRAY);
		DUDraw.filledSquare(x, y, size / 2);
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.square(x, y, size / 2);
	}
}
