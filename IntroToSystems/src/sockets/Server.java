package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(7000);
			
			System.out.println("Waiting for connection...");
			Socket s = ss.accept();
			System.out.println("Connected");
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			int o = (int)ois.readObject();
			int o2 = o * 2;
			
			oos.writeObject(o2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
