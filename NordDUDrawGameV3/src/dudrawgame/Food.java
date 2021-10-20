package dudrawgame;

import java.awt.Color;
import java.util.Random;

public class Food {

	private Color YELLOW = new Color(252, 186, 3);
	private Color GREEN = new Color(3, 252, 36);
	private Color RED = new Color(252, 3, 7);
	private Color PURPLE = new Color(177, 3, 252);
	private Color BLUE = new Color(7, 3, 252);
	private Color ORANGE = new Color(252, 123, 3);
	private Color PINK = new Color(252, 3, 148);
	
	
	private Color[] COLOR_LIST = new Color[7]; {
	COLOR_LIST[0] = YELLOW;
	COLOR_LIST[1] = GREEN;
	COLOR_LIST[2] = RED;
	COLOR_LIST[3] = PURPLE;
	COLOR_LIST[4] = BLUE;
	COLOR_LIST[5] = ORANGE;
	COLOR_LIST[6] = PINK; }

	private int xPos;
	private int yPos;
	private int id;
	private Color color;
	private Player p;
	
	public Food(int id, Player p) {
		this.p = p;
		this.id = id;
		boolean notInsidePlayer = false;
		Random ran = new Random();
		
		while(!notInsidePlayer) {
			int playerX = p.getCoords()[0];
			int playerY = p.getCoords()[1];
			this.yPos = ran.nextInt(Game.getXSize());
			this.xPos = ran.nextInt(Game.getYSize());
			
			if ((getXPos() > playerX - p.getSize()) && (getXPos() < playerX + 
					p.getSize()) && (getYPos() > playerY - p.getSize()) && (getYPos() < playerY + p.getSize())) {
			} else {
				notInsidePlayer = !notInsidePlayer;
			}
		}

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

	public int getID() {
		return this.id;
	}

}
