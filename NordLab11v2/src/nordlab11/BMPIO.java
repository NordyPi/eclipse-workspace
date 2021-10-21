package nordlab11;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BMPIO {

	public static Color[][] readBMPFile(String filename) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(filename, "rw");
		Color pixels[][] = null;
		if (raf.readByte() == 66) {
			System.out.println("check 1");
			if (raf.readByte() == 77) {
				System.out.println("check 2");
				raf.seek(10);
				if (Integer.reverseBytes(raf.readInt()) == 54) {
					System.out.println("check 3");
					if (Integer.reverseBytes(raf.readInt()) == 40) {
						System.out.println("check 4");
						int width = Integer.reverseBytes(raf.readInt());
						if (width % 4 == 0) {
							System.out.println("check 5");
							int height = Integer.reverseBytes(raf.readInt());
							raf.seek(28);
							if (Short.reverseBytes(raf.readShort()) == 24) {
								System.out.println("check 6");
								raf.seek(54);
								pixels = new Color[height][width];
								for (int r = 0; r < height; r++) {
									for (int c = 0; c < width; c++) {
										int red = raf.readUnsignedByte();
										int green = raf.readUnsignedByte();
										int blue = raf.readUnsignedByte();
										pixels[r][c] = new Color(red, green, blue);
									}
								}
							}
						}
					}
				}
			}
		}
		return pixels;
	}
	
	

}
