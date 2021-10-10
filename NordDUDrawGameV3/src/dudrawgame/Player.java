package dudrawgame;

import java.awt.event.KeyEvent;
import edu.du.dudraw.DUDraw;

public class Player {
	private int xPos;
	private int yPos;
	private int size;
	private int[] coords;
	private int speed;
	
	public Player() {
		this.xPos = 512;
		this.yPos = 512;
		this.size = 23;
		this.speed = 7;
		coords = new int[2];
		coords[0] = xPos;
		coords[1] = yPos;
	}
	
	public void move() {
		//Using isKeyPressed to check if key is being pressed.
		//checks for the edge of screen as to not go past
		//and if speed goes up, don't go past!
		if (DUDraw.isKeyPressed(KeyEvent.VK_D) && xPos < 1024 - (this.size)) {
				xPos += speed;
				if (xPos > 1024) {
					xPos = 1024 - this.size;
				}
		}
		if (DUDraw.isKeyPressed(KeyEvent.VK_A) && xPos > 0 + (this.size)) {
				xPos -= speed;
				if (xPos < 0) {
					xPos = this.size;
				}
		}
			
		if (DUDraw.isKeyPressed(KeyEvent.VK_W) && yPos < 1024 - (this.size)) {
				yPos += speed;
				if (yPos > 1024) {
					yPos = 1024 - this.size;
				}
		}
		if (DUDraw.isKeyPressed(KeyEvent.VK_S) && yPos > 0 + (this.size)) {
				yPos -= speed;
				if (yPos < 0) {
					yPos = this.size;
				}
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