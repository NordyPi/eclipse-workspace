package socket_painter;

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
	private ObjectInputStream ois;

	public static void main(String[] args) {
		Painter paint = new Painter();
	}
	
	public Painter() {
		try {
			socket = new Socket("localhost", 7000);
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			
		}
		
		s = Shape.LINE;
		c = Color.BLACK;
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

		// TODO: And later you will add the chat panel to the SOUTH

		// Lastly, connect the holder to the JFrame
		addWindowListener(this);
		setContentPane(content);
		

		// And make it visible to layout all the components on the screen
		setVisible(true);
		
		// makes new thread to handle server updates
		UpdateThread t = new UpdateThread(socket, painter);
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
		System.out.println("socket closed");
		try {
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
	
	public UpdateThread(Socket s, PaintingPanel painter) {
		this.s = s;
		this.painter = painter;
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		ArrayList<PaintingPrimitive> prims;
		try {
			prims = (ArrayList<PaintingPrimitive>) ois.readObject();
			for (PaintingPrimitive p: prims) {
				painter.addPrimitive(p);
			}
			painter.repaint();
			while (true) {
				PaintingPrimitive prim = (PaintingPrimitive) ois.readObject();
				painter.addPrimitive(prim);
				painter.repaint();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
