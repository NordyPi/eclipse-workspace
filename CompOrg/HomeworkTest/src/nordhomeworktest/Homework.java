package nordhomeworktest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Homework {

	public static void main(String[] args) {
		File inFile = new File("shorts2.dat");
		try {
			RandomAccessFile raf = new RandomAccessFile(inFile, "r");
			int rows;
			int cols;
			int array[][];
			int zeroRows[];
			int zeroCols = 0;
			
			cols = raf.readShort();
			rows = raf.readShort();
			array = new int[rows][cols];
			zeroRows = new int[rows];
			//System.out.println(rows);
			//System.out.println(cols);
			
			for (int r = 0; r < rows; r ++) {
				for (int c = 0; c < cols; c ++) {
					array[r][c] = raf.readShort();			}
			}
			
			for (int r = 0; r < rows; r ++) {
				int zeros = 0;
				for (int c = 0; c < cols; c ++) {
					if (array[r][c] == 0) {
						zeros ++;
					}
				}
				zeroRows[r] = zeros;
			}
			
			for (int c = 0; c < cols; c ++) {
				boolean allZeros = true;
				
				for (int r = 0; r < rows; r ++) {
					if (array[r][c] != 0) {
						allZeros = false;
					}
				}
				if (allZeros) {
					zeroCols ++;
				}
			}
			
			System.out.println(zeroCols);
			for (int i = 0; i < zeroRows.length; i ++) {
				System.out.println(zeroRows[i]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}