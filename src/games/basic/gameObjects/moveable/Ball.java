package src.games.basic.gameObjects.moveable;

import src.games.basic.position.interfaces.Positionable;

import java.awt.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Ball extends AbstractMoveableGameObject implements Runnable {


    private int radius;

    private Color colour;

    private int strokeSize;

    public Ball(Positionable pos, Positionable deltaPos, int radius, Color colour, int strokeSize) {
        // Beachte: jetzt nicht this.pos setzen, sondern super.pos !
        super(pos, deltaPos);        // Aufruf: AbstractGameObject(pos);
        this.radius = radius;
        this.colour = colour;
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

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Ball))
            return false;

        Ball otherBall = (Ball) other;
        // Beachte: this.pos funktioniert nicht, da Attribut pos in Oberklasse private ist!
        //		    --> getPos() funktioniert, egal ob Attribut pos hier oder in Oberklasse
        //		  	 	definiert ist
        Positionable thisPos = this.getPos();
        Positionable otherPos = otherBall.getPos();

        return  // vergleiche aktuelle Position
                //		verwende equals-Methode von Positionable
                thisPos.equals(otherPos)
                        // vergleiche Breite/H�he
                        && this.radius == otherBall.radius;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(colour);
        g2.setStroke(new BasicStroke(strokeSize));
        g2.drawOval(this.getPos().getX(), this.getPos().getY(), getWidth(), getHeight());
        g2.fillOval(this.getPos().getX(), this.getPos().getY(), getWidth(), getHeight());
    }


    public void move() {
        Positionable pos = this.getPos();
        int newX = this.getPos().getX() + this.getDeltaPos().getX();
        int newY = this.getPos().getY() + this.getDeltaPos().getY();
        this.setPos(newX, newY);
    }

    @Override
    public void run() {
        while(currentThread().isAlive()) {
            try {
                this.move();
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
