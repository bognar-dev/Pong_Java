package src.games.basic.examples.Pong;

import src.games.basic.gameObjects.moveable.Ball;
import src.games.basic.gameObjects.moveable.Paddle;
import src.games.basic.position.Position;
import src.games.basic.position.interfaces.Positionable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static java.lang.System.exit;
import static java.lang.Thread.*;

public class PongPanel extends JPanel implements Runnable {

    Dimension SCREEN_SIZE = new Dimension(1000, 600);
    Ball ball;
    Thread ballThread, gameThread, paddleOneThread, paddleTwoThread;
    Paddle p1, p2;

    int height, width;
    private double scoreLimit;

    PongPanel() {
        newBall(new Position(500, 300), new Position(-10, 10));
        newPaddles();
        addHandler();
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        scoreLimit = 10;
        gameThread = new Thread(this);
        ballThread = new Thread(ball);
        paddleOneThread = new Thread(p1);
        paddleTwoThread = new Thread(p2);
        ballThread.start();
        gameThread.start();
        paddleOneThread.start();
        paddleTwoThread.start();
    }

    private void addHandler() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                //System.out.println(input);
                switch (input) {
                    case 'e' -> exit(0);
                    case 'n' -> {
                        restartBall();


                    }

                }
            }


            @Override
            public void keyPressed(KeyEvent e) {
                char input = e.getKeyChar();
                //System.out.println(input);
                switch (input) {
                    case 'w' -> {
                        //System.out.println("w pressed");
                        p1.setIsPressedUp(true);
                    }
                    case 's' -> {
                        p1.setIsPressedDown(true);
                    }
                    case 'u' -> {
                        p2.setIsPressedUp(true);
                    }
                    case 'j' -> {
                        p2.setIsPressedDown(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char input = e.getKeyChar();
                //System.out.println(input);
                switch (input) {
                    case 'w' -> {
                        //System.out.println("w released");
                        p1.setIsPressedUp(false);
                    }
                    case 's' -> {
                        p1.setIsPressedDown(false);
                    }
                    case 'u' -> {
                        p2.setIsPressedUp(false);

                    }
                    case 'j' -> {
                        p2.setIsPressedDown(false);

                    }
                }

            }
        });
    }

    private void newBall(Positionable pos, Positionable speed) {
        ball = new Ball(pos, speed, 50, Color.WHITE, 6);
    }

    private void newPaddles() {
        p1 = new Paddle(new Position(50, 30), 5, 100, Color.WHITE, 8);
        p2 = new Paddle(new Position(SCREEN_SIZE.width - 50, 30), 5, 100, Color.WHITE, 8);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setStroke(new BasicStroke(8));
        p1.paintComponent(g2);
        p2.paintComponent(g2);
        ball.paintComponent(g2);
    }


    private void ballHandle() {
        if (isTouchingTopOrBottom()) {
            this.ball.reverseYDirection();
        }
        if (ball.isRightOf(p2)) {
            p1.increaseScore();
            restartBall();
            System.out.println("Player 1: " + p1.getScore() + " Player 2: " + p2.getScore());
        }
        if (ball.isLeftOf(p1)) {
            p2.increaseScore();
            restartBall();
            System.out.println("Player 1: " + p1.getScore() + " Player 2: " + p2.getScore());
        }
        if (ball.touches(p1) || ball.touches(p2)) {
            ball.reverseXDirection();
        }
    }

    private boolean isTouchingRight() {
        return this.ball.getPos().getX() >= SCREEN_SIZE.width;
    }

    private boolean isTouchingLeft() {
        return this.ball.getPos().getX() <= 0;
    }

    private void restartBall() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ball.setPos(new Position(500, 300));
        ball.setDeltaPos(new Position(getPosivitveOrNegative(10), getPosivitveOrNegative(10)));
    }

    private int getPosivitveOrNegative(int speed) {
        Random b = new Random();
        if (b.nextBoolean()) {
            return speed;
        } else {
            return -speed;
        }
    }


    private boolean isTouchingTopOrBottom() {
        return this.ball.getPos().getY() <= 0 || this.ball.getPos().getY() + this.ball.getHeight() >= SCREEN_SIZE.height;
    }

    @Override
    public void run() {
        while (gameThread.isAlive()) {
            //System.out.println(p1.getPos());
            this.validate();
            this.repaint();
            ballHandle();
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
