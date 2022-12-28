package src.games.basic.examples.Pong;

import src.games.basic.SoundEffect;
import src.games.basic.StopWatch;
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
import static java.lang.Thread.sleep;

public class PongPanelSinglePlayer extends PongPanel implements Runnable {
    Dimension SCREEN_SIZE = new Dimension(1000, 600);
    Ball ball;
    ScoreGameObject p1Score, p2Score;
    Thread ballThread, gameThread, paddleOneThread, paddleTwoThread, p1ScoreThread, p2ScoreThread;
    Paddle p1, p2;

    int ballSpeed;
    private boolean isFinished, sound;
    Color p1Colour, p2Colour, ballColour, backgroundColour;
    StopWatch stopWatch;
    private Thread pongThread,pingThread;
    private SoundEffect pongSound, pingSound;
    private Thread stopWatchThread;

    PongPanelSinglePlayer(int ballSize, int ballSpeed, int paddleSize, int gameLimit, Color p1Colour, Color p2Colour, Color ballColour, Color backgroundColour, boolean sound) {
        super(ballSize,ballSpeed,paddleSize,gameLimit,p1Colour,p2Colour,ballColour,backgroundColour,sound);

    }

    private void setSounds() {
        // Open an audio input stream.
        pongSound = new SoundEffect("./sounds/pongSounds/converted_ping.wav");
        pingSound = new SoundEffect("./sounds/pongSounds/converted_pong.wav");
    }


    private void startThreads() {
        gameThread = new Thread(this);
        ballThread = new Thread(ball);
        paddleOneThread = new Thread(p1);
        paddleTwoThread = new Thread(p2);
        p1ScoreThread = new Thread(p1Score);
        p2ScoreThread = new Thread(p2Score);
        pingThread = new Thread(pingSound);
        pongThread = new Thread(pongSound);
        stopWatchThread = new Thread(stopWatch);
        stopWatchThread.start();
        pingThread.start();
        pongThread.start();
        ballThread.start();
        gameThread.start();
        paddleOneThread.start();
        paddleTwoThread.start();
        p1ScoreThread.start();
        p2ScoreThread.start();
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
