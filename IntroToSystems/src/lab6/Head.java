package lab6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Head {
	private ServerSocket ss;
	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private int start;
	private int stop;
	
	public Head(int n, int p, int start, int stop, ServerSocket ss) {
		try {
			System.out.println("Server socket " + n + " on port: " + p + ". From " + start + " to " + stop);
			this.ss = ss;
			s = ss.accept();
			oos = new ObjectOutputStream(s.getOutputStream());
			//ois = new ObjectInputStream(s.getInputStream());
			oos.writeObject(start);
			oos.writeInt(stop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws IOException {
		int c = 4;
		int min = 1000;
		int max = 1000000;
		int range = (max-min) / c;
		
		ServerSocket ss = new ServerSocket(7000);
		
		ArrayList<Head> nodes = new ArrayList<Head>();
		for (int i = 0; i < c; i++) {
			Head h = new Head(i, 7000, range * i, range * (i + 1), ss);
			nodes.add(h);
		}
	}

}
