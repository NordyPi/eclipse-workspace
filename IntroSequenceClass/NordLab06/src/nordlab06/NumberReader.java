package nordlab06;

import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NumberReader {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter file name: ");
		String fileName = keyboard.nextLine();
		
		try {
			RandomAccessFile file = new RandomAccessFile(fileName, "r");
			int num = file.readInt();
			int highest = 0;
			int lowest = 1000000000;
			int cur = 0;
			
			for(int i = 0; i < num; i++) {
				cur = file.readInt();
				if (cur > highest) {
					highest = cur;
				}
				if (cur < lowest) {
					lowest = cur;
				}
			}
			
			System.out.println("Highest num: " + highest + " Lowest num: " + lowest);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
