package homework4;

import java.awt.Point;
import java.util.ArrayList;
import edu.du.dudraw.DUDraw;

public class DrawHull {

	public static void main(String[] args) {
		// configure DUDraw settings. did -10 to 110 to give gap around the max range of 0-100 for points.
		DUDraw.setPenColor(DUDraw.BLUE);
		DUDraw.setXscale(-10, 110);
		DUDraw.setYscale(-10, 110);
		
		// create random array size 50 and draw points to canvas
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Line> linesToDraw = new ArrayList<Line>();
		for(int i = 0; i < 50; i++) {
			Point temp = new Point((int)( Math.random() * 100), (int) (Math.random() * 100));
			points.add(temp);
			DUDraw.filledCircle(temp.x, temp.y, 1);
		}
		
		// run the algorithm
		// CHANGE COMMENT LINE HERE TO TEST
		BruteForce(points, linesToDraw);
		//QuickHull(points, linesToDraw);
		
		//Draw the solution lines
		DUDraw.setPenColor(DUDraw.RED);
		for(Line l : linesToDraw) {
			DUDraw.line(l.p1.x, l.p1.y, l.p2.x, l.p2.y);
		}
		
		DUDraw.show();
	}
	
	public static ArrayList<Point> BruteForce(ArrayList<Point> points, ArrayList<Line> lines){
		// set up solution list to return
		ArrayList<Point> solutionPoints = new ArrayList<Point>();
		// loop through comparing each point to each other point
		for(int i = 0; i < points.size() - 1; i++) {
			for(int j = i+1; j < points.size(); j++) {
				// calculates the vector that is used to compared to other points later on
				Point p1 = points.get(i);
				Point p2 = points.get(j);
				boolean isOuterEdge = true;
				int initialSign = 0;
				int yDiff = p2.y - p1.y;
				int xDiff = p1.x - p2.x;
				int vector = p1.x*p2.y - p1.y*p2.x;
				// checks every other point to the plane created between p1 and p2
				for(int k = 0; k < points.size(); k++){
					Point p3 = points.get(k);
					int pointRegion = yDiff*p3.x + xDiff*p3.y - vector;
					// compares p3 vector to the line between p1 and p2, breaks if false and stops checking
					if(initialSign != 0){
						if(pointRegion > 0 && initialSign < 0){
							isOuterEdge = false;
							break;
						} else if (pointRegion < 0 && initialSign > 0){
							isOuterEdge = false;
							break;
						} else {
							// do nothing
						}
					} else { 
						if(pointRegion > 0){
							initialSign = 1;
						} else if (pointRegion < 0){
							initialSign = -1;
						} else {
							// do nothing
						}
					}
				}
				// adds the solution points to the list with the helper method below
				// also adds the line to draw to the canvas to the list in main
				if(isOuterEdge){
					addSolution(solutionPoints, p1);
					addSolution(solutionPoints, p2);
					lines.add(new Line(p1, p2));
				}
			}
		}
		// returns the list of solution point pairs
		return solutionPoints;
	}
	
	public static ArrayList<Point> QuickHull(ArrayList<Point> points, ArrayList<Line> lines){
		// new array for solution points in the hull
		ArrayList<Point> solutionPoints = new ArrayList<Point>();
        int minPoint = -1;
        int maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        // loops through the points to find the points with the smallest and largest x values
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < minX) {
                minX = points.get(i).x;
                minPoint = i;
            }
            if (points.get(i).x > maxX) {
                maxX = points.get(i).x;
                maxPoint = i;
            }
        }
        // adds max and min to solution and removes them from the list (already processed)
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);
        solutionPoints.add(A);
        solutionPoints.add(B);
        points.remove(A);
        points.remove(B);
        // creates two new arrays for splitting the points up
        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();
        // loops through the points and splits them into the arrays depending on their location relative to min and max points
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (pointLocation(A, B, p) == -1) {
                leftSet.add(p);
            }
            else if (pointLocation(A, B, p) == 1) {
                rightSet.add(p);
            }
        }
        hullSet(A, B, rightSet, solutionPoints);
        hullSet(B, A, leftSet, solutionPoints);
 
        return solutionPoints;
    }

    public static void hullSet(Point A, Point B, ArrayList<Point> pointSet, ArrayList<Point> hullSolution) {
        int insertPosition = hullSolution.indexOf(B);
        // terminates if the list is empty
        if (pointSet.size() == 0) {
            return;
        }
        //if the set is only one point, it must be part of the external hull. adds and terminates.
        if (pointSet.size() == 1) {
            Point p = pointSet.get(0);
            pointSet.remove(p);
            hullSolution.add(insertPosition, p);
            return;
        }
        // find the new furthest points
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < pointSet.size(); i++) {
            Point p = pointSet.get(i);
            int distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }
        // add furthest point to the solution
        Point P = pointSet.get(furthestPoint);
        pointSet.remove(furthestPoint);
        hullSolution.add(insertPosition, P);
        // figure out which points are to the left of AP
        ArrayList<Point> leftOfAP = new ArrayList<Point>();
        for (int i = 0; i < pointSet.size(); i++) {
            Point M = pointSet.get(i);
            if (pointLocation(A, P, M) == 1) {
                leftOfAP.add(M);
            }
        }
        // Figure out which points are to the left of PB
        ArrayList<Point> leftOfPB = new ArrayList<Point>();
        for (int i = 0; i < pointSet.size(); i++){
            Point M = pointSet.get(i);
            if (pointLocation(P, B, M) == 1){
                leftOfPB.add(M);
            }
        }
        // recursively calls itself with the smaller point set
        hullSet(A, P, leftOfAP, hullSolution);
        hullSet(P, B, leftOfPB, hullSolution);
 
    }
    
    public static int distance(Point A, Point B, Point C)
    {
    	// helper method to find how far point C is from vector AB
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        // quick check to always return positive value for distance
        if (num < 0) {
            num = -num;
        }
        return num;
    }
    public static int pointLocation(Point A, Point B, Point P)
    {
    	// helper method to determine which side of vector AB point P is on
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0) {
            return 1;
        } else if (cp1 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
	
	public static void addSolution(ArrayList<Point> solutionList, Point p) {
		// adds points to the solution list if they aren't already on the list
		for (Point point : solutionList) {
			if(point.equals(p)) {
				return;
			}
			solutionList.add(p);
		}
	}

}
