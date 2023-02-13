package homework4;

import java.awt.Point;
import java.util.ArrayList;

public class TestTimes {

	public static void main(String[] args) {
		ArrayList<Line> linesToDraw = new ArrayList<Line>();
		int arraySize = 1000000;
		int trialCount = 100;
		long totalTime = 0;
		
		for(int c = 0; c < trialCount; c++) {
			ArrayList<Point> points = new ArrayList<Point>();
			for(int i = 0; i < arraySize; i++) {
				Point temp = new Point((int)( Math.random() * 100), (int) (Math.random() * 100));
				points.add(temp);
			}
			long startTime = System.currentTimeMillis();
			DrawHull.QuickHull(points, linesToDraw);
			long endTime = System.currentTimeMillis();
			totalTime += (endTime - startTime);
		}
		System.out.println(totalTime);
		System.out.println("time: " + totalTime / (double) trialCount);
		

	}

}
