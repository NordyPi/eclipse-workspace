package nordproject04;
import java.awt.Color;
import java.io.IOException;

public class Steganography {
    public static Color[][] extractSecretImage(String filename) throws IOException{
        Color[][] image = BMPIO.readBMPFile(filename);

        for (int r = 0; r < image.length; ++r) {
            for (int i = 0; i < image[0].length; ++i) {
                Integer red = (image[r][i].getRed() * 16)%256;
                Integer g = (image[r][i].getGreen() * 16)%256;
                Integer b = (image[r][i].getBlue() 	* 16)%256;
                image[r][i] = new Color(red.intValue(), g.intValue(), b.intValue());
            }
        }
        return image;
    }

    public static Color[][] embedSecretImage(String filename, String sFilename) throws IOException{
        Color[][] original = BMPIO.readBMPFile(filename);
        Color[][] embedded = BMPIO.readBMPFile(sFilename);
        Integer[] bytes = new Integer[3];
        Color[][] secretImage = new Color[original.length][original[0].length];

        for (int r = 0; r < original.length; ++r) {
            for (int c = 0; c < original[0].length; ++c) {
                bytes[0] = original[r][c].getRed() - (original[r][c].getRed()%16);
                bytes[1] = original[r][c].getGreen() - (original[r][c].getGreen()%16);
                bytes[2] = original[r][c].getBlue() - (original[r][c].getBlue()%16);
                if (r < embedded.length && c < embedded[0].length) {
                    bytes[0] += embedded[r][c].getRed() / 16;
                    bytes[1] += embedded[r][c].getGreen() / 16;
                    bytes[2] += embedded[r][c].getBlue() / 16;
                }
                secretImage[r][c] = new Color(bytes[0].intValue(), bytes[1].intValue(), bytes[2].intValue());
            }
        }
        BMPIO.writeBMPFile(filename, secretImage);
        return secretImage;
    }

}