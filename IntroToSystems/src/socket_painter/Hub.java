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
	// takes in info from main to work with while controlling socket flow
	public ConnectionThread(ServerSocket ss, ArrayList<Socket> socketList, ArrayList<PaintingPrimitive> prims) {
		this.ss = ss;
		this.socketList = socketList;
		this.prims = prims;
	}
	// Constantly waits for new connections
	public void run() {
		while(true) {
			try {
				// when a new connection is made, set up socket and stream
				// send the current list of drawings to the client
				Socket s = ss.accept();
				socketList.add(s);
				System.out.println("made new connection!");
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				outputs.add(oos);
				oos.writeObject(prims);
				// sets up a new thread to handle communcation with the newest connection
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
	private boolean runThread = true;
	
	public PainterThread(Socket s, ArrayList<PaintingPrimitive> prims, ArrayList<Socket> socketList, ArrayList<ObjectOutputStream> outputs) {
		this.s = s;
		this.prims = prims;
		this.socketList = socketList;
		this.outputs = outputs;
	}
	// this needs to be synchronized to avoid race conditions.
	// adds the new primitive to the list, and sends each socket the new drawing
	public synchronized void sendUpdates(Object o) {
		if (o.getClass() == Line.class || o.getClass() == Circle.class) {
			prims.add((PaintingPrimitive)o);
		}
		for (ObjectOutputStream out: outputs) {
			try {
				out.writeObject(o);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		try {
			// waits for updates from the client
			// when it recieves a drawing, call sendUpdates on the primitive
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			// receives the username and sends welcome message
			String user = (String) ois.readObject();
			sendUpdates(new String(user + " has entered the chat.\n"));
			while(runThread) {
				Object o = ois.readObject();
				sendUpdates(o);
			}
		} catch (IOException | ClassNotFoundException e) {
			// when the server detects the client has disconnected this error is called
			// stops the while loop. removes both the socket and outputstream from lists
			runThread = false;
			int index  = socketList.indexOf(s);
			System.out.println("socket disconnected, index: " + index);
			socketList.remove(index);
			outputs.remove(index);
		}
		
	}
}