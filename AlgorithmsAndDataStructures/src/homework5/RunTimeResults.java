package homework5;

import java.awt.Point;

public class RunTimeResults {

	public static void main(String[] args) {
		// set trial count here
		int trialCount = 10;
		// loop through powers of 10 up to 10000
		// you can increase to a number greater than 5, WARNING it will take forever to run.
		for (int i = 0; i < 5; i++) {
			long totalBrute = 0;
			long totalDivide = 0;
			// run with a random point array for each trial and record times
			for (int t = 0; t < trialCount; t++) {
				Point[] p = ClosestPairProblem.GenerateRandomPoints((int)Math.pow(10, i));
				long startBrute = System.currentTimeMillis();
				ClosestPairProblem.BruteForce(p);
				long endBrute = System.currentTimeMillis();
				totalBrute += (endBrute - startBrute);
				long startDivide = System.currentTimeMillis();
				ClosestPairProblem.DivideAndConquer(p);
				long endDivide = System.currentTimeMillis();
				totalDivide += (endDivide - startDivide);
			}
			// calculate the times for each trial and print it out
			System.out.println("For PointArray size of " + (int)Math.pow(10, i) + ", BruteForce time is: " +
					totalBrute / (double) trialCount + ", and DivideAndConquer time is: " + totalDivide / (double) trialCount);
		}

	}

}
