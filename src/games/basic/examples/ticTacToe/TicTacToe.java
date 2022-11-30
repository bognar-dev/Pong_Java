package src.games.basic.examples.ticTacToe;

import src.games.basic.gameObjects.CircularGameObject;
import src.games.basic.gameObjects.CrossGameObject;
import src.games.basic.gameObjects.interfaces.GameObject;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class TicTacToe extends JFrame {

    int WIDTH, HEIGHT, MARGIN;
    int aThirdHEIGHT = (int) (HEIGHT * 0.33);
    int twoThirdHEIGHT = (int) (HEIGHT * 0.66);
    int aThirdWIDTH = (int) (WIDTH * 0.33);
    int twoThirdWIDTH = (int) (WIDTH * 0.66);

    TicPanel ticPanel;
    Button newGame, endGame;

    JPanel buttons;
    JFrame main, winnerScreen;

    Info[] occupiedPos;
    boolean crossTurn;
    private JLabel winnerLabel;


    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(600, 600, 30, false);
    }

    TicTacToe(int width, int height, int margin, boolean redStart) {
        this.occupiedPos = new Info[9];
        this.crossTurn = redStart;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MARGIN = margin;
        createBoard();
        setButtons();
        setUpWinnerScreen();
        resetPostions();
        main = new JFrame("TicTacToe");
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setSize(1980, 1020);
        main.add(ticPanel, BorderLayout.CENTER);
        main.add(buttons, BorderLayout.SOUTH);
        main.setVisible(true);
        main.pack();
    }

    private void createBoard() {
        ArrayList<GameObject> objectList = new ArrayList<>();
        //objectList.add(new CrossGameObject(new Position(MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
        /*objectList.add(new CircularGameObject(new Position(MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
        objectList.add(new CrossGameObject(new Position((int) (HEIGHT * 0.33) + MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
        objectList.add(new CrossGameObject(new Position((int) (HEIGHT * 0.66) + MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));*/
        ticPanel = new TicPanel(objectList, 10, HEIGHT, WIDTH);
        ticPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ticPanel.setSize(600,600);
        ticPanel.setFocusable(true);
        addListener();
    }

    private void recalcSize() {
        aThirdHEIGHT = (int) (HEIGHT * 0.33);
        twoThirdHEIGHT = (int) (HEIGHT * 0.66);
        aThirdWIDTH = (int) (WIDTH * 0.33);
        twoThirdWIDTH = (int) (WIDTH * 0.66);
    }

    private void setUpWinnerScreen() {
        winnerScreen = new JFrame("Winner");
        Button continueButton = new Button("Continue");
        continueButton.addActionListener(
                e -> {
                    winnerScreen.setVisible(false);
                    winnerLabel.setVisible(false);
                    winnerScreen.validate();
                    winnerScreen.repaint();
                    //TODO: remove all items from screen like jLabel
                }
        );
        winnerScreen.setSize(400, 400);
        winnerScreen.add(continueButton, BorderLayout.SOUTH);
        winnerScreen.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void addListener() {
        this.addComponentListener(
                new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        HEIGHT = getHeight();
                        WIDTH = getWidth();
                        recalcSize();
                        ticPanel.setWidth(WIDTH);
                        ticPanel.setHeight(HEIGHT);
                        validate();
                        repaint();
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {
                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {
                    }
                }
        );
        ticPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                recalcSize();
                System.out.println(e.getPoint());
                if(e.getX() < aThirdWIDTH  && e.getY() < aThirdHEIGHT){
                    System.out.println("Spot 1");
                    Position pos = new Position(MARGIN, MARGIN);
                    addObject(pos, 0);
                }
                if(e.getX() > aThirdWIDTH && e.getY() < aThirdHEIGHT+MARGIN
                        && e.getX() < twoThirdWIDTH && e.getY() < aThirdHEIGHT){
                    System.out.println("Spot 2");
                    Position pos = new Position(aThirdHEIGHT + MARGIN, MARGIN);
                    addObject(pos, 1);
                }
                if(e.getX() > twoThirdWIDTH && e.getY() < aThirdHEIGHT+MARGIN
                        && e.getX() < WIDTH && e.getY() < aThirdHEIGHT){
                    System.out.println("Spot 3");
                    Position pos = new Position(twoThirdHEIGHT + MARGIN, MARGIN);
                    addObject(pos, 2);
                }
                if(e.getX() < aThirdWIDTH  && e.getY() > aThirdHEIGHT
                        && e.getY() < twoThirdHEIGHT){
                    System.out.println("Spot 4");
                    Position pos = new Position(MARGIN, aThirdWIDTH + MARGIN);
                    addObject(pos, 3);
                }
                if(e.getX() > aThirdWIDTH  && e.getY() > aThirdHEIGHT
                        && e.getY() < twoThirdHEIGHT && e.getX() < twoThirdWIDTH){
                    System.out.println("Spot 5");
                    Position pos = new Position(aThirdHEIGHT + MARGIN, aThirdWIDTH + MARGIN);
                    addObject(pos, 4);
                }
                if(e.getX() > twoThirdWIDTH  && e.getY() > aThirdHEIGHT
                        && e.getY() < twoThirdHEIGHT && e.getX() < WIDTH){
                    System.out.println("Spot 6");
                    Position pos = new Position(twoThirdHEIGHT + MARGIN, aThirdWIDTH + MARGIN);
                    addObject(pos, 5);
                }
                if(e.getX() < aThirdWIDTH  && e.getY() > twoThirdWIDTH
                        && e.getY() < HEIGHT){
                    System.out.println("Spot 7");
                    Position pos = new Position(MARGIN, twoThirdWIDTH + MARGIN);
                    addObject(pos, 6);
                }
                if(e.getX() < twoThirdWIDTH  && e.getY() > twoThirdWIDTH
                        && e.getY() < HEIGHT && e.getX() > aThirdWIDTH){
                    System.out.println("Spot 8");
                    Position pos = new Position(aThirdHEIGHT + MARGIN, twoThirdWIDTH + MARGIN);
                    addObject(pos, 7);
                }
                if(e.getX() < WIDTH  && e.getY() > twoThirdWIDTH
                        && e.getY() < HEIGHT && e.getX() > twoThirdWIDTH){
                    System.out.println("Spot 9");
                    Position pos = new Position(twoThirdHEIGHT + MARGIN, twoThirdWIDTH + MARGIN);
                    addObject(pos, 8);
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        ticPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                switch (input) {
                    case 'e' -> exit(0);
                    case 'n' -> {
                        crossTurn = true;
                        ticPanel.removeAllItems();
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '1' -> {
                        Position pos = new Position(MARGIN, MARGIN);
                        addObject(pos, 0);
                    }
                    case '2' -> {
                        Position pos = new Position(aThirdHEIGHT + MARGIN, MARGIN);
                        addObject(pos, 1);
                    }
                    case '3' -> {
                        Position pos = new Position(twoThirdHEIGHT + MARGIN, MARGIN);
                        addObject(pos, 2);
                    }
                    case '4' -> {
                        Position pos = new Position(MARGIN, aThirdWIDTH + MARGIN);
                        addObject(pos, 3);
                    }
                    case '5' -> {
                        Position pos = new Position(aThirdHEIGHT + MARGIN, aThirdWIDTH + MARGIN);
                        addObject(pos, 4);
                    }
                    case '6' -> {
                        Position pos = new Position(twoThirdHEIGHT + MARGIN, aThirdWIDTH + MARGIN);
                        addObject(pos, 5);
                    }
                    case '7' -> {
                        Position pos = new Position(MARGIN, twoThirdWIDTH + MARGIN);
                        addObject(pos, 6);
                    }
                    case '8' -> {
                        Position pos = new Position(aThirdHEIGHT + MARGIN, twoThirdWIDTH + MARGIN);
                        addObject(pos, 7);
                    }
                    case '9' -> {
                        Position pos = new Position(twoThirdHEIGHT + MARGIN, twoThirdWIDTH + MARGIN);
                        addObject(pos, 8);
                    }
                }

            }


            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void addObject(Position pos, int index) {
        if (!ticPanel.hasPosition(pos)) {
            if (crossTurn) {
                occupiedPos[index].setOccupied(true);
                occupiedPos[index].setInfo(Ownership.CROSS);
                ticPanel.add(new CrossGameObject(pos, (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
            } else {
                occupiedPos[index].setOccupied(true);
                occupiedPos[index].setInfo(Ownership.CIRCLE);
                ticPanel.add(new CircularGameObject(pos, (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
            }
            ticPanel.validate();
            ticPanel.repaint();
            checkForWin();
            this.crossTurn = !crossTurn;
        } else {
            System.out.println("try again");
        }
    }

    public void checkForWin() {
        if (occupiedPos[0].getSpotInfo() == occupiedPos[1].getSpotInfo() && occupiedPos[0].getSpotInfo() == occupiedPos[2].getSpotInfo() && occupiedPos[0].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[3].getSpotInfo() == occupiedPos[4].getSpotInfo() && occupiedPos[3].getSpotInfo() == occupiedPos[5].getSpotInfo() && occupiedPos[3].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[6].getSpotInfo() == occupiedPos[7].getSpotInfo() && occupiedPos[6].getSpotInfo() == occupiedPos[8].getSpotInfo() && occupiedPos[6].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[0].getSpotInfo() == occupiedPos[3].getSpotInfo() && occupiedPos[0].getSpotInfo() == occupiedPos[6].getSpotInfo() && occupiedPos[0].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[1].getSpotInfo() == occupiedPos[4].getSpotInfo() && occupiedPos[1].getSpotInfo() == occupiedPos[7].getSpotInfo() && occupiedPos[1].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[2].getSpotInfo() == occupiedPos[5].getSpotInfo() && occupiedPos[2].getSpotInfo() == occupiedPos[8].getSpotInfo() && occupiedPos[2].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[0].getSpotInfo() == occupiedPos[4].getSpotInfo() && occupiedPos[0].getSpotInfo() == occupiedPos[8].getSpotInfo() && occupiedPos[0].getSpotInfo() != Ownership.UNDEFINED ||
                occupiedPos[2].getSpotInfo() == occupiedPos[4].getSpotInfo() && occupiedPos[2].getSpotInfo() == occupiedPos[6].getSpotInfo() && occupiedPos[2].getSpotInfo() != Ownership.UNDEFINED) {


            winScreen();
        }
    }

    private void winScreen() {
        if (crossTurn) {
            showWinscreen("The winner is Cross");
        } else {
            showWinscreen("The winner is Circle");
        }
        winnerScreen.setVisible(true);

        System.out.println("Press N for new Game");
        resetPostions();
    }

    private void showWinscreen(String text) {
        winnerLabel = new JLabel(text, SwingConstants.CENTER);
        winnerLabel.setSize(400, 400);
        winnerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        ;
        winnerScreen.add(winnerLabel);
        System.out.println(text);
    }

    private void resetPostions() {
        for (int i = 0; i < occupiedPos.length; i++) {
            occupiedPos[i] = new Info();
        }
    }

    private void setButtons() {
        buttons = new JPanel();
        newGame = new Button("New Game");
        endGame = new Button("End Game");
        newGame.addActionListener(
                event -> {
                    ticPanel.removeAllItems();
                    ticPanel.validate();
                    ticPanel.repaint();
                }
        );
        endGame.addActionListener(
                e -> exit(0)
        );
        buttons.add(newGame);
        buttons.add(endGame);
    }


}
