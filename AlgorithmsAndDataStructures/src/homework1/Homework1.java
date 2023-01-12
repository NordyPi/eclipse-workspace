package homework1;

public class Homework1 {
	public static void main(String[] args) {
		int n = 10000;
		int numberOfTrials = 1000;
		int coefficientarray[] = new int[n];
		for (int i = 0; i < n; i++) {
			coefficientarray[i] = i;
		}
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < numberOfTrials; i++) {
			polynomialHornersMethod(coefficientarray, 0.5);
		}
		long endTime = System.currentTimeMillis();
		double runTime = (endTime-startTime) / (double) numberOfTrials;
		System.out.println("N Value: " + numberOfTrials + ", RunTime: " + runTime);
		}
	
	public static double exponentBruteForce(double x, int n) {
		double total = x;
		for (int i = 0; i < n; i++) {
			total = total * x;
		}
		return total;
	}
	
	public static int exponentDivideAndConquer(int x, int n) {
		int total = x;
		if (n == 1) {
			return x;
		}
		if (n == 0) {
			return 1;
		}
		int c;
		if(n % 2 == 0) {
			c = n / 2;
			for(int i = 1; i < c; i++) {
				total = total * x;
			}
			total = total * total;
		} else {
			c = (n - 1) / 2;
			for(int i = 1; i < c; i++) {
				total = total * total;
			}
			total = total * total;
			total = total* x;
		}
		return total;
	}
	
	public static double polynomialBruteForce(int[] coefficients, double x) {
		double total = 0;
		for(int i = 0; i < coefficients.length; i++) {
			total += coefficients[i] * exponentBruteForce(x, i);
		}
		return total;
	}
	
	public static double polynomialHornersMethod(int [] coefficients, double xValue) {
		double total = 0;
		for (int i = coefficients.length - 1; i >= 0; i--) {
			total = coefficients[i] + (xValue * total);
		}
		return total;
	}
	
	
}
