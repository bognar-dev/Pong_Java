package src.games.basic.examples.Pong;

import src.games.basic.gameObjects.moveable.Ball;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;


public class Pong extends JFrame{

    public static void main(String[] args) {
        Pong p = new Pong();
    }
    PongPanel pongPanel;

    Pong(){
        pongPanel = new PongPanel();
        this.add(pongPanel);
        this.setName("Pong");
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
