package edu.du.cs.loklinnord.assignment2;

import java.awt.Rectangle;
import java.util.List;

public class QuadTree {
	
	private class QTNode{
		private Rectangle space;
		private List<Planet> planets;
		private QTNode upperLeft;
		private QTNode upperRight;
		private QTNode lowerRight;
		private QTNode lowerLeft;
		
		public QTNode() {
			
		}
	}
	
	private QTNode root;
	
	public QuadTree(int xSize, int ySize, int maxPlanets) {
		
	}
}
