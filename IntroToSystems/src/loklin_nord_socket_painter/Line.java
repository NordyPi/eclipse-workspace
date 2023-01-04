package loklin_nord_socket_painter;

import java.awt.*;

public class Line extends PaintingPrimitive {
	private Point start;
	private Point end;
	
	public Line(Color c, Point s, Point e) {
		super(c);
		this.start = s;
		this.end = e;
	}

	@Override
	protected void drawGeometry(Graphics g) {
		g.drawLine(start.x, start.y, end.x, end.y);
	}
	public String toString() {
		return "line";
	}

}
