package src.games.basic.examples.Pong;

import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;

public class Pong extends JFrame {

    public static void main(String[] args) {
        Pong p = new Pong(500,500);
    }
    PongPanel pongPanel;
    JFrame main;

    Pong(int height,int width){
        pongPanel = new PongPanel(new Position(0,40),new Position(1,0));
        main = new JFrame("Pong");
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setSize(width,height);
        main.add(pongPanel, BorderLayout.CENTER);
        main.setVisible(true);
        while(true){
            pongPanel.start();
        }
    }
}
