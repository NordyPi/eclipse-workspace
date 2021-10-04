package dudrawgame;

import java.awt.Color;
import java.util.Random;

public class Food {
	
	private Color[] COLOR_LIST = new Color[7];
	private final Color YELLOW = new Color(252, 186, 3);
	private final Color GREEN = new Color(3, 252, 36);
	private final Color RED = new Color(252, 3, 7);
	private final Color PURPLE = new Color(177, 3, 252);
	private final Color BLUE = new Color(7, 3, 252);
	private final Color ORANGE = new Color(252, 123, 3);
	private final Color PINK = new Color(252, 3, 148);
	
	COLOR_LIST[0] = YELLOW;
	COLOR_LIST[1] = GREEN;
	COLOR_LIST[2] = RED;
	COLOR_LIST[3] = PURPLE;
	COLOR_LIST[4] = BLUE;
	COLOR_LIST[5] = ORANGE;
	COLOR_LIST[6] = PINK;

	private int xPos;
	private int yPos;
	private int id;
	private Color color;
	
	public Food(int id) {
		this.id = id;
		
		Random ran = new Random();
		this.yPos = ran.nextInt(1024);
		this.xPos = ran.nextInt(1024);
		this.color = COLOR_LIST[ran.nextInt(7)];
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}

}
