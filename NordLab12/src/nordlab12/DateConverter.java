package nordlab12;

import java.util.Scanner;

public class DateConverter {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		boolean finished = false;
		
		while(!finished) {
			System.out.println("Enter an integer representing the month: ");
			int month = keyboard.nextInt();		
			System.out.println("Enter an integer representing the day: ");
			int day = keyboard.nextInt();
			
			try {
				if (validate(month, day)) {
					System.out.println(convert(month, day));
				}
			} catch (Exception e) {
				System.out.println("Cannot convert this date");
			}

			System.out.println("Do you want to convert another date? Enter Y or N: ");
			String response = keyboard.next();
			System.out.println(response);
			if (response.equals("N")) {
				finished = true;
			}
		}

	}
	
	public static String getMonth(int i) {
		String[] months = {"Janurary", "February", "March", 
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December"
		};
		return months[i];
	}
	
	public static String convert(int m, int d) {
		
		return(getMonth(m) + " " + d);
	}
	
	public static boolean validate (int m, int d) throws Exception {
		if (d > 31 || d < 1) {
			throw new DayException();
		} else if (m > 12 || m < 1){
			throw new MonthException();
		} else if (m == 2 && d > 28) {
			throw new DayException();
		} else if ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30){
			throw new DayException();
		} else {
			return true;
		}
	}

}
