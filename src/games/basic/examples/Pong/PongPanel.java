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

public class PongPanel extends JPanel implements Runnable{

    private final Thread p2Thread;
    private final Thread p1Thread;
    Dimension SCREEN_SIZE = new Dimension(1000,600);
    Thread gameThread;
    Ball ball;
    Paddle p1,p2;
    static Position ballStartPos;

    int height,width;
    private double scoreLimit;

    PongPanel(){
        newBall(new Position(500,300), new Position(-20,20));
        newPaddles();
        addHandler();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        scoreLimit = 10;
        gameThread = new Thread(this);
        p1Thread = new Thread(p1);
        p2Thread = new Thread(p2);
        gameThread.start();
        p1Thread.start();
        p2Thread.start();
    }

    private void addHandler() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                System.out.println(input);
                switch (input) {
                    case 'e' -> exit(0);
                    case 'n' -> {;
                        if(gameThread.isInterrupted()){
                        }
                            restartBall();
                            p1.resetScore();
                            p2.resetScore();
                    }


                }

            }


            @Override
            public void keyPressed(KeyEvent e) {
                char input = e.getKeyChar();
                System.out.println(input);
                switch (input) {
                    case 'w' -> {
                        p1.setDeltaPos(new Position(0,-10));
                        p1.move();
                    }
                    case 's' -> {
                        p1.setDeltaPos(new Position(0,10));
                        p1.move();
                    }
                    case 'u' -> {
                        p2.setDeltaPos(new Position(0,-10));
                        p2.move();

                    }
                    case 'j' -> {
                        p2.setDeltaPos(new Position(0,10));
                        p2.move();

                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void newBall(Positionable pos, Positionable speed) {
        ball = new Ball(pos, speed,50, Color.BLACK,6);
    }

    private void newPaddles() {
        p1 = new Paddle(new Position(50,30),5,300,Color.BLACK,8);
        p2 = new Paddle(new Position(SCREEN_SIZE.width - 50,30),5,300,Color.BLACK,8);
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

    @Override
    public void run() {
    while(true){
        try {
            ballHandle();
            if(p1.getScore() == scoreLimit) {
                p1.resetScore();
                p2.resetScore();
                gameThread.interrupt();
            }
            if(p1.getPos().getY()<= 0 || p1.getPos().getY() >= SCREEN_SIZE.height){
                p1.reverseYDirection();
            }
            if(p2.getPos().getY()<= 0 || p2.getPos().getY() >= SCREEN_SIZE.height){
                p2.reverseYDirection();
            }
            this.revalidate();
            this.repaint();
            if(!gameThread.isInterrupted()){
                sleep(30);
                this.ball.move();
                /*this.p2.move();
                this.p1.move();*/
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    }

    private void ballHandle() {
        if(isTouchingTopOrBottom()){
            this.ball.reverseYDirection();
        }
        if(ball.isRightOf(p2)){
            p1.increaseScore();
            restartBall();
            System.out.println("Player 1: "+p1.getScore()+ " Player 2: "+ p2.getScore());
        }
        if(ball.isLeftOf(p1)){
            p2.increaseScore();
            restartBall();
            System.out.println("Player 1: "+p1.getScore()+ " Player 2: "+ p2.getScore());
        }
        if(ball.touches(p1)||ball.touches(p2)){
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
        ball.setPos(new Position(500,300));
        ball.setDeltaPos(new Position(getRandomSpeed(), getRandomSpeed()));
    }

    private int getRandomSpeed(){
        Random b = new Random();
        int speed = b.nextInt(-15,15);
        while(speed < 5 && speed > -5){
            speed = b.nextInt();
        }
        if(b.nextBoolean()){
            return speed;
        }else{
            return -speed;
        }
    }


    private boolean isTouchingTopOrBottom() {
        return this.ball.getPos().getY() <= 0 || this.ball.getPos().getY() + this.ball.getHeight() >= SCREEN_SIZE.height;
    }
}
