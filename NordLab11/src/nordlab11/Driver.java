package nordlab11;

import java.awt.Color;
import java.io.IOException;

import edu.du.dudraw.DUDraw;

public class Driver {

	public static void main(String[] args) {
		try {
			Color[][] pixels = BMPIO.readBMPFile("Alcatraz.bmp");
			int width = pixels.length;
			int length = pixels[0].length;
			
			for (int r = 0; r < width; r++) {
				for (int c = 0; c < length; c++) {
					
					DUDraw.filledRectangle(c, r, 1, 1);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
