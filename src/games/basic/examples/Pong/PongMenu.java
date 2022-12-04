package src.games.basic.examples.Pong;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static java.lang.Thread.sleep;

public class PongMenu extends JFrame implements Runnable {
    public static void main(String[] args) {
        PongMenu pM = new PongMenu(new Dimension(1600, 1000));
    }

    private int ballSize = 20;
    private int paddleSize = 100;
    private int ballSpeed = 20;

    private int gameLimit = 10;

    private boolean startPong;
    Pong pong;
    Dimension windowSize;
    Button playButton;
    JPanel buttonPanel, sliderPanel, labelPanel, colourPanel;
    JLabel ballSizeLabel, paddleSizeLabel, ballSpeedLabel,gameLimitLabel;
    Thread mainThread, pongThread;
    JSlider ballSizeSlider, paddleSizeSlider, ballSpeedSlider,gameLimitSlider;


    public PongMenu(Dimension windowSize) {
        this.windowSize = windowSize;
        this.startPong = false;
        setFrameSettings();
        setButtons();
        setSliders();
        //setColourMenu();
        setLabels();
        setThreads();
    }


    /*private void setColourMenu() {
        colourPanel = new JPanel();
        colourPanel.setLayout(new BoxLayout(colourPanel,BoxLayout.X_AXIS));
        colourPanel.setAutoscrolls(true);
        p1ColourChooser = new JColorChooser(Color.RED);
        p1ColourChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                p1Colour = p1ColourChooser.getColor();
            }
        });
        colourPanel.add(p1ColourChooser);
        this.add(colourPanel,BorderLayout.EAST);
    }*/


    private void setLabels() {
        labelPanel = new JPanel();
        ballSizeLabel = new JLabel("Ball size: " + ballSize);
        ballSpeedLabel = new JLabel("Ball speed: " + ballSpeed);
        paddleSizeLabel = new JLabel("Paddle size: " + paddleSize);
        gameLimitLabel = new JLabel("Game limit: "+ gameLimit);
        labelPanel.add(paddleSizeLabel);
        labelPanel.add(ballSizeLabel);
        labelPanel.add(ballSpeedLabel);
        labelPanel.add(gameLimitLabel);
        this.add(labelPanel, BorderLayout.CENTER);
        this.pack();
    }

    private void setThreads() {
        pongThread = new Thread(pong);
        mainThread = new Thread(this);
        mainThread.start();
    }

    private void setButtons() {
        buttonPanel = new JPanel();
        playButton = new Button("Play");
        playButton.addActionListener(
                e -> {
                    pong = new Pong(ballSize, ballSpeed, paddleSize, gameLimit);
                    this.setVisible(false);
                });
        buttonPanel.add(playButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setFrameSettings() {
        this.setName("PongMenu");
        //this.setIconImage();
        this.setBackground(Color.BLACK);
        this.setSize(windowSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setSliders() {
        sliderPanel = new JPanel();
        ballSizeSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, 10);
        ballSizeSlider.setMinorTickSpacing(10);
        ballSizeSlider.setValue(20);
        ballSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));
        ballSizeSlider.setFont(new Font("Serif", Font.ITALIC, 15));
        ballSizeSlider.setPaintTicks(true);
        ballSizeSlider.setPaintLabels(true);
        ballSizeSlider.addChangeListener(e -> ballSize = ballSizeSlider.getValue());
        ballSizeSlider.setVisible(true);

        paddleSizeSlider = new JSlider();
        paddleSizeSlider.setMinimum(5);
        paddleSizeSlider.setMaximum(1000);
        paddleSizeSlider.setValue(100);
        paddleSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));
        paddleSizeSlider.setFont(new Font("Serif", Font.ITALIC, 15));
        paddleSizeSlider.setPaintTicks(true);
        paddleSizeSlider.setPaintLabels(true);
        paddleSizeSlider.addChangeListener(e -> paddleSize = paddleSizeSlider.getValue());
        paddleSizeSlider.setVisible(true);

        ballSpeedSlider = new JSlider();
        ballSpeedSlider.setMinimum(5);
        ballSpeedSlider.setMaximum(30);
        ballSpeedSlider.setValue(20);
        ballSpeedSlider.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));
        ballSpeedSlider.setFont(new Font("Serif", Font.ITALIC, 15));
        ballSpeedSlider.setPaintTicks(true);
        ballSpeedSlider.setPaintLabels(true);
        ballSpeedSlider.addChangeListener(e -> ballSpeed = ballSpeedSlider.getValue());
        ballSpeedSlider.setVisible(true);

        gameLimitSlider = new JSlider();
        gameLimitSlider.setMinimum(5);
        gameLimitSlider.setMaximum(30);
        gameLimitSlider.setValue(10);
        gameLimitSlider.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));
        gameLimitSlider.setFont(new Font("Serif", Font.ITALIC, 15));
        gameLimitSlider.setPaintTicks(true);
        gameLimitSlider.setPaintLabels(true);
        gameLimitSlider.addChangeListener(e -> gameLimit = gameLimitSlider.getValue());
        gameLimitSlider.setVisible(true);


        sliderPanel.add(new JLabel("Paddle size"));
        sliderPanel.add(paddleSizeSlider);
        sliderPanel.add(new JLabel("Ball size"));
        sliderPanel.add(ballSizeSlider);
        sliderPanel.add(new JLabel("Ball speed"));
        sliderPanel.add(ballSpeedSlider);
        sliderPanel.add(new JLabel("Game limit"));
        sliderPanel.add(gameLimitSlider);
        this.add(sliderPanel, BorderLayout.NORTH);


    }

    @Override
    public void run() {
        while (mainThread.isAlive()) {
            ballSizeLabel.setText("Ballsize: " + ballSize);
            paddleSizeLabel.setText("Paddlesize: " + paddleSize);
            ballSpeedLabel.setText("Ball speed: " + ballSpeed);
            gameLimitLabel.setText("Game limit: "+ gameLimit);
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
