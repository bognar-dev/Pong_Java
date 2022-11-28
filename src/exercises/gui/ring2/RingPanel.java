package src.exercises.gui.ring2;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class RingPanel extends JPanel {

	LinkedList<SingleRing> liste;
	
	RingPanel(LinkedList<SingleRing> liste) {
		super();
		this.liste = liste;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for (SingleRing ring : liste) {
			ring.paintComponent(g);
		}
	}
}
