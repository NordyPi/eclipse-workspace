package dudrawgame;
import edu.du.dudraw.DUDraw;

//Implements Runnable so we can go @ 60 fps
public class Game implements Runnable {
	private boolean running;
	private Thread thread;
	Player p = new Player();
	int playerX;
	int playerY;
	
	private static int xSize = 1024;
	private static int ySize = 1024;
	
	private Food[] foodList = new Food[100];{
	for (int i = 0; i < foodList.length; i ++) {
		foodList[i] = new Food(i, p);
	}}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	public void configureCanvas() {
		DUDraw.setCanvasSize(xSize, ySize);
		DUDraw.setXscale(0, xSize);
		DUDraw.setYscale(0, ySize);
		DUDraw.enableDoubleBuffering();
	}
	
	public void update() {
		p.move();
		playerX = p.getCoords()[0];
		playerY = p.getCoords()[1];
		
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.filledSquare(p.getCoords()[0], p.getCoords()[1], p.getSize());
		
		eatFood();
		
		for (int i = 0; i < foodList.length; i ++) {
			DUDraw.setPenColor(foodList[i].getColor());
			DUDraw.filledCircle(foodList[i].getXPos(), foodList[i].getYPos(), 2);
		}
		DUDraw.setPenColor(DUDraw.BLACK);
		DUDraw.text(40, ySize - 20, "Size: " + p.getSize());
		DUDraw.text(40, ySize - 40, "Health: ");
	}
	
	public void eatFood() {
		for (int i = 0; i < foodList.length; i ++) {
			Food f = foodList[i];
			if ((f.getXPos() > playerX - p.getSize()) && (f.getXPos() < playerX + 
					p.getSize()) && (f.getYPos() > playerY - p.getSize()) && (f.getYPos() < playerY + p.getSize())) {
				p.increaseSize();
				foodList[i] = new Food(f.getID(), p);
			}
		}
	}
	
	@Override
	public void run() {
		//This loop will run only once per second, using Runnable and system time.
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
	    double delta = 0;
	    while(running){
	        long now = System.nanoTime();
	        delta += (now - lastTime) / ns;
	        lastTime = now;
	        while(delta >= 1){
	        	DUDraw.clear();
				update();
				DUDraw.show();
				//Resets delta for the next loop
	        	delta--;
	            }
	        }
	   }
	
	public static void main(String[] args) {
		Game game = new Game();
		
		game.configureCanvas();
		game.start();
	}
	
	public static int getXSize() {
		return xSize;
	}	
	public static int getYSize() {
		return ySize;
	}
}
