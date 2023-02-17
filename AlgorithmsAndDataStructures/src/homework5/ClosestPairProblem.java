package homework5;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import edu.du.dudraw.Draw;

public class ClosestPairProblem {

	public static void main(String[] args) {
		// specific how many points you want in the array here.
		int arraySize = 50;
		// runs the two algorithms and displays the points with DUDraw
		SameArrayTest(arraySize);
	}
	
	private static void SameArrayTest(int size) {
		// setup canvasses for point display
		Draw canvas = new Draw();
        canvas.setXscale(-10, 110);
        canvas.setYscale(-10, 110);
        canvas.setPenRadius(3);
        canvas.text(50,3,"DivideAndConquer");
        Draw canvas2 = new Draw();
        canvas2.setXscale(-10, 110);
        canvas2.setYscale(-10, 110);
        canvas2.setPenRadius(3);
        canvas2.text(50,3,"BruteForce");
		// generate and draw points
		Point[] random = GenerateRandomPoints(size);
		for (int i = 0; i < size; i++) {
            canvas.setPenColor(Color.BLACK);
            canvas.point(random[i].x, random[i].y);
            canvas2.setPenColor(Color.BLACK);
            canvas2.point(random[i].x, random[i].y);
        }
		// runs the algorithms and returns the solution points to the terminal
		Point[] bruteForce = BruteForce(random);
		Point[] divide = DivideAndConquer(random);
		System.out.println("The closest points returned by BruteForce are (" + bruteForce[0].x + "," + bruteForce[0].y + ") and (" + bruteForce[1].x+ "," + bruteForce[1].y + ").");
		System.out.println("The closest points returned by DivideAndConquer are (" + divide[0].x + "," + divide[0].y + ") and (" + divide[1].x+ "," + divide[1].y + ").");
		// draws the solution points to the canvas
		canvas.setPenColor(Color.red);
        canvas.point(divide[0].x, divide[0].y);
        canvas.point(divide[1].x, divide[1].y);
        canvas2.setPenColor(Color.red);
        canvas2.point(bruteForce[0].x, bruteForce[0].y);
        canvas2.point(bruteForce[1].x, bruteForce[1].y);
	}


	public static Point[] BruteForce(Point[] points) {
		// set up infinity distance to compare to, and make 2 point array for storing our closest pair
		double minDistance = Double.MAX_VALUE;
		Point[] minPoints = new Point[2];
		// loop through the points, comparing each point to every other point.
		for (int i = 0; i < points.length-1; i++) {
			for (int k = i + 1; k < points.length; k++) {
				// use helper method to calculate the distance between points. if its a new best, update the array.
				double calcDistance = distance(points[i], points[k]);
				if (calcDistance < minDistance) {
					minDistance = calcDistance;
					minPoints[0] = points[i];
					minPoints[1] = points[k];
				}
			}
		}
		return minPoints;
	}

	private static double distance(Point a, Point b) {
		// helper method to compute distance between two points. uses pythagoras theorem.
		double xDist = Math.abs(b.x - a.x);
		double yDist = Math.abs(b.y - a.y);
		return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
	}
	
	//Divide and conquer method: calls sortByX and the recursiveCall to find the two closest points
    public static Point[] DivideAndConquer(Point[] points) {
        sortByX(points);
        return recursiveCall(points);
    }

    //Sorts the given array of points in order by x value (least to greatest)
    private static void sortByX(Point[] points) {

        for (int i = 0; i < points.length - 1; i++) {
            int minIndex = i;
            for (int f = i + 1; f < points.length; f++) {
                if (points[f].x < points[minIndex].x) {
                    minIndex = f;
                }
            }
            Point temp = points[i];
            points[i] = points[minIndex];
            points[minIndex] = temp;
        }
    }

    private static Point[] recursiveCall(Point[] points) {
        if (points.length <= 3) {
            return BruteForce(points);
        }

        //SubArrays
        Point[] left = subArray(points, 0, (points.length/2) - 1);
        Point[] right = subArray(points, (points.length/2), points.length - 1);

        //Recursive call on left and right
        Point[] minLeft = recursiveCall(left);
        Point[] minRight = recursiveCall(right);

        //sets minRight to minDistancePoints and will check if left is smaller below
        Point[] minDistancePoints = minRight;

        //If the left one is smaller use that instead of minRight
        if (distance(minLeft[0], minLeft[1]) < distance(minRight[0], minRight[1])) {
            minDistancePoints = minLeft;
        }

        //Calculates the midXpoint
        double midX = ((points[points.length/2].x + points[(points.length/2) - 1].x) / 2.0);

        //Uses an ArrayList for easy adding (will convert back to Array later)
        ArrayList<Point> midPointsList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (Math.abs(points[i].x - midX) < distance(minDistancePoints[0], minDistancePoints[1])) {
                midPointsList.add(points[i]);
            }
        }

        //If the arrayList has more than 1 point
        if (midPointsList.size() > 1) {
            //Convert back to an Array
            Point[] midPoints = midPointsList.toArray(new Point[midPointsList.size()]);
            //Call bruteForce to get the minMid
            Point[] minMid = BruteForce(midPoints);
            //If the minMid is smaller than minDistancePoints the return that otherwise return minDistancePoints on line 110
            if (distance(minMid[0], minMid[1]) < distance(minDistancePoints[0], minDistancePoints[1])) {
                return minMid;
            }
        }

        return minDistancePoints;
    }

    //Finds subArray given a start and end index
    private static Point[] subArray(Point[] points, int start, int end) {
        Point[] result = new Point[(end - start) + 1];
        int index = 0;
        for (int i = start;  i <= end; i++) {
            result[index] = points[i];
            index++;
        }

        return result;
    }
	
	public static Point[] GenerateRandomPoints(int size) {
		// generates array of random points of size specified, range between 0 and 100 for x and y values
		Point[] randoms = new Point[size];
		for (int i = 0; i < size; i++) {
			randoms[i] = new Point((int)( Math.random() * 100), (int)(Math.random() * 100));
		}
		return randoms;
	}

}
