package src.games.basic.gameObjects.moveable;

import src.games.basic.position.Position;
import src.games.basic.position.interfaces.Positionable;

import java.awt.*;

import static java.lang.Math.sin;

public class MoveableTriangle extends AbstractMoveableGameObject{

    Color colour;

    Position pointA,pointB,pointC;


    public MoveableTriangle(Position pointA,Position pointB,Position pointC,Positionable pos, Positionable deltaPos,Color colour) {
        super(pos, deltaPos);
        this.colour = colour;
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(colour);
        g2.drawLine(pointA.getX(),pointA.getY(),pointB.getX(),pointB.getY());
        g2.drawLine(pointB.getX(),pointB.getY(),pointC.getX(),pointC.getY());
        g2.drawLine(pointC.getX(),pointC.getY(),pointA.getX(),pointA.getY());
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
