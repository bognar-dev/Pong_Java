package src.exercises.gui.ring1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RingPanel extends JPanel {

	private static final long serialVersionUID = 2L;
	
	@Override
	public void paintComponent(Graphics g) {
		((Graphics2D) g).setStroke( new BasicStroke(3.0f) );
		
		g.setColor(Color.blue);
		g.drawOval(100, 100, 50, 50);
		
		g.setColor(Color.black);
		g.drawOval(160, 100, 50, 50);
		
		g.setColor(Color.red);
		g.drawOval(220, 100, 50, 50);
		
		g.setColor(Color.yellow);
		g.drawOval(130, 130, 50, 50);
		
		g.setColor(Color.green);
		g.drawOval(190, 130, 50, 50);

	}
}
