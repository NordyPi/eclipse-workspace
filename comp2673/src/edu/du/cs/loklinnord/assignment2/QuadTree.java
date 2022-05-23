package edu.du.cs.loklinnord.assignment2;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.du.dudraw.DUDraw;
import edu.du.dudraw.Draw;

public class QuadTree {
	
	private class QTNode{
		private Rectangle space;
		private ArrayList<Planet> planets;
		private QTNode parent;
		private QTNode upperLeft;
		private QTNode upperRight;
		private QTNode lowerRight;
		private QTNode lowerLeft;
		private boolean isLeafNode;
		
		public QTNode(int xPos, int yPos, int width, int height) {
			space = new Rectangle(xPos, yPos, width, height);
			planets = new ArrayList<Planet>();
			parent = null;
			upperLeft = null;
			upperRight = null;
			lowerRight = null;
			lowerLeft = null;
			isLeafNode = true;
		}
		
		public void split() {
			isLeafNode = false;
			// set up the children with the divided rectangle
			int newHeight = space.height / 2;
			int newWidth = space.width / 2;
			upperLeft = new QTNode(space.x, space.y, newWidth, newHeight);
			upperRight = new QTNode(space.x + newWidth, space.y, newWidth, newHeight);
			lowerRight = new QTNode(space.x + newWidth, space.y - newHeight, newWidth, newHeight);
			lowerLeft = new QTNode(space.x, space.y - newHeight, newWidth, newHeight);
			upperLeft.parent = this;
			upperRight.parent = this;
			lowerRight.parent = this;
			lowerLeft.parent = this;
			// split the planet list as well
			QTNode sector;
			for (Planet p: planets) {
				sector = findSector(p);
				sector.planets.add(p);
				if (sector.planets.size() > maxPlanets) {
					sector.split();
				}
			}
		}
		
		public void draw(Draw panel) {
			// draw its own rectangle (DUDraw draws rectangle from center, ours stores at upper-left
			panel.setPenColor(DUDraw.BLACK);
			panel.rectangle(space.x + (space.width / 2), space.y - (space.height / 2), space.width / 2, space.height / 2);
			//System.out.println(this.toString());
			// call draw on all children inside try catch in case of null
			try {
				upperLeft.draw(panel);
				upperRight.draw(panel);
				lowerRight.draw(panel);
				lowerLeft.draw(panel);
			} catch (Exception e) {
				// do nothing idk
			}
		}
		
		public QTNode findSector(Planet p) {
			QTNode current = this;
			while (!this.isLeafNode) {
				if (p.between(upperLeft.bounds())) {
					current = current.upperLeft;
				} else if (p.between(upperRight.bounds())) {
					current = current.upperRight;
				} else if (p.between(lowerRight.bounds())) {
					current = current.lowerRight;
				} else {
					current = current.lowerLeft;
				}
			}
			return current;
		}
		
		public int[] bounds() {
			int[] coords = new int[4];
			coords[0] = this.space.x;
			coords[1] = this.space.x + this.space.width;
			coords[2] = this.space.y - this.space.height;
			coords[3] = this.space.y;
			return coords;
		}
		
		public String toString() {
			return "X coord: " + (space.x) + ". Y coord: " + (space.y) + ". Width: " + space.width + ". Height: " + space.height;
		}
	}
	
	private QTNode root;
	private int maxPlanets;
	
	public QuadTree(int xSize, int ySize, int maxPlanets) {
		this.maxPlanets = maxPlanets;
		root = new QTNode(0, ySize, xSize, ySize);
		root.split();
	}
	
	public void draw(Draw panel) {	
		root.draw(panel);
	}
	
	public void addPlanet(Planet p) {
		QTNode sector = root.findSector(p);
		sector.planets.add(p);
		if (sector.planets.size() > maxPlanets) {
			sector.split();
		}
	}

}
