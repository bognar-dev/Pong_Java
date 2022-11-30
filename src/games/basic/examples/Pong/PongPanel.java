package src.games.basic.examples.Pong;

import src.games.basic.gameObjects.moveable.Ball;
import src.games.basic.position.interfaces.Positionable;

import javax.swing.*;
import java.awt.*;

public class PongPanel extends JPanel{

    Ball ball;

    int height,width;

    PongPanel(Positionable pos,Positionable speed){
        ball = new Ball(pos,speed,10, Color.ORANGE,6);
    }

    public void start(){
        ball.move();
    }
}
