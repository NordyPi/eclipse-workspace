package nordlab16;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

public class WanderGame implements DrawListener {
	private int XSIZE = 600;
	private int YSIZE = 600;
	
	Draw canvas = new Draw();
	
	private double xPos;
	private double yPos;
	private double size;
	private Color color;
	private int speed;
	private ArrayList<Food> foods = new ArrayList<Food>();
	
	public WanderGame() {
		this.xPos = XSIZE / 2;
		this.yPos = YSIZE / 2;
		this.color = canvas.BLACK;
		this.speed = 9;
		this.size = 4;
		for (int i = 0; i < 50; i ++) {
			foods.add(new Food());
		}
		
		canvas.addListener(this);
		canvas.setCanvasSize(XSIZE, YSIZE);
		canvas.setXscale(0, XSIZE);
		canvas.setYscale(0, YSIZE);
		draw();
	}
	
	class Food {
		private double xPo;
		private double yPo;
		
		public Food() {
			this.xPo = Math.floor(Math.random()*(XSIZE));
			this.yPo = Math.floor(Math.random()*(YSIZE));
		}
		
	}
	
	public void draw() {
		System.out.println(this.color.getBlue());
		canvas.setPenColor(this.color);
		canvas.filledCircle(this.xPos, this.yPos, size);
		canvas.setPenColor(canvas.RED);
		for (Food f : foods) {
			canvas.filledCircle(f.xPo, f.yPo, 1);
		}
	}
	
	@Override
	public void keyPressed(int arg0) {
		
	}

	@Override
	public void keyReleased(int arg0) {
		
	}

	@Override
	public void keyTyped(char k) {
		if (k == 'w') {
			this.yPos += speed;
		}
		if (k == 's') {
			this.yPos -= speed;
		}
		if (k == 'a') {
			this.xPos -= speed;
		}
		if (k == 'd') {
			this.xPos += speed;
		}
		for (Food f : foods) {
			
		}
		for (int i = 0; i < foods.size(); i ++) {
			canvas.clear();
			if ((foods.get(i).xPo > xPos - size) && (foods.get(i).xPo < xPos + 
					size) && (foods.get(i).yPo > yPos - size) && (foods.get(i).yPo < yPos + size)) {
				size += 3;
				canvas.setPenColor(canvas.WHITE);
				canvas.filledCircle(foods.get(i).xPo, foods.get(i).yPo, 1);
				foods.remove(i);
			}
		}
		draw();
	}

	@Override
	public void mouseClicked(double x, double y) {
		canvas.clear();
		if ((x > xPos - size) && (x < xPos + 
				size) && (y > yPos - size) && (y < yPos + size)) {
			this.xPos = Math.floor(Math.random()*(XSIZE));
			this.yPos = Math.floor(Math.random()*(YSIZE));
			System.out.print("teleported");
		} else {
			System.out.print("color");
			this.color = new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
			System.out.println(this.color.getBlue());
		}
		draw();
		
	}

	@Override
	public void mouseDragged(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}