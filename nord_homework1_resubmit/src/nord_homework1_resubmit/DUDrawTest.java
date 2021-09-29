package nord_homework1_resubmit;

import edu.du.dudraw.DUDraw;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DUDrawTest {

	public static void main(String[] args) {
		
		//set up variables
		int avg = 0;
		String fileName;
		boolean complete = false;
		
		//Prompts for filename in console
		Scanner line;
		line = new Scanner(System.in);
		System.out.println("Enter file name: ");
		fileName = line.nextLine();
		
		//set up file reading
		File fileForInput;
		fileForInput = new File(fileName);
		Scanner file;
		
		while(!complete) {
			try {
				
				file = new Scanner(fileForInput);
				String text = file.nextLine();
				
				//look through file for 10 ints, add them to list, and increase avg
				for(int i = 0; i < 10; i ++) {
					avg += Character.getNumericValue(text.charAt(i));
					System.out.println(Character.getNumericValue(text.charAt(i)));
					System.out.println(avg);
				}
				file.close();
				line.close();
				//return the avg/10, so avg of the number list
				
				System.out.println((double) avg / 10.0);
				complete = true;
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
				System.out.println("Bad file name, please enter again: ");
				fileName = line.nextLine();
				fileForInput = new File(fileName);
			}
		}
		
		
	}

}
