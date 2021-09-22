package norddudrawgame;
import edu.du.dudraw.DUDraw;

public class Driver {
	
	public static void main(String[] args) {
		DUDraw.enableDoubleBuffering();
		DUDraw.setCanvasSize(100, 100);
		Player p = new Player();
		
		while (true) {
			DUDraw.clear();
			p.move();
			DUDraw.square(p.getCoords()[0], p.getCoords()[1], p.getSize());
			
			DUDraw.show();
		}
		
	}
}
