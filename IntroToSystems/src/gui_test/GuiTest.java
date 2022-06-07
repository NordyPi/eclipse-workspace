package gui_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GuiTest extends JFrame implements ActionListener {
	
	public GuiTest()  {
		
		setSize(500, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		//p.setBackground(Color.RED);
		p.setLayout(new BorderLayout());
		
		JButton b1 = new JButton("Click Here");
		p.add(b1, BorderLayout.NORTH);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PPPPPPPP!!!!!");
				
			}
		});
		
		
		JButton b2 = new JButton("No! - Click Here");
		p.add(b2, BorderLayout.WEST);
		
		MyPanel mp = new MyPanel();
		mp.setBackground(Color.GREEN);
		p.add(mp, BorderLayout.CENTER);
		
		
		setContentPane(p);
		setVisible(true);
		
		mp.repaint();
		
	}

	public static void main(String[] args) {
		new GuiTest();
		System.out.println("here");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("P!!!!!");
		
	}

}
