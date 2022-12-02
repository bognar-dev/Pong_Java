package src.games.basic.gameObjects;

import src.games.basic.position.Position;

import java.awt.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ScoreGameObject extends AbstractGameObject implements Runnable {

    int scoreLimit,strokeSize,score;
    Color colour;
    public ScoreGameObject(Position pos, Color colour, int scoreLimit, int strokeSize){
        super(pos);
        this.score = 0;
        this.colour = colour;
        this.strokeSize = strokeSize;
        this.scoreLimit = scoreLimit;
    }
    @Override
    public void run() {
        while(currentThread().isAlive()) {
            try {
                sleep(30);
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(colour);
        g2.setStroke(new BasicStroke(strokeSize));
        g2.setFont(new Font("Jokerman",Font.BOLD,strokeSize));
        g2.drawString(this.toString(),this.getPos().getX(), this.getPos().getY());
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    public void increaseScore() {
        score++;
    }
    public void setScore(int score){
        this.score = 0;
    }
    public void resetScore(){
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public int getScoreLimit(){
        return scoreLimit;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    public boolean reachedLimit() {
        return score == scoreLimit;
    }
}
