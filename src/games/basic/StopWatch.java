package src.games.basic;

import static java.lang.Thread.currentThread;

public class StopWatch implements Runnable {

    public StopWatch(){

    }

    private long elapsedTime;
    private long startTime;

    public long getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (currentThread().isAlive()) {
            elapsedTime = System.currentTimeMillis() - startTime;
        }
    }

    public void reset() {
        startTime = System.currentTimeMillis();
    }
}
