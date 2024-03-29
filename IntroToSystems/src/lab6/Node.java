package lab6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Node {
	private int nodeNum;
	private int start;
	private int stop;
	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public Node(int n, int p) {
		try {
			nodeNum = n;
			this.s = new Socket("localhost", p);
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			start = (int)ois.readObject();
			//stem.out.println("made it");
			stop = (int)ois.readObject();
			System.out.println("Node: " + nodeNum + " on port: " + p + ". From " + start + " to " + stop);
			findPrimes();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		Node n = new Node(0, 7000);
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
		int count = 0;
		for (int i = start; i < stop; i++) {
			count = count + isPrime(i);
		}
		System.out.println(count);
		try {
			oos.writeObject(new Integer(count));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
