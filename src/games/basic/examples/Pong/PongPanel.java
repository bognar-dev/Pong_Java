package src.games.basic.examples.Pong;

import src.games.basic.gameObjects.moveable.Ball;
import src.games.basic.gameObjects.moveable.Paddle;
import src.games.basic.position.Position;
import src.games.basic.position.interfaces.Positionable;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class PongPanel extends JPanel implements Runnable{

    Dimension SCREEN_SIZE = new Dimension(1000,600);
    Thread gameThread;
    Ball ball;
    Paddle p1,p2;
    Position ballStartPos = new Position(SCREEN_SIZE.width/2 -50,SCREEN_SIZE.height/2 - 50);

    int height,width;

    PongPanel(){
        newBall(ballStartPos, new Position(-5,20));
        newPaddles();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newBall(Positionable pos, Positionable speed) {
        ball = new Ball(pos, speed,50, Color.ORANGE,6);
    }

    private void newPaddles() {
        p1 = new Paddle(new Position(50,30),15,50,Color.PINK,8);
        p2 = new Paddle(new Position(SCREEN_SIZE.width - 50,30),15,50,Color.WHITE,8);
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
            this.ball.move();
            if(isTouchingTopOrBottom()){
                this.ball.reverseYDirection();
            }
            if(this.ball.getPos().getX() <= 0){
                /*p1.increaseScore();
                ball.setPos(ballStartPos);
                System.out.println(p1.getScore());*/
            }
            sleep(30);
            this.revalidate();
            this.repaint();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    }

    private boolean isTouchingTopOrBottom() {
        return this.ball.getPos().getY() <= 0 || this.ball.getPos().getY() + this.ball.getHeight() >= SCREEN_SIZE.height;
    }
}
