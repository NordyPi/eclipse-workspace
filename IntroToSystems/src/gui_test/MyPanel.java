package gui_test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.drawLine(10, 20, 100, 300);
		
		System.out.println("PC called");
		
	}

}
