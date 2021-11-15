package nordproject04;
import java.awt.Color;
import java.io.*;

public class BMPIO {
    public static Color[][] readBMPFile(String fileName) throws IOException {
        byte char1;
        byte char2;
        int offset;
        int header;

        Color[][] imageArray = null;

        RandomAccessFile raf = new RandomAccessFile(fileName, "r");

        // Making sure file is BMP
        char1 = raf.readByte();
        char2 = raf.readByte();
        if (char1 != 'B' || char2 != 'M') {
            raf.close();
        }

        // Reading offset and ensuring equal to 54
        raf.seek(10);
        offset = raf.readInt();
        offset = Integer.reverseBytes(offset);
        if (offset != 54) {
            raf.close();
        }

        // Reading header
        raf.seek(14);
        header = raf.readInt();
        header = Integer.reverseBytes(header);
        if (header != 40) {
            raf.close();
        }
        //reading image size
        raf.seek(18);
        int cols = raf.readInt();
        cols = Integer.reverseBytes(cols);
        if (cols % 4 != 0) {
            raf.close();
        }
        int rows = raf.readInt();
        rows = Integer.reverseBytes(rows);
        imageArray = new Color[rows][cols];

        raf.seek(28);
        short bPP = raf.readShort();
        bPP = Short.reverseBytes(bPP);
        if (bPP != 24) {
            raf.close();
        }

        // looping and reading through pixels and storing them
        raf.seek(54);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int b = raf.readUnsignedByte();
                int g = raf.readUnsignedByte();
                int r = raf.readUnsignedByte();
                imageArray[row][col] = new Color(r, g, b);
            }
        }

        raf.close();
        return imageArray;
    }


    public static void writeBMPFile(String originalFile, Color[][] imageArray) throws IOException{
        String fileName = copyBMPFile(originalFile);

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

        // Only overwriting pixel data
        raf.seek(54);
        for (int row = 0; row < imageArray.length; row++) {
            for (int col = 0; col < imageArray[0].length; col++) {
                raf.writeByte(imageArray[row][col].getBlue());
                raf.writeByte(imageArray[row][col].getGreen());
                raf.writeByte(imageArray[row][col].getRed());
            }
        }
        raf.close();
    }
    public static String copyBMPFile(String originalFile) throws IOException{
        String fileName = originalFile.substring(0, originalFile.length()-4) + "-S.bmp";
        System.out.println(fileName);

        RandomAccessFile raf = new RandomAccessFile(originalFile, "r");
        RandomAccessFile rafN = new RandomAccessFile(fileName, "rw");

        // Byte for byte copy of original file
        for (int i = 0; i < raf.length(); ++i) {
            rafN.write(raf.read());
        }
        raf.close();
        rafN.close();
        return fileName;
    }
}