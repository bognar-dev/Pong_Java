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

    ArrayList<Position> occupiedPos;
    boolean redturn;


    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(1000, 1000, 30,true);
    }

    TicTacToe(int width, int height, int margin, boolean redStart) {
        this.redturn = redStart;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MARGIN = margin;
        createBoard();
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1980, 1020);
        frame.add(ticPanel, BorderLayout.CENTER);
        frame.add(setButtons(), BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();
    }

    private void createBoard() {
        ArrayList<GameObject> objectList = new ArrayList<>();
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
                if(redturn){
                switch (input) {
                    case 'e' -> exit(0);
                    case 'n' -> {
                        ticPanel.removeAllItems();
                        occupiedPos.removeAll(occupiedPos);
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '1' ->{
                        ticPanel.add(new CrossGameObject(new Position(MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '2' -> {
                        ticPanel.add(new CrossGameObject(new Position((int) (HEIGHT * 0.33) +MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '3' -> {
                        ticPanel.add(new CrossGameObject(new Position((int) (HEIGHT * 0.66) +MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '4' -> {
                        ticPanel.add(new CrossGameObject(new Position(MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '5' -> {
                        ticPanel.add(new CrossGameObject(new Position((int) (HEIGHT * 0.33) +MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '6' -> {
                        ticPanel.add(new CrossGameObject(new Position((int) (HEIGHT * 0.66) +MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '7' -> {
                        ticPanel.add(new CrossGameObject(new Position(MARGIN,  (int) (WIDTH * 0.66) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '8' ->  {
                        ticPanel.add(new CrossGameObject(new Position((int) (HEIGHT * 0.33) +MARGIN,  (int) (WIDTH * 0.66) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                    case '9' -> {
                        ticPanel.add(new CrossGameObject(new Position((int) (HEIGHT * 0.66) +MARGIN,  (int) (WIDTH * 0.66) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, (int) (HEIGHT * 0.30) - MARGIN, Color.RED, true, 6));
                        ticPanel.validate();
                        ticPanel.repaint();
                    }
                }
                }else{
                    switch (input) {
                        case 'e' -> exit(0);
                        case 'n' -> {
                            ticPanel.removeAllItems();
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '1' ->{
                            ticPanel.add(new CircularGameObject(new Position(MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '2' -> {
                            ticPanel.add(new CircularGameObject(new Position((int) (HEIGHT * 0.33) +MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '3' -> {
                            ticPanel.add(new CircularGameObject(new Position((int) (HEIGHT * 0.66) +MARGIN, MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '4' -> {
                            ticPanel.add(new CircularGameObject( new Position(MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '5' -> {
                            ticPanel.add(new CircularGameObject(new Position((int) (HEIGHT * 0.33) +MARGIN, (int) (WIDTH * 0.33) + MARGIN) , (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '6' -> {
                            ticPanel.add(new CircularGameObject(new Position((int) (HEIGHT * 0.66) +MARGIN, (int) (WIDTH * 0.33) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '7' -> {
                            ticPanel.add(new CircularGameObject(new Position(MARGIN,  (int) (WIDTH * 0.66) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '8' ->  {
                            ticPanel.add(new CircularGameObject(new Position((int) (HEIGHT * 0.33) +MARGIN,  (int) (WIDTH * 0.66) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
                        case '9' -> {
                            ticPanel.add(new CircularGameObject(new Position((int) (HEIGHT * 0.66) +MARGIN,  (int) (WIDTH * 0.66) + MARGIN), (int) (WIDTH * 0.30) - MARGIN, Color.BLUE, false, 6));
                            ticPanel.validate();
                            ticPanel.repaint();
                        }
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

    private JPanel setButtons() {
        JPanel buttons = new JPanel();
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
                e -> {
                    exit(0);
                }
        );
        buttons.add(newGame);
        buttons.add(endGame);
        return buttons;
    }


}
