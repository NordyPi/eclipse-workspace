package lab6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HeadThread {

	public static void main(String[] args) throws IOException {
		int c = 4;
		int min = 1000;
		int max = 1000000;
		int range = (max-min) / c;
		int primes = 0;
		int threadNum = 0;
		ArrayList<ClientHandler> ths = new ArrayList<ClientHandler>();
		ServerSocket ss = new ServerSocket(7000);
		int start;
		int stop;
		while (c > threadNum) {
			Socket s = null;
			try
			{
				s = ss.accept();
				System.out.println("A new client is connected");
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				start = min + (range * threadNum);
				if (threadNum == c - 1) {
					stop = max;
				} else {
					stop = min + range * (threadNum + 1);
				}
				ClientHandler t = new ClientHandler(s, oos, ois, threadNum, start, stop);
				t.start();
				ths.add(t);
				threadNum++;
			}
			catch (Exception e) {
				s.close();
				e.printStackTrace();
			}
		}
		for (int i = 0; i < c; i++) {
			try {
				ths.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < c; i++) {
			primes += ths.get(i).p;
		}
		
		System.out.println("Number of primes found: " + primes);

	}
	
}

class ClientHandler extends Thread {
	final Socket s;
	final ObjectInputStream ois;
	final ObjectOutputStream oos;
	final int threadNum;
	final int start;
	final int stop;
	protected int p;
	
	public ClientHandler(Socket s, ObjectOutputStream oos, ObjectInputStream ois, int threadNum, int start, int stop) {
			this.s = s;
			this.oos = oos;
			this.ois = ois;
			this.threadNum = threadNum;
			this.start = start;
			this.stop = stop;
	}
	
	public void run() {
		try {
			System.out.println("thread running: " + threadNum);
			oos.writeObject(new Integer(start));
			oos.writeObject(new Integer(stop));
			p = (int) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
