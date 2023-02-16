package edu.du.nord.project;

import java.awt.event.KeyEvent;

import edu.du.dudraw.DUDraw;
import edu.du.nord.project.Simulator.Mode;

public class Driver {

	public static void main(String[] args) {
		Simulator game = new Simulator(300, 300);
		DUDraw.enableDoubleBuffering();
		while (true) {
			DUDraw.clear();
			//Checks to see if to call placeElement
			if(DUDraw.isMousePressed()) {
				game.placeElement((int)DUDraw.mouseX(), (int)DUDraw.mouseY());
			}
			
			//Checks for drawMode type switch keys
			if(DUDraw.isKeyPressed(KeyEvent.VK_S)) {
				game.switchMode(KeyEvent.VK_S);
			}
			if(DUDraw.isKeyPressed(KeyEvent.VK_F)) {
				game.switchMode(KeyEvent.VK_F);
			}
			
			//calls the sand animation and drawing the objects
			game.update();
			game.draw();
			
			//This draws the text saying what mode the player is in
			if(game.getMode() == Mode.SAND) {
				DUDraw.setPenColor(DUDraw.BLUE);
				DUDraw.text(20, 290, "SAND");
			} else {
				DUDraw.setPenColor(DUDraw.BLUE);
				DUDraw.text(20, 290, "FLOOR");
			}
			
			DUDraw.show();
		}

	}

}
