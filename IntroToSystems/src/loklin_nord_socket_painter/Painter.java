package loklin_nord_socket_painter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

public class Painter extends JFrame implements ActionListener, MouseListener, WindowListener {
	private enum Shape {LINE, CIRCLE};
	private Shape s;
	private Color c;
	private PaintingPanel painter;
	private Point temp;
	private Socket socket;
	private ObjectOutputStream oos;
	private String runThread;
	private String userName;
	private String chatHist;
	private JTextArea chatHistory;
	private JTextArea messageText;

	public static void main(String[] args) {
		Painter paint = new Painter();
	}
	
	public Painter() {
		// sets up the socket
		try {
			socket = new Socket("localhost", 7000);
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			
		}
		// set default variables and window behavior
		chatHist = "";
		s = Shape.LINE;
		c = Color.BLACK;
		runThread = "y";
		setSize(500, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		// Create the paints
		JPanel colors = new JPanel();
		colors.setLayout(new GridLayout(3, 1)); // 3 by 1
		// add red paint button
		JButton redPaint = new JButton();
		redPaint.addActionListener(this);
		redPaint.setActionCommand("red");
		redPaint.setBackground(Color.RED);
		redPaint.setOpaque(true);
		redPaint.setBorderPainted(false);
		colors.add(redPaint);  // Added in next open cell in the grid
		// add blue paint here
		JButton bluePaint = new JButton();
		bluePaint.addActionListener(this);
		bluePaint.setActionCommand("blue");
		bluePaint.setBackground(Color.BLUE);
		bluePaint.setOpaque(true);
		bluePaint.setBorderPainted(false);
		colors.add(bluePaint);
		// add green paint here
		JButton greenPaint = new JButton();
		greenPaint.addActionListener(this);
		greenPaint.setActionCommand("green");
		greenPaint.setBackground(Color.GREEN);
		greenPaint.setOpaque(true);
		greenPaint.setBorderPainted(false);
		colors.add(greenPaint);
		// add the panels to the overall panel, holder
		// note that holder's layout should be set to BorderLayout
		content.add(colors, BorderLayout.WEST);

		// create the shapes
		JPanel shapes = new JPanel();
		shapes.setLayout(new GridLayout(1, 2));
		// add line button
		JButton lineTool = new JButton("Line");
		lineTool.addActionListener(this);
		lineTool.setActionCommand("line");
		shapes.add(lineTool);
		// add circle button
		JButton circleTool = new JButton("Circle");
		circleTool.addActionListener(this);
		circleTool.setActionCommand("circle");
		shapes.add(circleTool);
		content.add(shapes, BorderLayout.NORTH);
		
		// setup the painter panel
		painter = new PaintingPanel();
		painter.addMouseListener(this);
		content.add(painter, BorderLayout.CENTER);

		// Creates all of the chat section stuff
		// TODO:: scrollbar!
		JPanel chat = new JPanel();
		JPanel messagePanel = new JPanel();
		JPanel historyPanel = new JPanel();
		historyPanel.setLayout(new BorderLayout());
		messagePanel.setLayout(new GridLayout(1, 2));
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		messageText = new JTextArea("Enter message here");
		messageText.setBackground(Color.LIGHT_GRAY);
		chatHistory = new JTextArea();
		chatHistory.setBackground(Color.GRAY);
		chatHistory.setEditable(false);
		chatHistory.setPreferredSize(new Dimension(100, 100));
		JScrollPane scroll = new JScrollPane(chatHistory);
		JButton sendButton = new JButton("Send Message");
		historyPanel.add(scroll);
		sendButton.addActionListener(this);
		sendButton.setActionCommand("message");
		messagePanel.add(messageText);
		messagePanel.add(sendButton);
		historyPanel.add(chatHistory);
		chat.add(messagePanel);
		chat.add(historyPanel);
		content.add(chat, BorderLayout.SOUTH);
		
		// Lastly, connect the holder to the JFrame
		addWindowListener(this);
		setContentPane(content);
		
		// And make it visible to layout all the components on the screen
		setVisible(true);
		
		// asks for name and sets window name
		userName = JOptionPane.showInputDialog("Enter your username");
		setTitle(userName + "'s SocketPainter");
		
		// makes new thread to handle server updates
		UpdateThread t = new UpdateThread(socket, painter, runThread, oos, userName, chatHist, chatHistory);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String in = e.getActionCommand();
		if (in.equals("red")) {
			c = Color.RED;
		} else if (in.equals("blue")) {
			c = Color.BLUE;
		} else if (in.equals("green")) {
			c = Color.GREEN;
		} else if (in.equals("circle")) {
			s = Shape.CIRCLE;
		} else if (in.equals("line")) {
			s = Shape.LINE;
		} else if (in.equals("message")) {
			try {
				oos.writeObject(userName + ": " + messageText.getText() + "\n");
				messageText.setText("");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		temp = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		PaintingPrimitive p;
		if (s == Shape.LINE) {
			p = new Line(c, temp, new Point(e.getX(), e.getY()));
			// sends line to server
			try {
				oos.writeObject(p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (s == Shape.CIRCLE) {
			p = new Circle(c, temp, new Point(e.getX(), e.getY()));
			// sends circle to server
			try {
				oos.writeObject(p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		painter.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// handles closing the socket when the window closes
		System.out.println("closing window");
		runThread = "n";
		try {
			oos.writeObject(new String(userName + " has left the chat."));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("socket closed");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class UpdateThread extends Thread {
	private Socket s;
	private ObjectInputStream ois;
	private PaintingPanel painter;
	private String runThread;
	private ObjectOutputStream oos;
	private String userName;
	private JTextArea chatBox;
	private String chatHist;
	
	// recieves info from the main method to control socket input and output
	public UpdateThread(Socket s, PaintingPanel painter, String run, ObjectOutputStream oos, String userName, String chatHist, JTextArea chatBox) {
		this.s = s;
		this.painter = painter;
		this.runThread = run;
		this.oos = oos;
		this.userName = userName;
		this.chatBox = chatBox;
		this.chatHist = chatHist;
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		ArrayList<PaintingPrimitive> prims;
		try {
			// reads in the list of prims from the server, adds to its own list, and paints them
			prims = (ArrayList<PaintingPrimitive>) ois.readObject();
			System.out.println(prims.size());
			for (PaintingPrimitive p: prims) {
				painter.addPrimitive(p);
			}
			painter.repaint();
			oos.writeObject(new String(userName));
			// continually waits for new drawings from the server, adds them to list, and paints
			// stops when the window is closed, changing string value
			while (runThread.equals(new String("y"))) {
				Object o = ois.readObject();
				if (o.getClass() == Line.class || o.getClass() == Circle.class) {
					painter.addPrimitive((PaintingPrimitive) o);
				} else {
					chatHist = chatHist + o.toString();
					chatBox.setText(chatHist);
				}
				painter.repaint();
			}
			s.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
