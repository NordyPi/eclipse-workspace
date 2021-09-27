package dudrawgame;
import edu.du.dudraw.DUDraw;

//Implements Runnable so we can go @ 60 fps
public class Game implements Runnable {
	private boolean running;
	private Thread thread;
	Player p = new Player();
	private int xSize;
	private int ySize;
	
	public Game() {
		xSize = 1024;
		ySize = 1024;
	}
	
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
		DUDraw.setCanvasSize(this.xSize, this.ySize);
		DUDraw.setXscale(0, this.xSize);
		DUDraw.setYscale(0, this.ySize);
		DUDraw.enableDoubleBuffering();
	}
	
	public void update() {
		p.move();
		DUDraw.filledSquare(p.getCoords()[0], p.getCoords()[1], p.getSize());
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
	        	//Update
	        	//Render
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
	
	public int getXSize() {
		return this.xSize;
	}
	
	public int getYSize() {
		return this.ySize;
	}
}
