package edu.du.cs.loklinnord.quad;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

public class SpaceShipSimulation implements DrawListener {
	// same as QT test, just sets up new instance
	public static void main(String[] args) {
		new SpaceShipSimulation();
	}
	// class variables
	private QuadTree qt;
	private List<Planet> planets;
	private Boolean enableQuadTree;
	private Draw mapCanvas; 
	private Draw viewCanvas;
	private SpaceShip ship;
	private int changex;
	private int changey;
	// constructor. sets up canvas, planets list, quad tree
	public SpaceShipSimulation() {
		mapCanvas = new Draw("map");
		mapCanvas.setCanvasSize(500, 500);
		mapCanvas.enableDoubleBuffering();
		mapCanvas.setXscale(0, 1000);
		mapCanvas.setYscale(0, 1000);
		viewCanvas = new Draw("view");
		viewCanvas.setCanvasSize(500, 500);
		viewCanvas.enableDoubleBuffering();
		planets = createPlanets(1000, 1000);
		qt = new QuadTree(1000, 1000, 10);
		// adds planets to qt
		for(int i = 0; i < planets.size(); i++) {
			qt.addPlanet(planets.get(i));		
		}	
		enableQuadTree = false;
		ship = new SpaceShip(250, 250, 100, 100);
		// sets movement variables to 0 and configures listener
		changex = 0;
		changey = 0;
		viewCanvas.addListener(this);
		viewCanvas.startUpdate();
	}
	// makes random collection of planets for our clusters
	public List<Planet> createPlanets(int x, int y) {
		List<Planet> p = new ArrayList<Planet>();
		for(int i = 0; i < 5; i++) {
			int counterClockWiseX = (int) (Math.random() * x);
			int counterClockWiseY = (int) (Math.random() * y);
			for(int j = 0; j < 100; j++) {
				int radius = (int) (Math.random() * x * 0.15);
				int angle = (int) (Math.random() * 360);
				int clockWiseX = (int) (radius * Math.cos(Math.toRadians(angle)));
				int clockWiseY = (int) (radius * Math.sin(Math.toRadians(angle)));
				Planet planet = new Planet((counterClockWiseX + clockWiseX), (counterClockWiseY + clockWiseY));
				p.add(planet);
			}
		}
		return p;	
	}
	
	@Override
	public void mousePressed(double x, double y) {
		// TODO Auto-generated method stub
		
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
	// checks for key presses to detirmine ship movement
	public void keyPressed(int keycode) {
		// quits the execution
		if(keycode == 'Q') {
			System.exit(0);
		}
		// enables quad tree
		if(keycode == 'T') {
			enableQuadTree = !enableQuadTree; 
		}
		if(keycode == 'A'){
			changey -=2;
			changex = 0;
		}
		if(keycode == 'S') {
			changex -= 2;
			changey = 0;
		}
		if(keycode == 'D') {
			changey += 2;
			changex = 0;
		}
		if(keycode == 'W') {
			changex += 2;
			changey = 0;
		}
		
		
	}

	@Override
	public void keyReleased(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// draws everything and handles ship movement
	public void update() {
		ship.move(changey, changex);
		List<Planet> unusedPlanets;
		// setes up unused planets list depending on QT usage
		if(enableQuadTree) {
			unusedPlanets = qt.locatePlanet(ship.getViewingArea());
		}
		else {
			unusedPlanets = planets;
		}
		boolean collision = false;
		for(int i = 0; i < unusedPlanets.size(); i++) {
			if(ship.collisionCheck(unusedPlanets.get(i))) {
				collision = true;
				unusedPlanets.get(i).colitionColor(true);
			}
			else {
				unusedPlanets.get(i).colitionColor(false);
			}
		}
		// checks for collisions
		if(collision) {
			ship.collide(true);
		}
		else {
			ship.collide(false);
		}
		// draws the qt, planets, and ship
		mapCanvas.clear();
		mapCanvas.setPenColor(Color.BLACK);
		qt.draw(mapCanvas);
		for(int i =0; i < unusedPlanets.size(); i++) {
			unusedPlanets.get(i).draw(mapCanvas);
		}
		ship.draw(mapCanvas);
		ship.drawViewingArea(mapCanvas);
		mapCanvas.show();
		Rectangle shipBounds = ship.getViewingArea();
		viewCanvas.setXscale(shipBounds.x, shipBounds.x + shipBounds.width);
		viewCanvas.setYscale(shipBounds.y, shipBounds.y + shipBounds.height);
		viewCanvas.clear();
		// draws the unsused planets
		for(int i = 0; i < unusedPlanets.size(); i++) {
			unusedPlanets.get(i).draw(viewCanvas);
		}
		ship.draw(viewCanvas);
		viewCanvas.show();
	}
}
