package src.games.basic.gameObjects;

import src.games.basic.position.Position;
import src.games.basic.position.interfaces.Positionable;

import java.awt.*;

public class CrossGameObject extends AbstractGameObject {


    int width;
    int height;
    Color color;
    boolean filled;

    int strokeSize;

    public CrossGameObject(Position pos, int width, int height, Color color, boolean filled, int strokeSize) {
        super(pos);
        this.height = height;
        this.width = width;
        this.color = color;
        this.filled = filled;
        this.strokeSize = strokeSize;
    }


    @Override
    public int getWidth() {return this.width;}

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Height: " + height + " Width: " + width +
                " Pos: " + this.getPos().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CrossGameObject))
            return false;

        CrossGameObject otherCross = (CrossGameObject) obj;
        Positionable thisPos  = this.getPos();
        // Beachte: this.pos funktioniert nicht, da Attribut pos in Oberklasse private ist!
        //		    --> getPos() funktioniert, egal ob Attribut pos hier oder in Oberklasse
        //		  	 	definiert ist
        Positionable otherPos = otherCross.getPos();

        return  // vergleiche aktuelle Position
                //		verwende equals-Methode von Positionable
                thisPos.equals( otherPos )
                        // vergleiche Breite/H�he
                        && this.getWidth() == otherCross.getWidth()
                        && this.getHeight() == otherCross.getHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(this.strokeSize));
        g2.setColor(this.color);
        g2.drawLine(this.getPos().getX(), this.getPos().getY(),this.getPos().getX()+ this.getWidth(),this.getPos().getY()+ this.getHeight());
        g2.drawLine(this.getPos().getX(),this.getPos().getY()+ this.getHeight(),this.getPos().getX() + this.getWidth(),this.getPos().getY());
    }
}

