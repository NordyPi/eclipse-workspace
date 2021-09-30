package nordlab06;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProgrammingAdvice {
	
	public static void main(String[] args) {

		try {
			File file = new File("test.txt");
			Scanner fileInput = new Scanner(file);
			Scanner keyboard = new Scanner(System.in);
			
			String advice = fileInput.nextLine();
			System.out.println("My advice is: " + advice);
			
			System.out.println("Enter your advice: ");
			advice = keyboard.nextLine();
			
				
			PrintWriter output = new PrintWriter(file);
			output.println(advice);
			
			output.flush();
			output.close();
			fileInput.close();
			keyboard.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
