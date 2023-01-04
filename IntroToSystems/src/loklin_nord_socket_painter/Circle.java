package loklin_nord_socket_painter;

import java.awt.*;

public class Circle extends PaintingPrimitive {
	private Point start;
	private Point end;
	
	public Circle(Color c, Point s, Point e) {
		super(c);
		this.start = s;
		this.end = e;
	}

	@Override
	public void drawGeometry(Graphics g) {
		int radius = (int) Math.abs(start.distance(end));
		g.drawOval(start.x - radius, start.y - radius, radius*2, radius*2);
	}
	public String toString() {
		return "Circle";
	}
}
