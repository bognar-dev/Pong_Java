package src.games.basic.examples.Pong;

import src.games.basic.gameObjects.ScoreGameObject;
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
    ScoreGameObject p1Score, p2Score;
    Thread ballThread, gameThread, paddleOneThread, paddleTwoThread, p1ScoreThread, p2ScoreThread;
    Paddle p1, p2;

    int ballSpeed;
    private boolean isFinished;

    PongPanel(int ballSize, int ballSpeed, int paddleSize,int gameLimit) {
        this.ballSpeed = ballSpeed;
        newBall(new Position(500, 300), new Position(getPosivitveOrNegative(ballSpeed), 10), ballSize);
        p1Score = new ScoreGameObject(new Position(250, 80), Color.WHITE, gameLimit, 60);
        p2Score = new ScoreGameObject(new Position(725, 80), Color.WHITE, gameLimit, 60);
        newPaddles(paddleSize);
        addHandler();
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
    }

    private void startThreads() {
        gameThread = new Thread(this);
        ballThread = new Thread(ball);
        paddleOneThread = new Thread(p1);
        paddleTwoThread = new Thread(p2);
        p1ScoreThread = new Thread(p1Score);
        p2ScoreThread = new Thread(p2Score);
        ballThread.start();
        gameThread.start();
        paddleOneThread.start();
        paddleTwoThread.start();
        p1ScoreThread.start();
        p2ScoreThread.start();
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
                        p1Score.resetScore();
                        p2Score.resetScore();
                    }
                    case ' ' -> startThreads();

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

    private void newBall(Positionable pos, Positionable speed, int ballSize) {
        ball = new Ball(pos, speed, ballSize, Color.WHITE, 1);
    }

    private void newPaddles(int paddleSize) {
        p1 = new Paddle(new Position(50, 30), 1, paddleSize, Color.WHITE, 10);
        p2 = new Paddle(new Position(SCREEN_SIZE.width - 50, 30), 1, paddleSize, Color.WHITE, 10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(500, 0, 500, SCREEN_SIZE.height);
        ball.paintComponent(g2);
        p1.paintComponent(g2);
        p2.paintComponent(g2);
        p1Score.paintComponent(g2);
        p2Score.paintComponent(g2);
        Toolkit.getDefaultToolkit().sync();
    }


    private void ballHandle() {
        if (isTouchingTop()) {
            this.ball.setPos(ball.getPos().getX(), 1);
            this.ball.reverseYDirection();
        }
        if (isTouchingBottom()) {
            this.ball.setPos(ball.getPos().getX(), SCREEN_SIZE.height - this.ball.getHeight());
            this.ball.reverseYDirection();
        }
        if (ball.isRightOf(p2)) {
            p1Score.increaseScore();
            restartBall();
            System.out.println("Player 1: " + p1Score.getScore() + " Player 2: " + p2Score.getScore());
        }
        if (ball.isLeftOf(p1)) {
            p2Score.increaseScore();
            restartBall();
            System.out.println("Player 1: " + p1Score.getScore() + " Player 2: " + p2Score.getScore());
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
        ball.setDeltaPos(new Position(getPosivitveOrNegative(ballSpeed), getPosivitveOrNegative(new Random().nextInt(10, 20))));
    }

    private int getPosivitveOrNegative(int speed) {
        Random b = new Random();
        if (b.nextBoolean()) {
            return speed;
        } else {
            return -speed;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }


    private boolean isTouchingTop() {
        return this.ball.getPos().getY() < 0;
    }

    private boolean isTouchingBottom() {
        return this.ball.getPos().getY() + this.ball.getHeight() > SCREEN_SIZE.height;
    }

    @Override
    public void run() {
        while (!gameThread.isInterrupted()) {
            //System.out.println(p1.getPos());
            this.validate();
            this.repaint();
            ballHandle();
            if (p2Score.reachedLimit()) {
                new PopupMenu("Player 2 wins").setEnabled(true);
                System.out.println("Player 2 Wins");
                interruptAllThreads();
                isFinished = true;
            }
            if (p1Score.reachedLimit()) {
                new PopupMenu("Player 1 wins").setEnabled(true);
                System.out.println("Player 1 Wins");
                interruptAllThreads();
                isFinished = true;
            }
            try {
                sleep(20);
            } catch (InterruptedException e) {
                isFinished = true;
                interruptAllThreads();

            }
        }
    }

    private void interruptAllThreads() {
        gameThread.interrupt();
        ballThread.interrupt();
        gameThread.interrupt();
        paddleOneThread.interrupt();
        paddleTwoThread.interrupt();
        p1ScoreThread.interrupt();
        p2ScoreThread.interrupt();
        exit(0);
    }
}
