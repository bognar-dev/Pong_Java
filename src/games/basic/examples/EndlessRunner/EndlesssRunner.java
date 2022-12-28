package src.games.basic.examples.EndlessRunner;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class EndlesssRunner extends JFrame implements Runnable {
    public static void main(String[] args) {
        EndlesssRunner er = new EndlesssRunner();
    }

    EndlessRunnerPanel endlessRunnerPanel;

    Thread mainThread,endlessRunnerThread;

    EndlesssRunner() {
        setRunnerPanel();
        this.setTitle("EndlessRunner");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.add(endlessRunnerPanel, BorderLayout.CENTER);
        this.pack();
        startThreads();
    }

    private void startThreads() {
        mainThread = new Thread(this);
        endlessRunnerThread = new Thread(endlessRunnerPanel);
        endlessRunnerThread.start();
        mainThread.start();
    }

    private void setRunnerPanel() {
        endlessRunnerPanel = new EndlessRunnerPanel();
    }

    @Override
    public void run() {
        while (mainThread.isAlive()) {
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
