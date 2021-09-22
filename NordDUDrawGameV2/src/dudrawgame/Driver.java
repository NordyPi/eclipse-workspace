package dudrawgame;
import edu.du.dudraw.DUDraw;

public class Driver {
	
	public static void main(String[] args) {
		DUDraw.enableDoubleBuffering();
		DUDraw.setCanvasSize(512, 512);
		DUDraw.setXscale(0.0, 100.0);
		DUDraw.setYscale(0.0, 100.0);
		Player p = new Player();
		
		while (true) {
			//DUDraw.clear();
			//p.move();
			DUDraw.square(p.getCoords()[0], p.getCoords()[1], p.getSize());
			
			DUDraw.show();
		}
		
	}
}
