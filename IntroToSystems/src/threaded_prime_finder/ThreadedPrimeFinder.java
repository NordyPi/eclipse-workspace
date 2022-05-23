package threaded_prime_finder;

import java.util.ArrayList;

public class ThreadedPrimeFinder extends Thread {
	public int i;
	public int start;
	public int stop;
	public int p;
	
	public ThreadedPrimeFinder(int i, int start, int stop) {
		this.i = i;
		this.start = start;
		this.stop = stop;
		this.p = 0;
	}

	public static void main(String[] args) {
		int min = 10;
		int max = 100;
		int numThreads = 4;
		int primes = 0;
		ArrayList<ThreadedPrimeFinder> ths = new ArrayList<ThreadedPrimeFinder>();
		
		for(int i = 0; i < numThreads; i++) {
			int start = min + ((max-min) / numThreads)*i;
			int stop = min + ((max-min) / numThreads)*(i+1);
			if (numThreads == i+1) {
				stop = max;
			}
			ThreadedPrimeFinder th = new ThreadedPrimeFinder(i, start, stop);
			ths.add(th);
			th.start();
		}
		
		for (int i = 0; i < numThreads; i++) {
			try {
				ths.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < numThreads; i++) {
			primes += ths.get(i).p;
		}
		
		System.out.println("Number of primes found: " + primes);

	}
	
	public void run() {
		findPrimes();
	}
	
	public int isPrime(int x){
		int prime = 1;
		int num = 2;
		while(prime == 1 && num < x) {
			if (x % num == 0) {
				prime = 0;
			} else {
				num++;
			}
		}
		return prime;
	}
	
	public int findPrimes() {
		System.out.println("Thread " + i + ": " + start + " to " + stop);
		int count = 0;
		for (int i = start; i <= stop; i++) {
			count += isPrime(i);
		}
		p = count;
		return count;
	}

}
