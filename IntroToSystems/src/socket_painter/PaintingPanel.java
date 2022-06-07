package socket_painter;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PaintingPanel extends JPanel {
	ArrayList<PaintingPrimitive> objects;
	
	public PaintingPanel() {
		setBackground(Color.WHITE);
		objects = new ArrayList<PaintingPrimitive>();
	}
	
	public void addPrimitive(PaintingPrimitive obj) {
		this.objects.add(obj);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (PaintingPrimitive obj: objects) {
			obj.draw(g);
		}
	}
}
