package loklin_nord_socket_painter;

import java.awt.*;
import java.io.Serializable;

public abstract class PaintingPrimitive implements Serializable {
	private Color color;
	
	public PaintingPrimitive(Color c) {
		this.color = c;
	}
	
	public final void draw(Graphics g) {
	    g.setColor(this.color);
	    drawGeometry(g);
	}

	protected abstract void drawGeometry(Graphics g);

}
