package src.exercises.gui.ring2;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class SingleRing {

	private int x;
	private int y;
	private int radius;
	private int randbreite;
	private Color color;
	
	public SingleRing(int x, int y, 
			int radius, int randbreite,
			Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.randbreite = randbreite;
		this.color = color;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(color);
		((Graphics2D) g).setStroke( new BasicStroke(randbreite) );
		g.drawOval(x, y, radius, radius);
	}
}
