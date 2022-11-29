package src.games.basic.examples.ticTacToe;

import src.games.basic.gameObjects.CircularGameObject;
import src.games.basic.gameObjects.CrossGameObject;
import src.games.basic.gameObjects.interfaces.GameObject;
import src.games.basic.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.System.exit;

public class TicTacToe extends JFrame {


    int WIDTH;
    int HEIGHT;

    int MARGIN;

    TicPanel ticPanel;
    Button newGame, endGame;

    JPanel buttons;
    JFrame main;

    Info[] occupiedPos;
    boolean crossTurn;


    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(1000, 1000, 30, true);
    }

    TicTacToe(int width, int height, int margin, boolean redStart) {
        this.occupiedPos = new Info[9];
        for (int i = 0; i < occupiedPos.length; i++) {
            occupiedPos[i] = new Info();
        }
        this.crossTurn = redStart;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MARGIN = margin;
        createBoard();
        setButtons();
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
        ticPanel.setFocusable(true);
        addListener();
    }

    private void addListener() {
        ticPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getPoint());
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
                        ticPanel.removeAllItems();
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '1' -> {
                        Position pos = new Position(MARGIN, MARGIN);
                        addObject(pos, 0);
                    }
                    case '2' -> {
                        Position pos = new Position((int) (HEIGHT * 0.33) + MARGIN, MARGIN);
                        addObject(pos, 1);
                    }
                    case '3' -> {
                        Position pos = new Position((int) (HEIGHT * 0.66) + MARGIN, MARGIN);
                        addObject(pos, 2);
                    }
                    case '4' -> {
                        Position pos = new Position(MARGIN, (int) (WIDTH * 0.33) + MARGIN);
                        addObject(pos, 3);
                    }
                    case '5' -> {
                        Position pos = new Position((int) (HEIGHT * 0.33) + MARGIN, (int) (WIDTH * 0.33) + MARGIN);
                        addObject(pos, 4);
                    }
                    case '6' -> {
                        Position pos = new Position((int) (HEIGHT * 0.66) + MARGIN, (int) (WIDTH * 0.33) + MARGIN);
                        addObject(pos, 5);
                    }
                    case '7' -> {
                        Position pos = new Position(MARGIN, (int) (WIDTH * 0.66) + MARGIN);
                        addObject(pos, 6);
                    }
                    case '8' -> {
                        Position pos = new Position((int) (HEIGHT * 0.33) + MARGIN, (int) (WIDTH * 0.66) + MARGIN);
                        addObject(pos, 7);
                    }
                    case '9' -> {
                        Position pos = new Position((int) (HEIGHT * 0.66) + MARGIN, (int) (WIDTH * 0.66) + MARGIN);
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
        if(crossTurn) {
           /* JFrame winnerPopUp = new JFrame("Winner");
            winnerPopUp.setSize(400,400);
            winnerPopUp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            winnerPopUp.add(new JLabel("The Winner is Cross"));
            winnerPopUp.setVisible(true);*/
            System.out.println("Cross Winner");
        }else{
            System.out.println("Circle Winner");
        }

        System.out.println("Press N for new Game");
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
