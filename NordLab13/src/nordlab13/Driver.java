package nordlab13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
		//Sets up filename and RAF
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a filename please: ");
		String filename = keyboard.nextLine();
		RandomAccessFile raf = new RandomAccessFile(new File(filename), "r");
		
		//Looks through the RAF and creates the list based on length
		int numInts = raf.readInt();
		int[] list = new int[numInts];
		for (int i = 0; i < numInts; i++) {
			list[i] = raf.readInt();
		}
		
		System.out.println(Sorting.selectionSort(list));
		
	}

}
