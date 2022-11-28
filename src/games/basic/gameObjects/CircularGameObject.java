package src.games.basic.gameObjects;
import src.games.basic.position.interfaces.Positionable;

import java.awt.*;


public class CircularGameObject extends AbstractGameObject {

	private int radius;

	private Color colour;

	private boolean isFilled;

	private int strokeSize;
	
	// Konstruktor
	// Beachte: Parameter Positionable 'pos' fehlte im Aufgabenblatt
	public CircularGameObject(Positionable pos, int radius, Color colour, boolean isFilled, int strokeSize) {
		// Beachte: jetzt nicht this.pos setzen, sondern super.pos !
		super(pos);		// Aufruf: AbstractGameObject(pos);
		this.radius = radius;
		this.colour = colour;
		this.isFilled = isFilled;
		this.strokeSize = strokeSize;
	}
	
	
	@Override
	public int getWidth() {
		return this.radius;
	}

	@Override
	public int getHeight() {
		return this.radius;
	}

	
	public String toString() {
		// verwende toString-Methode aus Positionable
		return ("pos = " + this.getPos()
				+ ", radius = " + this.radius + ")");
	}
	
	public boolean equals(Object other) {
		if (other == null || !(other instanceof CircularGameObject))
			return false;
		
		CircularGameObject otherCirc = (CircularGameObject)other;
			// Beachte: this.pos funktioniert nicht, da Attribut pos in Oberklasse private ist!
			//		    --> getPos() funktioniert, egal ob Attribut pos hier oder in Oberklasse 
			//		  	 	definiert ist
		Positionable thisPos  = this.getPos();
		Positionable otherPos = otherCirc.getPos();
		
		return  // vergleiche aktuelle Position
				//		verwende equals-Methode von Positionable
				thisPos.equals( otherPos )
				// vergleiche Breite/H�he
				&& this.radius == otherCirc.radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(colour);
		g2.setStroke(new BasicStroke(strokeSize));
		g2.drawOval(this.getPos().getX(),this.getPos().getY(),getWidth(),getHeight());
		if(isFilled)
			g2.fillOval(this.getPos().getX(),this.getPos().getY(),getWidth(),getHeight());
	}
}
