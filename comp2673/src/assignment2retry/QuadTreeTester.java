package assignment2retry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;


public class QuadTreeTester implements DrawListener {
	// sets up variables
	private QuadTree qt;
	private List<Planet> planets;
	private Draw canvas;
	// just makes a new instance of class
	public static void main(String[] args) {
		new QuadTreeTester();
	}
	// sets up the DUDraw canvas and sets up planet list and quadtree
	public QuadTreeTester() {
		canvas = new Draw("QuadTreeTester");
		canvas.setCanvasSize(500, 500);
		canvas.setXscale(0, 1000);
		canvas.setYscale(0, 1000);
		qt = new QuadTree(1000, 1000, 5);
		planets = new ArrayList<Planet>();
		canvas.addListener(this);
		update();
	}
	@Override
	public void mousePressed(double x, double y) {
		// makes a new planet on mousePressed. adds to list and quadtree, then updates canvas
		Planet planet = new Planet((int) x,(int) y);
		qt.addPlanet(planet);
		planets.add(planet);
		update();
	}

	@Override
	public void mouseDragged(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int keycode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int keycode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// clears, then draws the quadtree and all planets
		canvas.clear();
		canvas.setPenColor(Color.BLACK);
		qt.draw(canvas);
		for(int i = 0; i < planets.size(); i++) {
			planets.get(i).draw(canvas);

		}

	}
}
