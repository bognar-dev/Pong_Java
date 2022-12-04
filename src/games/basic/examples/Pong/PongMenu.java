package src.games.basic.examples.Pong;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Thread.sleep;

public class PongMenu extends JFrame implements Runnable {

    private String[] colourArr = {"Dark green", "Pink", "White", "Black", "Gray","Blue","Cyan","Yellow","Magenta","Orange","Red"};

    public static void main(String[] args) {
        PongMenu pM = new PongMenu(new Dimension(1600, 1000));
    }


    private int ballSize = 20;
    private int paddleSize = 100;
    private int ballSpeed = 20;

    private int gameLimit = 10;

    private Color DARKGREEN = new Color(0, 255, 100, 77);
    private Color BLUE = new Color(0, 166, 255, 77);

    private JList p2ColourList, p1ColourList, backgroundColourList, ballColourList;
    private Color p1Colour, p2Colour, ballColour, backgroundColour;
    Pong pong;

    boolean startPong;
    Dimension windowSize;
    Button playButton;
    JPanel buttonPanel, sliderPanel, labelPanel, colourPanel;
    JLabel ballSizeLabel, paddleSizeLabel, ballSpeedLabel, gameLimitLabel;
    Thread mainThread, pongThread;

    JSlider ballSizeSlider, paddleSizeSlider, ballSpeedSlider, gameLimitSlider;


    public PongMenu(Dimension windowSize) {
        this.windowSize = windowSize;
        this.startPong = false;
        setFrameSettings();
        setButtons();
        setSliders();
        //setColourMenu();
        setJLists();
        setLabels();
        setThreads();
    }


    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }

    private void setJLists() {
        colourPanel = new JPanel();
        colourPanel.setLayout(new GridLayout(2,2));
        colourPanel.setPreferredSize(new Dimension(400, 600));
        p1ColourList = new JList(colourArr);
        p2ColourList = new JList(colourArr);
        //p1ColourList.setLayout(new FlowLayout());
        backgroundColourList = new JList(colourArr);
        ballColourList = new JList(colourArr);
        colourPanel.add(new JLabel("Player 1 colour: "));
        colourPanel.add(p1ColourList);
        colourPanel.add(new JLabel("Player 2 colour: "));
        colourPanel.add(p2ColourList);
        colourPanel.add(new JLabel("Ball colour: "));
        colourPanel.add(ballColourList);
        colourPanel.add(new JLabel("Background colour: "));
        colourPanel.add(backgroundColourList);
        this.add(colourPanel, BorderLayout.EAST);
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
        gameLimitLabel = new JLabel("Game limit: " + gameLimit);
        labelPanel.setLayout(new GridLayout(4,1));
        //labelPanel.setPreferredSize(new Dimension(200, 600));

        labelPanel.add(paddleSizeLabel);

        labelPanel.add(ballSizeLabel);

        labelPanel.add(ballSpeedLabel);

        labelPanel.add(gameLimitLabel);
        changeFont(labelPanel,new Font("Wide Latin",0,30));

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
                    pong = new Pong(ballSize, ballSpeed, paddleSize, gameLimit, p1Colour, p2Colour, ballColour, backgroundColour);
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
            gameLimitLabel.setText("Game limit: " + gameLimit);
            getThemeColours();
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getThemeColours() {
        p1Colour = getColor((String) p1ColourList.getSelectedValue());
        p2Colour = getColor((String) p2ColourList.getSelectedValue());
        ballColour = getColor((String) ballColourList.getSelectedValue());
        backgroundColour = getColor((String) backgroundColourList.getSelectedValue());
    }

    Color getColor(String col) {
        if (col == null) {
            return Color.BLACK;
        }
        Color color;
        switch (col.toLowerCase()) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = BLUE;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "darkgray":
                color = Color.DARK_GRAY;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "dark green":
                color = DARKGREEN;
                break;

            case "yellow":
                color = Color.YELLOW;
                break;
            case "lightgray":
                color = Color.LIGHT_GRAY;
                break;
            case "magenta":
                color = Color.MAGENTA;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            case "red":
                color = new Color(136, 2, 2);
                break;
            case "white":
                color = Color.WHITE;
                break;
            default:
                color = Color.BLACK;
        }
        return color;
    }
}
