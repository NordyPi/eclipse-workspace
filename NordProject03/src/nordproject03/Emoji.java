package nordproject03;

public abstract class Emoji {
	int xPos;
	int yPos;
	int rad;
	
	public Emoji(int x, int y, int r) {
		this.xPos = x;
		this.yPos = y;
		this.rad = r;
	}
	
	public abstract void draw();
	
}
