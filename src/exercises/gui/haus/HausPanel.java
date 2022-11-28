package src.exercises.gui.haus;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HausPanel extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke( new BasicStroke(3.5f) );
		
		g.setColor(Color.blue);
		g.drawRect(100, 200, 100, 100);
		g.drawLine(100, 200, 150, 150);
		g.drawLine(150, 150, 200, 200);
		g.drawLine(100, 200, 200, 300);
		g.drawLine(200, 200, 100, 300);
		
	}
}
