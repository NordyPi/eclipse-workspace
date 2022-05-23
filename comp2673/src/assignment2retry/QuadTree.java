package assignment2retry;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import edu.du.dudraw.Draw;

public class QuadTree {
	// inner class QTNode
	class QTNode {
		// class instance variables
		private Rectangle space;
		private QTNode[] children;
		private List<Planet> planets;
		// constructor.. sets up empty planet list and empty children with specified bounds
		public QTNode(int xPos, int yPos, int width, int height) {
			space = new Rectangle(xPos, yPos, width, height);
			children = null;
			planets = new ArrayList<Planet>();
		}
		public void split() {
			if(space.width > 1 && space.height > 1) {
				// make a new list of 4 children for the split
				children = new QTNode[4];
				int halfWidth = space.width/2; 
				int halfHeight = space.height/2;
				// if statements check to see if dimensions are odd and adjusts accordingly.
				// each option creates 4 new children and adds them to the list
				if(space.width % 2 == 0 && space.height % 2 == 0) {
					children[0] = new QTNode(space.x, space.y, halfWidth, halfHeight);
					children[1] = new QTNode(space.x + halfWidth, space.y, halfWidth, halfHeight);
					children[2] = new QTNode(space.x, space.y + halfHeight, halfWidth, halfHeight);
					children[3] = new QTNode(space.x + halfWidth, space.y + halfHeight, halfWidth, halfHeight);
				} else if (space.width % 2 == 1 && space.height % 2 == 0) {
					children[0] = new QTNode(space.x, space.y, halfWidth, halfHeight);
					children[1] = new QTNode(space.x + halfWidth, space.y, halfWidth + 1, halfHeight);
					children[2] = new QTNode(space.x, space.y + halfHeight, halfWidth, halfHeight);
					children[3] = new QTNode(space.x + halfWidth, space.y + halfHeight, halfWidth + 1, halfHeight);
				} else if (space.width % 2 == 0 && space.height % 2 == 1) {
					children[0] = new QTNode(space.x, space.y, halfWidth, halfHeight);
					children[1] = new QTNode(space.x + halfWidth, space.y, halfWidth, halfHeight);
					children[2] = new QTNode(space.x, space.y + halfHeight, halfWidth, halfHeight + 1);
					children[3] = new QTNode(space.x + halfWidth, space.y + halfHeight, halfWidth, halfHeight + 1);
				} else {
					children[0] = new QTNode(space.x, space.y, halfWidth, halfHeight);
					children[1] = new QTNode(space.x + halfWidth, space.y, halfWidth + 1, halfHeight);
					children[2] = new QTNode(space.x, space.y + halfHeight, halfWidth, halfHeight + 1);
					children[3] = new QTNode(space.x + halfWidth, space.y + halfHeight, halfWidth + 1, halfHeight + 1);
				}
				// loops through planets and places them into the correct child
				for(int i = 0; i < planets.size(); i++) {
					int numOfChild = 0;
					while(!planets.get(i).insideArea(children[numOfChild].space)) {
						numOfChild++;
					}
					children[numOfChild].planets.add(planets.get(i));
				}
				// if a new child ends up with more than max planets, split it again
				for(int i = 0; i < 4; i++) {
					if(planets.size() == children[i].planets.size()) {
						children[i].split();
					}
				}
				// clears this nodes planet list (as it is parent now and no longer on the bottom)
				planets.clear();
			}
		}
		// looks through the tree recursively and returns list of planets
		public List<Planet> findPlanet(Rectangle r) {
			List<Planet> tempPlanets = new ArrayList<Planet>();
			if(children == null) {
				tempPlanets.addAll(planets);
			} else {
				for(int i = 0; i < 4; i++) {
					if(children[i].space.intersects(r)) {
						tempPlanets.addAll(children[i].findPlanet(r));
					}
				}
			}
			return tempPlanets;
		}
		// draws itself, and then calls draw on all of its children
		public void draw(Draw d) {
			d.rectangle(space.x + space.width/2, space.y + space.height/2, space.width/2, space.height/2);
			if(children != null) {
				for(int i = 0; i < 4; i++) {
					children[i].draw(d);
				}
			}
		}
	}
	
	private QTNode root;
	private int maxPlanets;
	// general constructor for quad tree.
	public QuadTree(int width, int height, int maxPlanets) {
		root = new QTNode(0,0,width,height);
		this.maxPlanets = maxPlanets;
	}
	// draws the quadtree by starting at root node
	public void draw(Draw d) {
		root.draw(d);
	}
	// looks through quadtree for a planet and returns it
	public List<Planet> locatePlanet(Rectangle r) {
		List<Planet> p = new ArrayList<Planet>();
		p.addAll(root.findPlanet(r));
		return p;
	}
	// adds planet to the quadtree
	public void addPlanet(Planet p) {
		QTNode node = root;
		if(p.insideArea(node.space)) {
			// goes down the quadtree until it hits a leaf node at the smallest subdivision
			while(node.children != null) {
				int numOfChild = 0;
				while(!p.insideArea(node.children[numOfChild].space)) {
					numOfChild++;
				}
				node = node.children[numOfChild];
			}
			// if the planet list is too large, split the node
			node.planets.add(p);
			if(node.planets.size() > maxPlanets) {
				node.split();
			}
		}
	}
}
