package nordproject05;

import edu.du.dudraw.Draw;

public class WindowDriver {

	public static void main(String[] args) {
		//Sets up everything here
		WindowDisplay yeet = new WindowDisplay(300, 300);
		//Adds windows to our canvas
		yeet.addWindow(150, 150, 25, 100, Draw.BLUE);
		yeet.addWindow(100, 130, 80, 80, Draw.RED);
		yeet.addWindow(60, 80, 60, 80, Draw.GREEN);
		yeet.addWindow(120, 60, 100, 80, Draw.BLACK);
		yeet.draw();
	}

}
