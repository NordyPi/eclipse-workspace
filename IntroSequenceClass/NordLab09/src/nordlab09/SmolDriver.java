package nordlab09;

import edu.du.dudraw.DUDraw;

public class SmolDriver {

	public static void main(String[] args) {
		MovingCircle[] circles = {new NormalCircle(0.02), new WobbyCircle(0.05),  new SpeedyCircle(0.03),
				new NormalCircle(0.01), new WobbyCircle(0.07),  new SpeedyCircle(0.02),
				new NormalCircle(0.07), new WobbyCircle(0.01),  new SpeedyCircle(0.05)};
	
		DUDraw.enableDoubleBuffering();
		while (true) {
			DUDraw.clear();
			for (MovingCircle c : circles) {
				c.move();
			}
			DUDraw.show();
		}
	}

}
