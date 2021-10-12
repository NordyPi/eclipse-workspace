package nordlab09;

import edu.du.dudraw.DUDraw;

public class NormalCircle extends MovingCircle {

	//Default constructor
	public NormalCircle(double r) {
		super(r);
		super.setColor(DUDraw.BLUE);
	}
	
	public void move() {
		forward();
		bounce();
		draw();
	}

}
