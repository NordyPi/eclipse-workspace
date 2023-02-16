package nordlab18;

public class RecursionDriver {

	public static void main(String[] args) {
		
		for(int i = 0; i < 7; i++)
	     {
	           if(i % 2 == 0) {
	                   System.out.println("***");
	            }
	            else{
	                   System.out.println("*");
	           }
	     }
		System.out.println("recursivebelow");
		recursiveMethod(7);
	}
	
	public static void recursiveMethod(int n){
		if(n < 0)
			   return;
		   if(n % 2 == 0){
			   System.out.println("***");
		   }
		   else{
			   System.out.println("*");
		   }
		   recursiveMethod(n-1);
	}
	
	public static int sumOfSquares(int k) {
		if (k == 1) {
			return 1;
		}
		return (k * k) + sumOfSquares(k-1);
	}
	
	public static int fib(int n) {
		if (n <= 1) {
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}

}
