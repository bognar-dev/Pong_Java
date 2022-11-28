package src.games.basic.gameObjects;
import src.games.basic.position.interfaces.Positionable;

import java.awt.*;


public class RectangularGameObject extends AbstractGameObject {

	private int width;
	private int height;

	private Color colour;

	private boolean isFilled;

	private int strokeSize;
	
	// Konstruktor
	// Beachte: Parameter Positionable 'pos' fehlte im Aufgabenblatt
	public RectangularGameObject(Positionable pos, int width, int height,Color colour,boolean isFilled, int strokeSize) {
		// Beachte: jetzt nicht this.pos setzen, sondern super.pos !
		super(pos);		// Aufruf: AbstractGameObject(pos);
		this.width  = width;
		this.height = height;
		this.colour = colour;
		this.isFilled = isFilled;
		this.strokeSize = strokeSize;
	}
	
	

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	
	public String toString() {
		// verwende toString-Methode aus Positionable
		return ("pos = " + this.getPos()
				+ ", size = " + this.getWidth() + " x " + this.getHeight()+ ")");
	}
	
	public boolean equals(Object other) {
		if (other == null || !(other instanceof RectangularGameObject))
			return false;
		
		RectangularGameObject otherRect = (RectangularGameObject)other;
		Positionable thisPos  = this.getPos();
			// Beachte: this.pos funktioniert nicht, da Attribut pos in Oberklasse private ist!
			//		    --> getPos() funktioniert, egal ob Attribut pos hier oder in Oberklasse 
			//		  	 	definiert ist
		Positionable otherPos = otherRect.getPos();
		
		return  // vergleiche aktuelle Position
				//		verwende equals-Methode von Positionable
				thisPos.equals( otherPos )
				// vergleiche Breite/H�he
				&& this.getWidth() == otherRect.getWidth()
				&& this.getHeight() == otherRect.getHeight();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(colour);
		g2.setStroke(new BasicStroke(strokeSize));
		g2.drawRect(this.getPos().getX(),this.getPos().getY(),getWidth(),getHeight());
		if(isFilled)
			g2.fillRect(this.getPos().getX(),this.getPos().getY(),getWidth(),getHeight());
	}
}
