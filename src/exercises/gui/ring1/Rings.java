package src.exercises.gui.ring1;

import javax.swing.JFrame;

public class Rings {

	public static void main(String[] args) {
		JFrame myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RingPanel panel = new RingPanel(); 
		
		myframe.add( panel );
		
		myframe.setSize(400, 400);
		myframe.setVisible(true);
	}
}
