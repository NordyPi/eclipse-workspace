package norddudrawgame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
	private int xPos;
	private int yPos;
	KeyListener listener;
	
	public Player() {
		this.xPos = 50;
		this.yPos = 50;
		listener = new MyKeyListener();
	}
	
	public void move() {
		/*
		 * if w is pressed, increase y
		 * if s is pressed, decreased y
		 * if d is pressed, increase x
		 * if a is pressed, decrease x
		 */
	}
}