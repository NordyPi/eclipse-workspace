package norddudrawgame;
import edu.du.dudraw.DUDraw;

public class Main {
	
	public static void main(String[] args) {
		DUDraw.enableDoubleBuffering();
		DUDraw.setCanvasSize(100, 100);
		while (true) {
			DUDraw.clear();
			Player.move();
			//use Player.getCoords to get x,y. Then draw square at that location
			
			DUDraw.show();
		}
		
	}
}
