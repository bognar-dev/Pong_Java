package src.exercises.gui.haus;

import java.awt.Color;

import javax.swing.JFrame;

public class Haus {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HausPanel panel = new HausPanel();
		panel.setBackground(Color.white);
		
		frame.add(panel);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
