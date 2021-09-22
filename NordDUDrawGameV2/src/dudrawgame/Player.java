package dudrawgame;
import edu.du.dudraw.DUDraw;

public class Player {
	private int xPos;
	private int yPos;
	private int size;
	private char key;
	private int[] coords;
	
	private enum dir {
		w, a, s, d 
	}
	
	public Player() {
		this.xPos = 50;
		this.yPos = 50;
		this.size = 2;
	}
	
	public void move() {
		key = DUDraw.nextKeyTyped();
		if (key == 'w') {
			yPos += 1;
		}
		else if (key == 's') {
			yPos -= 1;
		}
		
		if (key == 'd') {
			xPos += 1;
		}
		else if (key == 'a') {
			xPos -= 1;
		}
		coords[0] = xPos;
		coords[1] = yPos;
	}
	
	public int[] getCoords() {
		return coords;
	}
	
	public int getSize() {
		return size;
	}
}