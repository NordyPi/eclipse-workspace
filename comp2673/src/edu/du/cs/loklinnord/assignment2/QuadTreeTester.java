package edu.du.cs.loklinnord.assignment2;

import java.util.ArrayList;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

public class QuadTreeTester implements DrawListener {
	public int CANVAS_WIDTH = 500;
	public int CANVAS_HEIGHT = 500;
	public QuadTree qt;
	public ArrayList<Planet> planets;
	public Draw canvas;
	
	public QuadTreeTester() {
		qt = new QuadTree(CANVAS_WIDTH*2, CANVAS_HEIGHT*2, 8);
		planets = new ArrayList<Planet>();
		canvas = new Draw();
		
		canvas.enableDoubleBuffering();
		canvas.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setYscale(0, CANVAS_HEIGHT * 2);
		canvas.setXscale(0, CANVAS_WIDTH * 2);
		canvas.rectangle(CANVAS_HEIGHT, CANVAS_HEIGHT, CANVAS_WIDTH, CANVAS_HEIGHT);
		qt.addPlanet(new Planet(70, 80, 10));
		planets.add(new Planet(70, 80, 10));
		update();
	}
	
	public static void main(String[] args) {
		QuadTreeTester test = new QuadTreeTester();
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
	public void keyTyped(char arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(double arg0, double arg1) {	
		System.out.println("Canvas clicked!");
		Planet p = new Planet((int) canvas.mouseX(), (int) canvas.mouseY(), 10);
		planets.add(p);
		qt.addPlanet(p);
		update();
		
	}

	@Override
	public void mouseReleased(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		canvas.clear();
		for (Planet p: planets) {
			p.draw(canvas);
		}
		qt.draw(canvas);
		canvas.show();
		
	}
}