package src.games.basic.examples.EndlessRunner;

import src.games.basic.gameObjects.moveable.MoveableRectangle;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Thread.sleep;

public class EndlessRunnerPanel extends JPanel implements Runnable {

    MoveableRectangle player;
    Position playerStartPos;

    Thread gameThread, playerThread;

    EndlessRunnerPanel() {
        this.playerStartPos = new Position(50, 500);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setBackground(new Color(250, 206, 206, 255));
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 600));
        setUpPlayer();
        addListener();
        startThreads();
    }

    private void startThreads() {
        this.gameThread = new Thread(this);
        playerThread = new Thread(player);
        gameThread.start();
        playerThread.start();

    }

    private void addListener() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int input = e.getKeyCode();
                switch (input) {
                    case (KeyEvent.VK_SPACE):
                            System.out.println("W");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void setUpPlayer() {
        player = new MoveableRectangle(playerStartPos, new Position(0, 0), 50, 50, new Color(0, 0, 0), true, 6);
    }

    @Override
    public void run() {
        while (gameThread.isAlive()) {
            //System.out.println(player.getPos().getX()+":"+player.getPos().getY());
            if (player.getPos().getY() <= 200 || player.getPos().getY() >= 600)
                player.reverseYDirection();
            player.move();
            this.validate();
            this.repaint();
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        player.paintComponent(g2);
    }
}
