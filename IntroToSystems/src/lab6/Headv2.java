package lab6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Headv2 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int c = 4;
		int min = 10;
		int max = 100;
		int range = (max-min) / c;
		int primes = 0;
		
		ServerSocket ss = new ServerSocket(7000);
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
	
		for (int i = 0; i < c; i++) {
			s = ss.accept();
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			oos.writeObject(new Integer(min + range * i));
			if (i == c - 1) {
				oos.writeObject(new Integer(max));
			} else {
				oos.writeObject(new Integer(min + range * (i + 1)));
			}
			primes += (int)ois.readObject();
		}
		System.out.println("primes found: " + primes);

	}

}
