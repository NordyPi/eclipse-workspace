package nordproject05;

import java.awt.Canvas;
import java.awt.Color;
import java.util.ArrayList;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

public class WindowDisplay implements DrawListener {
	//class variables
	private ArrayList<Window> windows;
	private ArrayList<Window> windowReset;
	private Draw canvas;
	//constructor - sets up canvas, arraylist, and the listener
	public WindowDisplay(int xScale, int yScale) {
		canvas = new Draw();
		windows = new ArrayList<Window>();
		windowReset = new ArrayList<Window>();
		canvas.setCanvasSize(xScale, yScale);
		canvas.setXscale(0, xScale);
		canvas.setYscale(0, yScale);
		canvas.addListener(this);
	}
	//Adds a window to our window array with specified coords, dimensions, and color
	public void addWindow(double x, double y, double xD, double yD, Color c) {
		windows.add(new Window(x, y, xD, yD, c));
		windowReset.add(new Window(x, y, xD, yD, c));
	}
	//loops through our window array and draws all windows
	public void draw() {
		for (Window w : windows) {
			w.draw();
		}
	}
	//sets the window array equal to
	public void reset() {
		windows.clear();
		for (Window w : windowReset) {
			windows.add(w);
		}
	}
	@Override
	public void keyPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	//Checks for key input on the canvas
	public void keyTyped(char c) {
		//if q is pressed, quits the program
		if (c == 'q') {
			System.exit(0);
		}
		//if r is pressed, resets the windows
		if (c == 'r') {
			reset();
			draw();
		}
	}
	@Override
	//Checks for mouse click on canvas, if so, does window manipulation check
	public void mouseClicked(double x, double y) {
		//loops through the windows backwards, since the top window is last in the list
		for (int i = windows.size()-1; i >= 0; i --) {
			if (windows.get(i).isClicked(x, y)) {
				//if a window is clicked, it deletes it from the list, and then adds it at the end (on top)
				Window temp = windows.get(i);
				windows.remove(i);
				windows.add(temp);
				break;
			}
		}
		draw();
		
	}
	@Override
	public void mouseDragged(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	class Window {
		//class variables
		private double xPos;
		private double yPos;
		private double xDim;
		private double yDim;
		private Color color;
		//class constructor with coords, dimensions, and color
		public Window(double x, double y, double xD, double yD, Color c) {
			this.xPos = x;
			this.yPos = y;
			this.xDim = xD;
			this.yDim = yD;
			this.color = c;
		}
		//Draws the window on the canvas
		public void draw() {
			canvas.setPenColor(this.color);
			canvas.filledRectangle(xPos, yPos, xDim, yDim);
		}
		//Checks to see if the mouse coords from isClicked lies inside of the window
		public boolean isClicked(double mx, double my) {
			//mouse x and y have to be between the edges of the rectangles
			if ((mx > xPos - xDim) && (mx < xPos + 
					xDim) && (my > yPos - yDim) && (my < yPos + yDim)) {
				return true;
			} else {
				return false;
			}
		}
	}
}

