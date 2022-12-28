package src.games.basic.examples.Pong;

import src.games.basic.gameObjects.moveable.Ball;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;


public class Pong extends JFrame implements Runnable {

    public static void main(String[] args) {
        Pong p = new Pong(10, 30, 30, 10, Color.BLACK, Color.BLACK, Color.GRAY, Color.WHITE, false,false);
    }

    PongPanel pongPanel;
    Thread pongThread;
    boolean isFinished;

    Pong(int ballSize, int ballSpeed, int paddleSize, int gameLimit, Color p1Colour, Color p2Colour, Color ballColour, Color backgroundColour, boolean sound, boolean singlePlayer) {
        if (singlePlayer)
            pongPanel = new PongPanelSinglePlayer(ballSize, ballSpeed, paddleSize, gameLimit, p1Colour, p2Colour, ballColour, backgroundColour, sound);
        else
            pongPanel = new PongPanel(ballSize, ballSpeed, paddleSize, gameLimit, p1Colour, p2Colour, ballColour, backgroundColour, sound);
        this.isFinished = false;
        this.add(pongPanel);
        this.setName("Pong");
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        pongThread = new Thread(this);
        pongThread.start();
    }

    @Override
    public void run() {
        while (!pongThread.isInterrupted()) {
            if (pongPanel.isFinished()) {
                pongThread.interrupt();
                this.setVisible(false);
                isFinished = true;
            }
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
