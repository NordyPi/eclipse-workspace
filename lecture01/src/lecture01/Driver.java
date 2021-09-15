package lecture01;

import java.time.LocalTime;
import edu.du.dudraw.DUDraw;
import java.util.Random;

//Worked with Luke Clements to create and fix clock

public class Driver {

	public static void main(String[] args) {
		LocalTime now = LocalTime.now();
		Random random = new Random();
		double second;
		double minute;
		double hour;
		double secondx;
		double secondy;
		double minutex;
		double minutey;
		double hourx;
		double houry;

		DUDraw.enableDoubleBuffering();
		while (true) {
			// Set up angles
			now = LocalTime.now();
			second = (now.getSecond() / 60.0) * 2 * Math.PI;
			minute = (now.getMinute() / 60.0) * 2 * Math.PI;
			hour = ((now.getHour() % 12) / 12.0) * 2 * Math.PI;

			// Calculate coordinates
			secondx = 0.5 + (Math.sin(second) * 0.28);
			secondy = 0.5 + (Math.cos(second) * 0.28);
			minutex = 0.5 + (Math.sin(minute) * 0.25);
			minutey = 0.5 + (Math.cos(minute) * 0.25);
			hourx = 0.5 + (Math.sin(hour) * 0.20);
			houry = 0.5 + (Math.cos(hour) * 0.20);

			DUDraw.clear();
			// Draw clock hands
			DUDraw.setPenColor((int) random.nextInt(255), (int) random.nextInt(255), (int) random.nextInt(255));
			DUDraw.line(0.5, 0.5, secondx, secondy);
			DUDraw.line(0.5, 0.5, minutex, minutey);
			DUDraw.line(0.5, 0.5, hourx, houry);
			// Clock outline
			DUDraw.circle(0.5, 0.5, 0.3);
			DUDraw.show();
		}
	}

}
