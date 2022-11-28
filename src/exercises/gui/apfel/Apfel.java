/*
 * Apfelmaennchen
 *
 * Thomas Nitsche, 29.6.01
 */
package src.exercises.gui.apfel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Apfel {
	public static void main(String[] args) {
		Apfelmann a = new Apfelmann();
		a.init();
	}
}

class Point {
	// lokale Koordinaten (als komplexe Zahl interpretiert)
	private double x;
	private double y;

	// maximale Ausdehnung, identisch fuer alle Punkte
	private static double xmin, xmax, ymin, ymax;
	private static double xrange, yrange;

	static void setRange(int xrange, int yrange) {
		Point.xrange = (double) xrange;
		Point.yrange = (double) yrange;
	}

	static void setMinMax(double xmin, double xmax, 
			      double ymin, double ymax) {
		Point.xmin = xmin;
		Point.xmax = xmax;
		Point.ymin = ymin;
		Point.ymax = ymax;
	}

	static void defaultRange() {
		// Initialisierung Range
		setRange(400, 400);
		setMinMax(0, 400, 0, 400);
	}

	Point() {
		this.x = 0;
		this.y = 0;
	}

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// addiere komplexe Zahl
	Point add(Point other) {
		Point result = new Point(this.x, this.y);
		result.x = result.x + other.x;
		result.y = result.y + other.y;
		return result;
	}

	// Quadrat der komplexen Zahl
	Point sqr() {
		double real = this.x * this.x - this.y * this.y;
		double imag = 2 * this.x * this.y;
		Point result = new Point(real, imag);
		return result;
	}

	// Absolutbetrag der komplexen Zahl
	double abs() {
		double result = Math.sqrt(this.x * this.x + this.y * this.y);
		return result;
	}


	void paint(Color color, Graphics g, JPanel imagePanel) {
		// Skaliere aktuelle Koordinaten
		int xint, yint;
		xint = (int)( (x - xmin) / (xmax - xmin) * xrange);
		yint = (int)( (ymin - y) / (ymin - ymax) * yrange);

//		pad.setColor(color);
//		pad.drawDot(xint, yint);
		g.setColor(color);
		g.drawLine(xint, yint, xint, yint);
		//imagePanel.repaint();
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

class Apfelmann extends JFrame {
	
	int checkPoint(Point p) {
		int loops = 0;
		Point temp = new Point();

		do {
			// temp = (temp.sqr()).add(p);
			loops++;
			temp = temp.sqr();
			temp = temp.add(p);
		} while ((temp.abs() <= 2) && (loops < 100));

		return loops;
	}

	void paintPoint(double x, double y, Graphics g, JPanel imagePanel) {
		Point p = new Point(x, y);
		int loops = checkPoint(p);
		Color col;

		if (loops == 100)    { col = 
					new Color(0, 0, 0);
					// Color.black;
				     }
		else if (loops > 90) { col = 
					new Color(loops*2, 0, (loops-90)*25); }
		else if (loops > 80) { col = new Color(loops*2, 0, 0); }
		else if (loops > 60) { col = new Color(loops*3, 0, 0); }
		else if (loops > 20) { col = new Color(loops*4, 0, loops*2); }
		else if (loops > 10) { col = new Color(loops*5, 0, loops*10); }
		else 		     { col = new Color(0, 0, loops*20); }

		p.paint(col, g, imagePanel);
	}

	
	final int RANGE = 400;

	void init() {

//		Pad pad;

		int Ixrange, Iyrange, Ixmin, Ixmax, Iymin, Iymax;

		double xrange, yrange, xmin, xmax, ymin, ymax;
		// double xrange, yrange;
		double xstep, ystep;	

		
//		pad = new Pad();

		// initialisieren
		Ixrange = RANGE;
		Iyrange = RANGE;
		Ixmin = 0; 
		Ixmax = Ixrange;
		Iymin = 0;
		Iymax = Iyrange;

////		pad.setPadSize(Ixrange, Iyrange);
////		pad.setTitle("Apfelmaennchen");
////		pad.setVisible(true);
//		Pad.setHeight(Iyrange);
//		Pad.setWidth(Ixrange);
//		Pad.initialize("Apfelmaennchen");
//		Pad.setVisible(true);
		this.setSize(Ixrange, Iyrange);
		this.setTitle("Apfelmaennchen");
		this.setLocation(200, 100);
		setContentPane(getJContentPane());
		
		show();
		requestFocus();
		toFront();

		this.setVisible(true);

		xmin = -2.0 + (4.0 * Ixmin) / Ixrange;
		xmax = -2.0 + (4.0 * Ixmax) / Ixrange;
		ymin =  2.0 - (4.0 * Iymin) / Iyrange;
		ymax =  2.0 - (4.0 * Iymax) / Iyrange;
		xstep = (xmax - xmin) / Ixrange;
		ystep = (ymax - ymin) / Iyrange;

		Point.setRange(Ixrange, Iyrange);
		Point.setMinMax(xmin, xmax, ymin, ymax);

//		pad.clear();


		// Schleife
		//Graphics g = imagePanel.getGraphics();
		Graphics g = jContentPane.getGraphics();
		
		for (double x = xmin; x < xmax; x = x + xstep) {
			for (double y = ymin; y > ymax; y = y + ystep) {
				paintPoint(x, y,  g, imagePanel);
			}
		}
	}
	
	private JPanel jContentPane = null;
	private JPanel imagePanel = null;
	
	private JPanel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new JPanel();
			
			imagePanel.setBackground(Color.white);
			imagePanel.setForeground(Color.black);

			imagePanel.setSize(RANGE, RANGE);
		}
		return imagePanel;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
	
			jContentPane.setBackground(Color.white);
			jContentPane.setForeground(Color.black);


//			jContentPane.setLayout(new BorderLayout());
			jContentPane.setLayout(null);
//			jContentPane.setSize(RANGE, RANGE);
			jContentPane.setBounds(0, 0, RANGE, RANGE);	

			// imagePanel
//			jContentPane.add(getImagePanel(), BorderLayout.NORTH);
			jContentPane.add(getImagePanel());
			imagePanel.setBounds(0, 0, RANGE, RANGE);	
	
		}
		return jContentPane;
	}

}
