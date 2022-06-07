package socket_painter;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Hub {
	
	public static void main(String[] args) {
		ArrayList<PaintingPrimitive> prims = new ArrayList<PaintingPrimitive>();
		ArrayList<Socket> socketList = new ArrayList<Socket>();
		
		try {
			// Sets up server
			ServerSocket ss = new ServerSocket(7000);
			// thread to handle new connections
			ConnectionThread t = new ConnectionThread(ss, socketList, prims);
			t.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ConnectionThread extends Thread {
	private ArrayList<Socket> socketList;
	private ServerSocket ss;
	ArrayList<PaintingPrimitive> prims;
	ArrayList<ObjectOutputStream> outputs = new ArrayList<ObjectOutputStream>();
	
	public ConnectionThread(ServerSocket ss, ArrayList<Socket> socketList, ArrayList<PaintingPrimitive> prims) {
		this.ss = ss;
		this.socketList = socketList;
		this.prims = prims;
	}
	// Constantly waits for new connections
	public void run() {
		while(true) {
			try {
				Socket s = ss.accept();
				socketList.add(s);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				outputs.add(oos);
				oos.writeObject(prims);
				System.out.println("made new connection!");
				PainterThread t = new PainterThread(s, prims, socketList, outputs);
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

class PainterThread extends Thread {
	private Socket s;
	private ArrayList<PaintingPrimitive> prims;
	ArrayList<ObjectOutputStream> outputs;
	private ArrayList<Socket> socketList;
	
	public PainterThread(Socket s, ArrayList<PaintingPrimitive> prims, ArrayList<Socket> socketList, ArrayList<ObjectOutputStream> outputs) {
		this.s = s;
		this.prims = prims;
		this.socketList = socketList;
		this.outputs = outputs;
	}
	
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			while(true) {
				PaintingPrimitive pp = (PaintingPrimitive) ois.readObject();
				prims.add(pp);
				System.out.println(prims.size());
				System.out.println(pp.toString());
				for (ObjectOutputStream out: outputs) {
					out.writeObject(pp);
				}

			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}