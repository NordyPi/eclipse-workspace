package nordlab09;

import edu.du.dudraw.DUDraw;

public class SpeedyCircle extends NormalCircle {

	public SpeedyCircle(double r) {
		super(r);
		this.setColor(DUDraw.GREEN);
	}
	
	public void bounce(){
		if ((this.xPos >= 1.0 && this.xVel > 0) || (this.xPos <= 0.0 && this.xVel < 0)) {
			this.xVel = this.xVel * -1.15;
		}
		if ((this.yPos >= 1.0 && this.yVel > 0) || (this.yPos <= 0.0 && this.yVel < 0)) {
			this.yVel = this.yVel * -1.15;
		}
	}
}
